package classes;

import interfaces.GeneralState;

import java.util.ArrayList;
import java.util.Date;

import utils.DateManager;
import utils.Genre;
import utils.Schedule;
import utils.StatesStudent;
import utils.StatesWorker;
import utils.StatesWorkerWithComebackDate;

public class Worker extends Person{
    private Date comebackDate;

    protected GeneralState actualState;



    public Worker(String id, String name, Genre sex, GeneralState newState, Date comebackDate) 
    {
        super(id, name, sex);
        setActualState(newState, null);
        setComebackDate(comebackDate);
    }

    public Date getComebackDate() 
    {
        return comebackDate;
    }

    public void setComebackDate(Date comebackDate) 
    {
        if(actualState == StatesWorkerWithComebackDate.AWARE)
            this.comebackDate = comebackDate;
        else
            this.comebackDate = null;
    }
    
    public void setActualState(GeneralState newState, Date changeState)
    {
    	boolean repeat = (actualState == newState);
    	if(!repeat)
    	{
    		 actualState = newState;
    		 notifyAllObservers(changeState);
    	}
    }

    public GeneralState getActualState()
    {
        return actualState;
    }

	@Override
	public boolean isActive() 
	{
		return (actualState == StatesWorker.ACTIVE);
	}

	@Override
	public boolean canMatch(Date newDate, Schedule newSchedule) 
	{
		boolean check = true;
		if(!isActive() && !DateManager.isWeekend(newDate) || ((newSchedule != Schedule.WORKER_SCHEDULE_1) && (newSchedule != Schedule.WORKER_SCHEDULE_2)))
			check = false;
		
		return check;		
	}


}
