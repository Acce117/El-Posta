package utils;

import java.util.Date;

import classes.Person;

public class StateObserver extends Observer
{
		
	public StateObserver(Person observable) {
		super(observable);
	}

	public void update(Date pointReference)
	{
		faculty.replan(pointReference);
	}
}
