package com.pooh.main.employees;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import com.pooh.main.util.DBConnection;

public class EmployeesDAO {
//230118 7~8교시 종합 예제(SELECT)
//230119 7~8교시 종합 예제(INSERT, DELETE, UPDATE)
	
	//순서
	//1.DB 연결하기
	//2.Query문 작성
	//3.Query문 미리 보내기
	//4.Query문 내 ?에 넣을 데이터 작성
	//5.최종 실행 및 데이터 처리(저장하든 출력하든)
	//6.연결해제 및 return값 있으면 return 하기
	
	//230119 8교시 Function - 월급의 평균 구하기
	public HashMap<String, Double> getAvg() throws Exception{
		
		HashMap<String, Double> hm = new HashMap<String, Double>();
		
		Connection connection = DBConnection.getConnection();
		
		String sql = "SELECT AVG(SALARY)*12+100 AS A, SUM(SALARY) B FROM EMPLOYEES";
		
		PreparedStatement st = connection.prepareStatement(sql);
		
		ResultSet rs = st.executeQuery();
		
		//데이터 무조건 옴 - next()를 한번은 호출해야 꺼내온다.
		rs.next();
		//근데 받은 데이터는 avg, sum으로 2개인데 return은 한개만 할 수있다. 어떻게 해야할까? 고민해보자.
		//해결방법
		
//		비추 - avg값을 salary에 넣어서 돌려주는방법이 있지만 헷갈릴수 있어서 좋은방법은 아님.(특히 협업할때)
//		EmployeesDTO eDTO = new EmployeesDTO();
//		eDTO.setSalary(rs.getDouble("A"));
//		eDTO.setCommission_pct(rs);
		
//		1. List, Array로 받아서 하나씩 뽑아내기? // 배열도 가능
//		ArrayList<Double> ar = null;
//		ar.add(rs.getDouble(1));
//		ar.add(rs.getInt(2));
		
//		2. DTO(Class) - 다른데서 자주 쓸거면 괜찮지만 단발적으로 쓸거면 고민해봐야함
//		이제껏 DTO는 table과 연동하여 동일하게 만들었지만, 위의 두가지 데이터만 뽑아내고싶을때는
//		avg, sum만 변수로 담는 Class를 따로 선언해서 해당 Class에 넣고 그 타입을 리턴해도 좋다
		
		//3. Map(kay, value)
		hm.put("avg", rs.getDouble("A"));
		hm.put("sum", rs.getDouble(2));
		
		System.out.println(rs.getDouble(1));
		System.out.println(rs.getInt(2));
		
		DBConnection.disconnect(rs, st, connection);
		
		return hm;
	}
	
	
	//사원정보 수정
	public int updateData(EmployeesDTO eDTO) throws Exception{
		
		Connection connection = DBConnection.getConnection();
		
		//외부 프로그램이랑 통신할때의 데이터 타입은 String
		String sql = "UPDATE EMPLOYEES SET FIRST_NAME = ?, LAST_NAME = ?, EMAIL = ?, SALARY = ?, COMMISSION_PCT = ? "
				+ "WHERE EMPLOYEE_ID = ?"; 
		
		PreparedStatement st = connection.prepareStatement(sql);
		
		st.setString(1, eDTO.getFirst_name()); //외부에서 매개변수값 받아서 넣자
		st.setString(2, eDTO.getLast_name());
		st.setString(3, eDTO.getEmail());
		st.setInt(4, eDTO.getSalary());
		Double d = 0.0;
		String a= d.toString();
		st.setDouble(5, eDTO.getCommission_pct()); //null 일때 어떻게 해야할지 생각
		st.setInt(6, eDTO.getEmployee_id());
		
		int select = st.executeUpdate();
		
		DBConnection.disconnect(st, connection);
		
		return select;
		
	}
	
	
	//사원정보 삭제
	public int deleteData(EmployeesDTO eDTO) throws Exception{
		
		Connection connection = DBConnection.getConnection();
		
		String sql = "DELETE EMPLOYEES WHERE EMPLOYEE_ID = ?";
		
		PreparedStatement st = connection.prepareStatement(sql);
		
		st.setInt(1, eDTO.getEmployee_id());
		
		int result = st.executeUpdate();
		
		DBConnection.disconnect(st, connection);
		
		return result;
		
	}
	
	
	//사원정보 추가
	public int setData(EmployeesDTO eDTO) throws Exception{
		
		Connection connection = DBConnection.getConnection();
		
		String sql = "INSERT INTO EMPLOYEES (EMPLOYEE_ID, FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)\r\n"
				+ "VALUES (EMPLOYEES_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		PreparedStatement st = connection.prepareStatement(sql);
		
		st.setString(1, eDTO.getFirst_name());
		st.setString(2, eDTO.getLast_name());
		st.setString(3, eDTO.getEmail());
		st.setString(4, eDTO.getPhone_number());
		st.setString(5, eDTO.getHire_date());
		st.setString(6, eDTO.getJob_id());
		st.setInt(7, eDTO.getSalary());
		st.setDouble(8, eDTO.getCommission_pct());
		st.setInt(9, eDTO.getManager_id());
		st.setInt(10, eDTO.getDepartment_id());
		
		int result = st.executeUpdate();
		
		DBConnection.disconnect(st, connection);
		
		return result;
		
	}
	
	
	//특정 사원 검색(Last Name)해서 정보 보여주기
	public ArrayList<EmployeesDTO> getFind(String search) throws Exception{
		ArrayList<EmployeesDTO> ar = new ArrayList<EmployeesDTO>();
		
		Connection connection = DBConnection.getConnection();
		
		String sql = "SELECT * FROM EMPLOYEES e WHERE LAST_NAME LIKE ?"; //또는'%||a||%'
		
		PreparedStatement st = connection.prepareStatement(sql);
		
		st.setString(1, "%"+search+"%"); //또는 search
		
		ResultSet rs = st.executeQuery();
		
		while(rs.next()) {
			EmployeesDTO eDTO = new EmployeesDTO();
			eDTO.setEmployee_id(rs.getInt("EMPLOYEE_ID"));
			eDTO.setFirst_name(rs.getString("FIRST_NAME"));
			eDTO.setLast_name(rs.getString("LAST_NAME"));
			eDTO.setEmail(rs.getString("EMAIL"));
			eDTO.setPhone_number(rs.getString("PHONE_NUMBER"));
			eDTO.setHire_date(rs.getString("HIRE_DATE"));
			eDTO.setJob_id(rs.getString("JOB_ID"));
			eDTO.setSalary(rs.getInt("SALARY"));
			eDTO.setCommission_pct(rs.getDouble("COMMISSION_PCT"));
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
			eDTO.setHire_date(rs.getString("HIRE_DATE"));
			eDTO.setJob_id(rs.getString("JOB_ID"));
			eDTO.setSalary(rs.getInt("SALARY"));
			eDTO.setCommission_pct(rs.getDouble("COMMISSION_PCT"));
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
			eDTO.setHire_date(rs.getString("HIRE_DATE"));
			eDTO.setJob_id(rs.getString("JOB_ID"));
			eDTO.setSalary(rs.getInt("SALARY"));
			eDTO.setCommission_pct(rs.getDouble("COMMISSION_PCT"));
			eDTO.setManager_id(rs.getInt("MANAGER_ID"));
			eDTO.setDepartment_id(rs.getInt("DEPARTMENT_ID"));
			ar.add(eDTO);		
		}
		
		DBConnection.disconnect(rs, st, connection);
		
		return ar;
	} //getList 종료
	
} //EmployeesDAO 종료
