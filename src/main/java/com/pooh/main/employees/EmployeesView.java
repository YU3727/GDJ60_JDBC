package com.pooh.main.employees;

import java.util.ArrayList;

public class EmployeesView {
//230118 7~8교시 종합 예제(오늘 배운거)
	
	//단순 메시지 출력부, 한명출력부, 특정인출력부 만들기
	
	public void view(String msg) {
		System.out.println(msg);
	}
	
	public void view(ArrayList<EmployeesDTO> ar) {
		
		for(EmployeesDTO eDTO : ar) {
			this.view(eDTO);
		}
	}
	
	public void view(EmployeesDTO eDTO) {
		System.out.print(eDTO.getEmployee_id()+"\t");
		System.out.print(eDTO.getFirst_name()+"\t");
		System.out.print(eDTO.getLast_name()+"\t");
		System.out.print(eDTO.getEmail()+"\t");
		System.out.print(eDTO.getPhone_number()+"\t");
		System.out.print(eDTO.getHire_date()+"\t");
		System.out.print(eDTO.getJob_id()+"\t");
		System.out.print(eDTO.getSalary()+"\t");
		System.out.print(eDTO.getCommission_pct()+"\t");
		System.out.print(eDTO.getManager_id()+"\t");
		System.out.println(eDTO.getDepartment_id());
	}
	
	//null이면 출력안하고 나머진 출력하고 이렇게 해야하는데 일단 생각이 안난다;
	
	
	public void view2(ArrayList<EmployeesDTO> ar) {
		
		for(EmployeesDTO eDTO : ar) {
			this.view2(eDTO);
		}
	}
	
	public void view2(EmployeesDTO eDTO) {
		System.out.print(eDTO.getEmployee_id()+"\t");
		System.out.print(eDTO.getFirst_name()+"\t");
		System.out.print(eDTO.getLast_name()+"\t");
		System.out.print(eDTO.getJob_id()+"\t");
		System.out.println(eDTO.getDepartment_id());
	}
}
