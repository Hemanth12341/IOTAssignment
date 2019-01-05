package com.kone.services.iot.assignment.rest;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.kone.services.iot.assignment.model.Equipment;

/**
 * 
 * Rest Interface for Equipment service module.
 *
 */
public interface EquipmentRestService {
	
	/**
	 * This method is called to post equipment details
	 * 
	 * @param equipment
	 * @return
	 */
	public ResponseEntity<?> add(@RequestBody Equipment equipment);
	/**
	 * This method is called to fetch details pertaining to specific equipment
	 * 
	 * @param equipmentNumber
	 * @return
	 */
	
	public Equipment getSpecificEquipmentDetails(@PathVariable String equipmentNumber);
	/**
	 * This method is called to fetch details of 'n' equipments requested by user
	 * 
	 * @param noOfRecordsToFetch
	 * @return
	 */
	
	public List<Equipment> getEquipmentRecords(@PathVariable String noOfRecordsToFetch);
}
