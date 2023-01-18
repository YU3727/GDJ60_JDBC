package com.pooh.main.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBConnection {
//230117 7~8교시 java-DB 연결
	public static Connection getConnection() throws Exception {
		//**코드 진행 순서를 정리해놓고, 샘플코드 적어놓고, 보고 코드를 쓰는 공부를 하자.(단, 복사붙여넣기 X)
		
		//1. 접속 정보 준비(ID, PW, URL, Driver(option))
		// 1)id
		String username = "hr";
		
		// 2)pw
		String password = "hr";
		
		// 3)url정보(ip, port number) - jdbc:oracle:this:ip:port/serviceName
		//String url = "jdbc:oracle:this:ip:port/serviceName"; //ServiceName으로 접속할 경우
		//String url = "jdbc:oracle:thin:ip:port:SID";         //SID로 접속할 경우
		String url = "jdbc:oracle:thin:@localhost:1521/XEPDB1"; //여기로 접속하세요~의 의미
		
		//2. DB 접속 실행
		Connection connection = DriverManager.getConnection(url, username, password);
		//연결해주는 Socket 같은애(Connection 인터페이스) - 로그인을 시도하고 성공하면 객체가 온다. 실패하면 Exception 발생
		//Library를 쓰는 목적 - 안에 코드진행까지 알 필요는 없다. 그러나 이 코드를 쓰면 DB와 연결이 된다는 기능은 기억하자.
		
		//**유저 정보가 바뀌면 여기서 수정하면 된다.
		//connection에 DB가 연결된 정보를 가지고 있는데, 얘는 지역변수라 메서드가 끝나면 사라진다. 이 connection 정보를 
		//외부에 알려주기 위해 return값을 connection으로, 리턴타입을 Connection으로 하자
		return connection;
		
		//매번 다른 클래스에 객체를 만드는게 귀찮다... -> 그외지정자 static을 집어넣어서 static 클래스로 만들자.
		
//		===============================================================================================
		
//		// 4)driver정보 - 요샌 안써도 된다고 함.
//		String driver = "oracle.jdbc.driver.OracleDriver"; //package명.OracleDriver클래스명
//		
//		//'driver명을 클래스 이름처럼 쓰겠다'는 의미
//		Class.forName(driver);
		
//		System.out.println(connection); //이 주소값이 출력되면 연결이 됐다는 뜻
	
		
//		//3. Query문 생성
//		//이걸 DB와 연결된 connection에게 보내야함
//		String sql = "SELECT * FROM LOCATIONS";
//		
//		
//		//4. Query문을 미리 전송함(Query문 미완성 상태)
//		//query문을 보내는 인터페이스가 있다.
//		PreparedStatement st = connection.prepareStatement(sql);
//		//여기까지가 보낼 준비.
//		
//		
//		//5. ?에 값을 세팅함(option) - 요샌 안써도 되서 생략
//		
//		
//		//6. 최종 전송 및 결과 처리
//		//최종 쿼리문 보내기 -> DB에서 실행, 결과물을 rs에 받는것
//		ResultSet rs = st.executeQuery();
//		
//		//DB에서 한줄읽고 데이터 반환 > 한줄읽고 데이터 반환.. 데이터 없을 때 까지 반복
//		while(rs.next()) { //resultset을 읽어서 데이터가 있으면 true, 없으면 false
//			System.out.print(rs.getInt("LOCATION_ID")+"\t"); //니가 꺼내고싶은 column명을 써라
//			System.out.print(rs.getString("STREET_ADDRESS")+"\t"); 
//			System.out.print(rs.getString("POSTAL_CODE")+"\t"); 
//			System.out.print(rs.getString("CITY")+"\t");
//			System.out.print(rs.getString("STATE_PROVINCE")+"\t");
//			System.out.println(rs.getString("COUNTRY_ID"));
//		}
//		
//		
//		//7. 연결 해제
	}
	
	
	//연결을 끊어주는 메서드도 공통부분이기 때문에 DBConnection에 만듦,
	public static void disconnect(ResultSet rs, PreparedStatement st, Connection connection) throws Exception{ 
		rs.close();
		st.close();
		connection.close();
	}
	
}
