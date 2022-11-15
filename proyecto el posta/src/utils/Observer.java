package utils;

import classes.Faculty;
import classes.Person;

import java.util.Date;

public abstract class Observer 
{
	protected Faculty faculty;
	
	public Observer(Person observable){
		faculty = Faculty.getInstance();
		observable.addObserver(this);
	}
	
	public abstract void update(Date pointReference);
}
