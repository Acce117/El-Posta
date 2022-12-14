
package classes;
import java.util.Date;

import utils.Schedule;


public class Assignment{
    private Date day;
    private Schedule schedule;
    private boolean fail;
    private Person personOnWantch;

    public Assignment(Person personOnWatch, Date day, Schedule schedule){
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
    
    public void setFail(boolean fail){
        this.fail = fail;
    }

    public boolean isFailed(){
        return this.fail;
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