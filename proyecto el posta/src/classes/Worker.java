package classes;

import interfaces.GeneralState;


import java.util.Date;
import utils.DateManager;
import utils.Genre;
import utils.Schedule;
import utils.StatesWorker;
import utils.StatesWorkerWithComebackDate;

public class Worker extends Person{

	protected GeneralState actualState;



    public Worker(String id, String name, Genre sex, GeneralState newState, Date comebackDate) 
    {
        super(id, name, sex);
        setActualState(newState, null);
        setComebackDate(comebackDate);
    }

    public Date getComebackDate() 
    {
    	Date comebackDate = null;
        if(actualState instanceof StatesWorkerWithComebackDate)
            comebackDate = ((StatesWorkerWithComebackDate)(this.actualState)).getCombackDate();
        return comebackDate;
    }

    public void setComebackDate(Date comebackDate) 
    {
        if(actualState instanceof StatesWorkerWithComebackDate)
            ((StatesWorkerWithComebackDate)(this.actualState)).setCombackDate(comebackDate);
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
		return ((actualState instanceof StatesWorkerWithComebackDate) &&(actualState == StatesWorker.ACTIVE));
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
