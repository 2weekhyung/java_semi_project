/*
[과일 및 부자재 관리]

메인에서 ②을 선택하면 해당 시스템 접근

 1) 과일 관리
  1-1) 과일 신규 등록
  1-2) 과일 정보 변경
  1-3) 과일 정보 삭제
  1-4) 뒤로가기

 2) 부자재 관리
  2-1) 설탕 재고 관리
  2-2) 꼬치 재고 관리
  2-3) 뒤로가기

 3) 뒤로가기

*메소드 구성*

#1 1) ~ 3) 기능을 선택하는 메소드

#2 선택한 기능으로 이동시켜주는 메소드
*/
import java.util.Scanner;
import java.io.IOException;

class AdSetting extends AdMain
{
	//#1
	void setSelect() throws IOException
	{
		Scanner sc = new Scanner(System.in);
		int num = 0;			// 선택값 저장할 변수
		boolean temp = false;	// 반복문용 불린 변수

		System.out.println("\n\t[과일 및 부자재 관리]");
		System.out.println("[1] 과일 관리");
		System.out.println("[2] 부자재 관리");
		System.out.println("[3] 뒤로가기");

		do	// 사용자가 제대로(1~3 정수형) 입력할때까지 반복
		{
			System.out.print("\n실행할 기능을 선택하세요. : ");
			try	// 말 안 듣고 이상하게 입력했을때 대비용
			{
				num = sc.nextInt();
				temp = false;	// 여기는 오류가 안 나야 실행
			}
			catch (Exception e)
			{
				System.out.println("\n※입력오류※");
				System.out.println("1이상 3이하의 정수를 입력하세요.");
				sc.nextLine();
				temp = true;	// 오류나서 temp true로 바뀌면
			}
		}
		while (temp);			// 제대로 할 때까지 반복
		setGo(num);
	}// setSelect()

	//#2
	void setGo(int num) throws IOException
	{
		switch (num)
		{
			case 1 : new AdFruit().frSelect();break;
			case 2 : new AdSub().subSelect();break;
			case 3 : super.turnOn(); break;	// 뒤로가기 - 상속받은 부모(메인) 선택으로 돌아가기

			// 정수형은 입력했는데 1이상 3이하가 아니라면
			default : System.out.println("\n잘못된 선택입니다.");
					System.out.println("1이상 3이하의 정수를 입력하세요.");
					setSelect();
		}
	}// setGo()
}