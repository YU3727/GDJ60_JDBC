package com.pooh.main.locations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.pooh.main.util.DBConnection;

public class LocationDAO {
//230118 1~2교시 java-DB 연결 준비
	
	public LocationDTO getDetail(int location_id) throws Exception{
		LocationDTO lDTO = new LocationDTO();
		
		//1, 2 연결준비
		Connection connection = DBConnection.getConnection();
		
		//3. Query문 작성
		String sql = "SELECT * FROM LOCATIONS WHERE LOCATION_ID = ?";
		
		//4. Query문 미리 보내기
		PreparedStatement st = connection.prepareStatement(sql); //connection 인터페이스에 있는 prerpareStatement메서드를 사용
		
		//5. ?에 들어갈 내용 작성
		st.setInt(1, location_id);
		
		//6. 최종 전송 및 출력하기
		ResultSet rs = st.executeQuery();
		
		if(rs.next()) {
			lDTO = new LocationDTO();
			lDTO.setLocation_id(rs.getInt("LOCATION_ID"));
			lDTO.setStreet_address(rs.getString("STREET_ADDRESS"));
			lDTO.setPostal_code(rs.getString("POSTAL_CODE"));
			lDTO.setCity(rs.getString("CITY"));
			lDTO.setState_province(rs.getString("STATE_PROVINCE"));
			lDTO.setCountry_id(rs.getString("COUNTRY_ID"));
			
//			System.out.print(rs.getInt("LOCATION_ID")+"\t");
//			System.out.print(rs.getString("STREET_ADDRESS")+"\t");
//			System.out.print(rs.getString("POSTAL_CODE")+"\t");
//			System.out.print(rs.getString("CITY")+"\t");
//			System.out.print(rs.getString("STATE_PROVINCE")+"\t");
//			System.out.println(rs.getString("COUNTRY_ID"));
		}else {
			System.out.println("데이터가 없습니다(데이터 범위 : 1000~3200, 100단위)");
		}
		
		//7. 연결해제
		DBConnection.disconnect(rs, st, connection);
		
		
		return lDTO;
	}
	
	
	
	
	public ArrayList<LocationDTO> getList() throws Exception {
		ArrayList<LocationDTO> ar = new ArrayList<LocationDTO>();
		//혼자 코드 진행하는 연습(큰 뼈대 맞추고 세부진행)
		
		//DepartmentDAO, LocationDAO에서 공통적인 부분인 연결부분을 주석처리하고 DBConnection에서 공통으로 처리하자
//		//1. 접속 정보 준비 - ID, PW, URL
//		String user = "hr";
//		String password = "hr";
//		String url = "jdbc:oracle:thin:@192.168.1.79:1521/XEPDB1";
//		
//		
//		//2. DB 접속 - Connection interface
//		Connection connection = DriverManager.getConnection(url, user, password);
//		System.out.println(connection);
		
//		DBConnection dbConnection = new DBConnection();
//		Connection connection = dbConnection.getConnection();
		
		Connection connection = DBConnection.getConnection();
		
		//3. Query문 만들기
		String sql = "SELECT * FROM LOCATIONS";
		
		
		//4. Query문 미리 보내기
		PreparedStatement st = connection.prepareStatement(sql);
		
		
		//5. (option)?에 값을 세팅 - 있으면 하고 없으면 말자
		
		
		//6. 최종 전송 및 결과 처리
		ResultSet rs = st.executeQuery();
		
		while(rs.next()) {
			LocationDTO lDTO = new LocationDTO();
			lDTO.setLocation_id(rs.getInt("LOCATION_ID"));
			lDTO.setStreet_address(rs.getString("STREET_ADDRESS"));
			lDTO.setPostal_code(rs.getString("POSTAL_CODE"));
			lDTO.setCity(rs.getString("CITY"));
			lDTO.setState_province(rs.getString("STATE_PROVINCE"));
			lDTO.setCountry_id(rs.getString("COUNTRY_ID"));
			ar.add(lDTO);
		}
		
		
		//7. 연결 해제
		DBConnection.disconnect(rs, st, connection);
		
		return ar;
	}
}
