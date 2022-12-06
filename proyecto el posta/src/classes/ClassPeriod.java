package classes;
import utils.*;
import interfaces.IOrganize;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ClassPeriod extends PlanningPeriod implements IOrganize{
	
    private ArrayList<Student> maleStudents;
    //Pense trabajar a las estudiantes por separado igual como a los profesores, me parece mas facil
    private ArrayList<Student> femaleStudents;
    //Esto es para saber a quienes les toca hacer la guardia en fin de semana, si a estudiantes o a trabajadors
    
    private boolean lastPersonWorker;
    
    private ArrayList<Worker> workers;
    private ArrayList<Student> absent;
    
    private ArrayList<Person> noActivePeople;
    

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
    	Assignment asignment = new Assignment(person, date, schedule);
    	int asignmentIndex = asignments.indexOf(asignment);
    	if(asignmentIndex == -1)
    		asignments.add(asignment);
    	else
    		asignments.set(asignmentIndex, asignment);

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
				match(aux, day, Schedule.MALE_STUDENT_SCHEDULE);

				lastPersonHolidayMale = femaleStudents.get(i);
				check = true;
    		}
    		else if(!holidays.isHoliday(day)){
    			aux = femaleStudents.get(0);
    			match(aux, day, Schedule.MALE_STUDENT_SCHEDULE);
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
				match(aux, day, schedule);
				if(lastPersonHolidayWorker == lastPersonHolidayWorker1)
					lastPersonHolidayWorker1 = workers.get(i);
				else
					lastPersonHolidayWorker2 = workers.get(i);
				
				check = true;
    		}
    		else if(!holidays.isHoliday(day)){
    			match(aux, day, schedule);
				check = true;
    		}
    		index = ++i;
    	}
    	
    	return index;
    }
    
    public void organize(Date start, Date end){
    	
    	if(!maleStudents.isEmpty() || !femaleStudents.isEmpty() || !workers.isEmpty()){
        	//int w = 0;    	
        	int f = 0;
        	int index = 0;
        	while(start.compareTo(end) <= 0){
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
    }
    //-----------------------------------------------------------------------------------------------------------

	public void replan(Date pointReference, Person personToChange){
		clearAsignments(pointReference,end);
		updatePerson(personToChange);
		organize(pointReference, end);		
	}
	
	public void replan(Date pointReferenceStart, Date pointReferenceEnd, Person personToChange){
		clearAsignments(pointReferenceStart,pointReferenceEnd);
		updatePerson(personToChange);
		organize(pointReferenceStart,pointReferenceEnd);
	}

	private void clearAsignments(Date pointReferenceStart,Date pointReferenceEnd){
		
		boolean finished = false;
		for(int i = 0; i < asignments.size() && !finished; i++){
			
			if(DateManager.betweenDates(pointReferenceStart, pointReferenceEnd, asignments.get(i).getDay())){
				asignments.remove(i);
				i--;
			}
		}
		
	}

	private void updatePerson(Person personToChange) {
		if(personToChange.isActive())
			addPerson(personToChange);
		else
			removePerson(personToChange);		
	}

	private void removePerson(Person personToChange) {
		
		int index;
		
		if(personToChange instanceof Worker){
			index = workers.indexOf(personToChange);
			if(index != -1)
				workers.remove(index);
			
		}else{
			
			if(personToChange.getSex() == Genre.MALE){
				
				index = maleStudents.indexOf(personToChange);
				if(index != -1)
					maleStudents.remove(index);
				
			}else{
				
				index = femaleStudents.indexOf(personToChange);
				if(index != -1)
					femaleStudents.remove(index);				
			}						
		}		
	}
	
	private void addPerson(Person personToChange){
		
		if(personToChange instanceof Worker)
			workers.add(0,(Worker) personToChange);		
		else{
			
			if(personToChange.getSex() == Genre.MALE)
				maleStudents.add(0,(Student) personToChange);
			else
				femaleStudents.add(0,(Student) personToChange);
		}
		
	}
	
	

	private int ableStudent(int lastIndex, Date newDate)
	{
		int index = lastIndex;
		int size = maleStudents.size();//Prueba
		
		for(int auxIndex = (index+1)%size; auxIndex != index; auxIndex = (auxIndex+1)%size)
		{
			if(maleStudents.get(auxIndex).canMatch(newDate, null))
			{
				index = auxIndex;
			}
		}
		
		return index;
	}
	
	private int ableFemaleStudent(int lastIndex, Date newDate)
	{
		int index = lastIndex;
		int size = femaleStudents.size();//Prueba
		
		for(int auxIndex = (index+1)%size; auxIndex != index; auxIndex = (auxIndex+1)%size)
		{
			if(femaleStudents.get(auxIndex).canMatch(newDate, null))
			{
				index = auxIndex;
			}
		}
		
		return index;
	}
	
	private int ableWorker(int lastIndex, Date newDate)
	{
		int index = lastIndex;
		int size = workers.size();
		
		for(int auxIndex = (index+1)%size; auxIndex != index; auxIndex = (auxIndex+1)%size)
		{
			if(workers.get(auxIndex).canMatch(newDate, null))
			{
				index = auxIndex;
			}
		}
		
		return index;
	}
	
}
