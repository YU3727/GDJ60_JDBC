package com.pooh.main;

import com.pooh.main.util.DBConnection;

public class JDBCMain {
//230117 7교시 java-DB 연결
	public static void main(String[] args) {
		
		System.out.println("Start");
		
		DBConnection con = new DBConnection();
		
		try {
			con.getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		System.out.println("Finish");
	}
}
