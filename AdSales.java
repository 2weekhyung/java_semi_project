/*
[매출정보 조회]

메인에서 ①을 선택하면 해당 시스템 접근

① 매출정보 조회
 1) 총 매출 정보 조회
 2) 과일별 매출 정보 조회
 3) 조합별(n가지) 매출 정보 조회
 4) 뒤로가기

*메소드 구성*

#1 1) ~ 4) 기능을 선택하는 메소드
#2 
#3 
#4 
*/
import java.util.Scanner;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
import java.io.Serializable;
import java.util.Collections;
import java.util.Calendar;	
import java.util.InputMismatchException;

class AdSales implements Serializable
{	
	public static Map<LocalDate, List<Integer>> dailySales = new HashMap<>();

	int y;
	int m;
	int d;

	int s = 0;
	int s1 = 0;
	int s2 = 0;
	int s3 = 0;
	int totalSale;

	void saleSelect() throws IOException
	{	
		Scanner sc = new Scanner(System.in);
		int number = 0;			// 선택값 저장할 변수
		boolean temp = false;	// 반복문용 불린 변수

		System.out.println("\n\t[매출 및 판매통계]");
		System.out.println("[1] 매출 조회");
		System.out.println("[2] 과일별 판매 갯수 조회");
		System.out.println("[3] 조합별 탕후루 판매 갯수 조회");
		System.out.println("[4] 뒤로가기");

		do	// 사용자가 제대로(1~4 정수형) 입력할때까지 반복
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
				System.out.println("1이상 4이하의 정수를 입력하세요.");
				sc.nextLine();
				temp = true;	// 오류나서 temp true로 바뀌면
			}
		}
		while (temp);			// 제대로 할 때까지 반복
		srGo(number);
	}

	void srGo(int number) throws IOException
	{	
		
		AdMain am3 = new AdMain();

		switch (number)
		{
			case 1 : totalSalesInquiry();break;	// 총 매출 조회
			case 2 : fruitsNumOfSales();break;	// 과일별 매출 조회
			case 3 : combiOfhuru();break;		// 조합별(n가지) 매출 정보 조회
			case 4 : am3.turnOn(); break;	// 뒤로가기

			// 정수형은 입력했는데 1이상 4이하가 아니라면
			default : System.out.println("\n잘못된 선택입니다.");
					System.out.println("1이상 4이하의 정수를 입력하세요.");
					saleSelect();
		}
	}

	void totalSalesInquiry() throws IOException	// 총 매출 조회
	{
		//Pay ppay = new Pay();

		//System.out.println("총매출 " + ppay.gettotahap());
	
		saleCal();

		System.out.println("\n\t[매출 조회]\n");
		
			if (s != 0)
			{
				System.out.printf("오늘의 날짜: %d-%d-%d\n", y,m,d);
				System.out.println();
				System.out.printf("%d-%d-%d 매출: %d\n", y,m,d,s);
				System.out.println();
			}

			else
			{
				System.out.println("해당 날짜에 대한 매출 정보가 없습니다.");
			}
	

		
			if (s1 != 0)
			{		
				System.out.printf("%d-%d-%d 매출: %d\n", y,m,d-1,s1 );
				System.out.println();
			}

			else
			{
				System.out.println("해당 날짜에 대한 매출 정보가 없습니다.");
			}
		
			
			if (s2 != 0)
			{
				System.out.printf("%d-%d-%d 매출: %d\n", y,m,d-2,s2 );
				System.out.println();
			}

			else
			{
				System.out.println("해당 날짜에 대한 매출 정보가 없습니다.");
			}
		
			
			if (s3 != 0)
			{
				
				System.out.printf("%d-%d-%d 매출: %d\n", y,m,d-3,s3 );
				System.out.println();
			}

			else
			{
				System.out.println("해당 날짜에 대한 매출 정보가 없습니다.");
			}

			System.out.println("누적 매출: " + totalSale);
			System.out.println();
	
			saleSelect();
	}
	
	void fruitsNumOfSales() throws IOException // 과일별 갯수 조회
	{
		Pay ppay = new Pay();
		//System.out.println(ppay.fruitCounts);
		
		System.out.println("\n\t[과일별 판매 갯수 조회]\n");

		ppay.countFruits();

		ppay.countTotalFruits();
		saleSelect();
	}

	void combiOfhuru() throws IOException	// 조합별 조회
	{
		Pay.typefru();
		
		saleSelect();
	}

	public void addSale(LocalDate date, int saleAmount) {	// 날짜별 갯수 추가 - pay 에서 추가 중
        
        if (dailySales.containsKey(date)) {
            
            dailySales.put(date, Collections.singletonList(saleAmount));
        } else {
            
            List<Integer> salesList = new ArrayList<>();
            salesList.add(saleAmount);
            dailySales.put(date, salesList);
        }
	}

	public void printSalesByDate() {						// 날짜별 갯수 조회
        for (Map.Entry<LocalDate, List<Integer>> entry : dailySales.entrySet()) {
            LocalDate date = entry.getKey();
            List<Integer> saleAmounts = entry.getValue();
            int totalSaleAmount = 0;

            for (Integer saleAmount : saleAmounts) {
                totalSaleAmount += saleAmount;
            }

            System.out.println("날짜: " + date + ", 총 매출: " + totalSaleAmount);
        }
    }

	public void dateCal()
	{
		Calendar cal = Calendar.getInstance();

		y = cal.get(Calendar.YEAR);
		m = cal.get(Calendar.MONTH)+1;
		d = cal.get(Calendar.DATE);
	}

	public void saleCal()
	{	
		dateCal();

		LocalDate targetDate = LocalDate.of(y, m, d);
		List<Integer> salesForTargetDate = dailySales.get(targetDate);
		
		if (salesForTargetDate != null)
		{
			for (int saleAmount : salesForTargetDate)
			{
				s = saleAmount;
			}
		}
		

		LocalDate targetDate1 = LocalDate.of(y, m, d-1);
		List<Integer> salesForTargetDate1 = dailySales.get(targetDate1);
	
		if (salesForTargetDate1 != null)
		{
			for (int saleAmount : salesForTargetDate1)
			{
				s1 = saleAmount;
			}
		}

		LocalDate targetDate2 = LocalDate.of(y, m, d-2);
		List<Integer> salesForTargetDate2 = dailySales.get(targetDate2);

		if (salesForTargetDate2 != null)
		{
			for (int saleAmount : salesForTargetDate2)
			{
				s2 = saleAmount;
			}
		}

		LocalDate targetDate3 = LocalDate.of(y, m, d-3);
		List<Integer> salesForTargetDate3 = dailySales.get(targetDate3);

		if (salesForTargetDate3 != null)
		{
			for (int saleAmount : salesForTargetDate3)
			{
				s3 = saleAmount;
			}
		}

		totalSale = (s + s1 + s2 + s3);
	}
	
	public void countPrint()
	{	
		Pay ppay = new Pay();

		LocalDate desiredDate = LocalDate.of(2023, 10, 13);


ArrayList<ArrayList<String>> huruListForDesiredDate = ppay.pdHuruList.get(desiredDate);


if (huruListForDesiredDate != null) {
    System.out.println("Values for " + desiredDate + ": " + huruListForDesiredDate);
} else {
    System.out.println("No values found for " + desiredDate);
}
	}
}