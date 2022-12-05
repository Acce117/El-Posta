package utils;

import java.util.Date;

import classes.Person;
import classes.Worker;

//Esta clase va a registrar la entrada y la salida a la facultad de una persona
public class EnterLog 
{
	private Date enter;
	private Date exit;
	
	
	public Date getEnter() {
		return enter;
	}
	
	//Este se llama cuando se hace activo
	private void setEnter(Date enter) {
		if(enter != null)
			this.enter = enter;
	}
	
	public Date getExit() {
		return exit;
	}
	
	//Este se llama cuando se hace inactivo
	private void setExit(Date exit) {
		if(exit != null)
			this.exit = exit;
	}
	
	public boolean isFull()
	{
		return ((enter != null)&&(exit != null));
	}
	
	public boolean addDate(Date newDate, Person newPerson)
	{
		boolean check = !isFull();
		
		if(check)
		{
			if(newPerson.isActive())
				setEnter(newDate);
			else
			{
				setExit(newDate);
				if(newPerson instanceof Worker && ((Worker)newPerson).isOnTravel())
					setEnter(((Worker)newPerson).getComebackDate());
			}
		}
		
		return check;
	}
	
	public boolean canCount(Date toAdd)
	{
		boolean check = !isFull(); //Si se cumple esto puedo contar con cualquier dia
		boolean entry;
		if(!check)
		{
			//check entra como falso
			
			//Vemos si el estado inicial fue Activo o Inactivo
			entry = (enter == null || exit.before(enter));			
			if(entry) //
			{ 
				check = (enter == null);//Checkeo si no tiene fecha de entrada
				if(check)
				{
					//Si la fecha de entrada es null no puedo contar con la persona 
					//desde la fecha de salida en adelante
					check = DateManager.sameDate(exit,toAdd) || toAdd.after(exit);
				}
				else
				{
					//Sino la fecha no se puede encontrar entre
					//la fecha de salida y la de entrada
					check = !DateManager.betweenDates(exit, enter, toAdd);
				}				
			}
			else
			{
				check = (exit == null);
				if(check)
				{
					//Si la fecha de salida es null, puedo contar con la persona a partir de la fecha de
					//entrada
					check = DateManager.sameDate(enter,toAdd) || toAdd.after(enter);
				}
				else
				{
					//Sino la fecha tiene que estar dentro
					check = DateManager.betweenDates(enter, exit, toAdd);
				}	
			}
		}
		
		
		return check;
	}

}
