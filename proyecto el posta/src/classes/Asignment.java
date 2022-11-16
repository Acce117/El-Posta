
package classes;
import java.util.Date;

import utils.Schedules;


public class Asignment{
    

	private Date day;
    private boolean done;
    private Schedules schedule;
    

    private Person personOnWatch;


	public Asignment(Person personOnWatch, Date day, Schedules schedule){
        this.setDay(day);
        this.setPersonOnWatch(personOnWatch);
        this.setSchedule(schedule);
    }
	
	public void setDone(boolean done) {
		this.done = done;
	}

    public void setPersonOnWatch(Person personOnWatch){
        this.personOnWatch = personOnWatch;
    }
    
    public Person getPersonOnWatch() {
    	return personOnWatch;
    }

    private boolean validateSchedule(Schedules schedule){
        boolean check = true;
        
        if(schedule == null)
            check = false;
        
        return check;
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

	public Schedules getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedules schedule) {
		this.schedule = schedule;
	}
    

}