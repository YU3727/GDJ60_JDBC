package com.pooh.main.locations;

import java.util.Scanner;

public class LocationController {
//230118 4교시 컨트롤러 만들기
	
	//멤버변수 생성 - 의존성
	private Scanner sc;
	private LocationDAO lDAO;
	
	
	//생성자에서 객체생성 - 의존성 주입
	public LocationController() {
		this.sc = new Scanner(System.in);
		this.lDAO = new LocationDAO();
		
	}
	
	
	//컨트롤러 메서드
	public void start() throws Exception{
		boolean check = true;
		
		//무한반복
		while(check) {
			System.out.println("1.위치리스트\t2.위치상세정보\t3.종료");
			int select = sc.nextInt();
			
			switch(select) {
			default :
				System.out.println("1~3번 중에서 선택해주세요");
				break;
			case 1:
				lDAO.getList();
				break;
			case 2:
				System.out.println("검색할 위치 번호를 입력하세요");
				select = sc.nextInt();
				lDAO.getDetail(select);
				break;
			case 3:
				System.out.println("프로그램을 종료합니다");
				check = false;
			
			} //switch case문 종료	
			
		} //while문 종료
		
	}
	
}
