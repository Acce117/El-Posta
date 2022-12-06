package utils;

import java.util.ArrayList;
import java.util.Date;

import classes.Person;
import classes.Student;
import classes.Worker;

public class StateObserver extends Observer
{
	private ArrayList<EnterLog> personLog; //TODO hacer los metodos para que esto funcione
	//Se tiene que ir alternando entre fechas de entrada y salida y tener un metodo que verifique el dia
	
	public StateObserver(Person observable) {
		super(observable);
		personLog = new ArrayList<>();
	}

	public void update(Date pointReference, Person observer){
		
		addDate(pointReference, observer);
		faculty.replan(pointReference, observer);		
	}
	
	
	private void addDate(Date newDate, Person observer)
	{
		//Crea un registro nuevo si la lista esta vacia o si esta llena en la ultima posicion
		if(personLog.isEmpty() || personLog.get(personLog.size()-1).isFull())
			personLog.add(new EnterLog());
		
		personLog.get(personLog.size()-1).addDate(newDate, observer);									
	}
	
	public boolean toCount(Date toAdd)
	{
		boolean check = false;
		for(int i = 0; i < personLog.size() && !check; i++)
		{
			check = personLog.get(i).canCount(toAdd);
		}
		return check;
	}
		


}
