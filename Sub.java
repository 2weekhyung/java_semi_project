import java.util.HashMap;
import java.util.Map;

class Sub
{
	public static Map<Integer, SubProducts> sub = new HashMap<Integer, SubProducts>();
	public static void set()
	{
		sub.put(1,new SubProducts("¼³ÅÁ",0,10500));
		sub.put(2,new SubProducts("²¿Ä¡",0,350));
	}
}