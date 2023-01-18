package com.pooh.main;

import com.pooh.main.departments.DepartmentController;
import com.pooh.main.departments.DepartmentDAO;
import com.pooh.main.employees.EmployeesController;
import com.pooh.main.locations.LocationController;
import com.pooh.main.locations.LocationDAO;
import com.pooh.main.util.DBConnection;

public class JDBCMain {
//230117 7교시 java-DB 연결
	public static void main(String[] args) {
		
		System.out.println("Start");
		DepartmentController dCon = new DepartmentController(); //생성자 호출
		LocationController lCon = new LocationController();
		EmployeesController eCon = new EmployeesController();
		
		try {
			
//			dCon.start();
//			lCon.start();
			eCon.start();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		System.out.println("Finish");
	}
}
