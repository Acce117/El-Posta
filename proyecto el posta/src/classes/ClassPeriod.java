package classes;
import utils.*;
import interfaces.IOrganize;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class ClassPeriod extends PlanningPeriod implements IOrganize{

	private ArrayList<Student> maleStudents;
	//Pense trabajar a las estudiantes por separado igual como a los profesores, me parece mas facil
	private ArrayList<Student> femaleStudents;
	//Esto es para saber a quienes les toca hacer la guardia en fin de semana, si a estudiantes o a trabajadors

	private boolean lastPersonWorker;

	private ArrayList<Worker> workers;
	private ArrayList<Person> absents;

	private ArrayList<Person> noActivePeople;

	private Person lastPersonHolidayMale;
	private Person lastPersonHolidayFemale;
	private Person lastPersonHolidayWorker1;
	private Person lastPersonHolidayWorker2;
	private Holiday holidays;

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

	/*public int countAbsent(){
		return absents.size();

	}*/
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
		asignments.add(asignment);
	}

	//Metodo de organizacion----------------------------------------------------------------------------------

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
			if(!maleStudents.isEmpty())
				asignMaleStudent(start);
			
			if(DateManager.isWeekend(start)){
				if((!lastPersonWorker && !workers.isEmpty())){// || femaleStudents.isEmpty()){
					System.out.println(index);
					index = asignWorker(start, Schedule.WORKER_SCHEDULE_1, index);
					//TODO arreglar esto para si la lista de trabajadores es impar, ahora mismo planifica doble a los trabajadores
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

	public void replan(Date pointReference, Person personToChange) {
		organize(pointReference, end);

	}

	public void replan(Date pointReferenceStart, Date pointReferenceEnd, Person personToChange)
	{
		organize(pointReferenceStart,pointReferenceEnd);
	}

	public ArrayList<Person> getAbsents(){
		return absents;
	}

}