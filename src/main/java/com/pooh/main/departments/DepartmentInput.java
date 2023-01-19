package com.pooh.main.departments;

import java.util.Scanner;

public class DepartmentInput {
//230119 4교시 입력을 전문으로 받는 클래스
	
	private Scanner sc;
	
	
	public DepartmentInput() {
		this.sc = new Scanner(System.in);
	}
	
	//수정시 입력받는 메서드
	public DepartmentDTO updateData() {
		DepartmentDTO dDTO = new DepartmentDTO();
		
		System.out.println("수정할 부서 번호 입력");
		dDTO.setDepartment_id(sc.nextInt());
		System.out.println("수정할 부서명 입력");
		dDTO.setDepartment_name(sc.next());
		System.out.println("수정할 매니저 번호 입력");
		dDTO.setManager_id(sc.nextInt());
		System.out.println("수정할 지역번호 입력");
		dDTO.setLocation_id(sc.nextInt());
		
		return dDTO;
	}
	
	
	//추가시 입력받는 메서드
	public DepartmentDTO setData() {
		//DAO에서 DTO 타입의 데이터를 원하기 때문에
		DepartmentDTO dDTO = new DepartmentDTO();
		
		System.out.println("부서명 입력");
		dDTO.setDepartment_name(sc.next());
		System.out.println("매니저 번호 입력");
		dDTO.setManager_id(sc.nextInt());
		System.out.println("지역번호 입력");
		dDTO.setLocation_id(sc.nextInt());
		
		//입력 받은걸 여기서 쓸건 아니니까 리턴해서 결과값 돌려주기
		return dDTO;
		
	}
	
	
	//삭제시 입력받는 메서드
	public DepartmentDTO deleteData() {
		DepartmentDTO dDTO = new DepartmentDTO();
		
		System.out.println("삭제할 부서번호 입력");
		dDTO.setDepartment_id(sc.nextInt());
		
		return dDTO;
	}
}
