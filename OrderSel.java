import java.util.Scanner;
import java.util.InputMismatchException;
import java.io.IOException;

class OrderSel
{
	int oSel;						// 주문 선택 변수
	public final int O_CUSTOM = 1;	// 커스텀 주문
	public final int O_RANDOM = 2;	// 랜덤 주문
	public final int O_BACK = 3;

	public void orderSelect() throws IOException
	{
		Scanner sc = new Scanner(System.in);	

		try
		{
			do
			{	System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
				System.out.println("┃         주문하기                 ┃");
				System.out.println("┣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┫"); 
				System.out.println("┃ ① 커스텀 주문                   ┃");
				System.out.println("┃                                  ┃");
				System.out.println("┃ ② 랜덤 주문                     ┃");
				System.out.println("┃                                  ┃"); 
				System.out.println("┃ ③ 뒤로 가기                     ┃");
				System.out.println("┃                                  ┃");
				System.out.println("┣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┫"); 
				System.out.print("  ▶ "); 
				oSel = sc.nextInt();
				System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛"); 
				System.out.println();
			}
			while(oSel < 1 || oSel > 3);	
		}
		
		catch (InputMismatchException e)
		{
			System.out.println("올바른 숫자를 입력해 주세요.");
			orderSelect();
		}

		if (oSel == 1)
		{
			//커스텀 주문
			RCustom cob1 = new RCustom();
			cob1.cOrder();
			cob1.fruitIng();
		}

		else if (oSel == 2)
		{
			//랜덤 주문
			RandomFruit1212 rd12 = new RandomFruit1212();
			rd12.print();
			rd12.runRd();
			rd12.getSelectedFruits();
		}

		else if (oSel == 3)
		{
			//뒤로가기
			VendingMachine vm1 = new VendingMachine();
			vm1.turnOn();
		}
	}
}