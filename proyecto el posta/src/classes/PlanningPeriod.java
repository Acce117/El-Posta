package classes;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public abstract class PlanningPeriod {

    protected Date start;
    protected Date end;
    
    protected ArrayList<Asignment> asignments;

    public PlanningPeriod(Date start, Date end)
    {
        setStart(start);
        setEnd(end);
        
    }
    public abstract boolean canMatch(Person person, Date date);

    public abstract void match(Person actualPerson, Date actualDate, String schedule);


    public void setStart(Date start) 
    {
        if(start == null)
            throw new IllegalArgumentException("Fecha vacia");
        this.start = start;
        
    }

    public void setEnd(Date end) 
    {
        if(end == null)
            throw new IllegalArgumentException("Fecha vacia");
        if(start.after(end))
        	throw new IllegalArgumentException("El rango esta invertido");
        this.end = end;
    }
    
    public Date getStart() 
    {
    	return start;
    }
    
    public Date getEnd() 
    {
    	return end;
    }
    
    public ArrayList<Date> getDays(Person watcher)
    {
        ArrayList<Date> days = new ArrayList<>();
        return days;
    }
    
    public abstract void addAsignment();    
        
    public ArrayList<Asignment> getAsignments() 
    {
    	return asignments;
    }
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

    public int countAbsent()
    {
        int absents = 0;
        //TODO
        return absents;
    }
    public abstract void replan(Date pointReference);

}

