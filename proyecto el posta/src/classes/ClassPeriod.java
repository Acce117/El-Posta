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
    private ArrayList<Asignment> asignments;

    public ClassPeriod(Date start, Date end, ArrayList<Person> personList){
        super(start, end);
        
        this.absent = new ArrayList<>();
        this.maleStudents = new ArrayList<>();
        this.femaleStudents = new ArrayList<>();
        this.workers = new ArrayList<>();
        
        this.asignments = new ArrayList<>();
        this.noActivePeople = new ArrayList<>();
        
        this.lastPersonWorker = false;

        split(personList);
        organize(start, end);

    }
    
    //Metodos para verificar que pueda hacer guardia------------------------------------------------------------------------------------------------------

    public boolean canMatch(Person person){
    	boolean check = false;
    	if(person instanceof Student){
    		if(((Student)person).getActualState() == StatesStudent.ACTIVE)
    			check = true;
    	}
    	else
    		if(((Worker)person).getActualState() == StatesWorker.ACTIVE)
    			check = true;
    	
        return check;
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
        	if(canMatch(p))
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



    
    public void match(Person person, Date date, String schedule){
    	Asignment asignment = new Asignment(person, date, schedule);
    	asignments.add(asignment);
    }
    
    //Metodo de organizacion----------------------------------------------------------------------------------
    public void organize(Date start, Date end){
    	Person aux;
    	int w = 0;
    	int fS = 0;
    	
    	while(start.compareTo(end) < 0){
    		aux = maleStudents.get(0);
    		match(aux, start, Schedules.MALE_SCHEDULE.getSchedule());
    		maleStudents.remove(aux);
    		maleStudents.add((Student)aux);
    		
    		if(isWeekend(start)){
    			if(!lastPersonWorker){
                    match(workers.get(w++), start, Schedules.WORKER_SCHEDULE_1.getSchedule());
                    if(w < workers.size())
                    	match(workers.get(w++), start, Schedules.WORKER_SCHEDULE_2.getSchedule());
                    
                    if(w == workers.size() - 1){
                        lastPersonWorker = true;
                        w = 0;
                	}
                }
                else{
                    match(femaleStudents.get(fS), start, Schedules.FEMALE_SCHEDULE.getSchedule());
                    if(fS == femaleStudents.size()){
                        lastPersonWorker = false;
                        fS = 0;
                    }
                    else
                        fS++;
                }
    		}
    		
    		start = new Date(start.getTime() + 86400000);
    	}
    }    
    //-----------------------------------------------------------------------------------------------------------

	public void replan(Date pointReference) {
		organize(pointReference, end);
		
	}

}