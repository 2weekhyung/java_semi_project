/*
[프로그램 종료 클래스]
*/
class Exit
{
	public static void exit()
	{	
		Serial.fruitSet();
		Serial.subSet();
		
		// 정한울 시작 - 오류나면 지우기
		Serial.totahapSet();
		Serial.dailySalesSet();
		Serial.totalPdHuruListSet();
		Serial.pdHuruListSet();
		Serial.fruitCountsSet();

		System.out.println("\n프로그램을 종료합니다.");
		System.exit(-1);
	}
}