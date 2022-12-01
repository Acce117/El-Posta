package run;

import java.time.Period;
import java.util.ArrayList;
import java.util.Date;

import utils.Genre;
import utils.StatesStudent;
import classes.Asignment;
import classes.Faculty;
import classes.PlanningPeriod;
import classes.Student;

public class ConsoleMainTest {

	public static void main(String[] args) {
		Faculty fac = Faculty.getInstance();
	
		fac.addStudent("02011068328", "Pedrito", "eee", Genre.MALE, StatesStudent.ACTIVE);
		fac.addStudent("02021068328", "Edito", "eee", Genre.MALE, StatesStudent.ACTIVE);
		Date d = new Date();
		Date p = new Date("12/24/2022");
		fac.planningClassPeriod(d,p);
		System.out.println("Ya planifique\n\n\n\n");
		ArrayList<Asignment> a = fac.getClassPeriods().get(0).getAsignments();
		
		System.out.println("Asi quedo\n\n\n\n");
		for(Asignment b: a)
		{
			System.out.println(b.getPersonOnWatch().getName() + " " + b.getDay());						
		}
		
		Student q = fac.getStudents().get(0);
		q.setActualState(StatesStudent.LICENCE, new Date("12/10/2022"));
		System.out.println("Ya modifique\n\n\n\n");
		for(Asignment b: a)
		{
			System.out.println(b.getPersonOnWatch().getName() + " " + b.getDay());						
		}

	}

}
