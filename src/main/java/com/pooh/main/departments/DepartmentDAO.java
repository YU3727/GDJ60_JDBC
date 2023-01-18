package com.pooh.main.departments;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.pooh.main.util.DBConnection;

public class DepartmentDAO {
//230118 1~2교시 java-DB 연결 준비
	
	//3교시 하나의 값을 출력하기
	public void getDetail(int department_id) throws Exception{
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
			System.out.print(rs.getInt("DEPARTMENT_ID")+"\t");
			System.out.print(rs.getString("DEPARTMENT_NAME")+"\t");
			System.out.print(rs.getInt("MANAGER_ID")+"\t");
			System.out.println(rs.getInt("LOCATION_ID"));
		}else {
			System.out.println("데이터가 없습니다");
		}
		
		//7. 연결해제
		DBConnection.disconnect(rs, st, connection);
		
	}
	
	
	
	public void getList() throws Exception {
		
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
		
		//5. (option)?에 값을 세팅 - 있으면 하고 없으면 말자
		
		
		//6. 최종 전송 및 결과 처리
		ResultSet rs = st.executeQuery(); //rs는 connection에 연결되어있고, connection 쿼리를 st에 저장해줌
		
		while(rs.next()) {
			System.out.print(rs.getInt("DEPARTMENT_ID")+"\t");
			System.out.print(rs.getString("DEPARTMENT_NAME")+"\t");
			System.out.print(rs.getInt("MANAGER_ID")+"\t");
			System.out.println(rs.getInt("LOCATION_ID"));	
		}
		
		//7. 연결 해제 - 얘도 공통이라 연결관련 클래스인 DBConnection에서 처리하자.
		DBConnection.disconnect(rs, st, connection);
	}
}
