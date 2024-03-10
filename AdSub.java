/*
[부자재 관리]

과일 및 부자재 관리에서 2) 를 선택하면 해당 시스템 접근

 2) 부자재 관리
  2-1) 설탕 재고 관리
  2-2) 꼬치 재고 관리
  2-3) 뒤로가기

*메소드 구성*

#1 2-1) ~ 2-3) 기능을 선택하는 메소드

#2 선택한 상품 재고 관리 메소드

*/
import java.util.Scanner;
import java.io.IOException;

class AdSub extends AdSetting
{
	//#1
	void subSelect() throws IOException
	{
		Scanner sc = new Scanner(System.in);
		int number = 0;			// 선택값 저장할 변수
		boolean temp = false;	// 반복문용 불린 변수

		System.out.println("\n\t[부자재 관리]");
		System.out.println("[1] 설탕 재고 관리");
		System.out.println("[2] 꼬치 재고 관리");
		System.out.println("[3] 뒤로가기");

		do	// 사용자가 제대로(1~3 정수형) 입력할때까지 반복
		{
			System.out.print("\n실행할 기능을 선택하세요. : ");
			try	// 말 안 듣고 이상하게 입력했을때 대비용
			{
				number = sc.nextInt();
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
		if (number==3)
			super.setSelect();
		else if (number==1||number==2)
			subEdit(number);
		else
		{
			System.out.println("\n※입력오류※");
			System.out.println("1이상 3이하의 정수를 입력하세요.");
			subSelect();
		}
			
	}// subSelect()

	//#2
	void subEdit(int number) throws IOException
	{
		Scanner sc = new Scanner(System.in);	
		int su=0;
		int oldStock=Sub.sub.get(number).getStock();
		boolean temp = true;

		System.out.printf("\n[%s]의 재고를 변경합니다.",Sub.sub.get(number).getName());
		do
		{
			try
			{
				System.out.println("\n재고 추가 - 양수 입력 / 재고 감소 - 음수 입력");
				System.out.printf("현재 재고 [%d] → 변경할 재고 : ",oldStock);
				su = sc.nextInt();
				if (su<0)
				{
					if (oldStock+su<0)
					{
						System.out.println("재고는 [0] 미만이 될 수 없습니다.");
						temp = true;
					}
					else
						temp = false;
				}
				else
				{
					if (su>Sub.sub.get(number).getMax())
					{
						System.out.printf("재고는 [%d] 이하로만 입력할 수 있습니다.\n",Sub.sub.get(number).getMax());
						temp = true;
					}
					else if (oldStock+su>Sub.sub.get(number).getMax())
					{
						System.out.printf("재고는 [%d] 을(를) 초과 할 수 없습니다.",Sub.sub.get(number).getMax());
						temp = true;
					}
					else
						temp = false;
				}
			}
			catch (Exception e)
			{
				System.out.println("\n※입력 오류※");
				System.out.printf("[%d] 이하의 숫자형태로 입력할 수 있습니다.",Sub.sub.get(number).getMax());
				sc.nextLine();
				temp = true;
			}
		}
		while (temp);

		System.out.printf("%s의 재고 [%d → %d]로 변경하시겠습니까?(Y/N) : ",Sub.sub.get(number).getName(),oldStock,oldStock+su);
		String answer = sc.next();
		if (answer.equals("Y")||answer.equals("y"))
		{
			Sub.sub.get(number).setStock(oldStock+su);
			System.out.println("\n변경이 완료되었습니다.");
			subSelect();
		}
		else
		{
			System.out.println("\n변경을 취소합니다.");
			subSelect();
		}
	}// subEdit()
}