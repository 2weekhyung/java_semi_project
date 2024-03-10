/*
[과일 관리]

과일 및 부자재 관리에서 1) 을 선택하면 해당 시스템 접근

 1) 과일 관리
  1-1) 과일 신규 등록
  1-2) 과일 정보 변경
  1-3) 과일 정보 삭제
  1-4) 뒤로가기

*메소드 구성*

#1 1-1) ~ 1-3) 기능을 선택하는 메소드

#2 선택한 기능으로 이동시켜주는 메소드

#3 과일 신규 등록 메소드
#3-1 신규 등록 고유번호(키값) 지정 메소드
#3-2 신규 등록 과일 이름 중복 검사 메소드

#4 정보 변경을 원하는 과일을 선택하는 메소드
#4-0 4에서 선택한 과일의 변경 요소로 이동하는 메소드
#4-1 이름변경
#4-2 가격 변경
#4-3 재고 변경
#4-4 사이즈 변경

#5 정보 삭제를 원하는 과일을 선택하는 메소드
#5-1 5에서 선택한 과일의 정보를 삭제하는 메소드

#6 현재 과일 모두 출력하는 메소드
*/
import java.util.Scanner;
import java.io.IOException;

class AdFruit extends AdSetting 
{
	//#1
	void frSelect() throws IOException
	{
		Scanner sc = new Scanner(System.in);
		int number = 0;			// 선택값 저장할 변수
		boolean temp = false;	// 반복문용 불린 변수

		System.out.println("\n\t[과일 관리]");
		System.out.println("[1] 과일 신규 등록");
		System.out.println("[2] 과일 정보 변경");
		System.out.println("[3] 과일 정보 삭제");
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
		frGo(number);
	}// frSelect()

	//#2
	void frGo(int number) throws IOException
	{
		switch (number)
		{
			case 1 : newRegister(); break;	// 신규 등록
			case 2 : edit();break;			// 정보 변경
			case 3 : delete();break;		// 정보 삭제
			case 4 : super.setSelect(); break;	// 뒤로가기 - 상속받은 부모(메인) 선택으로 돌아가기

			// 정수형은 입력했는데 1이상 4이하가 아니라면
			default : System.out.println("\n잘못된 선택입니다.");
					System.out.println("1이상 4이하의 정수를 입력하세요.");
					frSelect();
		}
	}// frGo()

	//#3
	void newRegister() throws IOException
	{
		Scanner sc = new Scanner(System.in);
		int num = findNum();
		boolean temp = false;
		String name="";
		int price,stock,size;
		price=stock=size=0;
		
		// 이름 입력
		System.out.printf("\n고유번호 %d번 과일의 정보를 입력하세요.\n",num);
		System.out.print("과일의 이름 : ");
		name = sc.next();

		if (check(name))
		{
			System.out.printf("\n입력하신 과일 %s는 이미 존재하는 과일입니다.",name);
			System.out.println("과일 정보 변경 시스템을 이용하세요.");
			frSelect();
		}
		else
		{		
			// 가격 입력
			do
			{
				try
				{
					System.out.print(name + "의 가격 : ");
					price = sc.nextInt();
					temp = false;
				}
				catch (Exception e)
				{
					System.out.println("\n※입력 오류※");
					System.out.println("입력한 정보를 확인하고 다시 입력하세요.");
					sc.nextLine();
					temp = true;
				}
			}
			while (temp);
			
			// 재고 입력
			do
			{
				try
				{
					System.out.print(name + "의 재고(최대 50개) : ");
					stock = sc.nextInt();
					if (stock<=50)
						temp = false;
					else
					{
						System.out.println("과일의 최대 재고 50개 입니다. 다시 입력하세요.");
						temp = true;
					}
				}
				catch (Exception e)
				{
					System.out.println("\n※입력 오류※");
					System.out.println("입력한 정보를 확인하고 다시 입력하세요.");
					sc.nextLine();
					temp = true;
				}
			}
			while (temp);

			// 사이즈 입력
			do
			{
				try
				{
					System.out.print(name + "의 사이즈(1 or 2) : ");
					size = sc.nextInt();	//사이즈
					if (size==1||size==2)
						temp = false;
					else
					{
						System.out.println("사이즈는 1 또는 2만 입력이 가능합니다. 다시 입력하세요.");
						temp = true;
					}
				}
				catch (Exception e)
				{
					System.out.println("\n※입력 오류※");
					System.out.println("입력한 정보를 확인하고 다시 입력하세요.");
					sc.nextLine();
					temp = true;
				}
			}
			while (temp);
			
			// 입력한 정보 확인
			System.out.println("\n입력한 과일의 정보는");
			System.out.printf("고유번호 %d번 ",num);
			System.out.print("- 이름[ " + name + " ]");
			System.out.print(" 가격[ " + price + " ]");
			System.out.print(" 재고[ " + stock + " ]");
			System.out.print(" 사이즈[ " + size + " ] ");
			System.out.print("입니다.\n저장하시겠습니까?(Y/N) : ");
			
			// 저장 or 취소
			String answer = sc.next();
			if (answer.equals("y")||answer.equals("Y"))
			{
				Fruits.fruits.put(num,new FruitsProducts(name,price,stock,size));
				System.out.println("\n과일이 정상적으로 등록되었습니다.");
			}
			else
				System.out.println("\n과일 등록을 취소합니다.");
			frSelect();
		}
	}// newRegister()
	
