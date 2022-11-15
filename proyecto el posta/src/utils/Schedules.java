package utils;

public enum Schedules {
	MALE_SCHEDULE("20:00pm - 8:00am"),
    FEMALE_SCHEDULE("8:00am - 20:00pm"),
	WORKER_SCHEDULE_1("9:00am - 14:00pm"),
	WORKER_SCHEDULE_2("14:00pm - 19:00pm");
    
    private String name;
    
    Schedules(String s){
    	name = s;
    }
    
    public String getSchedule(){
    	return name;
    }
}
