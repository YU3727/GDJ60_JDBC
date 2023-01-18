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

		//1번
//		ar = eDAO.getList();
//		eView.view2(ar);
		
		//2번
		System.out.println("사원번호를 입력 하세요");
		int num = sc.nextInt();
		eDTO = eDAO.getDetail(num);
		eView.view(eDTO);
		
		//3번 검색
	}
}
