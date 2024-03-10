import java.io.*;
import java.util.*;
class Serial
{															// ★★★여기 수정하세요★★★
	static File f0 = new File(System.getProperty("user.dir"),"\\data\\FruitsInfo.ser");
	static File f1 = new File(System.getProperty("user.dir"),"\\data\\SubsInfo.ser");
	static File f2 = new File(System.getProperty("user.dir"),"\\data\\totahapInfo.ser");
	static File f3 = new File(System.getProperty("user.dir"),"\\data\\dailySalesInfo.ser");
	static File f4 = new File(System.getProperty("user.dir"),"\\data\\totalPdHuruListInfo.ser");
	static File f5 = new File(System.getProperty("user.dir"),"\\data\\pdHuruListInfo.ser");
	static File f6 = new File(System.getProperty("user.dir"),"\\data\\fruitCountsInfo.ser");

	
	@SuppressWarnings("unchecked")
	public static void main(String[] args)
	{

		fruitSet();
		Sub.set();
		subSet();
		
		// 정한울 시작 오류나면 지우기
		totahapSet();
		dailySalesSet();
		totalPdHuruListSet();
		pdHuruListSet();
		fruitCountsSet();
	}
	
	@SuppressWarnings("unchecked")
	static void fruitSet()
	{
		try
		{
			FileOutputStream fos = new FileOutputStream(f0);
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			ObjectOutputStream oos = new ObjectOutputStream(fos);

			oos.writeObject(Fruits.fruits);
			oos.close();
			fos.close();
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	@SuppressWarnings("unchecked")
	static void subSet()
	{
		try
		{
			FileOutputStream fos= new FileOutputStream(f1);
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			ObjectOutputStream oos = new ObjectOutputStream(fos);

			oos.writeObject(Sub.sub);
			oos.close();
			fos.close();
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	static void fruitGet()
	{
		if (f0.exists())
		{
			try
			{
				FileInputStream fis = new FileInputStream(f0);
				ObjectInputStream ois = new ObjectInputStream(fis);
				Object obj = ois.readObject();
				ois.close();
				fis.close();
	
				Fruits.fruits = (HashMap)obj;
			}
			catch (Exception e)
			{
				System.out.println(e);
			}
		}
	}

	@SuppressWarnings("unchecked")
	static void subGet()
	{
		if (f1.exists())
		{
			try
			{
				FileInputStream fis = new FileInputStream(f1);
				ObjectInputStream ois = new ObjectInputStream(fis);
				Object obj = ois.readObject();
				ois.close();
				fis.close();
	
				Sub.sub = (HashMap)obj;
			}
			catch (Exception e)
			{
				System.out.println(e);
			}
		}
	}
	
	// - 정한울 시작 오류나면 지우기
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@SuppressWarnings("unchecked")
		static void totahapSet()	// totahap(총매출) setter
		{	
			Pay py4 = new Pay();

			try
			{
				FileOutputStream fos = new FileOutputStream(f2);
				BufferedOutputStream bos = new BufferedOutputStream(fos);
				ObjectOutputStream oos = new ObjectOutputStream(fos);

				oos.writeObject(py4.gettotahap());
				oos.close();
				fos.close();
			}
		
			catch (Exception e)
			{
				System.out.println(e);
			}
		}

	@SuppressWarnings("unchecked")
	static void totahapGet()	// totahap(총매출) getter
	{	
		Pay py4 = new Pay();

		if (f2.exists())
		{
			try
			{
				FileInputStream fis = new FileInputStream(f2);
				ObjectInputStream ois = new ObjectInputStream(fis);
				Object obj = ois.readObject();
				ois.close();
				fis.close();
	
				py4.settotahap((Integer)obj);
			}
			catch (Exception e)
			{
				System.out.println(e);
			}
		}
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@SuppressWarnings("unchecked")
		static void dailySalesSet()	// dailySales(일별 매출) setter
		{	

			try
			{
				FileOutputStream fos = new FileOutputStream(f3);
				BufferedOutputStream bos = new BufferedOutputStream(fos);
				ObjectOutputStream oos = new ObjectOutputStream(fos);

				oos.writeObject(AdSales.dailySales);
				oos.close();
				fos.close();
			}
		
			catch (Exception e)
			{
				System.out.println(e);
			}
		}

	@SuppressWarnings("unchecked")
	static void dailySalesGet()	// dailySales(일별 매출) getter
	{	

		if (f3.exists())
		{
			try
			{
				FileInputStream fis = new FileInputStream(f3);
				ObjectInputStream ois = new ObjectInputStream(fis);
				Object obj = ois.readObject();
				ois.close();
				fis.close();
	
				AdSales.dailySales = (Map)obj;
			}
			catch (Exception e)
			{
				System.out.println(e);
			}
		}
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@SuppressWarnings("unchecked")
		static void totalPdHuruListSet()	// totalPdHuruList(결제 완료된 총 탕후루 리스트) setter
		{	

			try
			{
				FileOutputStream fos = new FileOutputStream(f4);
				BufferedOutputStream bos = new BufferedOutputStream(fos);
				ObjectOutputStream oos = new ObjectOutputStream(fos);

				oos.writeObject(Pay.totalPdHuruList);
				oos.close();
				fos.close();
			}
		
			catch (Exception e)
			{
				System.out.println(e);
			}
		}

		@SuppressWarnings("unchecked")
	static void totalPdHuruListGet()	// totalPdHuruList(결제 완료된 총 탕후루 리스트) getter
	{	

		if (f4.exists())
		{
			try
			{
				FileInputStream fis = new FileInputStream(f4);
				ObjectInputStream ois = new ObjectInputStream(fis);
				Object obj = ois.readObject();
				ois.close();
				fis.close();
	
				Pay.totalPdHuruList = (HashMap)obj;
			}
			catch (Exception e)
			{
				System.out.println(e);
			}
		}
	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@SuppressWarnings("unchecked")
		static void pdHuruListSet()	// pdHuruList(날짜 별 결제 완료된 총 탕후루 리스트) setter
		{	

			try
			{
				FileOutputStream fos = new FileOutputStream(f5);
				BufferedOutputStream bos = new BufferedOutputStream(fos);
				ObjectOutputStream oos = new ObjectOutputStream(fos);

				oos.writeObject(Pay.pdHuruList);
				oos.close();
				fos.close();
			}
		
			catch (Exception e)
			{
				System.out.println(e);
			}
		}

	@SuppressWarnings("unchecked")
	static void pdHuruListGet()	// pdHuruList(날짜 별 결제 완료된 총 탕후루 리스트) getter
	{	

		if (f5.exists())
		{
			try
			{
				FileInputStream fis = new FileInputStream(f5);
				ObjectInputStream ois = new ObjectInputStream(fis);
				Object obj = ois.readObject();
				ois.close();
				fis.close();
	
				Pay.pdHuruList = (HashMap)obj;
			}
			catch (Exception e)
			{
				System.out.println(e);
			}
		}
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@SuppressWarnings("unchecked")
		static void fruitCountsSet()	// fruitCounts(판매된 과일의 갯수) setter
		{	

			try
			{
				FileOutputStream fos = new FileOutputStream(f6);
				BufferedOutputStream bos = new BufferedOutputStream(fos);
				ObjectOutputStream oos = new ObjectOutputStream(fos);

				oos.writeObject(Pay.fruitCounts);
				oos.close();
				fos.close();
			}
		
			catch (Exception e)
			{
				System.out.println(e);
			}
		}

	@SuppressWarnings("unchecked")
	static void fruitCountsGet()	// fruitCounts(판매된 과일의 갯수) getter
	{	

		if (f6.exists())
		{
			try
			{
				FileInputStream fis = new FileInputStream(f6);
				ObjectInputStream ois = new ObjectInputStream(fis);
				Object obj = ois.readObject();
				ois.close();
				fis.close();
	
				Pay.fruitCounts = (Map)obj;
			}
			catch (Exception e)
			{
				System.out.println(e);
			}
		}
	}



}