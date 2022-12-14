package classes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import utils.*;

public class Faculty{
	private static Faculty instance;
	private ArrayList<Person> people;
	private ArrayList<PlanningPeriod> periods;
	private ArrayList<WorkerWithDates> vacationWatches;

	private Faculty(){
		this.people = new ArrayList<>();
		this.periods = new ArrayList<>();
		vacationWatches = new ArrayList<>();
	}

	public static Faculty getInstance(){
		if(instance == null){
			instance = new Faculty();
		}

		return instance;
	}
	
	private void checkDuplicatePeriod(PlanningPeriod newPeriod)
	{
		for(PlanningPeriod i : periods)
		{
			PeriodValidator.checkCollisionOnPeriods(i, newPeriod);
		}
	}

	public void planningClassPeriod(Date start, Date end){
		ClassPeriod newClassPeriod;
		ArrayList<PlanningPeriod> periodsAux = (ArrayList<PlanningPeriod>) periods.clone();
		ArrayList<Person> peopleAux = new ArrayList<>();
		
		Collections.reverse(periodsAux);
		ClassPeriod aux = null;
		
		for(int i = 0; i < periodsAux.size() && aux == null; i++){
			if(periodsAux.get(i) instanceof ClassPeriod)
					aux = (ClassPeriod)periodsAux.get(i);
		}
		
		if(aux != null)
			peopleAux.addAll(aux.getAbsents());
		
		peopleAux.addAll(people);
		
		newClassPeriod = new ClassPeriod(start, end, peopleAux);
		
		checkDuplicatePeriod(newClassPeriod);
		periods.add(newClassPeriod);
		
		//ArrayList<Assignment> a = periods.get(0).getAsignments();

		/*for(Assignment b: a){
			//System.out.println(b.getPersonOnWatch().getName());
			System.out.println(b.getDay());
		}*/

	}

	public void planningVacationPeriod(Date start, Date end)
	{
		VacationPeriod newVacationPeriod = new VacationPeriod(start,end,vacationWatches);
		checkDuplicatePeriod(newVacationPeriod);
		cleanVacationWatches();
		periods.add(newVacationPeriod);

		
		ArrayList<Asignment> a = periods.get(0).getAsignments();
		
		for(Asignment b: a){
			//System.out.println(b.getPersonOnWatch().getName());
			System.out.println(b.getDay());
		}

	}
	
	public void cleanVacationWatches()
	{
		vacationWatches.clear();
	}

	public ArrayList<WorkerWithDates> getVacationWatches() {
		return vacationWatches;
	}

	//Reporte
	/*public int countAbsent()
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
	}*/

	//Determina los trabajadores que hacen guardia en vacaciones
	//Reporte
	public ArrayList<Worker> listOfVacationWorkers()
	{
		ArrayList<Worker> vacationWorkers = new ArrayList<>();
		for(PlanningPeriod i : periods)
		{
			if(i instanceof VacationPeriod)
			{
				vacationWorkers.addAll(((VacationPeriod)i).getWorkers());
			}
		}
		return vacationWorkers;
	}

	//Reporte
	public ArrayList<Worker> listOfTravelWorkers(){
		ArrayList<Worker> travelWorkers = new ArrayList<>();
		for(Person person: people){
			if(person instanceof Worker){
				if(((Worker)person).isOnTravel())
					travelWorkers.add((Worker)person);
			}
		}
		return travelWorkers;
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

	
	private void addPerson(Person toAdd)
	{
		Person newPerson = toAdd;
		Observer observe = new StateObserver(newPerson);
		people.add(newPerson);
	}

	public void addStudent(String id, String name, String lastName, Genre sex, StatesStudent state)
	{
		addPerson(new Student(id, name, lastName, sex, state));
		
	}
	public void addWorker(String id, String name, String lastName, Genre sex, GeneralState state)
	{
		addPerson(new Worker(id, name, lastName, sex, state));
	}

	public void addWorker(String id, String name, String lastName, Genre sex, GeneralState state, Date day)
	{
		addPerson(new Worker(id, name, lastName, sex, state, day));
	}
	
	public void replan(Date pointReferenceStart, Date pointReferenceEnd, Person observer)
	{
		for(PlanningPeriod i : periods)
		{
			i.replan(pointReferenceStart,observer);
		}
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
	

	public ArrayList<Date> getVacationDays(Worker worker) 
	{
		ArrayList<Date> getVacationDays = new ArrayList<Date>();
		//Veo si el trabajador esta
		boolean found = false;
		Worker actualWorker;
		if(!vacationWatches.isEmpty())
			for(int i = 0; i < vacationWatches.size() && !found; i++)
			{			
				actualWorker = vacationWatches.get(i).getToAsignWorker();
				if(actualWorker.equals(worker))
				{
					//Si esta retorno los dias que estan asignados a ese trabajador
					getVacationDays = vacationWatches.get(i).getListVacationWatch();
					found = true;
				}
			}

		return getVacationDays;
	}


	public void addVacationDate(Worker worker, Date newDate) {
		// TODO Auto-generated method stub
		boolean found = false;
		Worker actualWorker;
		//Ver si ya esta guardado ese trabajador
		if(!vacationWatches.isEmpty())
			for(int i = 0; i < vacationWatches.size() && !found; i++)
			{
				actualWorker = vacationWatches.get(i).getToAsignWorker();
				//si esta guardado se agrega el dia en ese WorkerWithDates
				if(actualWorker.equals(worker))
				{
					vacationWatches.get(i).addVacationWatch(newDate);
					found = true;
				}

			}

		if(!found || vacationWatches.isEmpty())
		{
			WorkerWithDates newWorkerWithDates = new WorkerWithDates(worker);			
			newWorkerWithDates.addVacationWatch(newDate);
			vacationWatches.add(newWorkerWithDates);
		}
	}

	public Person findPerson(String id)
	{
		Person toFind = null;
		for(int i = 0; i < people.size() && (toFind == null); i++)
		{
			if(people.get(i).getId().equals(id))
			{
				toFind = people.get(i);
			}
		}
		return toFind;
	}
	
	public void removePerson(String id) 
	{
		Person toRemove = findPerson(id);
		if(toRemove != null)
		{
			int index = people.indexOf(toRemove);
			if(toRemove instanceof Student)
				((Student)toRemove).setActualState(StatesStudent.DROPPED_OUT, new Date());
			else
				((Worker)toRemove).setActualState(StatesWorker.DROPPED_OUT, new Date());
			people.remove(index);
		}
	}

}