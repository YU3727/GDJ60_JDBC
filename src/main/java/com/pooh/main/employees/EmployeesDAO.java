package com.pooh.main.employees;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.pooh.main.util.DBConnection;

public class EmployeesDAO {
//230118 7~8교시 종합 예제(오늘 배운거)
	
	//순서
	//1.DB 연결하기
	//2.Query문 작성
	//3.Query문 미리 보내기
	//4.Query문 내 ?에 넣을 데이터 작성
	//5.최종 실행 및 데이터 처리(저장하든 출력하든)
	//6.연결해제 및 return값 있으면 return 하기
	
	
	//특정 사원 검색(Last Name)해서 정보 보여주기
	public ArrayList<EmployeesDTO> getFind(String search) throws Exception{
		ArrayList<EmployeesDTO> ar = new ArrayList<EmployeesDTO>();
		
		Connection connection = DBConnection.getConnection();
		
		String sql = "SELECT * FROM EMPLOYEES e WHERE LAST_NAME LIKE ?";
		
		PreparedStatement st = connection.prepareStatement(sql);
		
		st.setString(1, "%"+search+"%");
		
		ResultSet rs = st.executeQuery();
		
		while(rs.next()) {
			EmployeesDTO eDTO = new EmployeesDTO();
			eDTO.setEmployee_id(rs.getInt("EMPLOYEE_ID"));
			eDTO.setFirst_name(rs.getString("FIRST_NAME"));
			eDTO.setLast_name(rs.getString("LAST_NAME"));
			eDTO.setEmail(rs.getString("EMAIL"));
			eDTO.setPhone_number(rs.getString("PHONE_NUMBER"));
			eDTO.setHire_date(rs.getDate("HIRE_DATE"));
			eDTO.setJob_id(rs.getString("JOB_ID"));
			eDTO.setSalary(rs.getInt("SALARY"));
			eDTO.setCommission_pct(rs.getLong("COMMISSION_PCT"));
			eDTO.setManager_id(rs.getInt("MANAGER_ID"));
			eDTO.setDepartment_id(rs.getInt("DEPARTMENT_ID"));
			ar.add(eDTO);
		}
		
		DBConnection.disconnect(rs, st, connection);
		
		return ar;
				
	} //getFind 끝
	
	
	//개별사원의 모든정보 받아오기
	public EmployeesDTO getDetail(int empolyee_id) throws Exception{
		EmployeesDTO eDTO = null; //eDTO는 선언, 초기화만 되고 객체는 없음 / 선언만 함 = null, 선언하고 객체만듬 = not null
		                                                          // data가 null로 들어간것과는 상관없다.
		Connection connection = DBConnection.getConnection();
		
		String sql = "SELECT * FROM EMPLOYEES e WHERE EMPLOYEE_ID = ?";
		
		PreparedStatement st = connection.prepareStatement(sql);
		
		st.setInt(1, empolyee_id);
		
		ResultSet rs = st.executeQuery();
		
		if(rs.next()) { //ResultSet에 데이터를 받아왔을 경우 eDTO 객체를 생성함.(null데이터가 들어가도 null이 아님)
			eDTO = new EmployeesDTO();
			eDTO.setEmployee_id(rs.getInt("EMPLOYEE_ID"));
			eDTO.setFirst_name(rs.getString("FIRST_NAME"));
			eDTO.setLast_name(rs.getString("LAST_NAME"));
			eDTO.setEmail(rs.getString("EMAIL"));
			eDTO.setPhone_number(rs.getString("PHONE_NUMBER"));
			eDTO.setHire_date(rs.getDate("HIRE_DATE"));
			eDTO.setJob_id(rs.getString("JOB_ID"));
			eDTO.setSalary(rs.getInt("SALARY"));
			eDTO.setCommission_pct(rs.getLong("COMMISSION_PCT"));
			eDTO.setManager_id(rs.getInt("MANAGER_ID"));
			eDTO.setDepartment_id(rs.getInt("DEPARTMENT_ID"));
		}
		
		DBConnection.disconnect(rs, st, connection);
		
		return eDTO;
		
	}
	
	
	//전체 list 받아오기 - ar에 데이터 저장 후 리턴
	public ArrayList<EmployeesDTO> getList() throws Exception{
		ArrayList<EmployeesDTO> ar = new ArrayList<EmployeesDTO>();
		
		Connection connection = DBConnection.getConnection();
		
		String sql = "SELECT * \r\n"
				+ "FROM EMPLOYEES e \r\n"
				+ "ORDER BY HIRE_DATE DESC";
		
		PreparedStatement st = connection.prepareStatement(sql);
		
		ResultSet rs = st.executeQuery(); //쿼리 실행, 결과값을 resultset인 rs에 저장
		
		while(rs.next()) {
			EmployeesDTO eDTO = new EmployeesDTO();
			eDTO.setEmployee_id(rs.getInt("EMPLOYEE_ID"));
			eDTO.setFirst_name(rs.getString("FIRST_NAME"));
			eDTO.setLast_name(rs.getString("LAST_NAME"));
			eDTO.setEmail(rs.getString("EMAIL"));
			eDTO.setPhone_number(rs.getString("PHONE_NUMBER"));
			eDTO.setHire_date(rs.getDate("HIRE_DATE"));
			eDTO.setJob_id(rs.getString("JOB_ID"));
			eDTO.setSalary(rs.getInt("SALARY"));
			eDTO.setCommission_pct(rs.getLong("COMMISSION_PCT"));
			eDTO.setManager_id(rs.getInt("MANAGER_ID"));
			eDTO.setDepartment_id(rs.getInt("DEPARTMENT_ID"));
			ar.add(eDTO);		
		}
		
		DBConnection.disconnect(rs, st, connection);
		
		return ar;
	} //getList 종료
	
} //EmployeesDAO 종료
