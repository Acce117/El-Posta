package classes;

import java.util.ArrayList;
import java.util.Date;

import utils.Observer;
import utils.Genre;
import utils.PersonalValidator;
import utils.Schedule;
import utils.StateObserver;

public abstract class Person{
	protected ArrayList<Observer> observers = new ArrayList<>();
	protected String id;
    protected String name;
    protected String lastName;
    
    
	protected Genre sex; 

    public Person(String id, String name, String lastName, Genre sex){
        setName(name);
        setLastName(lastName);
        setSex(sex);        
        setId(id);
    }

    public String getId() {
        return id;
    }
    public void setId(String id){
    	//PersonalValidator.checkID(id, sex);
    	this.id = id;
    }
   
    public String getName() {
    	
        return name;
    }

    public void setName(String name){
        
    	//PersonalValidator.checkName(name);
        this.name = name;
    }
    
    public String getLastName() {
    	return lastName;
    }
    
    public void setLastName(String lastName) {
    	this.lastName = lastName;
    }

    public Genre getSex() {
        return sex;
    }

    protected void setSex(Genre sex) {
        this.sex = sex;
    }
    
    public void addObserver(Observer observer){
    	observers.add(observer);
    }
    
    protected void notifyAllObservers(Date pointReference)
    {
        for(Observer i : observers)
        {
            i.update(pointReference);
        }
    }
    public abstract boolean isActive();
    public abstract boolean canMatch(Date newDate, Schedule newSchedule);    
    
    public boolean enabled(Date newDate)
    {
    	boolean check = false;
    	Observer actualObserver;    	
    	for(int i = 0; i < observers.size() && !check; i++)
    	{
    		actualObserver = observers.get(i);
    		if(actualObserver instanceof StateObserver)
    		{
    			check = ((StateObserver)actualObserver).toCount(newDate);
    			//comprueba en cada registro si la fecha no se encuentra entra su fecha de entrada y salida
    			//esta modelado para que la salida sea siempre distinto de null
    			//si la entrada es null es xq no se puede contar con la persona, despues de su fecha de salida
    			//si la fecha de entrada no es null, no se puede contar con la persona entre ese rango de fechas
    		}
    		
    			
    	}
    	return (check && isActive());
    } 
	
	
	public Date lastLog()
	{
		Date last = null;
		StateObserver state = null;
		for(int i = 0; i < observers.size() && state == null; i++)
		{
			if(observers.get(i) instanceof StateObserver)
			{
				state = (StateObserver) observers.get(i);
			}
		}
		if(state != null)
			last = state.maxLog();
		return last;
	}
	
}
