package com.kone.services.iot.assignment.services;

import java.util.LinkedList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloudant.client.api.Database;
import com.cloudant.client.api.model.FindByIndexOptions;
import com.cloudant.client.api.model.Response;
import com.cloudant.client.api.query.QueryBuilder;
import com.cloudant.client.api.query.QueryResult;
import com.cloudant.client.api.query.Selector;
import com.kone.services.iot.assignment.model.Equipment;

import static com.cloudant.client.api.query.Expression.eq;

/**
 * 
 * Service Interface for adding,fetching specific and 'n' equipment details respectively.
 *
 */
@Service
public class EquipmentServiceImpl implements EquipmentService {

	private static final Logger LOG = LoggerFactory.getLogger(EquipmentServiceImpl.class);

	@Autowired
	Database mydb;
	
	/**
	 * This service method is called to post equipment details in IBM cloudant service
	 * 
	 * @param equipment
	 * @return
	 */

	public String addDetails(Equipment equipment) {
		LOG.info("Entered into add details service implementation with equipment"+equipment);
		String id = null;
		String returnString = null;
		QueryResult<Equipment> equipmentDetails=null;
		String equipmentNumber = equipment.getEquipmentNumber();
		List<Equipment>EquipmentDetails=null;
		try{
			Selector expressionSelector = eq("equipmentNumber", equipmentNumber);
			//Connecting to IBM cloudant service to fetch the details
			equipmentDetails = mydb.query(new QueryBuilder(expressionSelector).
					   build().toString(), Equipment.class);
			LOG.info("Retrieved the response from IBM cloudant service in line of verifying duplicate records");
			EquipmentDetails=equipmentDetails.getDocs();
			for(Equipment individualEquipmentDetails:EquipmentDetails){
				System.out.println(individualEquipmentDetails.getContractEndDate());
				System.out.println(individualEquipmentDetails.getEquipmentNumber());
			}
			if(EquipmentDetails.isEmpty() && EquipmentDetails.size()==0){
				//Connecting to IBM cloudant service to post the details
				Response response = mydb.post(equipment);
				id = response.getId();
				LOG.info("Sucessfully added details to IBM cloudant service");
				returnString = "Successfully added record to IBM cloud with ID " + id;
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		if (!EquipmentDetails.isEmpty() && EquipmentDetails.size()!=0){
			LOG.error("Duplicate data,information with above equipment number already exist");
			returnString = "Data with the above equipment number already exist";
		}
		return returnString;

	}
	
	/**
	 * This service method is called to fetch details pertaining to specific equipment from IBM Cloudant service
	 * 
	 * @param equipmentNumber
	 * @return
	 */

	public Equipment getDetails(String equipmentNumber){
		
		LOG.info("Entered into the service to fetch the equipment details");
			QueryResult<Equipment> equipmentDetailsPresentInDB=null;
			List<Equipment>ListOfEquipmentDetails=null;
			Equipment equipment=new Equipment();
			try{
			Selector expressionSelector = eq("equipmentNumber", equipmentNumber);
			//Connecting to IBM cloudant service to fetch the details
			equipmentDetailsPresentInDB = mydb.query(new QueryBuilder(expressionSelector).
					   build().toString(), Equipment.class);
			LOG.info("Retrieved the information from IBM cloudant service for a specific equipment number");
			ListOfEquipmentDetails=equipmentDetailsPresentInDB.getDocs();
			for(Equipment individualEquipmentDetails:ListOfEquipmentDetails){
				equipment.setEquipmentNumber(individualEquipmentDetails.getEquipmentNumber());
				equipment.setAddress(individualEquipmentDetails.getAddress());
				equipment.setContractStartDate(individualEquipmentDetails.getContractEndDate());
				equipment.setContractEndDate(individualEquipmentDetails.getContractEndDate());
				equipment.setStatus(individualEquipmentDetails.getStatus());
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return equipment;
	}
	
	/**
	 * This service method is called to fetch details of 'n' equipments requested by user from IBM Cloudant service
	 * 
	 * @param noOfRecordsToFetch
	 * @return
	 */
	
	@SuppressWarnings("deprecation")
	public List<Equipment> getEquipmentRecordDetails(String noOfRecordsToFetch){
		LOG.info("Entered into the service to fetch the equipment details of limit specified");
		int count=1;
		int noOfRecordsInterested=Integer.parseInt(noOfRecordsToFetch);
		List<Equipment> equipmentDetailsPresentInDB=null;
			List<Equipment> listOfEquipmentObjects=new LinkedList<Equipment>();
			try{
				//Connecting to IBM cloudant service to fetch the details
				equipmentDetailsPresentInDB = mydb.findByIndex("\"selector\": {}", 
                Equipment.class, 
                new FindByIndexOptions() 
                        .fields("equipmentNumber").fields("address").fields("contractStartDate").fields("contractEndDate").fields("status"));
				LOG.info("Retrieved the respponse from IBM cloudant service");
			for(Equipment individualEquipmentDetails:equipmentDetailsPresentInDB){
				Equipment equipment=new Equipment();
				if(noOfRecordsInterested>=count){
				equipment.setEquipmentNumber(individualEquipmentDetails.getEquipmentNumber());
				equipment.setAddress(individualEquipmentDetails.getAddress());
				equipment.setContractStartDate(individualEquipmentDetails.getContractEndDate());
				equipment.setContractEndDate(individualEquipmentDetails.getContractEndDate());
				equipment.setStatus(individualEquipmentDetails.getStatus());
				listOfEquipmentObjects.add(equipment);
				count++;
				}
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return listOfEquipmentObjects;
	}
}
