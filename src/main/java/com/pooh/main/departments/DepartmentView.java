package com.pooh.main.departments;

import java.util.ArrayList;

public class DepartmentView {
//230118 5교시
	
	//하나 뷰 만들고, 여러개 뷰 볼수있는거는 하나 뷰 참고해서 만들고
	//오버로딩을 이용해서 한 메서드로 이용 가능하게
	
	
	public void view(String msg) {
		System.out.println(msg);
	}
	
	
	public void view(ArrayList<DepartmentDTO> ar) throws Exception{
		
		for(DepartmentDTO dDTO : ar) {
			this.view(dDTO);
		}
	}
	
	
	
	public void view(DepartmentDTO dDTO) throws Exception{
		System.out.print(dDTO.getDepartment_id()+"\t");
		System.out.print(dDTO.getDepartment_name()+"\t");
		System.out.print(dDTO.getManager_id()+"\t");
		System.out.println(dDTO.getLocation_id());
	}
	
	
}
