package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateManager 
{
    public static boolean isWeekend(Date day)
    {
    	boolean check = false;
    	SimpleDateFormat df = new SimpleDateFormat( "dd/MM/yy" );  
        df.applyPattern( "EEE" ); 
        String date= df.format( day );
        if(date.equals("s�b") || date.equals("dom")) 
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
