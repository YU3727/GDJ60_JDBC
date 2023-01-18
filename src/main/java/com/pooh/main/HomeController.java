package com.pooh.main;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pooh.main.departments.DepartmentDAO;
import com.pooh.main.departments.DepartmentDTO;
import com.pooh.main.locations.LocationDAO;
import com.pooh.main.locations.LocationDTO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
//230118 6교시 그냥 해보는거. 나중에 배운다
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping("departments")
	public void getDepartments(Model model) throws Exception{
		
		DepartmentDAO dDAO = new DepartmentDAO();
		ArrayList<DepartmentDTO> ar = dDAO.getList();
		
		model.addAttribute("list", ar);


	}
	
	@RequestMapping("locations")
	public ArrayList<LocationDTO> getLocations() throws Exception{
		
		LocationDAO lDAO = new LocationDAO();
		ArrayList<LocationDTO> ar = lDAO.getList();
		return ar;
	}
	
	
}
