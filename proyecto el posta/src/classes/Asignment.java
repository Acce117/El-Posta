
package classes;
import java.util.Date;

import utils.Schedule;


public class Asignment{
    

	private Date day;
    private boolean done;
    private Schedule schedule;
    

    private Person personOnWatch;


	public Asignment(Person personOnWatch, Date day, Schedule schedule){
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

    private boolean validateSchedule(Schedule schedule){
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

	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}
    

}