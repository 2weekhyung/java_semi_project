/*
[관리자 코드 인증 클래스]

프로그램이 실행되면 사용자에게 관리자 코드를 입력받아
해당 입력값이 관리자 코드와 일치하는지의 여부를 판단하고

-일치한다면? 관리자 시스템으로 이동 가능
-5회 이상 틀린다면? 프로그램 강제 종료(접근 불가)

*메소드 구성*

#1 관리자 코드 setter

#2 관리자 코드 getter

#3 사용자에게 입력받은 코드값 판별 결과로 프로그램 종료 or 관리자 시스템 이동

#4 사용자에게 입력받은 코드와 관리자 코드 비교하여 결과 반환
*/

import java.util.Scanner;
class Certify
{
	private String admin = "minzzi";	//관리자 코드 private~ 아무도 접근 노노
	
	//#1
	void setAdmin(String admin)
	{
		this.admin = admin;
	}

	//#2
	String getAdmin()
	{
		return admin;
	}

	//#3
	void adCertify()	// 입력받은 코드 판별 결과를 받아서
	{					// 관리자 모드로 이동 or 프로그램 종료 수행
		Scanner sc = new Scanner(System.in);
		System.out.println("\n관리자 코드를 입력하세요.");
		System.out.println("입력 횟수 [5회] 초과시 프로그램이 종료됩니다.");
		
		if (!codeCheck())		// 판별 결과가 false라면
		{
			System.out.println("\n관리자 시스템 접근 불가.\n프로그램이 종료됩니다.");
			Exit.exit();		// 프로그램 종료
		}	
		else					// 판별 결과가 true라면
		{
			System.out.println("\n관리자 시스템을 실행합니다.");
			return;				// 메소드 종료(main에서 관리자 모드 실행 가능)
		}
	}

	//#4
	boolean codeCheck()	// 입력받은 코드 판별 후 true or false 반환
	{
		Scanner sc = new Scanner(System.in);
		String code = "";			// 입력받은 코드
		int num=1;					// 입력횟수 5회 초과시 강제종료
		boolean result = false;		// 코드 판별 결과
		do
		{
			System.out.print("관리자코드 : ");
			code = sc.next();
			if (code.equals(getAdmin()))
				result = true;
			else
			{
				if (num==5)	// 입력횟수가 5회 초과되면
				{
					System.out.println("\n인증코드 입력 횟수를 초과하였습니다.");
					break;	// 반복문 종료 후 false 반환
				}
				else		// 코드는 틀렸지만 5회 초과가 아니라면
				{
					System.out.printf("\n인증코드가 일치하지 않습니다. 입력 횟수 %d번 남았습니다.\n",5-num);
					num++;	// 횟수만 추가하고 다시 입력받기
				}
			}
		}
		while (!result);	// result가 false일 때(틀렸을 때) 반복
		return result;		// 입력 코드가 일치하면 result true로 바꿈
	}						// true로 바뀌면 반복문 탈출~~~
}