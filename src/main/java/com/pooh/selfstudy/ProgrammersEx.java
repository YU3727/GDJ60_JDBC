package com.pooh.selfstudy;

import com.pooh.main.TempCalculator;

public class ProgrammersEx {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		TempCalculator tp = new TempCalculator();
		int[] a = tp.solution(1, 2, 3, 4);
		for(int i=0; i<a.length; i++) {
			System.out.println(a[i]);
		}
		
	}

	public int[] solution(int numer1, int denom1, int numer2, int denom2) {
        int[] answer = {0, 0};
        
        int i1 = 0;
        int i2 = 0;
        if(denom1 == denom2){
            //(numer1 + numer2) / denom1;
            i1 = (numer1 + numer2);
            i2 = denom1;
        }else{
            //((numer1*denom2) + (numer2*denom1)) / (denom1*denom2);
            i1 = ((numer1*denom2) + (numer2*denom1));
            i2 = (denom1*denom2);
        }
        
        while(i2 != 0) {
        	int r = i1 % i2;
        	
        	i1 = i2;
        	i2 = r;
        }
        
        answer[0] = i1;
        answer[1] = i2;
        
        return answer;
		
	}

}
