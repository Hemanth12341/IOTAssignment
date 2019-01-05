package com.kone.services.iot.assignment.rest;

import com.kone.services.iot.assignment.model.Equipment;
import com.kone.services.iot.assignment.model.ResponseObject;
import com.kone.services.iot.assignment.services.EquipmentService;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * 
 *  Rest Interface for Equipment service module.
 *
 */
@CrossOrigin(origins="*")
@RestController
@RequestMapping(value = "/equipmentService")
public class EquipmentRestServiceImpl implements EquipmentRestService{

	private static final Logger LOG = LoggerFactory.getLogger(EquipmentRestServiceImpl.class);
	@Autowired
	EquipmentService equipmentService;
	/**
	 * This method is called to post equipment details
	 * 
	 * @param equipment
	 * @return
	 */
	
	@RequestMapping(value="/equipment",method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<?> add(@RequestBody Equipment equipment) {
		LOG.info("Retrieved equipment information from the request with equipment details to post "+equipment);
		LOG.info("Entering into the service implementation to add details of equipment into IBM cloudant service");
		String response=equipmentService.addDetails(equipment);
		LOG.info("Retrieved the response from the service implementation");
		if(response.equalsIgnoreCase("Data with the above equipment number already exist")){
			return new ResponseEntity<String>("Duplicate data,information with above equipment number already exist",HttpStatus.NOT_ACCEPTABLE);
		}
		ResponseObject responseObject=new ResponseObject();
		responseObject.setResponse(response.toString());
		return new ResponseEntity<ResponseObject>(responseObject,HttpStatus.OK); 
	}
	/**
	 * This method is called to fetch details pertaining to specific equipment
	 * 
	 * @param equipmentNumber
	 * @return
	 */
	@RequestMapping(value="/equipment/{equipmentNumber}",method = RequestMethod.GET, consumes = "application/json",produces = "application/json")
	public Equipment getSpecificEquipmentDetails(@PathVariable String equipmentNumber) {
		LOG.info("Retrieved equipment number from the request "+equipmentNumber);
		LOG.info("Entering into the service implementation to fetch the equipment details depending upon equipment number");
		Equipment response=equipmentService.getDetails(equipmentNumber);
		LOG.info("Retrieved the information of specific equipment details");
		return response;
		
	}
	/**
	 * This method is called to fetch details of 'n' equipments requested by user
	 * 
	 * @param noOfRecordsToFetch
	 * @return
	 */
	@RequestMapping(value="/equipment/search/{limit}",method = RequestMethod.GET, consumes = "application/json",produces = "application/json")
	public List<Equipment> getEquipmentRecords(@PathVariable String limit) {
		LOG.info("Retrieved limit of equipment number from the request "+limit);
		LOG.info("Entering into the service implementation to fetch the equipment details depending upon the limit");
		List<Equipment> response=equipmentService.getEquipmentRecordDetails(limit);
		LOG.info("Retrieved the information of equipment details of limit specified");
	
		return response;
		
	}
	
}
