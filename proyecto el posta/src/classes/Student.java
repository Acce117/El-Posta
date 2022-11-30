package classes;

import java.util.Date;

import utils.DateManager;
import utils.Genre;
import utils.Schedule;
import utils.StatesStudent;

public class Student extends Person{

    protected StatesStudent actualState;

    public Student(String id, String name, String lastName, Genre sex, StatesStudent newState) {
        super(id, name, lastName, sex);
        setActualState(newState, null);
    }

    public void setActualState(StatesStudent newState, Date changeState)
    {
    	boolean repeat = (actualState == newState);
    	if(!repeat)
    	{
    		  actualState = newState;
    		  notifyAllObservers(changeState);
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

	@Override
	public boolean canMatch(Date newDate, Schedule newSchedule) 
	{
		boolean check = true;
		
		if(!isActive() && newSchedule != Schedule.MALE_STUDENT_SCHEDULE && sex == Genre.MALE)
			check = false;
		else if(!isActive() && newSchedule != Schedule.FEMALE_STUDENT_SCHEDULE && sex == Genre.FEMALE && !DateManager.isWeekend(newDate))
			check = false;
		
		return check;
	}
    
}
