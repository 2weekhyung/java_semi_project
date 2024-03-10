import java.io.IOException;

interface VmMenu
{
	public void turnOn() throws IOException;		// 메뉴 호출 메소드

	public void menuDisp();		// 메뉴 출력 메소드

	public void menuSelect() throws IOException;  // 메뉴 선택 메소드

	public void menuRun() throws IOException;	// 메뉴 실행 메소드
}