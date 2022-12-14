package test;
/*
 * findPerson
 * listOfTravelWorkers
 */
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import utils.Genre;
import utils.StatesStudent;
import utils.StatesWorker;
import utils.StatesWorkerWithComebackDate;
import classes.Faculty;
import classes.Person;
import classes.Student;
import classes.Worker;

public class testFaculty {
	private Faculty faculty;
	
	@Before
	public void setUp() throws Exception {
		faculty = Faculty.getInstance();
		faculty.addStudent("02050778986", "Ernesto", "Carralero", Genre.MALE, StatesStudent.ACTIVE);
		faculty.addStudent("03050778986", "Alfredo", "Hernandez", Genre.MALE, StatesStudent.LICENCE);
		faculty.addStudent("05050778986", "Dionisio", "Gregorio", Genre.MALE, StatesStudent.ACTIVE);
		faculty.addStudent("08050778916", "Alejandra", "Castro", Genre.FEMALE, StatesStudent.ACTIVE);
		faculty.addStudent("09050778986", "Fabio", "Ford", Genre.MALE, StatesStudent.LICENCE);
		faculty.addStudent("02060778936", "María", "Cardoso", Genre.FEMALE, StatesStudent.ACTIVE);
		
		
		faculty.addWorker("04050778976", "Carmen", "Esperanza", Genre.FEMALE, new StatesWorkerWithComebackDate(), new Date("5/7/2023"));
		faculty.addWorker("06050778996", "Marisel", "Conde", Genre.FEMALE, StatesWorker.LICENCE);
		faculty.addWorker("07050778986", "Armando", "Esponto", Genre.MALE, new StatesWorkerWithComebackDate(), new Date("6/10/2023"));
		faculty.addWorker("01050778986", "Jesus", "Manuel", Genre.MALE, StatesWorker.ACTIVE);
	}

	@After
	public void tearDown() throws Exception {
		faculty = null;
	}

	@Test
	public void testListOfTravelWorkers() {
		ArrayList<String> id = new ArrayList<>();
		id.add("04050778976");
		id.add("07050778986");
		
		assertEquals(id.size(), faculty.listOfTravelWorkers().size());
		
		for(int i = 0; i < id.size(); i++){
			assertEquals(id.get(i), faculty.listOfTravelWorkers().get(i).getId());
		}
		faculty.getPeople().clear();
	}

	@Test
	public void testFindPerson(){
		
		Person p = faculty.findPerson("05050778986");
		
		assertEquals("Dionisio", p.getName());
		faculty.getPeople().clear();
	}
}
