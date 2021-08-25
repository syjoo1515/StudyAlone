package Thread_Game1;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
	Scanner s=new Scanner(System.in);
	int n=s.nextInt();
	for (int i = 0; i < n; i++) {
		double x1=s.nextInt();
		double y1=s.nextInt();
		double r1=s.nextInt();
		double x2=s.nextInt();
		double y2=s.nextInt();
		double r2=s.nextInt();
		if(x1==x2&&y1==y2&&r1==r2)
			System.out.println(-1);
		else if(Math.sqrt(Math.pow(x1-x2,2)+Math.pow(y2-y1,2))<Math.abs(r1-r2))
			System.out.println(0);
		else if(Math.sqrt(Math.pow(x1-x2,2)+Math.pow(y2-y1,2))==Math.abs(r1-r2))
			System.out.println(1);
		else if(Math.sqrt(Math.pow(x1-x2,2)+Math.pow(y2-y1,2))>r1+r2) 
			System.out.println(0);
		else if(Math.sqrt(Math.pow(x1-x2,2)+Math.pow(y2-y1,2))==r1+r2)
			System.out.println(1);
		else if(Math.sqrt(Math.pow(x1-x2,2)+Math.pow(y2-y1,2))<r1+r2)
			System.out.println(2);
	}
}
}
