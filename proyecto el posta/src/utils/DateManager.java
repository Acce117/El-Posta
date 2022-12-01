package utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateManager 
{
    public static boolean isWeekend(Date day)
    {
    	boolean check = false;
    	/*SimpleDateFormat df = new SimpleDateFormat( "dd/MM/yy" );  
        df.applyPattern( "EEE" ); 
        String date= df.format( day );*/
    	GregorianCalendar cal = new GregorianCalendar();
    	cal.setTime(day);
    	int weekDay = cal.get(Calendar.DAY_OF_WEEK);
        //if(date.equals("sáb") || date.equals("dom")) 
        if(weekDay == 7 || weekDay == 1) //Equivalente a sabado y domingo
    	{ 
            check = true;
        } 
    	
    	return check;
    }
    
    public static boolean sameDate(Date start, Date end)
    {
    	return (start.getDate() == end.getDate() && start.getMonth() == end.getMonth() && start.getYear() == end.getYear());
    }
    
    public static boolean betweenDates(Date start, Date end, Date middle)
    {
    	return ((sameDate(start,middle) || middle.after(start)) && (sameDate(middle, end) || middle.before(end)));
    }
    
    
}
