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
        if(date.equals("sáb") || date.equals("dom")) 
        { 
            check = true;
        } 
    	
    	return check;
    }
}
