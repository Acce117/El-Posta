package run;

import java.time.Period;
import java.util.Date;

import utils.Genre;
import utils.StatesStudent;
import classes.Faculty;
import classes.PlanningPeriod;

public class ConsoleMainTest {

	public static void main(String[] args) {
		Faculty fac = Faculty.getInstance();
	
		fac.addStudent("02011068328", "Pedrito", "eee", Genre.MALE, StatesStudent.ACTIVE);
		fac.addStudent("02021068328", "Pedito", "eee", Genre.MALE, StatesStudent.ACTIVE);
		Date d = new Date();
		Date p = new Date("12/24/2022");
		System.out.println(p);
		fac.planningClassPeriod(d,p);

		for(PlanningPeriod i : fac.getClassPeriods())
		{
			System.out.println(i.getStart() + " " + i.getEnd());
		}


	}

}
