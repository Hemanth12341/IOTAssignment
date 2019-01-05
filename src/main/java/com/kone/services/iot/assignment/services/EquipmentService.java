package com.kone.services.iot.assignment.services;

import java.util.List;

import com.kone.services.iot.assignment.model.Equipment;

/**
 * 
 * Service Interface for adding,fetching specific and 'n' equipment details respectively.
 *
 */
public interface EquipmentService {

	/**
	 * This service method is called to post equipment details in IBM cloudant service
	 * 
	 * @param equipment
	 * @return
	 */
	public String addDetails(Equipment equipment);
	
	/**
	 * This service method is called to fetch details pertaining to specific equipment from IBM Cloudant service
	 * 
	 * @param equipmentNumber
	 * @return
	 */
	public Equipment getDetails(String equipmentNumber);
	
	/**
	 * This service method is called to fetch details of 'n' equipments requested by user from IBM Cloudant service
	 * 
	 * @param noOfRecordsToFetch
	 * @return
	 */
	
	public List<Equipment> getEquipmentRecordDetails(String noOfRecordsToFetch);
}
