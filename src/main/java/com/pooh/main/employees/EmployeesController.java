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
	EmployeesInput eInput;
	
	//생성자에서 객체생성 - 의존성 주입
	public EmployeesController() {
		this.sc = new Scanner(System.in);
		this.eDAO = new EmployeesDAO();
		ar = new ArrayList<EmployeesDTO>(); //this 생략가능
		eView = new EmployeesView();
		eDTO = new EmployeesDTO();
		eInput = new EmployeesInput();
	}
	
	
	public void start() throws Exception{
		boolean check = true;
		int select = 0;
		String msg = "";
		
		while(check) {
			System.out.println("0.전체사원정보  1.간단한 전체사원정보  2.개별사원정보  3.사원검색(Last Name)");
			System.out.println("4.사원정보추가  5.사원정보삭제  6.사원정보수정  7.프로그램종료");
			
			select = sc.nextInt();
			
			switch (select) {
			default :
				System.out.println("1~7번 메뉴중에 선택해주세요");
				break;
			case 0:
				ar = eDAO.getList();
				eView.view(ar);
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
			case 4: //사원정보추가
				eDTO = eInput.setData();
				select = eDAO.setData(eDTO);
				if(select > 0) {
					eView.view("입력 성공");
				}else {
					eView.view("입력 실패");
				}
				break;
			case 5: //사원정보삭제
				eDTO = eInput.deleteData();
				select = eDAO.deleteData(eDTO);
				if(select > 0) {
					eView.view("삭제 성공");
				}else {
					eView.view("삭제 실패");
				}
				break;
			case 6: //사원정보수정
				eDTO = eInput.updateData();
				select = eDAO.updateData(eDTO);
				if(select >0 ) {
					eView.view("수정 성공");
				}else {
					eView.view("수정 실패");
				}
				break;
			case 7: //종료
				System.out.println("프로그램을 종료합니다");
				check = false;
			
			} //switch case 끝
			
		} //while 끝	
		
	} //start 끝
	
} //클래스 끝
