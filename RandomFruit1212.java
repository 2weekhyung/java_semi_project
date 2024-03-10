import java.util.*;
import java.io.IOException;


public class RandomFruit1212 
{
	static List<String> huru = new ArrayList<String>(); // 과일의 이름을 저장할 huru 

	// 난수를 발생 시켜 랜덤으로 출력 될 과일들의 정보를 담을 selectedFruits
	public static List<FruitsProducts> selectedFruits = new ArrayList<>(); 
	static List<FruitsProducts> allRandomFruits = new ArrayList<>();
	
	Coating ct = new Coating(); // 코팅 클래스 인스턴스생성

	VendingMachine vm = new VendingMachine(); // 밴딩머신 인스턴스 생성

	int[] temp = new int[10];

	public void print()  throws IOException
	{
		if (Sub.sub.get(1).getStock() < 30 && Sub.sub.get(2).getStock() < 1)
		{
			System.out.println("현재 설탕과 꼬치가 부족합니다.");
			System.out.println("더 이상 주문 할 수 없습니다.");
			System.out.println("장바구니로 이동합니다.");

			
			ShopingCart cart = new ShopingCart();

			cart.sMenu();
		}
		else if (Sub.sub.get(2).getStock() < 1)
		{
			System.out.println("꼬치가 부족합니다.");
			System.out.println("더 이상 주문 할 수 없습니다.");
			System.out.println("장바구니로 이동합니다.");

			
			ShopingCart cart = new ShopingCart();

			cart.sMenu();
		}
		else if (Sub.sub.get(1).getStock() < 30)
		{
			System.out.println("설탕이 부족합니다.");
			System.out.println("더 이상 주문 할 수 없습니다.");
			System.out.println("장바구니로 이동합니다.");
		
			ShopingCart cart = new ShopingCart();

			cart.sMenu();
		}
		else 	
		{
			System.out.println(" \n   원하는 과일을 선택해주세요! (size 6 초과 Ⅹ)");
			System.out.println("   ※ [] 안의 숫자가 사이즈 입니다.");
			System.out.println("  ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

			for (int i = 0; i < Fruits.fruits.size();) 
			{
				for (int j = 1; Fruits.fruits.size() - i > 0; j++) 
				{
					if (Fruits.fruits.containsKey(j)) 
					{
						temp[i] = j;
						if (Fruits.fruits.get(j).getSize() * Fruits.fruits.get(j).getStock() <= 6)
						{
							System.out.printf("%5d. %s [%d] (품절)", (i + 1), Fruits.fruits.get(j).getName(),
									Fruits.fruits.get(j).getSize());
						} 
						else	
						{
							System.out.printf("%5d. %s [%d] (%d)", (i + 1), Fruits.fruits.get(j).getName(),
									Fruits.fruits.get(j).getSize(), Fruits.fruits.get(j).getStock());
						}
						i++;
						if (i % 4 == 0) 
						{
							System.out.println();
						}
					}
				}
			}

			System.out.println();
			System.out.println("  ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			runRd();
		}
	}
	       
    public void runRd() throws IOException
	{
		Scanner sc = new Scanner(System.in);
		Fruits fruits = new Fruits(); // Fruits 객체 생성, 과일 정보를 가지고 있음
		
		while (true) 
		{
			huru.clear(); // 이전 과일의 정보를 누적 하지 않도록 huru 를 초기화
			
			System.out.println("   랜덤 탕후루 추천을 받으시겠습니까?");
			System.out.println("   [1] 예 / [2] 아니오(메인메뉴) ");
			System.out.print("   ▶ ");
			int choice = sc.nextInt();
			sc.nextLine(); // 개행 문자 처리

			if (choice == 1) 
			{
				Random rd = new Random(); // 랜덤 인스턴스 생성 

				int totalSize = 0; // 출력된 과일의 전체 사이즈 합 초기화
				int totalPrice = 0; // 전체 과일 가격 합계 초기화

				// 사용 가능한 과일 인덱스를 저장하는 리스트 초기화
				List<Integer> availableFruitIndices = new ArrayList();

				
				
				// 새로운 자료구조에 모든 랜덤 추천된 과일 정보를 추가
                

				for (int i = 1; i <= fruits.fruits.size(); i++) 
				{
					FruitsProducts fruit = fruits.fruits.get(i);		// availableFruitIndices 에 유효한 과일들을 담아 
																		// 과일을 랜덤으로 선택하는데에 사용함 
					if (fruit.getSize() > 0 && fruit.getStock() > 6)  // 재고가 6개 초과인 과일만 선택 그리고 사이즈가 0 이상인 과일 선택
					{
						availableFruitIndices.add(i); // 재고와 사이즈가 0 이상인 과일의 인덱스를 저장
					}
				}

					 // 새로운 조건: availableFruitIndices가 비어 있다면 모든 과일이 품절
					 selectedFruits.clear(); //  huru 와 마찬가지로 누적되서 출력되지 않도록 selectedFruits 를 초기화 

					if (availableFruitIndices.isEmpty())
					{
						System.out.println("\n   과일이 모두 소진되었습니다.");
						System.out.println("   메인메뉴로 돌아갑니다.");
						vm.turnOn();
					} 
					else
					{

					while (!availableFruitIndices.isEmpty()) 
					{
						int rdIndex = rd.nextInt(availableFruitIndices.size());
						int rdFruitIndex = availableFruitIndices.get(rdIndex);
						FruitsProducts rdFruit = fruits.fruits.get(rdFruitIndex);
						int fruitSize = rdFruit.getSize();

						// 전체 사이즈 합이 6 이하인 과일만 선택 ＃중요
						if (totalSize + fruitSize <= 6) 
						{
							selectedFruits.add(rdFruit); // 과일을 선택 목록에 추가
							totalSize += fruitSize;

							if (rdFruit.getStock() <= 1) 
							{
								// 재고가 1개 남은 경우, 해당 과일은 다음에는 선택하지 않도록 인덱스 제거
								availableFruitIndices.remove(rdIndex);
							}

							totalPrice += rdFruit.getPrice();  //  가격들을 합한다.

							if (totalSize == 6) 
							{
								break; // 전체 사이즈 합이 6이 되면 선택 종료
							}
						} 
						else
						{
							availableFruitIndices.remove(rdIndex);
						}

					}
					}

					

					
					// selectedFruits 리스트에 있는 선택된 과일들의 이름이 huru 리스트에 추가 
					for (int i = 0; i < selectedFruits.size(); i++) 
					{
						 FruitsProducts randomFruit = selectedFruits.get(i);
					
						huru.add(randomFruit.getName());
						
					}
					
					// 사용자에게 랜덤으로 출력된 과일과 그 과일의 사이즈 
					// 과일 가격들을 합산하여 가격 까지 출력해서 보여줌 
					System.out.println("\n   랜덤으로 추천된 과일:");
					for (int i = 0; i < selectedFruits.size(); i++) 
					{
						FruitsProducts randomFruit = selectedFruits.get(i);
						System.out.println("   "+ (i + 1) + ". " + randomFruit.getName() + " [" + randomFruit.getSize() + "]");
						// 추천된 과일의 이름과 사이즈 출력
					}
					System.out.println("\n   전체 가격: " + totalPrice + "원"); // 전체 가격 출력
				
					
					// 위 구문을 수행 후 선택 옵션 출력
					while (true)
					{
					
						System.out.println("\n   원하시는 옵션 번호를 입력해 주세요. ");
						System.out.println("   [1] 설탕 입히러 가기 / [2] 다시 추천 받기 / [3] 메인메뉴 ");
						System.out.print("   ▶ ");
						int sugarOption = sc.nextInt();
						sc.nextLine(); // 개행 문자 처리

							if (sugarOption == 1)					
							{		
							// 모든 랜덤 추천된 과일 정보를 새로운 자료구조에 추가
							 allRandomFruits.addAll(selectedFruits);
					
							ShopingCart ob = new ShopingCart();
							ob.setHuruPrice(totalPrice);
							ob.setHuruList(huru);
							ob.huruInput();
							
							System.out.println();
							ct.sugarPrint();
						}
						else if (sugarOption == 2)
						{
							

							// selectedFruits를 초기화
							selectedFruits.clear();

							System.out.println();
							runRd();
						}
						else if (sugarOption == 3)
						{
							vm.turnOn();
						}
						else
							System.out.println("\n  올바른 번호를 입력해주세요!\n");
					}
			} 
			else if (choice == 2)  // 2번을 누를시 메인메뉴로 이동
			{
				System.out.println();
				vm.turnOn();
				
			}
			else
				System.out.println("\n  올바른 번호를 입력해주세요!\n");

		}


	}

	// 다른 클래스에서 selectedFruits에 접근 할 수 있도록 메소드 생성
	public List<FruitsProducts> getSelectedFruits() 
	{
    return selectedFruits;
	}

	public static void main(String[] args) throws IOException
	{
        RandomFruit1212 rd12 = new RandomFruit1212();
        rd12.print();
        rd12.runRd();
		rd12.getSelectedFruits();
	}
}
