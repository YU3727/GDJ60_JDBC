package com.pooh.main;

import java.util.Scanner;

import javax.swing.Icon;

import com.pooh.main.departments.DepartmentController;
import com.pooh.main.employees.EmployeesController;
import com.pooh.main.locations.LocationController;

public class FrontController {
//230120 7교시
	//전체관리를 하는 컨트롤러
	
	private Scanner sc;
	private DepartmentController dCon;
	private LocationController lCon;
	private EmployeesController eCon;
	
	public FrontController() {
		this.sc = new Scanner(System.in);
		dCon = new DepartmentController();
		lCon = new LocationController();
		eCon = new EmployeesController();
	}
	
	
	public void start() throws Exception{
		boolean check = true;
		int select = 0;
		
		while(check) {
			System.out.println("관리할 작업을 선택하세요");
			System.out.println("1.부서관리, 2.지역관리, 3.사원관리, 4.국가관리, 5.대륙관리 6.종료");
			select = sc.nextInt();
			
			switch(select) {
			default:
				System.out.println("1~6번 중에 선택해주세요");
				break;
			case 1:
				dCon.start();
				break;
			case 2:
				lCon.start();
				break;
			case 3:
				eCon.start();
				break;
			case 4:
				break;
			case 5:
				break;
			case 6:
				System.out.println("프로그램을 종료합니다");
				check = false;
			}
			
		}
		
	}
	
}
