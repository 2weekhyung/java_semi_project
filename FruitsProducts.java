/*
[과일 객체의 구성요소]
*/
import java.io.Serializable;
class FruitsProducts implements Serializable
{
	private String name;	// 이름
	private int price;		// 가격
	private int stock;		// 재고
	
	private int size;		// 사이즈
	private int count;      //-- 계산 카운터 /결제시 0 초기화 
	private int	addCount;   //-- 과일 누적 카운터 /결제시 카운트만큼 누적

	// ★ new!! ★
	private int cTemp;		//-- 초기화시 누적 카운트 감소를 위한 변수 
	private int sTemp;		// 초기화시 재고 증가를 위한 임시 변수

	private int tempC;
	private int tempS;

	FruitsProducts(String name, int price, int stock, int size)
	{
		this.name = name;
		this.price = price;
		this.stock = stock;
		this.size = size;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	public void setPrice(int price)
	{
		this.price = price;
	}
	public void setStock(int stock)
	{
		this.stock = stock;
	}
	public void setSize(int size)
	{
		this.size = size;
	}
	public void setAddCount(int addCount)
	{
		this.addCount = addCount;
	}

	// ★ new!! ★
	public void setCTemp(int cTemp)
	{
		this.cTemp = cTemp;
	}
		public void setSTemp(int sTemp)
	{
		this.sTemp = sTemp;
	}

		// ★ new!! new!!  ★
	public void setTempC(int tempC)
	{
		this.tempC = tempC;
	}
	public void setTempS(int tempS)
	{
		this.tempS = tempS;
	}


	public String getName()
	{
		return name;
	}
	public int getPrice()
	{
		return price;
	}
	public int getStock()
	{
		return stock;
	}
	public int getSize()
	{
		return size;
	}
	public int getAddCount()
	{
		return addCount;
	}

	// ★ new!! ★
	public int getCTemp()
	{
		return cTemp;
	}	
	public int getSTemp()
	{
		return sTemp;
	}

		// ★ new!! new!!  ★
	public int getTempC()
	{
		return tempC;
	}	
	public int getTempS()
	{
		return tempS;
	}
}