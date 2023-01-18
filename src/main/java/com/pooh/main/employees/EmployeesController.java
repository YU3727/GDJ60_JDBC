package com.pooh.main.employees;

import java.util.ArrayList;
import java.util.Scanner;

public class EmployeesController {
//230118 7~8교시 종합 예제(오늘 배운거)
	
	//변수 선언 - 의존성
	Scanner sc;
	EmployeesDAO eDAO;
	ArrayList<EmployeesDTO> ar;
	EmployeesView eView;
	EmployeesDTO eDTO;
	
	//생성자에서 객체생성 - 의존성 주입
	public EmployeesController() {
		this.sc = new Scanner(System.in);
		this.eDAO = new EmployeesDAO();
		ar = new ArrayList<EmployeesDTO>(); //this 생략가능
		eView = new EmployeesView();
		eDTO = new EmployeesDTO();
	}
	
	
	public void start() throws Exception{
		boolean check = true;
		int select = 0;
		String msg = "";
		
		while(check) {
			System.out.println("작업을 선택해주세요");
			System.out.println("1.사원정보리스트\t2.개별사원정보\t3.사원검색(Last Name)\t4.프로그램 종료");
			select = sc.nextInt();
			
			switch (select) {
			default :
				System.out.println("1~4번 메뉴중에 선택해주세요");
				break;
			case 1: //사원정보리스트
				ar = eDAO.getList();
				System.out.println("사번\t이름\t성\t직급\t부서번호");
				eView.view2(ar);
				break;
			case 2: //개별사원정보
				System.out.println("검색할 사원의 사원번호를 입력 하세요");
				select = sc.nextInt();
				eDTO = eDAO.getDetail(select);
				if(eDTO != null) {
					eView.view(eDTO);
				}else {
					eView.view("Data가 없습니다");
				}
				break;
			case 3: //사원검색(Last Name)
				System.out.println("검색할 사원의 last name을 입력해주세요(특정 문자 포함 검색 가능)");
				msg = sc.next();
				ar = eDAO.getFind(msg);
				if(ar.size() != 0) { //이게 일단 작동을 안하는데... 내일 강사님꺼를 봐야겠네
					eView.view(ar);
				}else {
					eView.view("Data가 없습니다");
				}
				break;
			case 4:
				System.out.println("프로그램을 종료합니다");
				check = false;
			
			} //switch case 끝
			
		} //while 끝	
		
	} //start 끝
	
} //클래스 끝
