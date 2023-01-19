package com.pooh.main.locations;

import java.util.Scanner;

public class LocationInput {
//230119 5교시 DAO에 입력할 값을 넣는 클래스
	
	private Scanner sc;
	
	public LocationInput() {
		sc = new Scanner(System.in);
	}
	
	
	//입력할 떄
	public LocationDTO setData() {
		LocationDTO lDTO = new LocationDTO();
		
		System.out.println("위치 번호를 입력하세요");
		lDTO.setLocation_id(sc.nextInt());
		System.out.println("주소를 입력하세요");
		lDTO.setStreet_address(sc.next());
		System.out.println("우편번호를 입력하세요");
		lDTO.setPostal_code(sc.next());
		System.out.println("도시명을 입력하세요");
		lDTO.setCity(sc.next());
		System.out.println("도단위 지명을 입력하세요");
		lDTO.setState_province(sc.next());
		System.out.println("국가코드를 입력해주세요");
		lDTO.setCountry_id(sc.next());
		
		return lDTO;		
	}
	
	
	//삭제할 때
	public LocationDTO deleteData() {
		LocationDTO lDTO = new LocationDTO();
		
		System.out.println("삭제할 위치번호를 입력하세요");
		lDTO.setLocation_id(sc.nextInt());
		
		return lDTO;
		
	}
	
}
