package com.pooh.main.departments;

import java.util.ArrayList;

import com.pooh.main.employees.EmployeesDTO;

public class DepartmentDTO {
//230118 4교시 - DB와 연결할 떄 DTO가 필요하다.
	
	//DTO 만들때의 규칙
	
	//1. 모든 멤버변수의 접근지정자는 private
	//2. 멤버변수의 데이터타입과 변수명은 Table내 컬럼의 데이터타입과 컬럼명과 일치해야한다.
	//3. 데이터의 입출력을 위해 getter/setter 메서드 생성
	//4. 기본생성자는 꼭 만들어야한다. +생성자는 여러개 있어도 상관없음
	
	//DTO 하나가 부서의 row 한줄의 데이터를 담고있음.
	
	//멤버변수
	private Integer department_id; //데이터 타입 선언할때 숫자는 primitive type 대신 wrapper class를 이용한 reference type으로 선언한다.
	private String department_name;
	private Integer manager_id;
	private Integer location_id;
	
	//has 관계에 따라 '부서는 사원을 가진다'가 성립해서 멤버변수로 선언 가능.
	//부서와 사원은 1:N 관계이기 때문에 하나의 부서에 여러명의 사원을 포함할 수 있어야 하기 때문에
	//그냥 EmployeesDTO로 선언하는게 아니라 LIST로 선언해야하한다.
	//private EmployeesDTO eDTO;
	private ArrayList<EmployeesDTO> eDTOs;
	
	//Default Constructor
	public DepartmentDTO() {}
	

	public ArrayList<EmployeesDTO> geteDTOs() {
		return eDTOs;
	}

	public void seteDTOs(ArrayList<EmployeesDTO> eDTOs) {
		this.eDTOs = eDTOs;
	}

	public Integer getDepartment_id() {
		return department_id;
	}
	public void setDepartment_id(Integer department_id) {
		this.department_id = department_id;
	}
	public String getDepartment_name() {
		return department_name;
	}
	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}
	public Integer getManager_id() {
		return manager_id;
	}
	public void setManager_id(Integer manager_id) {
		this.manager_id = manager_id;
	}
	public Integer getLocation_id() {
		return location_id;
	}
	public void setLocation_id(Integer location_id) {
		this.location_id = location_id;
	}
	
}
