package com.pooh.main;

import com.pooh.main.departments.DepartmentDAO;
import com.pooh.main.locations.LocationDAO;
import com.pooh.main.util.DBConnection;

public class JDBCMain {
//230117 7교시 java-DB 연결
	public static void main(String[] args) {
		
		System.out.println("Start");
		
		DepartmentDAO ddao = new DepartmentDAO();
		LocationDAO ldao = new LocationDAO();
		
		try {
//			ddao.getList();
//			ldao.getList();
//			ddao.getDetail(30);
			ldao.getDetail(20);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		System.out.println("Finish");
	}
}
