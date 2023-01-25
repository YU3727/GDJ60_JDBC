package com.pooh.main.departments;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.spi.DirStateFactory.Result;

import com.pooh.main.employees.EmployeesDTO;
import com.pooh.main.util.DBConnection;

public class DepartmentDAO {
//230118 1~7교시 java-DB 연결, SELECT
//230119 3교시 JDBC - INSERT / 4교시 DELETE / 5교시 UPDATE
//230125 4교시 join
	
	//230125 4교시 join
	//부서 번호가 30번인 부서에 근무하는 사원들의 이름과 부서명을 출력
	public void getInfos() throws Exception{
		
		Connection connection = DBConnection.getConnection();
		
		String sql = "SELECT E.FIRST_NAME, D.DEPARTMENT_NAME "
				+ "FROM EMPLOYEES E "
				+ "INNER JOIN DEPARTMENT D "
				+ "ON (E.DEPARTMENT_ID = D.DEPARTMENT_ID) "
				+ "WHERE D.DEPARTMENT_ID = 30";
		
		PreparedStatement st = connection.prepareStatement(sql);
		
		ResultSet rs = st.executeQuery();
		DepartmentDTO dDTO = new DepartmentDTO();
		dDTO.seteDTOs(new ArrayList<EmployeesDTO>()); //dDTO내 arraylist는 하나의 공간만 만들면됨
		
		while(rs.next()) {
			//부서정보는 한개, 사원정보는 하나의 부서내에 여러번 들어가는거
			if(dDTO.getDepartment_name() == null) {
				dDTO.setDepartment_name(rs.getString("DEPARTMENT_NAME"));
			}
			EmployeesDTO eDTO = new EmployeesDTO(); //사원이 여러명 만들어져야 하니까 반복마다 만듬
			eDTO.setFirst_name(rs.getString("FIRST_NAME"));
			dDTO.geteDTOs().add(eDTO);
			
		}
		
	}
	
	
	//230125 4교시 join - 지옥인데?
	//여러개의 table이 모여서 하나의 결과를 만들기 때문에 join을 사용하면 table을 묶을 필요가 있음.
	public DepartmentDTO getInfo() throws Exception{
		DepartmentDTO dDTO = null;
		
		Connection connection = DBConnection.getConnection();
		
		String sql = "SELECT E.FIRST_NAME, D.DEPARTMENT_NAME "
				+ "FROM EMPLOYEES E "
				+ "INNER JOIN "
				+ "DEPARTMENTS D "
				+ "ON(E.DEPARTMENT_ID = D.DEPARTMENT_ID) "
				+ "WHERE E.EMPLOYEE_ID = 100";
		
		PreparedStatement st = connection.prepareStatement(sql);
		
		ResultSet rs = st.executeQuery();
		
		//받은 데이터는 employee, department, 리턴타입은 한개라 두 데이터를 하나로 묶어야함
		//list나 Map을 써야한다. > Object로 집어넣고 꺼낼때 형변환을 해줘야함
		if(rs.next()) {
			//값이 있냐고 조회했을때 값이 있으면 dDTO 생성
			dDTO = new DepartmentDTO();
			dDTO.seteDTOs(new ArrayList<EmployeesDTO>());
			dDTO.setDepartment_name(rs.getString("DEPARTMENT_NAME"));
			dDTO.geteDTOs().get(0).setFirst_name(rs.getString("FIRST_NAME"));
//			EmployeesDTO eDTO = new EmployeesDTO();
//			eDTO.setFirst_name(rs.getString("FIRST_NAME"));
//			dDTO.geteDTO().add(eDTO);
		}
		
		return dDTO;
	}
	
	//5교시 - UPDATE
	public int updateData(DepartmentDTO dDTO) throws Exception {
		
		Connection connection = DBConnection.getConnection();
		
		String sql = "UPDATE DEPARTMENTS SET DEPARTMENT_NAME=?, MANAGER_ID=?, LOCATION_ID=? )"
				+ "WHERE DEPARTMENT_ID = ?)";
		
		PreparedStatement st = connection.prepareStatement(sql);
		
		//?의 순서는 Query문에 있는 순서대로다(table상 id는 1번인덱스지만, query문에서는 4번째?기 떄문에 4번임)
		st.setString(1, dDTO.getDepartment_name());
		st.setInt(2, dDTO.getManager_id());
		st.setInt(3, dDTO.getLocation_id());
		st.setInt(4, dDTO.getDepartment_id());
		
		int result = st.executeUpdate();
		
		DBConnection.disconnect(st, connection);
		
		return result;
		
	}
	
	
	//4교시 - DELETE
	public int deleteData(DepartmentDTO dDTO) throws Exception{
		//지울때는 중복되지 않는 데이터(PK) 값으로 지워주는게 좋다.
		
		Connection connection = DBConnection.getConnection();
		
		String sql = "DELETE DEPARTMENTS WHERE DEPARTMENT_ID = ?";
		
		PreparedStatement st = connection.prepareStatement(sql);
		
		//필요값이 하나더라도 DAO에서는 DTO로부터 값을 받아오는 식으로 처리한다.
		st.setInt(1, dDTO.getDepartment_id()); //1번째 물음표에 값을 넣음. 값은 외부에서 매개변수로 받아옴
		
		int result = st.executeUpdate(); //executeQuery가 아니라 Update
		
		DBConnection.disconnect(st, connection);
		
		return result;
	}
	
	
	//230119 3교시 - INSERT
	public int setData(DepartmentDTO dDTO) throws Exception{
		//순서는 똑같다
		
		//1.DB연결
		Connection connection = DBConnection.getConnection();
		
		//2.query문 작성 - insert
		String sql = "INSERT INTO DEPARTMENTS (DEPARTMENT_ID, DEPARTMENT_NAME, MANAGER_ID, LOCATION_ID )\r\n"
				+ "VALUES (DEPARTMENTS_SEQ.NEXTVAL, ?, ?, ?)"; //받아와야하는 것들은 다 ?다
		
		//3.query문 미리 보내기
		PreparedStatement st = connection.prepareStatement(sql);
		
		//4.?에 들어갈 값 세팅 - ?의 순서대로 인덱스번호가 1번부터 매겨짐
		st.setString(1, dDTO.getDepartment_name());
		st.setInt(2, dDTO.getManager_id());
		st.setInt(3, dDTO.getLocation_id()); //지금 데이터가 없고 필요한 값을 받아와야 집어넣을 수 있음. > 매개변수가 필요
		
		//5.최종 실행 (INSERT, UPDATE, DELETE는 결과값으로 숫자를 받는다(0실패, 1이상성공)
		int result = st.executeUpdate();
		
		//6.연결 해제 및 리턴값 설정
		DBConnection.disconnect(st, connection);
		
		return result;
	}

	
	
