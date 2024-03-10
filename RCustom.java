// 과일 커스텀 구성

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Calendar;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;



public class RCustom
{
	int n;													// 사용자가 선택하는 과일값을 담을 변수
	int se;													// 계절 변수
	int size = 0;											// 과일이 담기는 사이즈 변수
	int sum = 0;											// 탕후루 전체가격 변수
	int[] temp = new int[10];
	
 
	static public List<String> huru = new ArrayList<String>();		// 탕후루를 구성하는 리스트 변수


	VendingMachine menu = new VendingMachine();				// 메인 메뉴 클래스 인스턴스 생성

	static Fruits fru = new Fruits();								// 기본 과일 클래스 인스턴스 생성

	Coating sugar = new Coating();							//  설탕코팅 클래스 인스턴스 생성
	//SubsidiaryMaterial sm = new SubsidiaryMaterial();	    //  설탕, 꼬치 클래스 인스턴스 생성
	

	// 과일 메뉴판 출력하는 메소드	
	public void fruitPrint() throws IOException
	{
		
		// 꼬치 부족할 때 나오는 메소드

		if (Sub.sub.get(1).getStock() < 30 && Sub.sub.get(2).getStock() < 1)
		{
			System.out.println("현재 설탕과 꼬치가 부족합니다.");
			System.out.println("더 이상 주문 할 수 없습니다.");
			System.out.println("장바구니로 이동합니다.");

			resetHuru();
			ShopingCart cart = new ShopingCart();

			cart.sMenu();
		}
		else if (Sub.sub.get(2).getStock() < 1)
		{
			System.out.println("꼬치가 부족합니다.");
			System.out.println("더 이상 주문 할 수 없습니다.");
			System.out.println("장바구니로 이동합니다.");

			resetHuru();
			ShopingCart cart = new ShopingCart();

			cart.sMenu();
		}
		else if (Sub.sub.get(1).getStock() < 30)
		{
			System.out.println("설탕이 부족합니다.");
			System.out.println("더 이상 주문 할 수 없습니다.");
			System.out.println("장바구니로 이동합니다.");

			RCustom rus = new RCustom();
			rus.resetHuru();

			ShopingCart cart = new ShopingCart();

			cart.sMenu();
		}
		else 	
		{
			System.out.println(" \n   원하는 과일을 선택해주세요! (size 6 초과 Ⅹ)");
			System.out.println("   ※ [] 안의 숫자가 사이즈 입니다.");
			System.out.println("  ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

			for (int i = 0; i<fru.fruits.size();)
			{
				for (int j=1; fru.fruits.size()-i > 0; j++)
				{
					if (fru.fruits.containsKey(j))
					{
						temp[i]=j;
						if (fru.fruits.get(j).getSize() * fru.fruits.get(j).getStock() <= 6)
						{
							System.out.printf ("%5d. %s [%d] (품절)" ,(i+1), fru.fruits.get(j).getName(),fru.fruits.get(j).getSize());
						}

						else 
						{
							System.out.printf("%5d. %s [%d] (%d)" ,(i+1), fru.fruits.get(j).getName(),fru.fruits.get(j).getSize(), fru.fruits.get(j).getStock());
						}
						i++;
						if (i%4==0)
						{
							System.out.println();
						}
					}
					
				}
			}

			System.out.println();
			System.out.println("  ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.print("   >> ");
		}
	}

		
	// 과일 주문하는 메소드
	public void cOrder() throws IOException
	{	
		n = 0;
		boolean flag = false;

		Scanner sc = new Scanner(System.in);
		fruitPrint();
		n = sc.nextInt();
		
		try
		{	
			if (n > fru.fruits.size() || n <- -1)
			{
				System.out.println("\n    잘못 누르셨습니다!");
				System.out.println("    다시 눌러주세요!");
				flag = true;
				cOrder();
			}
		}
		catch (Exception e)
		{	

			System.out.println("\n    웨에에에↗엥에에↗에엥↘웨우에엥↗→에에엥↗");
			System.out.println("    비상!!!!!!!! 비상!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			System.out.println("    심각한 오류 발생 ");
			
			System.out.println("\n    해결 방법은 다시 입력하기입니다~! ^0^");

			cOrder();

		}
		


		
		// 코드 수정 절대 금지
		for (int i = 1; i<fru.fruits.size();i++)
		{	
			if ((n - i ) == 0)
				break;
			else if (fru.fruits.get(n) == null)
			{
				n += 1;
				break;
			}
			else if (fru.fruits.get(n-i) == null)
			{
				n += 1;
				break;
			}
		}

		if (size == 5)
		{
			if (fru.fruits.get(n).getSize() == 2)
			{
				System.out.println(" 현재 사이즈는 5입니다. [2] 사이즈를 가진 과일은 선택이 불가능합니다.");
				size = 5;
				cOrder();	
			}
		}
		
		if (size == 0)
		{
			size = 0;
			sum = 0;
		}

		if (flag == true)
		{
			size -= fru.fruits.get(n).getSize();
			sum -= fru.fruits.get(n).getPrice();
			fru.fruits.get(n).setAddCount(fru.fruits.get(n).getAddCount()-1);
			fru.fruits.get(n).setCTemp(fru.fruits.get(n).getCTemp()-1);									// 임시 변수 증가
			fru.fruits.get(n).setStock(fru.fruits.get(n).getStock()+1);									// 과일 재고 감소
			fru.fruits.get(n).setSTemp(fru.fruits.get(n).getSTemp()-1);									// 임시 변수 증가

		}
		
		size += fru.fruits.get(n).getSize();														// 탕후루 사이즈 증가
		sum += fru.fruits.get(n).getPrice();														// 탕후루 가격 증가

		fru.fruits.get(n).setAddCount(fru.fruits.get(n).getAddCount()+1);							// 과일 판매개수 증가
		fru.fruits.get(n).setCTemp(fru.fruits.get(n).getCTemp()+1);									// 임시 변수 증가
		//fru.fruits.get(n).setTempC(fru.fruits.get(n).getTempC()+1);								// 임시 임시완 변수 증가

		
		// 과일 재고시 품절
		if (fru.fruits.get(n).getSize() * fru.fruits.get(n).getStock() <= 6)
		{
			System.out.println("현재 과일의 재고가 부족합니다.");
			System.out.println("다른 과일을 선택해주세요!");
			size -= fru.fruits.get(n).getSize();
			sum -= fru.fruits.get(n).getPrice();
			cOrder();	
		}
		
		fru.fruits.get(n).setStock(fru.fruits.get(n).getStock()-1);									// 과일 재고 감소
		fru.fruits.get(n).setSTemp(fru.fruits.get(n).getSTemp()+1);									// 임시 변수 증가
		//fru.fruits.get(n).setTempS(fru.fruits.get(n).getTempS()+1);								// 임시 임시완 변수 증가

		// 사이즈가 6일때 
		if (size == 6)
		{	
			pushFruits(n);
			sugarCoating();
		}

	} // end cOrder() 

	public void fruitIng()	throws IOException
	{	
		Scanner sc = new Scanner(System.in);	
				
		
			do
			{		
				pushFruits(n);

				System.out.println("사이즈 : " + size);
				System.out.println("이름 : "+ huru);
				System.out.println("가격 : "+ sum);

				int num = 0;

				
				System.out.println("\n 1. 과일구성 계속하기 2. 설탕 시럽 입히러 가기 (과일 선택 종료) 3. 메뉴로 나가기(초기화)\n");
				System.out.print(" >> ");	
				num = sc.nextInt();
				System.in.skip(2);

				if (num == 1)
				{
					cOrder();

				} 
				else if (num == 2)
				{
					ShopingCart ob = new ShopingCart();		// 여기가 입력
					ob.setHuruPrice(sum);
					ob.huruInput();	

					//temp();
					sugar.sugarPrint();

					break; 
					
				}
				else if (num == 3)
				{	
					resetHuru();
					menu.turnOn();					
					
				}
				else 

				{	System.in.skip(2);
					n = 0;
					System.out.println("잘못 누르셨습니다.");
					System.out.println("다시 입력해주세요!");
					cOrder();
					break;	

				}

			}
			while (size < 6);

	}
	
	// 선택된 과일을 담는 메소드
	public void pushFruits(int name)
	{	
		huru.add(fru.fruits.get(name).getName());	
	}

	//임시 초기화 변수
	public void temp()
	{	
		for (int m = 1;  m <= fru.fruits.size() ; m++)
		{
			fru.fruits.get(m).setTempC(fru.fruits.get(m).getTempC() + fru.fruits.get(m).getCTemp());
			fru.fruits.get(m).setTempS(fru.fruits.get(m).getTempS() + fru.fruits.get(m).getSTemp());

			fru.fruits.get(m).setSTemp(0);
			fru.fruits.get(m).setCTemp(0);
		
			//System.out.println("테스트 용 : " + fru.fruits.get(m).getTempS());
		}

	} 

	// 구성을 취소했을 때 롤백하는 메소드
	public void resetHuru() throws IOException
	{
		for (int m = 1; m <= fru.fruits.size() ; m++)
		{				
			
			
			fru.fruits.get(m).setStock(fru.fruits.get(m).getStock() + (fru.fruits.get(m).getSTemp()));
			fru.fruits.get(m).setAddCount(fru.fruits.get(m).getAddCount() - (fru.fruits.get(m).getCTemp()));
			
			//System.out.println(fru.fruits.get(m).getName() + " 의 현재 재고: " + fru.fruits.get(m).getStock());
			//System.out.println(fru.fruits.get(m).getName() + " 의 추가 될 재고: " + fru.fruits.get(m).getSTemp());
		
			fru.fruits.get(m).setSTemp(0);
			fru.fruits.get(m).setCTemp(0);

		}	
		huru.clear();
		
	}
	
	// 장바구니 비우기 했을때 재고 초기화
	public void allReset()
	{
		for (int m = 1; m <= fru.fruits.size() ; m++)
		{	
			//System.out.println(fru.fruits.get(m).getName() + " 의 현재 재고: " + fru.fruits.get(m).getStock());				// 테스트 용 (지워도 무방)

			fru.fruits.get(m).setSTemp(fru.fruits.get(m).getSTemp() + fru.fruits.get(m).getTempS());
			fru.fruits.get(m).setStock(fru.fruits.get(m).getStock() + (fru.fruits.get(m).getSTemp()));
			
			fru.fruits.get(m).setCTemp(fru.fruits.get(m).getCTemp() + fru.fruits.get(m).getTempC());
			fru.fruits.get(m).setAddCount(fru.fruits.get(m).getAddCount() - (fru.fruits.get(m).getCTemp()));
			
			
			//System.out.println(fru.fruits.get(m).getName() + " 의 추가 될 재고: " + fru.fruits.get(m).getSTemp());			// 테스트 용 (지워도 무방)
		
			// 임시 변수 초기화 (원래 재고보다 값이 늘지 않게 하기 위해서)
			fru.fruits.get(m).setSTemp(0);
			fru.fruits.get(m).setCTemp(0);
			fru.fruits.get(m).setTempC(0);
			fru.fruits.get(m).setTempS(0);

		}	
		huru.clear();
		
	
	}
	
	// 설탕 코팅 메소드
	public void sugarCoating() throws IOException
	{	
		//size = 6;
		System.out.println("\n 사이즈를 모두 채우셨습니다.");
		System.out.println(" 설탕 시럽을 선택하세요!");
		
		ShopingCart ob = new ShopingCart();		// 여기가 입력
		ob.setHuruPrice(sum);
		ob.huruInput();

		sugar.sugarPrint();
		
	}

	/*
	public static void main(String[] args)  throws IOException
	{
		Serial.fruitGet();
		Serial.subGet();
		RCustom ob = new RCustom();

		Coating sugar = new Coating();					  //  설탕코팅 클래스 인스턴스 생성

		SubsidiaryMaterial sm = new SubsidiaryMaterial(); //  설탕, 꼬치 클래스 인스턴스 생성
		sm.subMaterialInput(); // sm의 메소드

		ob.cOrder();
		ob.fruitIng();
		
	}
	*/

}
