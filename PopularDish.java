import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class PopularDish 
{
    private HashMap<ArrayList<String>, Integer> combinationCount = new HashMap<>();	// 키: [과일조합], 값: [판매 카운트]
    private List<Map.Entry<ArrayList<String>, Integer>> sortedCombinationCount;		// 위의 자료구조를 판매 카운트를 기준으로 내림차순 정렬
    private ArrayList<String> fruitNames = new ArrayList<>();						// fruit의 이름을 담을 자료구조

	ArrayList<ArrayList<String>> allHuruLists = new ArrayList<>();

	int pSel;
	public final int P_DISH = 1;
	public final int P_ORDER = 2;
	public final int P_BACK = 3;
	
	public void pMenuSelect() throws IOException
	{
		Scanner sc = new Scanner(System.in);
		
		VendingMachine vm5 = new VendingMachine();

		try
		{
			do
			{	System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
				System.out.println("┃         ★인기 TOP3★            ┃");
				System.out.println("┣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┫"); 
				System.out.println("┃ ① ★인기 TOP3★ 조회            ┃");
				System.out.println("┃                                  ┃");
				System.out.println("┃ ② 주문 하기                     ┃");
				System.out.println("┃                                  ┃"); 
				System.out.println("┃ ③ 뒤로 가기                     ┃");
				System.out.println("┃                                  ┃");
				System.out.println("┣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┫"); 
				System.out.print("  ▶ "); 
				pSel = sc.nextInt();
				System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛"); 
				System.out.println();
			}
			while(pSel < 1 || pSel > 3);	
		}
		
		catch (InputMismatchException e)
		{
			System.out.println("올바른 숫자를 입력해 주세요.");
			pMenuSelect();
		}

		if (pSel == P_DISH)
		{
			// 인기 Top3 조회
			print();
			vm5.turnOn();
		}

		else if (pSel == P_ORDER)
		{
			// 주문 하기
			OrderSel os9 = new OrderSel();
			os9.orderSelect();
		}

		else if (pSel == P_BACK)
		{
			//뒤로가기
			
			vm5.turnOn();
		}
	}

    public void input() throws IOException
	{
        Pay ob1 = new Pay();
		
		for (ArrayList<ArrayList<String>> huruLists : ob1.pdHuruList.values()) 
		{
			allHuruLists.addAll(huruLists);
		}

        for (FruitsProducts fruit : Fruits.fruits.values()) 
		{	
			// 과일 이름 담기
            fruitNames.add(fruit.getName());
        }

        for (ArrayList<String> combination : allHuruLists) 
		{	
			// 유효한(fruits 구조에 있는 과일들만) 조합만 거르기

            boolean validCombination = true;

            for (String fruit : combination) {
                if (!fruitNames.contains(fruit)) {
                    validCombination = false;
                    break;
                }
            }

            if (!validCombination) {
                continue;
            }
			
			// 정렬하기
            Collections.sort(combination);

			// 카운트세기
			 combinationCount.put(combination, combinationCount.getOrDefault(combination, 0) + 1);
        }
		
		// 카운트를 기준으로 내림차순 정렬
        sortedCombinationCount = new ArrayList<>(combinationCount.entrySet());
        sortedCombinationCount.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));
		
		allHuruLists.clear();
		combinationCount.clear();
    }

	public void print() throws IOException
	{	
		Scanner sc = new Scanner(System.in);

		int temp=0;

		input();

		int count = 0;

		System.out.println("  ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        for (Map.Entry<ArrayList<String>, Integer> entry : sortedCombinationCount)
		{
            count++;
            System.out.printf("  %d위 조합: %s 판매 갯수 : %d\n", count, entry.getKey(), entry.getValue());
            if (count >= 3) 
			{
                break;
            }
        }
		System.out.println("  ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		
		do
		{
			System.out.println();
			System.out.println("  주문하시려면 해당하는 순위의 숫자를 누르세요 (4번은 뒤로가기)");
			System.out.print("  ▶ ");
			temp = sc.nextInt();
			System.out.println("  ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		}
		while (temp > 4 || temp < 1);

		
		if (temp == 1)
		{
			order1();
		}

		else if (temp == 2)
		{
			order2();
		}

		else if (temp == 3)
		{
			order3();
		}

		else
		{
			pMenuSelect();
		}

		
	}

	public void order1() throws IOException
	{	
		ArrayList<String> combination = sortedCombinationCount.get(0).getKey();
		int totalPrice = 0;

		for (String fruit : combination)
		{
			FruitsProducts fruitProduct = null;

			// 과일 이름으로 과일 객체 찾기
			for (Map.Entry<Integer, FruitsProducts> entry : Fruits.fruits.entrySet()) 
			{
				FruitsProducts product = entry.getValue();

				if (product.getName().equals(fruit)) 
				{
					fruitProduct = product;
					break;
				}
			}

			// 과일 객체가 있을 경우 가격 누적
			if (fruitProduct != null) 
			{
				totalPrice += fruitProduct.getPrice();
			}
		}

		RCustom rc3 = new RCustom();
		rc3.huru.addAll(sortedCombinationCount.get(0).getKey());

		ShopingCart sc3 = new ShopingCart();
		sc3.setKeyCounter(sc3.huruList.size()+1);
		
		sc3.setHuruPrice(totalPrice);
		sc3.huruInput();

		decreaseStock(combination);
		
		Coating co3 = new Coating();
		co3.sugarPrint();
	}

	public void order2() throws IOException
	{	
		ArrayList<String> combination = sortedCombinationCount.get(1).getKey();
		int totalPrice = 0;

		for (String fruit : combination)
		{
			FruitsProducts fruitProduct = null;

			// 과일 이름으로 과일 객체 찾기
			for (Map.Entry<Integer, FruitsProducts> entry : Fruits.fruits.entrySet()) 
			{
				FruitsProducts product = entry.getValue();

				if (product.getName().equals(fruit)) 
				{
					fruitProduct = product;
					break;
				}
			}

			// 과일 객체가 있을 경우 가격 누적
			if (fruitProduct != null) 
			{
				totalPrice += fruitProduct.getPrice();
			}
		}

		RCustom rc3 = new RCustom();
		rc3.huru.addAll(sortedCombinationCount.get(1).getKey());

		ShopingCart sc3 = new ShopingCart();
		sc3.setKeyCounter(sc3.huruList.size()+1);
		
		sc3.setHuruPrice(totalPrice);
		sc3.huruInput();

		decreaseStock(combination);
		
		Coating co3 = new Coating();
		co3.sugarPrint();
	}

	public void order3() throws IOException
	{	
		ArrayList<String> combination = sortedCombinationCount.get(2).getKey();
		int totalPrice = 0;

		for (String fruit : combination)
		{
			FruitsProducts fruitProduct = null;

			// 과일 이름으로 과일 객체 찾기
			for (Map.Entry<Integer, FruitsProducts> entry : Fruits.fruits.entrySet()) 
			{
				FruitsProducts product = entry.getValue();

				if (product.getName().equals(fruit)) 
				{
					fruitProduct = product;
					break;
				}
			}

			// 과일 객체가 있을 경우 가격 누적
			if (fruitProduct != null) 
			{
				totalPrice += fruitProduct.getPrice();
			}
		}

		RCustom rc3 = new RCustom();
		rc3.huru.addAll(sortedCombinationCount.get(2).getKey());

		ShopingCart sc3 = new ShopingCart();
		sc3.setKeyCounter(sc3.huruList.size()+1);
		
		sc3.setHuruPrice(totalPrice);
		sc3.huruInput();

		decreaseStock(combination);
		
		Coating co3 = new Coating();
		co3.sugarPrint();
	}



	public void decreaseStock(ArrayList<String> combination) {
    for (String fruit : combination) {
        FruitsProducts fruitProduct = null;

        // 과일 이름으로 과일 객체 찾기
        for (Map.Entry<Integer, FruitsProducts> entry : Fruits.fruits.entrySet()) {
            FruitsProducts product = entry.getValue();
            if (product.getName().equals(fruit)) {
                fruitProduct = product;
                break;
            }
        }

        if (fruitProduct != null) {
            int currentStock = fruitProduct.getStock();
            if (currentStock > 0) {
                fruitProduct.setStock(currentStock - 1);
            } else {
                System.out.println(fruit + "의 재고가 부족하여 감소시킬 수 없습니다.");
            }
        } else {
            System.out.println("해당하는 과일 정보를 찾을 수 없습니다: " + fruit);
        }
    }
}

}