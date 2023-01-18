package com.pooh.main.departments;

import java.util.Scanner;

public class DepartmentController {
//230118 4교시 컨트롤러 만들기
	
	//멤버변수 - 의존성 표시
	private Scanner sc;
	private DepartmentDAO dDAO;
	
	
	//생성자에 객체생성 - 의존성 주입(injection)
	public DepartmentController() {
		this.sc = new Scanner(System.in);
		this.dDAO = new DepartmentDAO();
		
	}
	
	
	//컨트롤러 메서드
	public void start() throws Exception{
		boolean check = true;
		
		//무한반복 돌리기
		while(check) {
			System.out.println("1.부서리스트\t2.부서상세정보\t3.종료");
			int select = sc.nextInt();
			
			switch (select) {
			default :
				System.out.println("1~3번 메뉴중 선택하세요");
				break;
			case 1:
				dDAO.getList();
				break;
			case 2:
				System.out.println("부서 번호를 입력하세요");
				select = sc.nextInt();
				dDAO.getDetail(select);
				break;
			case 3:
				System.out.println("프로그램을 종료합니다");
				check = false;
			
			
			}
		}
		
		
	}
	
	
}
