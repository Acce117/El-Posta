package classes;

import java.util.ArrayList;
import java.util.Date;

import utils.Genre;
import utils.StatesStudent;
import utils.StatesWorker;

public class Worker extends Person{
    private Date comebackDate;

    protected StatesWorker actualState;



    public Worker(String id, String name, Genre sex, StatesWorker newState, Date comebackDate) {
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
        if(actualState == StatesWorker.AWARE)
            this.comebackDate = comebackDate;
        else
            this.comebackDate = null;
    }
    
    public void setActualState(StatesWorker newState, Date changeState)
    {
    	boolean repeat = (actualState == newState);
    	if(!repeat)
    	{
    		notifyAllObservers(changeState);
    		 actualState = newState;
    	}
    }

    public StatesWorker getActualState()
    {
        return actualState;
    }

	@Override
	public boolean isActive() 
	{
		return (actualState == StatesWorker.ACTIVE);
	}


}
