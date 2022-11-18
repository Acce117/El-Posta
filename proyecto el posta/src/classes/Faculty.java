package classes;

import interfaces.GeneralState;

import java.util.ArrayList;
import java.util.Date;

import utils.*;

public class Faculty{
	private static Faculty instance;
    private ArrayList<Person> people;
    private ArrayList<PlanningPeriod> periods;

    private Faculty(){
        this.people = new ArrayList<>();
        this.periods = new ArrayList<>();
    }
    
    public static Faculty getInstance(){
    	if(instance == null){
    		instance = new Faculty();
    	}
    	
    	return instance;
    }

    public void planningClassPeriod(Date start, Date end)
    {
    	ClassPeriod newClassPeriod = new ClassPeriod(start,end,people);
    	for(PlanningPeriod i : periods)
    	{
    		PeriodValidator.checkCollisionOnPeriods(i, newClassPeriod);
    	}
        periods.add(newClassPeriod);
        
    }

    public void planningVacationPeriod(Date start, Date end)
    {
        periods.add(new VacationPeriod(start,end));
    }

    public int countAbsent(Date start, Date end)
    {
        int absents = 0;
        if(!periods.isEmpty())
        {
        	for(PlanningPeriod i : periods)
        	{
        		absents += i.countAbsent(start,end);
        	}
        }
        return absents;
    }

    //Determina los trabajadores que hacen guardia en vacaciones
    public ArrayList<Worker> listOfForeignerWorkers()
    {
        ArrayList<Worker> foreignerWorkers = new ArrayList<>();
        for(PlanningPeriod i : periods)
        {
            if(i instanceof VacationPeriod)
            {
                foreignerWorkers.addAll(((VacationPeriod)i).getWorkers());
            }
        }
        return foreignerWorkers;
    }

    public ArrayList<Date> getListOfWatchDays(Person person)
    {
        ArrayList<Date> days = new ArrayList<>();
        FacultyValidator.checkPersonOnWatch(person);
        for(PlanningPeriod i : periods)
        {
            days.addAll(i.getDays(person));
        }
        return days;
    }    

    public void addStudent(String id, String name, Genre sex, StatesStudent state)
    {
        people.add(new Student(id, name, sex, state));
    }

    public void addWorker(String id, String name, Genre sex, GeneralState state, Date day)
    {
        people.add(new Worker(id, name, sex, state, day));
    }
    
    public void replan(Date pointReference, Person observer) 
    {
        for(PlanningPeriod i : periods)
        {
            i.replan(pointReference,observer);
        }
    }

	public ArrayList<Person> getPeople() 
	{
		return people;
	}

	public ArrayList<Student> getStudents() {
		
		ArrayList<Student> students = new ArrayList<>();
		
		for(Person person: people){
			if(person instanceof Student){
				students.add((Student)person);
			}
		}
		return students;
	}

	public ArrayList<Worker> getWorkers() {
		ArrayList<Worker> workers = new ArrayList<>();
		
		for(Person person: people){
			if(person instanceof Worker)
				workers.add((Worker)person);
		}
		return workers;
	}

	public ArrayList<ClassPeriod> getClassPeriods() {
		ArrayList<ClassPeriod> classPeriods = new ArrayList<>();
		
		for(PlanningPeriod period: periods){
			if(period instanceof ClassPeriod){
				classPeriods.add((ClassPeriod)period);
			}
		}
		
		return classPeriods;
	}

	public ArrayList<VacationPeriod> getVacationPeriods() {
		ArrayList<VacationPeriod> vacationPeriods = new ArrayList<>();
		
		for(PlanningPeriod period: periods){
			if(period instanceof VacationPeriod){
				vacationPeriods.add((VacationPeriod)period);
			}
		}
		
		return vacationPeriods;
	}
}