package classes;
import utils.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class ClassPeriod extends PlanningPeriod implements IOrganize{

	private ArrayList<Student> maleStudents;
	//Pense trabajar a las estudiantes por separado igual como a los profesores, me parece mas facil
	private ArrayList<Student> femaleStudents;
	//Esto es para saber a quienes les toca hacer la guardia en fin de semana, si a estudiantes o a trabajadors

	private boolean lastPersonWorker;

	private ArrayList<Worker> workers;

	private ArrayList<Person> noActivePeople;


	private Holiday holidays;

	private ArrayList<Person> absents;

	//	private Person lastPersonHolidayMale;
	//	private Person lastPersonHolidayFemale;
	//	private Person lastPersonHolidayWorker1;
	//	private Person lastPersonHolidayWorker2;

	public ClassPeriod(Date start, Date end, ArrayList<Person> personList){
		super(start, end);        
		this.absents = new ArrayList<>();
		this.maleStudents = new ArrayList<>();
		this.femaleStudents = new ArrayList<>();
		this.workers = new ArrayList<>();
		this.noActivePeople = new ArrayList<>();
		this.lastPersonWorker = false;
		holidays = Holiday.getInstance();

		split(personList);
		organize(start, end);  

	}

	@Override
	public void match(Person person, Date date, Schedule schedule){
		Asignment asignment = new Asignment(person, date, schedule);
		int asignmentIndex = asignments.indexOf(asignment);
		if(asignmentIndex == -1)
			asignments.add(asignment);
		else
			asignments.set(asignmentIndex, asignment);

	}


	//Splitea la lista completa de personas en trabajadores y estudiantes

	protected void split(ArrayList<Person> personList){
		if(personList.size() == 0)
			throw new IllegalArgumentException("Lista vac\u00eda, no se puede planificar guardia sin personal");

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

	//Metodo de organizacion----------------------------------------------------------------------------------
	//TODO hacerlo public, si es public hacerla private
	private void asignMaleStudent(Date day){
		Person aux = maleStudents.get(0);
		if(holidays.isHoliday(day)){ 
			if(lastPersonHolidayMale == null || !lastPersonHolidayMale.equals(aux))
				lastPersonHolidayMale = maleStudents.get(0);
			else if(maleStudents.size() > 1){
				Collections.swap(maleStudents, 0, 1);
				aux = maleStudents.get(0);
			}
		}
		match(aux, day, Schedule.MALE_STUDENT_SCHEDULE);
		maleStudents.remove(aux);
		maleStudents.add((Student)aux);
	}


	private int asignFemaleStudent(Date day, int index){
		Person aux = femaleStudents.get(index);
		if(holidays.isHoliday(day)){
			if(lastPersonHolidayFemale == null || !lastPersonHolidayFemale.equals(aux)){
				lastPersonHolidayFemale = femaleStudents.get(index);
			}
			else if(femaleStudents.size() > 1){
				Collections.swap(femaleStudents, index, (index+1)%femaleStudents.size());
				aux = femaleStudents.get(index);
			}
		}

		match(aux, day, Schedule.FEMALE_STUDENT_SCHEDULE);
		return ++index;
	}

	private int asignWorker(Date day, Schedule schedule, int index){
		Person lastPersonHolidayWorker;
		if(schedule.equals(Schedule.WORKER_SCHEDULE_1))
			lastPersonHolidayWorker = lastPersonHolidayWorker1;
		else
			lastPersonHolidayWorker = lastPersonHolidayWorker2;

		Person aux = workers.get(index);
		if(holidays.isHoliday(day)){ 
			if(lastPersonHolidayWorker == null || !lastPersonHolidayWorker.equals(aux)){
				if(schedule.equals(Schedule.WORKER_SCHEDULE_1))
					lastPersonHolidayWorker1 = workers.get(index);
				else
					lastPersonHolidayWorker2 = workers.get(index);
			}
			else if(workers.size() > 1){
				Collections.swap(workers, index, (index + 1)%workers.size());
				aux = workers.get(index);
			}
		}

		match(aux, day, schedule);
		return (index+1)%workers.size();
	}

	public void organize(Date start, Date end){

		int index = 0;
		while(start.compareTo(end) < 0){
			updateList(start);
			if(!maleStudents.isEmpty())
				asignMaleStudent(start);

			if(DateManager.isWeekend(start)){
				//Comprueba si no se ha repetido el ultimo trabajador ademas de que la lista de trabajadores ni la de las hembras puede estar vacia
				//if((!lastPersonWorker && !workers.isEmpty())|| (femaleStudents.isEmpty()) && !workers.isEmpty()){
				//El mismo if comentado, lo que aplicando el axioma de distributiva para hacerlo m???s corto
				if(!workers.isEmpty() && (!lastPersonWorker || femaleStudents.isEmpty())){
					System.out.println(index);
					index = asignWorker(start, Schedule.WORKER_SCHEDULE_1, index);

					index = asignWorker(start, Schedule.WORKER_SCHEDULE_2, index%workers.size());
					if(index == workers.size()%2){
						index = 0;
						lastPersonWorker = !lastPersonWorker;
					}
				}
				else if(!femaleStudents.isEmpty()){
					index = asignFemaleStudent(start, index);

					if(index >= femaleStudents.size()){
						index = 0;
						lastPersonWorker = !lastPersonWorker;
					}
				}
			}
			start = new Date(start.getTime() + 86400000);
		}
	}
	//-----------------------------------------------------------------------------------------------------------
	//-----------------------------------------------------------------------------------------------------------

	private void updateList(Date start) {

		int deleteIndex = 0;
		//remover los que no estan disponibles y pasarlos a la lista de inactivos revisando en las respectivas listas
		deleteIndex = deleteInactivePeople(start);
		//los que pueden ese dia se van para la su respectiva lista de activos
		addActive(deleteIndex,start);

		//se agregan de ultimo a la de inactivos
		//se agregan de primeros a la respectiva lista
	}

	private int deleteInactivePeople(Date start) {
		int deleted = 0;
		int size;

		//se recorre cada lista
		for(int i = 0; i < maleStudents.size(); i++)
		{
			if(!maleStudents.get(i).enabled(start))
			{
				size = noActivePeople.size();
				removePerson(maleStudents.get(i));
				deleted+=size - noActivePeople.size()+1;//el +1 es porque agrego una persona en el metodo removePerson				
			}
		}
		for(int i = 0; i < femaleStudents.size(); i++)
		{
			if(!femaleStudents.get(i).enabled(start))
			{
				size = noActivePeople.size();
				removePerson(femaleStudents.get(i));
				deleted+=size - noActivePeople.size()+1;//el +1 es porque agrego una persona en el metodo removePerson				
			}
		}
		for(int i = 0; i < workers.size(); i++)
		{
			if(!workers.get(i).enabled(start))
			{
				size = noActivePeople.size();
				removePerson(workers.get(i));
				deleted+=size - noActivePeople.size()+1;//el +1 es porque agrego una persona en el metodo removePerson				
			}
		}

		return deleted;
	}

	private void addActive(int deleteIndex, Date start) {

		Person personToChange = null;
		for(int i = 0; i < noActivePeople.size()-deleteIndex; i++)
		{
			personToChange = noActivePeople.get(i);
			if(personToChange.enabled(start))
				addPerson(personToChange);
		}

	}

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
	//Estos getters son para las pruebas, no son relevantes para el sistema en si
	public ArrayList<Person> getAbsents(){
		return absents;
	}

	public ArrayList<Student> getFemaleStudents() {
		return femaleStudents;
	}

	public ArrayList<Student> getMaleStudents() {
		return maleStudents;
	}

	public ArrayList<Worker> getWorkers() {
		return workers;
	}


	private void updatePerson(Person personToChange) {
		if(personToChange.isActive())
			addPerson(personToChange);
		else
			removePerson(personToChange);		
	}

	private void removePerson(Person personToChange) {

		int index;
		//Para evitar duplicados en noActivePeople
		noActivePeople.removeAll(Collections.singleton(personToChange));
		noActivePeople.add(personToChange);
		if(personToChange instanceof Worker){
			index = workers.indexOf(personToChange);
			if(index != -1)
				workers.removeAll(Collections.singleton(personToChange));

		}else{

			if(personToChange.getSex() == Genre.MALE){

				index = maleStudents.indexOf(personToChange);
				if(index != -1)
					maleStudents.removeAll(Collections.singleton(personToChange));

			}else{

				index = femaleStudents.indexOf(personToChange);
				if(index != -1)
					femaleStudents.removeAll(Collections.singleton(personToChange));				
			}						
		}		
	}

	private void addPerson(Person personToChange){

		noActivePeople.removeAll(Collections.singleton(personToChange));
		if(personToChange instanceof Worker)
			workers.add(0,(Worker) personToChange);		
		else{

			if(personToChange.getSex() == Genre.MALE)
				maleStudents.add(0,(Student) personToChange);
			else
				femaleStudents.add(0,(Student) personToChange);
		}

	}

}
