package com.pooh.main.locations;

import java.util.ArrayList;

public class LocationView {
//230118 5교시
	
	//오버로딩을 통해 매개변수가 다른 메서드들을 하나의 메서드로 사용
	
	//데이터 없을때 그냥 메시지를 출력해주기
	public void view(String msg) {
		System.out.println(msg);
	}
	
	//lDTO가 여러개일때 ar
	public void view(ArrayList<LocationDTO> ar) {
		
		for(LocationDTO lDTO : ar) {
			this.view(lDTO);
		}
		
	}
	
	//하나의 지역정보를 보고싶을때
	public void view(LocationDTO lDTO) {
		System.out.println(lDTO.getLocation_id());
		System.out.println(lDTO.getStreet_address());
		System.out.println(lDTO.getPostal_code());
		System.out.println(lDTO.getCity());
		System.out.println(lDTO.getState_province());
		System.out.println(lDTO.getCountry_id());
	}
	
}
