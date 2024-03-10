import java.io.IOException;
import java.time.LocalDate;

public class Main
{	
	public static void main(String[] args) throws IOException
	{	
		Certify cf = new Certify();
		AdMain am = new AdMain(); //CuMain cm = new CuMain(); 판매모드 인스턴스 생성 구문
		
		cf.adCertify();	// 관리자 코드 판별하는 메소드 실행결과가 false라면 프로그램 종료. 즉, 이하 코드 실행 안 됨
						// 실행 결과가 참이라면 이하 코드 실행가능
		Serial.fruitGet();
		Serial.subGet();

		// 정한울 시작 - 오류나면 지우기
		Serial.totahapGet();
		Serial.dailySalesGet();
		Serial.totalPdHuruListGet();
		Serial.pdHuruListGet();
		Serial.fruitCountsGet();

		do
		{
			am.turnOn();
			am.switchSM(); //cm.cuSelect(); 판매모드 실행 구문
			
		}
		while (true);
	}
}