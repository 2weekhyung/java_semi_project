import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.text.DecimalFormat; // 돈 나올떄 , 나오게해주는 함수가 포함되엉 있음
import java.lang.Math;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.time.LocalDate;
import java.io.Serializable;
import java.util.Set;
import java.util.HashSet;
import java.util.Comparator;
import java.util.Collections;

public class Pay extends ShopingCart implements Serializable
{
	
	VendingMachine ve = new VendingMachine(); 
	ShopingCart  sh = new ShopingCart();
	
	static HashMap<LocalDate, ArrayList<ArrayList<String>>> pdHuruList = new HashMap<>(); // 결제가 완료된 탕후루 - 날짜별 저장
	static HashMap<Integer, ArrayList<String>> totalPdHuruList = new HashMap<>();	// 결제가 완료된 탕후루 - 날짜 상관없이 총 저장
	static private int totalHap;

	static Map<String, Integer> fruitCounts = new HashMap<>();				  // 판매된 과일 갯수(날짜별)
	
	static Map<String, Integer> fruitCounts1 = new HashMap<>();				  // 판매된 과일 갯수(총)

	//새로 추가한곳
	static HashMap<Integer, ArrayList<String>> pd_HuruList1 = new HashMap<>(); //조합별 개수
	static HashMap<Integer, ArrayList<String>> pd_HuruList2 = new HashMap<>(); //인기 조합별 개수

	static List<String> availableFruits = new ArrayList<String>();  // 과일 선택지에서 사용 가능한 과일 이름 목록

	static HashSet<String> type = new HashSet();
	static HashSet<String> type1 = new HashSet();

	static Map<String, Integer> fruittype = new HashMap<>();
	static Map<String, Integer> fruittype1 = new HashMap<>();
	
	AdSales ad = new AdSales();
	int choice;
	static private int totahap; // 이놈은 총 매출조회할떄 씀
	int totlamoney;

	Coating co = new Coating();

	public int getTotalHap()
	{
		return totalHap;
	}

	public void setTotalHap(int totalHap)
	{
		this.totalHap =totalHap;
	}
	
	public int gettotahap()
	{
		return totahap;
	}
	
	public void settotahap(int totahap)
	{
		this.totahap = totahap;
	}

	// 달력...
	Calendar ca = new GregorianCalendar();
			int year  = ca.get(Calendar.YEAR);
			int month = ca.get(Calendar.MONTH);
			int day   = ca.get(Calendar.DATE);


	public void paymenu() throws IOException
	{
        Scanner sc = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
				System.out.println("┃         결제 메뉴                ┃");
				System.out.println("┣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┫"); 
				System.out.println("┃ ① 결제하기                      ┃");
				System.out.println("┃                                  ┃");
				System.out.println("┃ ② 장바구니로 돌아가기           ┃");
				System.out.println("┃                                  ┃"); 
				System.out.println("┃ ③ 메인 메뉴로 돌아가기          ┃");
				System.out.println("┃                                  ┃");
				System.out.println("┣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┫"); 
				System.out.print("  ▶ "); 
				choice = sc.nextInt();
				System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛"); 
				} 
			catch (Exception e)
				{
					e.toString();
					System.out.println("비상~~~ 오류 발생! ");
					System.out.println("해결할 방법은 오직하나  ");
					System.out.println("다시 입력하세요 ^_^");
					sc.nextLine(); // 버퍼 비우기
					continue; // 잘못된 입력 처리 후 다시 반복문 시작
				}

