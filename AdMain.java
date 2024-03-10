/*
[관리자 메인메뉴 클래스]

관리자 로그인(코드인증) 성공시 접근할 수 있으며
메인에서 선택하는 기능은 총 4가지, 그 외 세부적인 선택지는 다음과 같다.

① 매출정보 조회
 1) 총 매출 정보 조회
 2) 과일별 매출 정보 조회
 3) 조합별(n가지) 매출 정보 조회
 4) 뒤로가기

② 과일 및 부자재 관리
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

③ 고객용(판매용) 시스템 전환

④ 프로그램 종료

*메소드 구성*

#1 ①~④ 기능을 선택하는 메소드

#2 선택한 기능으로 이동시켜주는 메소드
*/

import java.util.Scanner;
import java.io.IOException;

class AdMain implements VmMenu
{	
	private static final int AD_SALES = 1;		// 매출정보 조회
	private static final int AD_SETTING = 2;		// 과일 및 부자재 관리
	private static final int AD_TOCUSTOM = 3;	// 고객용(판매용) 시스템 전환
	private static final int AD_EXIT = 4;		// 프로그램 종료(관리자만 접근)

	private static int adMenuSel = 0;
	
	@Override
	public void turnOn() throws IOException
	{
		menuDisp();
		menuSelect();
		menuRun();
	}
	
	@Override
	public void menuDisp()
	{
		System.out.println("\n\t[관리자 시스템]");
		System.out.println("[1] 매출 및 판매통계");
		System.out.println("[2] 과일 및 부자재 관리");
		System.out.println("[3] 판매용 시스템 전환");
		System.out.println("[4] 프로그램 종료\n");
	}
	
	@Override
	public void menuSelect() throws IOException
	{
		Scanner sc = new Scanner(System.in);

		String inputTemp;
		
		boolean mJudge = false;

		do
		{
			System.out.println("메뉴를 선택해 주세요");
			System.out.print("▶ ");
			inputTemp = sc.next();
			System.out.println();

			mJudge = false;

			if (inputTemp.equals("1"))
			{
				adMenuSel = AD_SALES;
				return;
			}
			
			else if (inputTemp.equals("2"))
			{
				adMenuSel = AD_SETTING;
				return;
			}
			
			else if (inputTemp.equals("3"))
			{
				adMenuSel = AD_TOCUSTOM;
				return;
			}

			else if (inputTemp.equals("4"))
			{
				adMenuSel = AD_EXIT;
				return;
			}

			else
			{
				System.out.println("올바른 값을 입력해 주세요.");
				System.out.println();
				mJudge = true;
			}
		}
		while (mJudge);
	}
	
	@Override
	public void menuRun() throws IOException
	{
		if (adMenuSel == AD_SALES)
		{
			// 매출정보 조회
			new AdSales().saleSelect();
		}

		else if (adMenuSel == AD_SETTING)
		{
			// 과일 및 부자재 관리
			new AdSetting().setSelect();
		}

		else if (adMenuSel == AD_TOCUSTOM)
		{
			// 고객용(판매용) 시스템 전환
			Serial.fruitSet(); 
			Serial.subSet(); 
			//정한울 시작 - 오류나면 지우기

			Serial.totahapSet();
			Serial.dailySalesSet();
			Serial.totalPdHuruListSet();
			Serial.pdHuruListSet();
			Serial.fruitCountsSet();
			switchSM();

		}

		else if (adMenuSel == AD_EXIT)
		{	
			// 프로그램 종료(관리자만 접근)
			Exit.exit();	
		}
	}

	void switchSM() throws IOException
	{
				Scanner sc = new Scanner(System.in);
				String inputTemp1;
				
				System.out.println("\t[판매모드]");
				System.out.println();

				System.out.println("판매 준비가 완료 되었습니까? (Y/N)");
				System.out.print("▶ ");
				inputTemp1 = sc.next();

				if (inputTemp1.equals("Y") || inputTemp1.equals("y"))
				{
					new VendingMachine().turnOn();	
				}
				else
				{
					turnOn();
				}
	}
}