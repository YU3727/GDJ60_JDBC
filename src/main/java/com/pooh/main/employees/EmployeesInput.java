package com.pooh.main.employees;

import java.util.Scanner;

public class EmployeesInput {
//230119 7~8교시 종합 예제(INSERT, DELETE, UPDATE)
	
	private Scanner sc;
	
	public EmployeesInput() {
		sc = new Scanner(System.in);
	}
	
	//수정시 입력받을 부분 - 이름, 성, 이메일, 샐러리, 커미션pct만
	public EmployeesDTO updateData() throws Exception{
		EmployeesDTO eDTO = new EmployeesDTO();
		
		System.out.println("수정할 사원의 번호를 입력하세요");
		eDTO.setEmployee_id(sc.nextInt());
		System.out.println("수정할 이름(First name)을 입력하세요");
		eDTO.setFirst_name(sc.next());
		System.out.println("수정할 성(Last name)을 입력하세요");
		eDTO.setLast_name(sc.next());
		System.out.println("수정할 e-mail을 입력하세요");
		eDTO.setEmail(sc.next());
		System.out.println("수정할 월급을 입력하세요");
		eDTO.setSalary(sc.nextInt());
		System.out.println("수정할 커미션 비율을 입력하세요");
		eDTO.setCommission_pct(sc.nextDouble());
		
		return eDTO;
	}
	
	
	//삭제시 입력받을 부분 - EMPLOYEE_ID만
	public EmployeesDTO deleteData() throws Exception{
		EmployeesDTO eDTO = new EmployeesDTO();
		
		System.out.println("삭제할 사원의 번호를 입력하세요");
		eDTO.setEmployee_id(sc.nextInt());
		
		return eDTO;
	}
	
	
	//추가시 입력받을 부분 - FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID
	public EmployeesDTO setData() throws Exception{
		EmployeesDTO eDTO = new EmployeesDTO();
		boolean check = true;
		int select = 0;
		
		System.out.println("이름(First name)을 입력하세요");
		eDTO.setFirst_name(sc.next());
		System.out.println("성(Last name)을 입력하세요");
		eDTO.setLast_name(sc.next());
		System.out.println("e-mail을 입력하세요");
		eDTO.setEmail(sc.next());
		System.out.println("전화번호를 입력하세요");
		eDTO.setPhone_number(sc.next());
		System.out.println("날짜를 입력하세요");
		eDTO.setHire_date(sc.next());
		while (check) {
			System.out.println("직군과 직급을 선택하세요");
			System.out.println("1.IT_PROG  2.ST_CLERK  3.SA_REP  4.HR_REP");
			select = sc.nextInt();
			switch(select) {
			default :
				System.out.println("1~4번중에 선택");
				break;
			case 1:
				eDTO.setJob_id("IT_PROG");
				check = false;
				break;
			case 2:
				eDTO.setJob_id("ST_CLERK");
				check = false;
				break;
			case 3:
				eDTO.setJob_id("SA_REP");
				check = false;
				break;
			case 4:
				eDTO.setJob_id("HR_REP");
				check = false;
				break;
			}
		}
		
		System.out.println("월급을 입력하세요");
		eDTO.setSalary(sc.nextInt());
		System.out.println("커미션 비율을 입력하세요");
		eDTO.setCommission_pct(sc.nextDouble());
		System.out.println("매니저의 ID를 입력하세요(100번)");
		eDTO.setManager_id(sc.nextInt());
		System.out.println("부서 번호를 입력하세요(10~100, 10단위)");
		eDTO.setDepartment_id(sc.nextInt());
		
		return eDTO;
	}
	
}
