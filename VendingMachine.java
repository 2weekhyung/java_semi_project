import java.util.Scanner;
import java.util.Map;
import java.io.IOException;

class VendingMachine implements VmMenu
{	
	int menuSel;
	public final int T_ORDER = 1;			// 주문하기
	public final int T_POPULAR_DISH = 2;	// 인기 TOP3
	public final int T_CART = 3;			// 장바구니
	public final int T_ADMIN_MODE = 4;		// 관리자모드

	private static final String ADMIN_PASSWORD = "minzzi";	// 관리자 모드 진입 비밀번호
	

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
		System.out.println();
		System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("┃  ┏━━━━━━━━━━━━━━━━━━━┓           ┃");
		System.out.println("┃  ┃ ①   주문하기     ┃    ____   ┃"); 
		System.out.println("┃  ┗━━━━━━━━━━━━━━━━━━━┛   ┗━━━┛   ┃");
		System.out.println("┃                                  ┃");
		System.out.println("┃  ┏━━━━━━━━━━━━━━━━━━━┓           ┃");
		System.out.println("┃  ┃ ② ★인기 TOP 3★ ┃           ┃"); 
		System.out.println("┃  ┗━━━━━━━━━━━━━━━━━━━┛           ┃");
		System.out.println("┃                          ▼▼▼  ┃");
		System.out.println("┃  ┏━━━━━━━━━━━━━━━━━━━┓ ┏━━━━━━━┓ ┃");
		System.out.println("┃  ┃ ③   장바구니     ┃ ┃ ______┃ ┃");
		System.out.println("┃  ┗━━━━━━━━━━━━━━━━━━━┛ ┗━━━━━━━┛ ┃");
		System.out.println("┃       ┏━ 〓〓〓━┓                ┃");
		System.out.println("┃       ┃         ┃                ┃");
		System.out.println("┃   ┏┳┳┳╋┳┳┳┳┳┳┳┳┳╋┳┳┳┓            ┃");
		System.out.println("┃   ┣╋╋╋╋╋╋╋╋╋╋╋╋╋╋╋╋╋┫            ┃");
		System.out.println("┃   ┗╋╋╋╋╋╋╋╋╋╋╋╋╋╋╋╋╋┛            ┃");
		System.out.println("┃    ┗╋╋╋╋╋╋╋╋╋╋╋╋╋╋╋┛             ┃");
		System.out.println("┃     ┗┻╋╋╋╋╋╋╋╋╋╋╋┻┛              ┃");
		System.out.println("┃       ┗┻┻┻┻┻┻┻┻┻┛                ┃");
		System.out.println("┣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┫");
	}

	@Override
	public void menuSelect() throws IOException
	{
		Scanner sc = new Scanner(System.in);

		String inputTemp;
		
		boolean mJudge = false;

		do
		{
			System.out.print("  메뉴를 선택해 주세요 ");
			System.out.print("▶ ");
			inputTemp = sc.next();
			System.out.println();

			mJudge = false;

			if (inputTemp.equals("1"))
			{
				menuSel = T_ORDER;
				return;
			}
			
			else if (inputTemp.equals("2"))
			{
				menuSel = T_POPULAR_DISH;
				return;
			}
			
			else if (inputTemp.equals("3"))
			{
				menuSel = T_CART;
				return;
			}

			else if (inputTemp.equals(ADMIN_PASSWORD))
			{
				menuSel = T_ADMIN_MODE;
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
		if (menuSel == T_ORDER)
		{
			// 주문하기

			OrderSel os1 = new OrderSel();
			os1.orderSelect();
		}

		else if (menuSel == T_POPULAR_DISH)
		{
			// 인기 TOP3
			PopularDish pd = new PopularDish();
			pd.pMenuSelect();
		}

		else if (menuSel == T_CART)
		{
			// 장바구니
			ShopingCart sc1 = new ShopingCart();
			sc1.sMenu();
		
		}

		else if (menuSel == T_ADMIN_MODE)
		{	
			// 관리자 모드 진입
			System.out.println("로그인에 성공했습니다.");
			System.out.println("관리자 모드에 진입합니다.");

			AdMain am1 = new AdMain();
			am1.turnOn();
		}
	}

}