            if (choice == 1)
			{
                // 결제하기
				//System.out.println(rc.sum);
				//System.out.println(shh.huru);
				//System.out.println(shh.huruprice);
               System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓"); 
				System.out.println("┃ ① 카드 결제                     ┃");
				System.out.println("┃                                  ┃");
				System.out.println("┃ ② 현금 결제                     ┃");
				System.out.println("┃                                  ┃"); 
				System.out.println("┣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┫"); 
				System.out.print("  ▶ ");
                choice = sc.nextInt();
				System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛"); 
                if (choice == 1)
				{
                    // 카드 결제 하기
                    cardPay();
                } 
				else if (choice == 2) 
				{
                    // 현금 결제 하기
                    cashPay();
                }
				else
				{
                    System.out.println("다시 입력하세요");
				}
            }
			else if (choice == 2)
			{
                // 장바구니 돌아가기
				sMenu();
            }
			else if (choice == 3)
			{
                // 장바구니 초기화?
				// 회의 필요
                
				
				// 메인메뉴로 돌아가기
                ve.turnOn();
                break; // 반복문 종료
            } 
			else
			{
                System.out.println("다시 입력하세요");
            }
        }
    }


	public void cashPay() throws IOException// 현금 결제 함수
	{
		
	 // 사용자로부터 결제 금액 입력
       Scanner sc = new Scanner(System.in);
	   DecimalFormat df = new DecimalFormat("###,###"); // 돈 출력할때 , 나오게 하는 거
	   
       int usermoney;  // 사용자가 입력할 현금
	   
				for (int i = 0; i < ShopingCart.priceList.size(); i++)
				{
					int price = ShopingCart.priceList.get(i);
					totlamoney+=price;
	
					//increaseAddCount(fruitName);
					//테스트 System.out.println(fruitName + " - 가격: " + price + "원");
				}

       do
		{	
		   String changemoney2 = df.format(totlamoney);
		   	System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓"); 
		    System.out.print  ("┃ 총액은 "+changemoney2+"입니다.");System.out.println("              ┃");
			System.out.println("┣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┫");
			System.out.print  ("  ▶ 현금을 넣어 주세요: ");


		//일단 테스트 용도 
		// 판매된 과일 정보 출력
		/*
		System.out.println("판매된 과일 정보:");
		for (FruitsProducts fruit : fru.fruits.values())
		{
			int addCount = fruit.getAddCount();
			if (addCount > 0)
			{
				System.out.println(fruit.getName() + " - 판매된 개수: " + addCount);
			}
		}
		*/
            usermoney = sc.nextInt();
            if (usermoney < totlamoney) 
				{
					
					int aa=usermoney - totlamoney;
					int change=Math.abs(aa);
					String changemoney1 = df.format(change);
					System.out.println("입력한 금액이 결제 금액보다 부족합니다.");
					System.out.println("부족한 금액: " + changemoney1 + "원");
					System.out.println("다시 이용하려면 금액을 더 입력해 주세요.");
					
				}
		} while (usermoney < totlamoney);


		String changemoney = df.format(usermoney - totlamoney);
        System.out.println("┣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┫");
        System.out.println("┃ 현금결제가 완료되었습니다.       ┃");
        System.out.println("┃ 거스름돈: "+changemoney + "원");
		System.out.println("┣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┫");
        System.out.println("┃ 영수증을 출력하시겠습니까?       ┃");
		System.out.println("┃                                  ┃");
		System.out.println("┃ ① 예                            ┃");
		System.out.println("┃                                  ┃");
		System.out.println("┃ ② 아니오                        ┃");
		System.out.println("┃                                  ┃");
		System.out.println("┣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┫");
		System.out.print("  ▶ ");
		choice = sc.nextInt();
		System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		System.out.println();
		if (choice == 1)
		{
			// 영수증 출력
			System.out.println("  ■■■ 주  문  서 ■■■");
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"); 
			System.out.println("  ▶ 주 문 일 자: " + year + "년" + (month+1) + "월" + day + "일");
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"); 
			System.out.println("  ▶ 주 문 내 역 ◀");
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"); 

			
			for (int i = 0; i < ShopingCart.priceList.size(); i++)
			{	
				
				int price = ShopingCart.priceList.get(i);
				System.out.printf("  ●●● %d번 탕후루 ●●●\n", (i+1));
				System.out.printf("  탕후루 구성  : %s \n", ShopingCart.huruList.get(i+1));
				System.out.printf("  설탕 두께    : %s \n", co.sugarCoatinList.get(i));
				System.out.printf("  가격         : %d 원\n", price);
				System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			}

		
			String hapmoney = df.format(totlamoney);
			System.out.println   ("  판 매 총 액  : "+ hapmoney+ "원");
			System.out.println   ("  현금 지불액  : " + usermoney+ "원");


			// 판매 총액을 전체 매출에 넣기
			//totahap+=rc.sum;
			totalHap+=totlamoney;
			totahap = totalHap;

			//setTotalHap(totalHap);
			ad.addSale(LocalDate.of(year, (month+1), day), totalHap);


			// 그리고 다시 판패 총액 0으로 초기화
			totlamoney=0;
			
			// 결제된 과일을 pd_HuruList에 넣기
			totalPdHuruList.putAll(huruList);
			pd_HuruList1.putAll(huruList);
			input();

			// 고객용(판매용) 시스템 전환
			Serial.fruitSet(); 
			Serial.subSet(); 
			//정한울 시작 - 오류나면 지우기

			Serial.totahapSet();
			Serial.dailySalesSet();
			Serial.totalPdHuruListSet();
			Serial.pdHuruListSet();
			Serial.fruitCountsSet();

			// 장바구니 초기화
			for (int m = 1; m <= fru.fruits.size() ; m++)
			{		
				fru.fruits.get(m).setSTemp(0);
				fru.fruits.get(m).setCTemp(0);
				
				fru.fruits.get(m).setTempC(0);
				fru.fruits.get(m).setTempS(0);


			}	

			ShopingCart.huruList.clear();
			ShopingCart.priceList.clear();
			co.sugarCoatinList.clear();
			setKeyCounter(1);
			
			// 메인메뉴로 돌아가기
			ve.turnOn();
			
		}
		else if (choice ==2)
		{
			System.out.println("구매해주셔서 감사합니다 또 이용해주세요");
			
			totalHap+=totlamoney;
			totahap = totalHap;
			ad.addSale(LocalDate.of(year, (month+1), day), totalHap);
			
			
			// 결제된 과일을 pd_HuruList에 넣기
			totalPdHuruList.putAll(huruList);
			pd_HuruList1.putAll(huruList);
			input();
			
			// 고객용(판매용) 시스템 전환
			Serial.fruitSet(); 
			Serial.subSet(); 
			//정한울 시작 - 오류나면 지우기

			Serial.totahapSet();
			Serial.dailySalesSet();
			Serial.totalPdHuruListSet();
			Serial.pdHuruListSet();
			Serial.fruitCountsSet();

			// 장바구니 초기화
			for (int m = 1; m <= fru.fruits.size() ; m++)
			{		
				fru.fruits.get(m).setSTemp(0);
				fru.fruits.get(m).setCTemp(0);
				fru.fruits.get(m).setTempC(0);
				fru.fruits.get(m).setTempS(0);

			}	

			ShopingCart.huruList.clear();
			ShopingCart.priceList.clear();
			co.sugarCoatinList.clear();
			setKeyCounter(1);
			
			// 메인메뉴로 돌아가는 함수
			ve.turnOn();
			
		}
    }


