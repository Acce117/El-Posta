
package classes;
import java.util.Date;


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
    
    public String getSchedule() {
		return schedule;
	}
}