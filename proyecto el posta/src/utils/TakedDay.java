package utils;
import java.util.Date;
import java.util.ArrayList;
public class TakedDay 
{
	private final int TAKE = 2;
	private ArrayList<Date> takedDays;
	private static TakedDay instance;
	
	private TakedDay()
	{
		takedDays = new ArrayList<Date>();
	}
	
	public static TakedDay getInstance()
	{
		if(instance == null)
			instance = new TakedDay();		
		return instance;
	}
	
	public boolean isTaked(Date newDate)
	{
		int count = 0;
		boolean check = false;
		check = (newDate == null);
		for(int i = 0; i < takedDays.size() && !check; i++)
		{
			if((DateManager.sameDate(takedDays.get(i), newDate)))
				count++;
			check = (count>=TAKE);
		}
		
		return check;
	}
	
	public void addDay(Date newDate)
	{
		if(newDate != null && !isTaked(newDate))
			takedDays.add(newDate);
	}
	
	public ArrayList<Date> getTakedDays() 
	{
		return takedDays;
	}
	
	public void refresher()
	{
		takedDays.clear();
	}
	
}