public void cardPay() throws IOException// 카드 결제 함수
	{
		
		Scanner sc =new Scanner(System.in);
		DecimalFormat df = new DecimalFormat("###,###");

		System.out.println("┣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┫");
        System.out.println("┃ 카드결제가 완료되었습니다.       ┃");
		System.out.println("┣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┫");
        System.out.println("┃ 영수증을 출력하시겠습니까?       ┃");
		System.out.println("┃                                  ┃");
		System.out.println("┃ ① 예                            ┃");
		System.out.println("┃                                  ┃");
		System.out.println("┃ ② 아니오                        ┃");
		System.out.println("┃                                  ┃");
		System.out.println("┣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┫");
		System.out.print("  ▶ ");
		choice = sc.nextInt();
		System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		System.out.println();

		//System.in.skip(2);
		if (choice == 1)
		{
			// 영수증 출력
		System.out.println("  IC 신용 승인(자판기용)");
			System.out.println("  ■■■ 주  문  서 ■■■");
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"); 
			System.out.println("  ▶ 주 문 일 자: " + year + "년" + (month+1) + "월" + day + "일");
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"); 
			System.out.println("  ▶ 주 문 내 역 ◀");
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"); 
		
		
			for (int i = 0; i < ShopingCart.priceList.size(); i++)
			{	
				
				int price = ShopingCart.priceList.get(i);
				totlamoney+=ShopingCart.priceList.get(i);
				
				System.out.printf("  ●●● %d번 탕후루 ●●●\n", (i+1));
				System.out.printf("  탕후루 구성  : %s \n", ShopingCart.huruList.get(i+1));
				System.out.printf("  설탕 두께    : %s \n", co.sugarCoatinList.get(i));
				System.out.printf("  가격         : %d 원\n", price);
				System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			}
		
				
			String hapmoney = df.format(totlamoney);
			System.out.println   ("  판 매 총 액  : " + hapmoney+ "원");
			System.out.println   ("  카드 지불액  : " + hapmoney+ "원");
			System.out.println   ("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");			
			System.out.println   ("  [신 용 지 불]");
			System.out.println   ("  *** 신용승인정보(고객용) ***");
			System.out.println   ("  카 드 종 류  : 쌍용신용카드");
			System.out.println   ("  카 드 번 호  : ****-****-****-****");
			System.out.println   ("  결 제 방 식  : 일시불");

			System.out.println("\n  구매해주셔서 감사합니다 또 이용해주세요");

		//plusAddCount();

			// 판매 총액을 전체 매출에 넣기
			
			totalHap+=totlamoney;
			totahap = totalHap;
			ad.addSale(LocalDate.of(year, (month+1), day), totalHap);
			
			// 그리고 다시 판패 총액 0으로 초기화
			totlamoney=0;

			// 결제된 과일을 pd_HuruList에 넣기
			totalPdHuruList.putAll(huruList);
			pd_HuruList1.putAll(huruList);
			input();

			// 고객용(판매용) 시스템 전환
			Serial.fruitSet(); 
			Serial.subSet(); 
			//정한울 시작 - 오류나면 지우기

			Serial.totahapSet();
			Serial.dailySalesSet();
			Serial.totalPdHuruListSet();
			Serial.pdHuruListSet();
			Serial.fruitCountsSet();

			// 장바구니 초기화
			for (int m = 1; m <= fru.fruits.size() ; m++)
			{		
				fru.fruits.get(m).setSTemp(0);
				fru.fruits.get(m).setCTemp(0);

				fru.fruits.get(m).setTempC(0);
				fru.fruits.get(m).setTempS(0);
			}	

			ShopingCart.huruList.clear();
			ShopingCart.priceList.clear();
			co.sugarCoatinList.clear();
			setKeyCounter(1);
			
			//System.out.println(totahap);
			
			
			// 메인메뉴로 돌아가기
			ve.turnOn();

		}
		else if (choice ==2)
		{
			System.out.println("구매해주셔서 감사합니다 또 이용해주세요");

			for (int i = 0; i < ShopingCart.priceList.size(); i++)
			{	
				
				int price = ShopingCart.priceList.get(i);
				totlamoney+=ShopingCart.priceList.get(i);
			}

			totalHap+=totlamoney;
			totahap = totalHap;
			ad.addSale(LocalDate.of(year, (month+1), day), totalHap);
			
			// 결제된 과일을 pd_HuruList에 넣기
			totalPdHuruList.putAll(huruList);
			pd_HuruList1.putAll(huruList);
			input();

			// 장바구니 초기화
			for (int m = 1; m <= fru.fruits.size() ; m++)
			{		
				fru.fruits.get(m).setSTemp(0);
				fru.fruits.get(m).setCTemp(0);

				fru.fruits.get(m).setTempC(0);
				fru.fruits.get(m).setTempS(0);
			}	

			ShopingCart.huruList.clear();
			ShopingCart.priceList.clear();
			co.sugarCoatinList.clear();
			setKeyCounter(1);
			
			// 메인메뉴로 돌아가는 함수 
			ve.turnOn();
		}
    }

	public void input() 
	{
		LocalDate date = LocalDate.of(year, (month + 1), day);
		ArrayList<ArrayList<String>> huruListForDate = pdHuruList.get(date);

		if (huruListForDate == null) 
		{	
			// 프로그램 실행 일의 키의 값이 null 이라면 새로운 구조 생성
			huruListForDate = new ArrayList<>();
		}

		for (Map.Entry<Integer, ArrayList<String>> entry : huruList.entrySet()) 
		{	
			// 프로그램 실행 일의 키의 값이 null 이 아니라면 장바구니에서 결제완료 된 탕후루들을 pdHuruList에 추가
			ArrayList<String> huruListA = entry.getValue();
			
			ArrayList<String> copyHuruList = new ArrayList<>(huruListA); // 원본 huruList에 영향을 절대 주지 않도록 깊은 복사 수행
			huruListForDate.add(copyHuruList);
		}

		pdHuruList.put(date, huruListForDate);
		//System.out.println("날짜별 저장 테스트 : " + pdHuruList);		// 테스트
	}

	
	public static void plusAddCount() // 총 과일 갯수 세기
	{
		//Map<String, Integer> fruitCounts = new HashMap<>();

		for (Map.Entry<Integer, ArrayList<String>> entry : totalPdHuruList.entrySet()) 
			{
				ArrayList<String> fruits = entry.getValue();
				for (String fruit : fruits) 

				{
					fruitCounts.put(fruit, fruitCounts.getOrDefault(fruit, 0) + 1);
				}
			}

		for (Map.Entry<String, Integer> entry : fruitCounts.entrySet()) 
			{
				System.out.println(entry.getKey() + ": " + entry.getValue() + "개");
			}
	}

	public static void countFruits() // 날짜별 과일 갯수 세기
	{
        for (Map.Entry<LocalDate, ArrayList<ArrayList<String>>> entry : pdHuruList.entrySet()) 
			{
				LocalDate date = entry.getKey();
				ArrayList<ArrayList<String>> huruListForDate = entry.getValue();
				Map<String, Integer> fruitCounts = new HashMap<>();

				for (ArrayList<String> huru : huruListForDate) 
				{
					for (String fruit : huru) 
					{
						fruitCounts.put(fruit, fruitCounts.getOrDefault(fruit, 0) + 1);
					}
				}	

				
				System.out.println(date + ":");
				for (Map.Entry<String, Integer> fruitCountEntry : fruitCounts.entrySet()) 
				{
					System.out.println(fruitCountEntry.getKey() + ": " + fruitCountEntry.getValue());
				}
			}
    }

	public static void plusAddCount1() // 총 과일 갯수 세기
	{
		//Map<String, Integer> fruitCounts = new HashMap<>();

		for (Map.Entry<Integer, ArrayList<String>> entry : totalPdHuruList.entrySet()) 
			{
				ArrayList<String> fruits = entry.getValue();
				for (String fruit : fruits) 

				{
					fruitCounts.put(fruit, fruitCounts1.getOrDefault(fruit, 0) + 1);
				}
			}

		for (Map.Entry<String, Integer> entry : fruitCounts1.entrySet()) 
			{
				System.out.println(entry.getKey() + ": " + entry.getValue() + "개");
			}
	}
	public static void countTotalFruits() {
    Map<String, Integer> fruitCounts = new HashMap<>();

    for (ArrayList<ArrayList<String>> huruListForDate : pdHuruList.values()) {
        for (ArrayList<String> huru : huruListForDate) {
            for (String fruit : huru) {
                fruitCounts.put(fruit, fruitCounts.getOrDefault(fruit, 0) + 1);
            }
        }
    }

    // Print total fruit counts
	System.out.println();
    System.out.println("누적 :");
    for (Map.Entry<String, Integer> fruitCountEntry : fruitCounts.entrySet()) {
        System.out.println(fruitCountEntry.getKey() + ": " + fruitCountEntry.getValue());
    }
}

