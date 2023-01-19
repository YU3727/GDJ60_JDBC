package com.pooh.main.locations;

import java.util.ArrayList;
import java.util.Scanner;

public class LocationController {
//230118 4교시 컨트롤러 만들기
	
	//멤버변수 생성 - 의존성
	private Scanner sc;
	private LocationDAO lDAO;
	private LocationView lView;
	private LocationInput lInput;
	
	
	//생성자에서 객체생성 - 의존성 주입
	public LocationController() {
		this.sc = new Scanner(System.in);
		this.lDAO = new LocationDAO();
		this.lView = new LocationView();
		lInput = new LocationInput();
	}
	
	
	//컨트롤러 메서드
	public void start() throws Exception{
		boolean check = true;
		ArrayList<LocationDTO> ar;
		LocationDTO lDTO;
		
		//무한반복
		while(check) {
			System.out.println("1.위치리스트\t2.위치상세정보\t3.주소검색(특정문자포함)\t4.위치추가\t5.위치삭제\t6.프로그램종료");
			int select = sc.nextInt();
			
			switch(select) {
			default :
				System.out.println("1~3번 중에서 선택해주세요");
				break;
			case 1:
				ar = lDAO.getList();
				lView.view(ar);
				break;
			case 2:
				System.out.println("검색할 위치 번호를 입력하세요");
				select = sc.nextInt();
				lDTO = lDAO.getDetail(select);
				if(lDTO != null) {
					lView.view(lDTO);
				}else {
					lView.view("Data가 없습니다");
				}
				break;
			case 3:
				System.out.println("검색할 주소를 입력하세요");
				String add = sc.next();
				ar = lDAO.getFind(add);
				lView.view(ar);
				break;
			case 4: //추가
				lDTO = lInput.setData();
				select = lDAO.setData(lDTO);
				if(select > 0) {
					lView.view("입력 성공");
				}else {
					lView.view("입력 실패");
				}
				break;
			case 5: //삭제
				lDTO = lInput.deleteData();
				select = lDAO.deleteData(lDTO);
				if(select > 0) {
					lView.view("삭제 성공");
				}else {
					lView.view("삭제 실패");
				}
				break;
			case 6:
				System.out.println("프로그램을 종료합니다");
				check = false;
			
			} //switch case문 종료
			
		} //while문 종료
		
	}
	
}