	//3교시 하나의 값을 출력하기
	public DepartmentDTO getDetail(int department_id) throws Exception{
		DepartmentDTO dDTO = null;
		
		//1, 2. DB접속
		Connection connection = DBConnection.getConnection();
		
		//3.Query문 생성
		//String sql = "SELECT * FROM DEPARTMENTS WHERE DEPARTMENT_ID = "+department_id; //이렇게 넣으면 sql injection 공격에 당하기 쉽다.
		String sql = "SELECT * FROM DEPARTMENTS WHERE DEPARTMENT_ID = ?"; //그래서 값이 바뀌면 ? 를 넣어줌
		
		//4. Query문을 미리 전송함
		PreparedStatement st = connection.prepareStatement(sql); //injection을 방어하기위해 PreparedStatement를 사용한다.
		
		//5. ?에 들어갈 값 세팅
		st.setInt(1, department_id); //(index번호, id) oracle의 시작번호는 1번(0번이 아님....하아)
		
		//6. 최종 전송 및 결과 출력
		ResultSet rs = st.executeQuery();
		
		//꺼내올 출력 column이 없거나 하나뿐이기 때문에 while을 쓸 필요가 없음
		if(rs.next()) {
			dDTO = new DepartmentDTO();
			dDTO.setDepartment_id(rs.getInt("DEPARTMENT_ID"));
			dDTO.setDepartment_name(rs.getString("DEPARTMENT_NAME"));
			dDTO.setManager_id(rs.getInt("MANAGER_ID"));
			dDTO.setLocation_id(rs.getInt("LOCATION_ID"));
		}
		
		//7. 연결해제
		DBConnection.disconnect(rs, st, connection);
		
		return dDTO;
	}
	
	
	
	public ArrayList<DepartmentDTO> getList() throws Exception {
		ArrayList<DepartmentDTO> ar = new ArrayList<DepartmentDTO>();
		
//		//1. 접속 정보 준비
//		String user = "hr";
//		String password = "hr";
//		String url = "jdbc:oracle:thin:@192.168.1.79:1521/XEPDB1";
//		
//		//2. DB 접속
//		Connection connection = DriverManager.getConnection(url, user, password);
//		System.out.println(connection);
		
		//공통부분을 없앤 대신에 DB 연결정보를 받아오자
//		DBConnection dbConnection = new DBConnection(); -> static 클래스로 만들어서 객체생성이 불필요해짐
//		Connection connection = dbConnection.getConnection();
		
		Connection connection = DBConnection.getConnection(); //static 클래스의 메서드 호출. 클래스명.메서드명()
		
		//3. Query문 만들기
		String sql = "SELECT * FROM DEPARTMENTS"; //이런거 할때는 Developer에서 실행해봐야한다. Query문이 틀릴수도 있기 때문
		
		//4. Query문 미리 보내기
		PreparedStatement st = connection.prepareStatement(sql);
		
		//5. ?에 값을 세팅 - (option)
		
		//6. 최종 전송 및 결과 처리
		ResultSet rs = st.executeQuery(); //rs는 connection에 연결되어있고, connection 쿼리를 st에 저장해줌
		
		while(rs.next()) {
			DepartmentDTO dDTO = new DepartmentDTO();
			dDTO.setDepartment_id(rs.getInt("DEPARTMENT_ID"));
			dDTO.setDepartment_name(rs.getString("DEPARTMENT_NAME"));
			dDTO.setManager_id(rs.getInt("MANAGER_ID"));
			dDTO.setLocation_id(rs.getInt("LOCATION_ID"));
			
			//dDTO가 돌면 이전에 입력된 정보가 없어진다. 이 정보들을 모아둘 ArrayList가 필요함 > 위에 선언하고 오자
			//ArrayList에 dDTO 정보를 넣어주자
			ar.add(dDTO);
			
		}
		
		//7. 연결 해제 - 얘도 공통이라 연결관련 클래스인 DBConnection에서 처리하자.
		DBConnection.disconnect(rs, st, connection);
		
		//마지막으로 데이터를 담아둔 ar을 리턴하자 > DB로 부터 꺼내온 데이터가 사라지는게 아니라 ar에 저장되어 리턴된다.
		return ar;
	}
}
