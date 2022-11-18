package classes;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import utils.Schedule;


public abstract class PlanningPeriod {

    protected Date start;
    protected Date end;
    
    protected ArrayList<Asignment> asignments;

    public PlanningPeriod(Date start, Date end)
    {
        setStart(start);
        setEnd(end);
        
    }

    public abstract void match(Person actualPerson, Date actualDate, Schedule schedule);


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
        
    public ArrayList<Asignment> getAsignments() 
    {
    	return asignments;
    }

    public int countAbsent(Date start, Date end)
    {
        int absents = 0; 
        Date actualDate;
        for(Asignment i : asignments)
        {
        	actualDate = i.getDay();
        	if((actualDate.after(start) || actualDate.equals(start)) && ((actualDate.before(end) || actualDate.equals(end)) && !i.isDone()))
        	{
        		absents++;
        	}
        }
        return absents;
    }

    public abstract void replan(Date pointReference, Person changedPerson);

}

