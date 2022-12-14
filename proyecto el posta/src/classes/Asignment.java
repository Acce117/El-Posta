
package classes;
import java.util.Date;

import utils.Schedule;


public class Asignment{
    private Date day;
    private Schedule schedule;
    private boolean done;
    private Person personOnWantch;

    public Asignment(Person personOnWatch, Date day, Schedule schedule){
        this.day = day;
        setPersonOnWatch(personOnWatch);
        setSchedule(schedule);
    }
    public void setSchedule(Schedule schedule) 
    {
    	this.schedule = schedule;
    }
    
    public void setPersonOnWatch(Person personOnWatch){
        this.personOnWantch = personOnWatch;
    }
    
    public Date getDay() {
		return day;
	}
    
    public void setDone(boolean done){
        this.done = done;
    }

    public boolean isDone(){
        return this.done;
    }


	public void setDay(Date day) {
		this.day = day;
	}

	public Schedule getSchedule() {
		return schedule;
	}
	public Person getPersonOnWatch() {
		return personOnWantch;
	}
}