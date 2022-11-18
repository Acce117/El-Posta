package utils;

import java.util.Date;

import classes.PlanningPeriod;
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
    	if(start.after(end))
    		throw new IllegalArgumentException("Las fechas estan intercambiadas");
    	int startMonth = start.getMonth();
    	int endMonth = end.getMonth();
    	if(startMonth > endMonth)    	
    		endMonth+=12;
    	
    	if(endMonth-startMonth>DIFFERENCE_BETWEEN_PERIODS)
    		throw new IllegalArgumentException("A lo mucho tiene que haber una diferencia de " + DIFFERENCE_BETWEEN_PERIODS); 
    		
    	
    }
    

    private static boolean between(Date a, Date b, Date c)
    {
    	return ((a.after(b) || a.equals(b)) && (a.before(c) || a.equals(c)));
    }
    /*Colision entre periodos:
     * Dado un periodo A y un periodo B
     * A solapa a B si uno de los limites de A se encuentra dentro de los limites de B
     * Ejemplo:
     * Sea A = [1/11/2022 ; 8/11/2022]
     * Sea B = [5/11/2022 ; 11/11/2022]
     *   5/11/2022 se encuentra entre [1/11/2022 ; 8/11/2022] por tanto A solapa a B
     * La logica seria la misma si B solapa a A
     */
    public static void checkCollisionOnPeriods(PlanningPeriod a, PlanningPeriod b)
    {
    	Date initialDateFirst = a.getStart();
    	Date finalDateFirst = a.getEnd();
    	Date initialDateSecond = b.getStart();
    	Date finalDateSecond = b.getEnd();
    	//Verificar que A no solape a B
    	if(between(initialDateFirst,initialDateSecond,finalDateSecond) || between(finalDateFirst,initialDateSecond,finalDateSecond))
    	{
    		throw new IllegalArgumentException("Se solapan los periodos\n" + "el de [" + a.getStart() + "," + a.getEnd() + "] con [" + b.getStart() + "," + b.getEnd() + "]");
    	}
    	//Verificar que B no solape a A
    	if(between(initialDateSecond,initialDateFirst,finalDateFirst) || between(finalDateSecond,initialDateFirst,finalDateFirst))    	
    	{
    		throw new IllegalArgumentException("Se solapan los periodos\n" + "el de [" + a.getStart() + "," + a.getEnd() + "] con [" + b.getStart() + "," + b.getEnd() + "]");
    	}
    }
    
}
