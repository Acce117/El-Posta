package utils;

public enum Schedule {
	MALE_STUDENT_SCHEDULE("20:00pm - 8:00am"),
    FEMALE_STUDENT_SCHEDULE("8:00am - 20:00pm"),
	WORKER_SCHEDULE_1("9:00am - 14:00pm"),
	WORKER_SCHEDULE_2("14:00pm - 19:00pm");
    
    private String name;
    
    Schedule(String s){
    	name = s;
    }
    
    public String getSchedule(){
    	return name;
    }
}
