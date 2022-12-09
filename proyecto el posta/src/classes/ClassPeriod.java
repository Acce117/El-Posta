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
	private ArrayList<Student> absent;

	private ArrayList<Person> noActivePeople;


	private Holiday holidays;

	private ArrayList<Person> absents;

	private Person lastPersonHolidayMale;
	private Person lastPersonHolidayFemale;
	private Person lastPersonHolidayWorker1;
	private Person lastPersonHolidayWorker2;

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

	public int countAbsent(){
		return absent.size();

	}
	//----------------------------------------------------------------------------------------------------------

	//Splitea la lista completa de personas en trabajadores y estudiantes

//	protected void split(ArrayList<Person> personList){
//		if(personList.size() == 0)
//			throw new IllegalArgumentException("Lista vacía, no se puede planificar guardia sin personal");
//
//		for(Person p: personList){
//			if(p.isActive())
//				if(p instanceof Student){
//					if(p.getSex() == Genre.MALE)
//						maleStudents.add((Student) p);
//					else
//						femaleStudents.add((Student) p);
//				}
//				else
//					workers.add((Worker) p);
//			else
//				noActivePeople.add(p);
//		}
//	}

	@Override
	public void match(Person person, Date date, Schedule schedule){
		Assignment asignment = new Assignment(person, date, schedule);
		int asignmentIndex = asignments.indexOf(asignment);
		if(asignmentIndex == -1)
			asignments.add(asignment);
		else
			asignments.set(asignmentIndex, asignment);

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

	//Metodo de organizacion----------------------------------------------------------------------------------

	private void asignMaleStudent(Date day){
		Person aux = maleStudents.get(0);
		System.out.println(ableStudent(0, day));
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

			for(int auxIndex = (index+1)%size; auxIndex != lastIndex; auxIndex = (auxIndex+1)%size)
			{
				System.out.println(auxIndex + " " + index);
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
