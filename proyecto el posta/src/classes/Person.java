package classes;

import java.util.ArrayList;
import java.util.Date;
import utils.Observer;

import utils.Genre;;

public class Person{
	protected ArrayList<Observer> observers = new ArrayList<>();
	protected String id;
    protected String name;
    protected Genre sex; 

    public Person(String id, String name, Genre sex){
        setName(name);
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

    public Genre getSex() {
        return sex;
    }

    protected void setSex(Genre sex) {
        this.sex = sex;
    }
    
    public void addObserver(Observer observer){
    	observers.add(observer);
    }
}
