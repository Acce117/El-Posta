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
    private ArrayList<Asignment> asignments;

    public ClassPeriod(Date start, Date end, ArrayList<Person> personList){
        super(start, end);
        this.maleStudents = new ArrayList<>();
        this.femaleStudents = new ArrayList<>();
        this.workers = new ArrayList<>();
        this.asignments = new ArrayList<>();
        this.lastPersonWorker = false;

        split(personList);
        organize(start, end);

    }
    
    //Metodos para verificar que pueda hacer guardia------------------------------------------------------------------------------------------------------
    /*
    public boolean canMatchMale(Person person){
    	if(((Student)person).getActualState() == Student)
    }
    */
    public boolean canMatch(Person person, Date date){
    	/*
    	boolean check = true;
    	if(person instanceof Student)
    		if(person.getSex() == Genre.MALE)
    			check = canMatchMale(person);
    		else
    			check = canMatchFemale(person);
    	else
    		check = canMatchWorker
    	
    	 */
        return false;
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
            if(p instanceof Student){
                if(p.getSex() == Genre.MALE)
                    maleStudents.add((Student) p);
                else
                    femaleStudents.add((Student) p);
            }
            else
                workers.add((Worker) p);
        }
    }



    @Override
    public void match(Person person, Date date, Schedules schedule){
    	Asignment asignment = new Asignment(person, date, schedule);
    	asignments.add(asignment);
    }
    
    public void organize(Date start, Date end){
    	/*
        if(start.compareTo(end) > 0)
        	throw new IllegalArgumentException("Fecha de inicio y fin no v�lidas, la de fin debe ser despu�s de la de inicio");
        
        Person aux;
        int mS = 0;
        int fS = 0;
        int w = 0;
        while(start.compareTo(end) < 0){
        	if(canMatch(maleStudents.get(mS), start)){
        		match(maleStudents.get(mS), start, Schedules.MALE_SCHEDULE.getSchedule());
        		aux = maleStudents.get(mS);
        		maleStudents.remove(mS);
        		maleStudents.add((Student)aux);
        		if(mS > 0)
                    mS--;
                if(isWeekend(start)){
                    if(!lastPersonWorker){
                        match(workers.get(w++), start, Schedules.WORKER_SCHEDULE_1.getSchedule());
                        match(workers.get(w), start, Schedules.WORKER_SCHEDULE_2.getSchedule());
                        if(w == workers.size()){
                            lastPersonWorker = true;
                            w = 0;
                    	}
                        else
                            w++;
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
        	else{
                mS++;
        	}
        }
    	 */
    }
    public void fullOrganize() {
        // TODO Auto-generated method stub
        
    }


	@Override
	public void replan(Date pointReference) {
		// TODO Auto-generated method stub
		
	}


}