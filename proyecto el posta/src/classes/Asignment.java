
package classes;
import java.util.Date;

import utils.Schedule;


public class Asignment{
    private Date day;
    private String schedule;
    private boolean done;
    private Person personOnWantch;

    public Asignment(Person personOnWatch, Date day, String schedule){
        this.day = day;
        setPersonOnWatch(personOnWatch);
        setSchedule(schedule);
    }
    public void setSchedule(String schedule) {
    	this.schedule = schedule;
    }
    
    public void setPersonOnWatch(Person personOnWatch){
        this.personOnWantch = personOnWatch;
    }
    
    public Date getDay() {
		return day;
	}
    
    public Person getPersonOnWantch() {
		return personOnWantch;
	}
    
    public void setDone(boolean done){
        this.done = done;
    }

    public boolean isDone(){
        return this.done;
    }

	public Date getDay() {
		return day;
	}

	public void setDay(Date day) {
		this.day = day;
	}

	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}
    
    public String getSchedule() {
		return schedule;
	}
}