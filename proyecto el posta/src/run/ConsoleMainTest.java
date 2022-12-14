package run;

import java.time.Period;
import java.util.ArrayList;
import java.util.Date;
import utils.Genre;
import utils.StatesStudent;
import utils.StatesWorker;
import classes.Asignment;
import classes.Faculty;
import classes.PlanningPeriod;
import classes.Student;
import classes.Worker;
import classes.WorkerWithDates;

public class ConsoleMainTest {

	public static void main(String[] args) 
	{
		Faculty fac = Faculty.getInstance();
		fac.addStudent("02011068328", "Pedrito", "Estudiante", Genre.MALE, StatesStudent.ACTIVE);
		fac.addStudent("02011068328", "Edito", "Estudiante", Genre.MALE, StatesStudent.ACTIVE);
		fac.addStudent("02011068328", "Dito", "Estudiante", Genre.MALE, StatesStudent.ACTIVE);
		
		/*
		fac.addWorker("02011068328", "Pedrito", "Trabajador", Genre.MALE, StatesWorker.ACTIVE);
		fac.addWorker("02021068328", "Edito", "Trabajador", Genre.MALE, StatesWorker.ACTIVE);
		
		fac.addVacationDate(fac.getWorkers().get(0), new Date("12/4/2022"));
		fac.addVacationDate(fac.getWorkers().get(1), new Date("12/3/2022"));
		*/
		
		Date d = new Date("12/1/2022");
		Date p = new Date("12/10/2022");
		fac.planningClassPeriod(d, p);
		//fac.planningVacationPeriod(d, p);
		System.out.println("Ya planifique\n\n\n\n");
		ArrayList<Asignment> a = (ArrayList<Asignment>) fac.getClassPeriods().get(0).getAsignments();
		
		System.out.println("Asi quedo\n\n\n\n");
		for(Asignment b: a)
		{
			System.out.println(b.getPersonOnWatch().getName() + " " + b.getDay());						
		}
		
//		Worker q = fac.getWorkers().get(0);
		Student q = fac.getStudents().get(0);
		q.setActualState(StatesStudent.LICENCE, new Date("12/5/2022"));
		q.setActualState(StatesStudent.ACTIVE, new Date("12/8/2022"));
		System.out.println("Ya modifique\n\n\n\n");
		for(Asignment b: a)
		{
			System.out.println(b.getPersonOnWatch().getName() + " " + b.getDay());						
		}

	}

}
