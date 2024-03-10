import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;


public class Coating
{	
	int temp = 0;

	int n; // 설탕 옵션 입력값 
	
	VendingMachine menu = new VendingMachine(); // 메인 메뉴 클래스 인스턴스 생성
	
	static List<String> sugarCoatinList = new ArrayList<String>();
	
	// RandomFruit1212 클래스에서 selectedFruits 정보 가져오기
	//selectedFruits =  랜덤으로 추천된 과일을 huru에 담기전에  저장하는 리스트
	List<FruitsProducts> selectedFruits = RandomFruit1212.selectedFruits;
	
	static List<Integer> kcList = new ArrayList<Integer>(); //  꼬치 재고 담는 리스트

	//  추가 설탕 재고를 저장할 자료구조
    static List<Integer> sugarStockList = new ArrayList<Integer>();

	public void sugarPrint() throws IOException
	{

		//System.out.println(sm.subMaterialList.get("꼬치").getSubStock());
		

		System.out.println("     원하시는 설탕 코팅 옵션을 선택해주세요.");
		System.out.println("\n     현재 남은 설탕의 양 : " + Sub.sub.get(1).getStock());
		System.out.println("  ☞ (1.얇게(10g))  (2.보통(20g))  (3.두껍게(30g))"); 
		System.out.println("  ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.print("  ▶ ");
		


		sugarOrder(); //  로 이동
	
	} // sugarPrint() end



	
	// 설탕 주문(재고 감소)하는 메소드

	// 1번 =  10g  → 1을 입력하면  10 감소
	// 2번 =  20g  → 2을 입력하면  20 감소
	// 3번 =  30g  → 3을 입력하면  30 감소
	
	public void sugarOrder() throws IOException 
	{
		Scanner sc = new Scanner(System.in);
    
		while (true)
		{
			n = sc.nextInt();
        
			if (n == 1) 
			{
				Sub.sub.get(1).setStock(Sub.sub.get(1).getStock() - 10);
				sugarCoatinList.add("얇음");
				sugarStockList.add(10);
				break; 
			} 
			else if (n == 2) 
			{
				Sub.sub.get(1).setStock(Sub.sub.get(1).getStock() - 20);
				sugarCoatinList.add("보통");
				sugarStockList.add(20);
				break; 
			}
			else if (n == 3) 
			{
				Sub.sub.get(1).setStock(Sub.sub.get(1).getStock() - 30);
				sugarCoatinList.add("두꺼움");
				sugarStockList.add(30);
				break; 
			}	
			else 
			{
				System.out.println(">> 정확한 값을 입력해주세요.\n ");
				 sugarPrint();
			}
			
		 }

			// 정상 입력 후 sFinish() 메소드 호출
			 sFinish();

	} // sugarOrder() end
		
		
	// 입력값 * 10 만큼 설탕 선택 출력
	// 안내문 출력 후 옵션선택
	public void sFinish() throws IOException 
	{
		Scanner sc = new Scanner(System.in);
	
		int num;
		

		while (true)
		{	
			
			System.out.printf("\n  (%dg)의 설탕 옵션을 선택하셨습니다.\n", n*10); 
			System.out.println("  탕후루 구성이 완료 되었습니다."); 
			System.out.println("\n  1.다시 고르기 2. 장바구니 담기  3. 취소하고 메인메뉴로 나가기");
			System.out.println("  ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.print("  ▶ ");
			num = sc.nextInt();		 //  출력된 옵션 선택
			System.out.println();

			temp ++;				 // 장바구니 취소시 사용되는 변수
	
			if (num == 1) // 1번은 선택한 설탕 옵션을 반환하고 다시 선택하러감.
			{
				Sub.sub.get(1).setStock(Sub.sub.get(1).getStock()+n*10);
				sugarCoatinList.remove(temp-1);
				temp --;
				sugarStockList.clear();
				kcList.clear();
				sugarPrint();

			}
			else if (num == 2) // 2번은 선택한 설탕 옵션과, 꼬치 1개도 소비하여 장바구니에 담기.
			{	
				for (FruitsProducts randomFruit : selectedFruits)		// selectedFruits 안에 있는 랜덤 추천된 과일들을
				{														// for 문을 통해 그 과일들의 재고를 1개씩 감소
					int stock = randomFruit.getStock();

					if (stock > 0 && randomFruit.getSize() > 0)
					{
						randomFruit.setStock(stock - 1);
					}
				
				}

				Sub.sub.get(2).setStock(Sub.sub.get(2).getStock()-1);
				kcList.add(1);
				System.out.println("남은 설탕의 양 : " + Sub.sub.get(1).getStock());
				System.out.println("남은 꼬치의 양 : " + Sub.sub.get(2).getStock());
				System.out.println("구성 완료된 탕후루가 장바구니에 담겼습니다. ");
			
				RCustom huru = new RCustom();	
				huru.temp();

				menu.turnOn(); 
			}
			else if (num == 3) // 3번은 선택한 설탕 옵션을 반환하고, 메인메뉴로 이동
			{
				Sub.sub.get(1).setStock(Sub.sub.get(1).getStock()+n*10);
				
				// 장바구니 초기화 
				// temp == 0
				RCustom huru = new RCustom();
				huru.resetHuru();

				ShopingCart cart = new ShopingCart();

				cart.huruList.remove(cart.huruList.size());		// hashmap
				cart.priceList.remove(temp-1);					// 리스트

				sugarCoatinList.remove(temp-1);
				cart.setKeyCounter(cart.huruList.size()+1);

				sugarStockList.clear(); //설탕 초기화
				kcList.clear(); // 꼬치 초기화
				
				temp --;

				menu.turnOn();

			}
			else 
			{
				System.out.println(">> 정확한 값을 입력해주세요.\n " );
				
			}

		}
	} // sFinish() end
	
/*
	public static void main(String[] args) throws IOException
	{
		Coating ct =  new Coating();  // Coating클래스 인스턴스 새성
		Sub s = new Sub(); // SubsidiaryMaterial 클래스 인스턴스 생성
		Sub.set(); // sm의 메소드

			ct.sugarPrint();
			ct.sugarOrder();
			ct.sFinish();
	}
*/
}
