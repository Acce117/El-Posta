package classes;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import utils.DateManager;
import utils.Schedule;


public abstract class PlanningPeriod {

	protected static Person lastPersonHolidayMale;
	protected static Person lastPersonHolidayFemale;
	protected static Person lastPersonHolidayWorker1;
	protected static Person lastPersonHolidayWorker2;
	protected Date start;
	protected Date end;
	
    protected ArrayList<Asignment> asignments;


	public PlanningPeriod(Date start, Date end)
	{
		setStart(start);
		setEnd(end);
		asignments = new ArrayList<Asignment>();

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
        	if((actualDate.after(start) || actualDate.equals(start)) && ((actualDate.before(end) || actualDate.equals(end)) && !i.isFailed()))
        	{
        		absents++;
        	}
        }
        return absents;
    }

	public Date getStart() 
	{
		return start;
	}

	public Date getEnd() 
	{
		return end;
	}

	public abstract void replan(Date pointReference, Person changedPerson);
	public abstract void replan(Date pointReferenceStart, Date pointReferenceEnd, Person changedPerson);
	

	public Asignment findAsignment(Date toFind, Schedule schedule)
	{
		Asignment assignmentToFind = null;
		boolean find = false;
		
		Asignment actualAssignment;
		for(int i = 0; i < asignments.size() && !find; i++)
		{
			actualAssignment = asignments.get(i);
			if(DateManager.sameDate(actualAssignment.getDay(), toFind) && actualAssignment.getSchedule() == schedule)
			{
					assignmentToFind = asignments.get(i);
					find = true;					
			}
		}
		
		return assignmentToFind;
	}

}

