package classes;
import utils.*;
import interfaces.IOrganize;

import java.util.ArrayList;
import java.util.Date;

public class ClassPeriod extends PlanningPeriod implements IOrganize{
	
    private ArrayList<Student> maleStudents;
    //Pense trabajar a las estudiantes por separado igual como a los profesores, me parece mas facil
    private ArrayList<Student> femaleStudents;
    //Esto es para saber a quienes les toca hacer la guardia en fin de semana, si a estudiantes o a trabajadors
    
    private boolean lastPersonWorker;
    
    private ArrayList<Worker> workers;
    private ArrayList<Student> absent;
    
    private ArrayList<Person> noActivePeople;
    
    private Person lastPersonHolidayMale;
    private Person lastPersonHolidayFemale;
    private Person lastPersonHolidayWorker1;
    private Person lastPersonHolidayWorker2;
    private Holiday holidays;

    public ClassPeriod(Date start, Date end, ArrayList<Person> personList){
        super(start, end);        
        this.absent = new ArrayList<>();
        this.maleStudents = new ArrayList<>();
        this.femaleStudents = new ArrayList<>();
        this.workers = new ArrayList<>();
        this.noActivePeople = new ArrayList<>();
        
        this.lastPersonWorker = false;
        holidays = Holiday.getInstance();

        split(personList);
        organize(start, end);  

    }

    public int countAbsent(){
        return absent.size();

    }
    //----------------------------------------------------------------------------------------------------------
    
    //Splitea la lista completa de personas en trabajadores y estudiantes

    protected void split(ArrayList<Person> personList){
        if(personList.size() == 0)
            throw new IllegalArgumentException("Lista vacía, no se puede planificar guardia sin personal");
        
        for(Person p: personList){
        	if(p.isActive())
        		if(p instanceof Student){
        			if(p.getSex() == Genre.MALE)
        				maleStudents.add((Student) p);
        			else
        				femaleStudents.add((Student) p);
        		}
        		else
        			workers.add((Worker) p);
        	else
        		noActivePeople.add(p);
        }
    }



    @Override
    public void match(Person person, Date date, Schedule schedule){
    	Asignment asignment = new Asignment(person, date, schedule);
    	asignments.add(asignment);
    }
    
    //Metodo de organizacion----------------------------------------------------------------------------------
    
    private void asignMaleStudent(Date day){
    	Person aux;
    	boolean check = false;
    	for(int i = 0; i < maleStudents.size() && !check; i++){
    		aux = maleStudents.get(i);
    		if(holidays.isHoliday(day) && lastPersonHolidayMale != aux){
    			aux = maleStudents.get(0);
				match(aux, day, Schedule.MALE_STUDENT_SCHEDULE);
				maleStudents.remove(aux);
				maleStudents.add((Student)aux);
				lastPersonHolidayMale = maleStudents.get(i);
				check = true;
    		}
    		else if(!holidays.isHoliday(day)){
    			aux = maleStudents.get(0);
    			match(aux, day, Schedule.MALE_STUDENT_SCHEDULE);
				maleStudents.remove(aux);
				maleStudents.add((Student)aux);
				check = true;
    		}
    	}
    }
    
    private void asignFemaleStudent(Date day){
    	Person aux;
    	boolean check = false;
    	
    	for(int i = 0; i < femaleStudents.size() && !check; i++){
    		aux = femaleStudents.get(i);
    		if(holidays.isHoliday(day) && lastPersonHolidayFemale != aux){
    			aux = femaleStudents.get(0);
<<<<<<< Updated upstream
<<<<<<< Updated upstream
				match(aux, day, Schedule.FEMALE_STUDENT_SCHEDULE);
=======
				match(aux, day, Schedule.MALE_STUDENT_SCHEDULE);
>>>>>>> Stashed changes
=======
				match(aux, day, Schedule.MALE_STUDENT_SCHEDULE);
>>>>>>> Stashed changes
				lastPersonHolidayMale = femaleStudents.get(i);
				check = true;
    		}
    		else if(!holidays.isHoliday(day)){
    			aux = femaleStudents.get(0);
<<<<<<< Updated upstream
<<<<<<< Updated upstream
    			match(aux, day, Schedule.FEMALE_STUDENT_SCHEDULE);
=======
    			match(aux, day, Schedule.MALE_STUDENT_SCHEDULE);
>>>>>>> Stashed changes
=======
    			match(aux, day, Schedule.MALE_STUDENT_SCHEDULE);
>>>>>>> Stashed changes
				check = true;
    		}
    	}
    }
    
    private int asignWorker(Date day, Schedule schedule, int index){
    	Person lastPersonHolidayWorker;
    	if(schedule.equals(Schedule.WORKER_SCHEDULE_1))
    		lastPersonHolidayWorker = lastPersonHolidayWorker1;
    	else
    		lastPersonHolidayWorker = lastPersonHolidayWorker2;
    	
    	Person aux;
    	boolean check = false;
    	for(int i = index; i < workers.size() && !check; i++){
    		aux = workers.get(i);
    		if(holidays.isHoliday(day) && lastPersonHolidayWorker != aux){
<<<<<<< Updated upstream
    			//aux = workers.get(0);
=======
    			aux = workers.get(0);
<<<<<<< Updated upstream
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
				match(aux, day, schedule);
				if(lastPersonHolidayWorker == lastPersonHolidayWorker1)
					lastPersonHolidayWorker1 = workers.get(i);
				else
					lastPersonHolidayWorker2 = workers.get(i);
				
				check = true;
    		}
    		else if(!holidays.isHoliday(day)){
<<<<<<< Updated upstream
    			//aux = workers.get(0);
=======
    			aux = workers.get(0);
<<<<<<< Updated upstream
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
    			match(aux, day, schedule);
				check = true;
    		}
    		index = ++i;
    	}
    	
    	return index;
    }
    
    public void organize(Date start, Date end){

    	//int w = 0;
    	int f = 0;
    	int index = 0;
    	while(start.compareTo(end) < 0){
    		asignMaleStudent(start);
    		if(DateManager.isWeekend(start)){
    			if(!lastPersonWorker && !workers.isEmpty()){
    				index = asignWorker(start, Schedule.WORKER_SCHEDULE_1, index);
    				index = asignWorker(start, Schedule.WORKER_SCHEDULE_2, index);
    				if(index >= workers.size())
    					index = 0;
    					lastPersonWorker = !lastPersonWorker;
    			}
    			else{
    				asignFemaleStudent(start);
    				if(f < femaleStudents.size())
    					f++;
    				else
    					lastPersonWorker = !lastPersonWorker;
    			}
    		}
    		start = new Date(start.getTime() + 86400000);
    	}
    }
    //-----------------------------------------------------------------------------------------------------------

	public void replan(Date pointReference, Person personToChange) {
		organize(pointReference, end);
		
	}
	
	public void replan(Date pointReferenceStart, Date pointReferenceEnd, Person personToChange)
	{
		organize(pointReferenceStart,pointReferenceEnd);
	}
	

}