// 과일 조합 판매 개수 올라가는 함수 // 관리자모드에서만 볼 수 있음 

public static void typefru() 
{
    System.out.println("과일 조합별 판매 개수:");
    HashMap<String, Integer> fruitTypeCount = new HashMap<>();

    for (Map.Entry<LocalDate, ArrayList<ArrayList<String>>> entry : pdHuruList.entrySet()) 
	{
        LocalDate date = entry.getKey();
        System.out.println("");
        System.out.println(date + " 판매 갯수 " + entry.getValue().size() + "개");

        for (ArrayList<String> huru : entry.getValue()) 
		{
            
            Collections.sort(huru);
            ArrayList<String> uniqueFruits = new ArrayList<>(new HashSet<>(huru));
            String key = String.join(", ", uniqueFruits);

            
            fruitTypeCount.put(key, fruitTypeCount.getOrDefault(key, 0) + 1);
        }
    }

    
    for (Map.Entry<String, Integer> entry : fruitTypeCount.entrySet()) {
        System.out.println(entry.getKey() + " " + entry.getValue() + "개");
    }
}






	
	public static void main(String [] args) // 테스트용 메인 함수  
	{
		/*
		ShopingCart sh = new ShopingCart();
		Pay ppay = new Pay();
		ppay.setShopping(sh);
		
		ppay.paymenu();

*/
		
		
	}
	
}//class end   