	//#3-1
	int findNum() throws IOException
	{
		int num;
		for (num=1; num<=Fruits.fruits.size(); num++)
		{			   //----------- 직렬화로 받은 객체
			if (!Fruits.fruits.containsKey(num))	// 1 부터 객체에 키값이 있는지 찾기
			{
				break;	// 없으면 반복문 탈출~
			}
		}
		return num;
	}

	//#3-2
	boolean check(String name) throws IOException
	{
		int j=1;
		boolean result = false;
		for (int i=0; i<Fruits.fruits.size();j++)
		{
			if (!Fruits.fruits.containsKey(j))
			{
				continue;
			}
			else
			{
				if (Fruits.fruits.get(j).getName().equals(name))
				{
					result = true;
					break;
				}
				i++;
			}
		}
		return result;
	}

	//#4
	void edit() throws IOException
	{
		Scanner sc = new Scanner(System.in);
		int num=-1;
		boolean temp=true;
		printAll();
		do
		{
			try
			{
				System.out.print("\n변경할 과일의 고유번호를 입력하세요.(뒤로가려면 [0] 입력) : ");
				num = sc.nextInt();
				if (Fruits.fruits.containsKey(num))
					temp = false;
				else if (num==0)
					temp = false;
				else
				{
					System.out.println("고유번호를 확인하고 다시 입력하세요.");
					temp = true;
				}
			}
			catch (Exception e)
			{
				System.out.println("\n※입력 오류※");
				System.out.println("고유번호를 확인하고 다시 입력하세요.");
				sc.nextLine();
				temp = true;
			}
		}
		while (temp);
		if (num==0)
			frSelect();
		else
			goEdit(num);
	}
	
	//#4-0
	void goEdit(int num) throws IOException
	{
		Scanner sc = new Scanner(System.in);
		int yoso=-1;
		boolean temp = true;
		System.out.printf("\n선택한 과일 [%s]의 변경할 요소를 선택하세요.\n",Fruits.fruits.get(num).getName());
		System.out.println("\n[1] 이름\n[2] 가격\n[3] 재고\n[4] 사이즈\n[5] 뒤로가기");
		do
		{
			try
			{
				System.out.print("변경할 요소 선택 : ");
				yoso = sc.nextInt();
				temp = false;
			}
			catch (Exception e)
			{
				System.out.println("\n※입력 오류※");
				System.out.println("고유번호를 확인하고 다시 입력하세요.");
				sc.nextLine();
				temp = true;
			}
		}
		while (temp);

		switch (yoso)
		{
			case 1:name(num); break;	//이름
			case 2:price(num);break;	//가격
			case 3:stock(num);break;	//재고
			case 4:size(num);break;		//사이즈
			case 5:edit();break;	//뒤로가기
			default:System.out.println("\n※입력오류※\n1이상 5이하에 해당하는 정수를 입력하세요.");goEdit(num);
		}
	}

