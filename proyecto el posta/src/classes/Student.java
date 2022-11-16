package classes;

import java.util.Date;

import utils.Genre;
import utils.StatesStudent;

public class Student extends Person{

    protected StatesStudent actualState;

    public Student(String id, String name, Genre sex, StatesStudent newState) {
        super(id, name, sex);
        setActualState(newState, null);
    }

    public void setActualState(StatesStudent newState, Date changeState)
    {
    	boolean repeat = (actualState == newState);
    	if(!repeat)
    	{
    		notifyAllObservers(changeState);
    		  actualState = newState;
    	}
    }

    public StatesStudent getActualState()
    {
        return actualState;
    }

	@Override
	public boolean isActive() 
	{
		return (actualState == StatesStudent.ACTIVE);
	}
    
}
