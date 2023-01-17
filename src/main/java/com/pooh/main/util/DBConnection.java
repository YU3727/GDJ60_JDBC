package com.pooh.main.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBConnection {
//230117 7~8교시 java-DB 연결
	public void getConnection() throws Exception {
		//1.id
		String username = "hr";
		
		//2.pw
		String password = "hr";
		
		//3.url정보(ip, port number) //jdbc:oracle:this:ip:port:serviceid
		String url = "jdbc:oracle:thin:@localhost:1521/XEPDB1"; //여기로 접속하세요~의 의미
		
		//4.driver정보
		String driver = "oracle.jdbc.driver.OracleDriver";
		
		Class.forName(driver);
		
		//연결해주는 Socket 같은애 = Connection 인터페이스
		//로그인을 시도하고 성공하면 객체가 온다. 실패하면 Exception 발생
		Connection connection = DriverManager.getConnection(url, username, password);
		
//		System.out.println(connection); //이 주소값이 출력되면 연결이 됐다는 뜻
		
		//이걸 DB와 연결된 connection에게 보내야함
		String sql = "SELECT * FROM LOCATIONS";
		
		//query문을 보내는 인터페이스가 있다.
		PreparedStatement st = connection.prepareStatement(sql);
		//여기까지가 보낼 준비.
		
		//최종 쿼리문 보내기 -> DB에서 실행, 결과물을 rs에 받는것
		ResultSet rs = st.executeQuery();
		
		//DB에서 한줄읽고 데이터 반환 > 한줄읽고 데이터 반환.. 데이터 없을 때 까지 반복
		while(rs.next()) { //resultset을 읽어서 데이터가 있으면 true, 없으면 false
			System.out.print(rs.getInt("LOCATION_ID")+"\t"); //니가 꺼내고싶은 column명을 써라
			System.out.print(rs.getString("STREET_ADDRESS")+"\t"); 
			System.out.print(rs.getString("POSTAL_CODE")+"\t"); 
			System.out.print(rs.getString("CITY")+"\t");
			System.out.print(rs.getString("STATE_PROVINCE")+"\t");
			System.out.println(rs.getString("COUNTRY_ID"));
		}
		
	}
	
}