	//#4-1
	void name(int num) throws IOException
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("\n선택한 과일의 새롭게 변경할 이름을 입력하세요.");
		System.out.printf("현재 이름 [%s] → 변경할 이름 : ",Fruits.fruits.get(num).getName());
		String newName = sc.next();
		
		//이름이 중복이면
		if (check(newName))
		{
			System.out.println("\n입력하신 이름의 과일이 이미 존재합니다.");
			System.out.println("과일 선택으로 돌아갑니다.");
			edit();
		}

		//이름이 중복이 아니면
		else
		{
			System.out.printf("%d번 과일 [%s]의 이름을 [%s]로 변경하시겠습니까?(Y/N) : ",num,Fruits.fruits.get(num).getName(),newName);
			String answer = sc.next();
			if (answer.equals("Y")||answer.equals("y"))
			{
				Fruits.fruits.get(num).setName(newName);
				System.out.println("\n변경이 완료되었습니다.");
				edit();
			}
			else
			{
				System.out.println("/n변경을 취소합니다.");
				edit();
			}
		}
	}

	//#4-2
	void price(int num) throws IOException
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("\n선택한 과일의 새롭게 변경할 가격을 입력하세요.");	
		int newPrice=0;
		boolean temp = true;

		do
		{
			try
			{
				System.out.printf("현재 가격 [%d원] → 변경할 가격 : ",Fruits.fruits.get(num).getPrice());
				newPrice = sc.nextInt();
				if (newPrice<0)
				{
					System.out.println("0 이상의 정수형태로 다시 입력하세요.");
					temp = true;
				}
				else
					temp = false;
			}
			catch (Exception e)
			{
				System.out.println("\n※입력 오류※");
				System.out.println("0 이상의 정수형태로 다시 입력하세요.");
				sc.nextLine();
				temp = true;
			}
		}
		while (temp);

		System.out.printf("%d번 과일 [%s]의 가격을 [%d원 → %d원]으로 변경하시겠습니까?(Y/N) : ",num,Fruits.fruits.get(num).getName(),Fruits.fruits.get(num).getPrice(),newPrice);
		String answer = sc.next();
		if (answer.equals("Y")||answer.equals("y"))
		{
			Fruits.fruits.get(num).setPrice(newPrice);
			System.out.println("\n변경이 완료되었습니다.");
			edit();
		}
		else
		{
			System.out.println("/n변경을 취소합니다.");
			edit();
		}
	}

	//#4-3
	void stock(int num) throws IOException
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("\n선택한 과일의 변경할 재고를 입력하세요.");	
		int su=0;
		int oldStock=Fruits.fruits.get(num).getStock();
		boolean temp = true;

		do
		{
			try
			{
				System.out.println("\n재고 추가 - 양수 입력 / 재고 감소 - 음수 입력");
				System.out.printf("현재 재고 [%d개] → 변경할 재고 : ",oldStock);
				su = sc.nextInt();
				if (su<0)
				{
					if (oldStock+su<0)
					{
						System.out.println("재고는 0 미만이 될 수 없습니다.");
						temp = true;
					}
					else
						temp = false;
				}
				else
				{
					if (su>50)
					{
						System.out.println("재고는 50이하로만 입력할 수 있습니다.");
						temp = true;
					}
					else if (oldStock+su>50)
					{
						System.out.println("재고는 50개를 초과 할 수 없습니다.");
						temp = true;
					}
					else
						temp = false;
				}
			}
			catch (Exception e)
			{
				System.out.println("\n※입력 오류※");
				System.out.println("50이하의 숫자형태로 입력할 수 있습니다.");
				sc.nextLine();
				temp = true;
			}
		}
		while (temp);

		System.out.printf("%d번 과일 [%s]의 재고 [%d개 → %d개]로 변경하시겠습니까?(Y/N) : ",num,Fruits.fruits.get(num).getName(),oldStock,oldStock+su);
		String answer = sc.next();
		if (answer.equals("Y")||answer.equals("y"))
		{
			Fruits.fruits.get(num).setStock(oldStock+su);
			System.out.println("\n변경이 완료되었습니다.");
			edit();
		}
		else
		{
			System.out.println("/n변경을 취소합니다.");
			edit();
		}
	}

	//#4-4
	void size(int num) throws IOException
	{
		Scanner sc = new Scanner(System.in);
		int newSize=0;
		System.out.printf("\n선택한 과일의 사이즈는 %d입니다.",Fruits.fruits.get(num).getSize());
		if (Fruits.fruits.get(num).getSize()==1)
			newSize=2;
		else if (Fruits.fruits.get(num).getSize()==2)
			newSize=1;

		System.out.printf("%d번 과일 [%s]의 사이즈를 %d로 변경하시겠습니까?(Y/N) : ",num,Fruits.fruits.get(num).getName(),newSize);
		String answer = sc.next();
		if (answer.equals("Y")||answer.equals("y"))
		{
			Fruits.fruits.get(num).setSize(newSize);
			System.out.println("\n변경이 완료되었습니다.");
			edit();
		}
		else
		{
			System.out.println("/n변경을 취소합니다.");
			edit();
		}
	}

	//#5
	void delete() throws IOException
	{
		Scanner sc = new Scanner(System.in);
		int num=-1;
		boolean temp=true;
		printAll();
		do
		{
			try
			{
				System.out.print("\n삭제할 과일의 고유번호를 입력하세요. (뒤로가려면 [0] 입력) : ");
				num = sc.nextInt();
				if (num==0)
					break;
				else
				{
					if (Fruits.fruits.containsKey(num))
					temp = false;

					else
					{
						System.out.println("고유번호를 확인하고 다시 입력하세요.");
						temp =true;
					}
				}
				
			}
			catch (Exception e)
			{
				System.out.println("\n※입력 오류※");
				System.out.println("고유번호를 확인하고 다시 입력하세요.");
				sc.nextLine();
				temp = true;
			}
		}
		while (temp);
		if (num==0)
			frSelect();
		else
			goDelete(num);
	}

	//#5-1
	void goDelete(int num) throws IOException
	{
		Scanner sc = new Scanner(System.in);
		String name = Fruits.fruits.get(num).getName();
		int price = Fruits.fruits.get(num).getPrice();
		int stock = Fruits.fruits.get(num).getStock();
		int size = Fruits.fruits.get(num).getSize();

		System.out.println("선택하신 과일의 정보는");		
		System.out.printf("%2d번 - [%s] %5d원 %3d개 (size %d)",num,name,price,stock,size);
		System.out.print("입니다.\n해당 과일의 정보를 삭제하시겠습니까?(Y/N) : ");

		String answer = sc.next();
		if (answer.equals("Y")||answer.equals("y"))
		{
			Fruits.fruits.remove(num);
			System.out.println("\n삭제가 완료되었습니다.");
			frSelect();
		}
		else
		{
			System.out.println("\n삭제를 취소합니다.");
			frSelect();
		}
	}

	//#6
	void printAll() throws IOException
	{
		Scanner sc = new Scanner(System.in);
		System.out.printf("\n현재 등록된 과일은 %d개 입니다.\n",Fruits.fruits.size());
		for (int i=1; i<=Fruits.fruits.size(); )
		{
			for (int j=1; Fruits.fruits.size()-i>=0; j++)
			{
				if (Fruits.fruits.containsKey(j))
				{
					String name = Fruits.fruits.get(j).getName();
					int price = Fruits.fruits.get(j).getPrice();
					int stock = Fruits.fruits.get(j).getStock();
					int size = Fruits.fruits.get(j).getSize();
					System.out.printf("%2d번 - [%s] %5d원 %3d개 (size %d)",j,name,price,stock,size);
					System.out.println();
					i++;
				}
			}
		}
	}// printAll()
}