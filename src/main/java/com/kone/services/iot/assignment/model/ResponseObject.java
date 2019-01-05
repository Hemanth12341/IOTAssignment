package com.kone.services.iot.assignment.model;

import java.io.Serializable;

public class ResponseObject implements Serializable{

	/**
	 * This DTO holds information about response from  various API's
	 */
	private static final long serialVersionUID = 1L;
	private String response;
	/**
	 * @return the response
	 */
	public String getResponse() {
		return response;
	}
	/**
	 * @param response the response to set
	 */
	public void setResponse(String response) {
		this.response = response;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ResponseObject [response=" + response + "]";
	}


	
	
}
