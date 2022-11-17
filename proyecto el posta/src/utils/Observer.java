package utils;

import classes.Faculty;
import classes.Person;

import java.util.Date;

public abstract class Observer 
{
	protected Person observable;

	protected Faculty faculty;
	
	public Observer(Person observable){
		faculty = Faculty.getInstance();
		setObservable(observable);
	}
	
	public abstract void update(Date pointReference, Person observable);
	
	public Person getObservable() {
		return observable;
	}
	
	public void setObservable(Person observable) {
		this.observable = observable;
		this.observable.addObserver(this);
	}
}
