package utils;

import java.util.Date;
public class PeriodValidator 
{
	private static final int DIFFERENCE_BETWEEN_PERIODS = 6; 
    public static void checkPeriods(Date start, Date end)
    {
    	if(start == null)
    		throw new IllegalArgumentException("Fecha inicial Vacia");
    	if(end == null)
    		throw new IllegalArgumentException("Fecha final Vacia");
    	if(start.equals(end))
    		throw new IllegalArgumentException("Fechas iguales");
    	int startMonth = start.getMonth();
    	int endMonth = end.getMonth();
    	if(startMonth > endMonth)    	
    		endMonth+=12;
    	
    	if(endMonth-startMonth>DIFFERENCE_BETWEEN_PERIODS)
    		throw new IllegalArgumentException("A lo mucho tiene que haber una diferencia de " + DIFFERENCE_BETWEEN_PERIODS); 
    		
    	
    }
}
