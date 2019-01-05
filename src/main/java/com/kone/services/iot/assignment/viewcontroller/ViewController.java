package com.kone.services.iot.assignment.viewcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 
 * View controller to expose index html page
 *
 */
@Controller
public class ViewController {

	/**
	 * 
	 * Method to expose index html page
	 *
	 */
	@GetMapping("/index")
	public String home(){
	 return "index";
	}
}
