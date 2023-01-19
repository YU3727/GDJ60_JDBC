package com.pooh.main.departments;

import java.util.ArrayList;
import java.util.Scanner;

public class DepartmentController {
//230118 4교시 컨트롤러 만들기
	
	//멤버변수 - 의존성 표시
	private Scanner sc;
	private DepartmentDAO dDAO;
	private DepartmentView dView;
	private DepartmentInput dInput;
	
	
	//생성자에 객체생성 - 의존성 주입(injection)
	public DepartmentController() {
		this.sc = new Scanner(System.in);
		this.dDAO = new DepartmentDAO();
		this.dView = new DepartmentView();
		dInput = new DepartmentInput();
	}
	
	
	//컨트롤러 메서드
	public void start() throws Exception{
		boolean check = true;
		ArrayList<DepartmentDTO> ar = null;
		DepartmentDTO dDTO = null;
		
		//무한반복 돌리기
		while(check) {
			System.out.println("1.부서리스트\t2.부서상세정보\t3.부서추가\t4.부서삭제\t5.부서정보수정\t6.종료");
			int select = sc.nextInt();
			
			switch (select) {
			default :
				System.out.println("1~6번 중에 선택하세요");
				break;
			case 1: //모든부서
				ar = dDAO.getList();
				dView.view(ar);
				break;
			case 2: //한 부서
				System.out.println("부서 번호를 입력하세요");
				select = sc.nextInt();
				dDTO = dDAO.getDetail(select);
				if(dDTO != null) {
					dView.view(dDTO);
				}else {
					dView.view("Data가 없습니다");
				}
				break;
			case 3:
				dDTO = dInput.setData();
				select = dDAO.setData(dDTO); //dDAO로 가서 입력받은 데이터인 dDTO를 넣어주는데 이 결과값이 int
				if(select >0) {
					dView.view("추가 성공");
				}else {
					dView.view("추가 실패");
				}
				break;
			case 4:
				dDTO = dInput.deleteData();
				select = dDAO.deleteData(dDTO);
				String msg = "삭제 실패";
				if(select > 0) {
					msg = "삭제 성공";
				}
				dView.view(msg);
				break;
			case 5:
				dDTO = dInput.updateData();
				select = dDAO.updateData(dDTO);
				if(select > 0) {
					dView.view("수정 성공");
				}else {
					dView.view("수정 실패");
				}
				break;
			case 6:
				System.out.println("프로그램을 종료합니다");
				check = false;
			
			}
			
		}
		
	}
	
}
