import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import java.util.Scanner;
import java.util.InputMismatchException;

class ShopingCart extends RCustom // 장바구니 예시 클래스
{
	static HashMap<Integer, ArrayList<String>> huruList = new HashMap<>();	// 완성된 탕후루 배열을 저장할 자료구조
	static List<Integer> priceList = new ArrayList<Integer>();
	public static List<String> sugarCoatinList = new ArrayList<String>();
	Serial sr = new Serial();

	private int huruPrice;		  // sum 의 누적합을 받을 탕후루 하나의 가격 변수
	private int totalPrice;				  // 탕후루 전체가격을 담을 변수
	private static int keyCounter = 1;
	int sSel;
	Coating co1 = new Coating();
	int temp = 0;

	public void setHuruPrice(int huruPrice) // 탕후루 가격 setter
	{
        this.huruPrice = huruPrice;
    }

	public void setKeyCounter(int keyCounter) // keyCounter 가격 setter
	{
        this.keyCounter = keyCounter;
    }

	// 추가
	//ShopingCart 클래스에서 huru 정보를 받아와서 사용.
	public void setHuruList(List<String> huruList)
	{
        this.huru = new ArrayList<>(huruList);
	}

	public void sMenu() throws IOException
	{	
		Scanner sc = new Scanner(System.in);	
		
		try
		{
			do
			{	System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
				System.out.println("┃         장바구니                 ┃");
				System.out.println("┣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┫"); 
				System.out.println("┃ ① 장바구니 출력                 ┃");
				System.out.println("┃                                  ┃");
				System.out.println("┃ ② 결제하기                      ┃");
				System.out.println("┃                                  ┃"); 
				System.out.println("┃ ③ 장바구니 비우기               ┃");
				System.out.println("┃                                  ┃");
				System.out.println("┃ ④ 뒤로가기                      ┃");
				System.out.println("┃                                  ┃");
				System.out.println("┣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┫"); 
				System.out.print("  ▶ "); 
				sSel = sc.nextInt();
				System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛"); 
				System.out.println();
			}
			while(sSel < 1 || sSel > 4);	
		}
		catch (InputMismatchException e)
		{
			System.out.println("올바른 숫자를 입력해 주세요.");
			sMenu();
		}

		if (sSel == 1)
		{
			//장바구니 출력
			huruPrint();
			
		}

		else if (sSel == 2)
		{	
			if (huruList.isEmpty())
			{
				System.out.println("장바구니가 비었습니다.\n");
				sMenu();
			}

			else
			{
				Pay ppap = new Pay();
				ppap.paymenu();
			}
			
			
		}

		else if (sSel == 3)
		{	
			RandomFruit1212 rd12 = new RandomFruit1212();

			//★★★★★★ 장바구니 비우기 ★★★★★★
			System.out.println("\n정말 장바구니를 비우시겠습니까? (Y/N)");
			System.out.print("▶ ");
			
			String answer = sc.next();

			if (answer.equals("Y")||answer.equals("y"))
			{	
				sr.subGet();
				//someMethod(); // 설탕 복구 메소드
				//kcStock1();   // 꼬치 복구 메소드

				setKeyCounter(1);

				sr.fruitGet();
				huruList.clear();		// 탕후루 이름 맵
				priceList.clear();		// 가격 리스트
				sugarCoatinList.clear();
		

				System.out.println("\n삭제가 완료되었습니다.");
				sMenu();
			}
			
			else
			{
				System.out.println("\n삭제를 취소합니다.");

				/*
				for (int m = 1; m <= fru.fruits.size() ; m++)
				{		
					fru.fruits.get(m).setSTemp(0);
					fru.fruits.get(m).setCTemp(0);
				}	
				*/

				sMenu();
			}
			
		}

		else if (sSel == 4)
		{
			//뒤로가기
			VendingMachine vm2 = new VendingMachine();
			vm2.turnOn();
		}
	}
		
	public void huruInput() throws IOException
	{
		huruList.put(keyCounter++, new ArrayList<>(huru));
		huru.clear();		// 하나의 탕후루를 구성하면 배열 초기화
		priceList.add(huruPrice);
	}

	public void huruPrint() throws IOException
	{	
		Scanner sc = new Scanner(System.in);

		if (huruList.isEmpty())
		{
			System.out.println("장바구니가 비었습니다.\n");
			sMenu();
		}
		
		else
		{	
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"); 
			System.out.println("          장바구니 목록            ");
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"); 

			for (int i = 1; i <= huruList.size(); i++) 
			{
				System.out.printf ("  ■■■ %d번 탕후루 ■■■\n", i);
				System.out.println();
				System.out.printf ("  탕후루 구성: %s\n", huruList.get(i));
				System.out.printf ("  설 탕 두 께: %s\n", co1.sugarCoatinList.get(i-1));
				System.out.printf ("  가격       : %d원 \n", priceList.get(i-1));
				System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"); 
			
			}
        
		calculateTotalPrice();

			sMenu();
		}
	}

	public void calculateTotalPrice() 
	{
        totalPrice = 0;

        for (Integer price : priceList) 
		{
            totalPrice += price;
        }
        
		System.out.println("  총 가격: " + totalPrice + "원");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
	}

	public void someMethod() 
	{
        // Coating 클래스의 sugarStockList에 접근
        List<Integer> sugarStock = Coating.sugarStockList;
        
           // sugarStock 값을 순회하며 각 값에 해당하는 양만큼 설탕 재고를 증가시킴
		for (Integer sugarAmount : sugarStock) 
		{
            // 여기에서 설탕의 재고 증가 로직을 추가
            // 예를 들어, 설탕 클래스에서 해당 메소드를 호출하여 재고 증가
            // sugarAmount에 따라서 설탕 재고를 증가
            int sugarStockIncrease = sugarAmount; // 10으로 나누어 각각 10g 단위로 증가
            Sub.sub.get(1).setStock(Sub.sub.get(1).getStock() + sugarStockIncrease);
        }
		sugarStock.clear();
    
    }

	public void kcStock1()
	{
		// Coating 클래스의 sugarStockList에 접근
        List<Integer> kcStock = Coating.kcList;

		for (Integer kcAmount : kcStock)
		{
			// 꼬치의 재고 증가 로직을 추가
			// kcAmount에 따라서 꼬치 재고를 증가
			Sub.sub.get(2).setStock(Sub.sub.get(2).getStock() + kcAmount);
		}

		kcStock.clear();
	
	}

}