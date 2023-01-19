package com.pooh.main;

import com.pooh.main.departments.DepartmentController;
import com.pooh.main.departments.DepartmentDAO;
import com.pooh.main.departments.DepartmentDTO;
import com.pooh.main.employees.EmployeesController;
import com.pooh.main.employees.EmployeesDAO;
import com.pooh.main.locations.LocationController;
import com.pooh.main.locations.LocationDAO;
import com.pooh.main.locations.LocationDTO;
import com.pooh.main.util.DBConnection;

public class JDBCMain {
//230117 7교시 java-DB 연결
	public static void main(String[] args) {
		
		System.out.println("Start");
		DepartmentController dCon = new DepartmentController(); //생성자 호출
		LocationController lCon = new LocationController();
		EmployeesController eCon = new EmployeesController();
		
		//test
		DepartmentDAO dDAO = new DepartmentDAO();
		LocationDAO lDAO = new LocationDAO();
		
		try {
			
//			dCon.start();
			eCon.start();
//			lCon.start();
			
			//test
//			DepartmentDTO dDTO = new DepartmentDTO();
//			dDTO.setDepartment_name("TEST NAME");
//			dDTO.setManager_id(300);
//			dDTO.setLocation_id(1700);
//			int result = dDAO.setData(dDTO);		
//			dDTO.setDepartment_id(310);
//			int result = dDAO.deleteData(dDTO);
			
//			LocationDTO lDTO = new LocationDTO();
//			lDTO.setStreet_address("test name2");
//			lDTO.setPostal_code("0000");
//			lDTO.setCity("Seoul");
//			lDTO.setState_province("seoul");
//			lDTO.setCountry_id("MX");
//			int result = lDAO.setData(lDTO);
			
//			lDTO.setLocation_id(3700);
//			int result = lDAO.deleteData(lDTO);
			
//			if(result > 0) {
//				System.out.println("성공");
//			}else {
//				System.out.println("실패");
//			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		System.out.println("Finish");
	}
}
