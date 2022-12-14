package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import utils.Genre;
import utils.StatesStudent;
import utils.StatesWorker;
import classes.ClassPeriod;
import classes.GeneralState;
import classes.Person;
import classes.Student;
import classes.Worker;

public class testClassPeriod {
	private ClassPeriod classPeriod;
	private ArrayList<Person> list;
	private ArrayList<Student> testCaseListMale;
	private ArrayList<Student> testCaseListFemale;
	private ArrayList<Worker> testCaseListWorker;
	
	private Person p1;
	private Person p2;
	private Person p3;
	private Person p4;
	private Person p5;
	private Person p6;
	private Person p7;
	private Person p8;
	private Person p9;
	private Person p10;
	
	@Before
	public void setUp() throws Exception {
		list = new ArrayList<>();
		p1 = new Student("02050778986", "Ernesto", "Carralero", Genre.MALE, StatesStudent.ACTIVE);
		p2 = new Student("03050778986", "Alfredo", "Hernandez", Genre.MALE, StatesStudent.LICENCE);
		p3 = new Worker("04050778976", "Carmen", "Esperanza", Genre.FEMALE, StatesWorker.ACTIVE);
		p4 = new Student("05050778986", "Dionisio", "Gregorio", Genre.MALE, StatesStudent.ACTIVE);
		p5 = new Worker("06050778996", "Marisel", "Conde", Genre.FEMALE, StatesWorker.LICENCE);
		p6 = new Worker("07050778986", "Armando", "Esponto", Genre.MALE, StatesWorker.LICENCE);
		p7 = new Student("08050778916", "Alejandra", "Castro", Genre.FEMALE, StatesStudent.ACTIVE);
		p8 = new Student("09050778986", "Fabio", "Ford", Genre.MALE, StatesStudent.LICENCE);
		p9 = new Worker("01050778986", "Jesus", "Manuel", Genre.MALE, StatesWorker.ACTIVE);
		p10 = new Student("02060778936", "María", "Cardoso", Genre.FEMALE, StatesStudent.ACTIVE);
		
		list.add(p1);
		list.add(p2);
		list.add(p3);
		list.add(p4);
		list.add(p5);
		list.add(p6);
		list.add(p7);
		list.add(p8);
		list.add(p9);
		list.add(p10);
		
		testCaseListMale = new ArrayList<>();
		testCaseListFemale = new ArrayList<>();
		testCaseListWorker = new ArrayList<>();
		
		classPeriod = new ClassPeriod(new Date("1/1/2022"), new Date("1/31/2022"), list);
	}
	@After
	public void tearDown() throws Exception {
		classPeriod = null;
	}
	//Pruebas al metodo split(ArrayList<Person>)
	@Test
	public void testSplitMaleGood() {
		testCaseListMale.add((Student)p1);
		testCaseListMale.add((Student)p4);
		assertEquals(testCaseListMale.size(), classPeriod.getMaleStudents().size());
		for(int i = 0; i<testCaseListMale.size(); i++){
			assertTrue(testCaseListMale.get(i).equals(classPeriod.getMaleStudents().get(i)));
		}
	}
	@Test
	public void testSplitFemaleGood() {
		testCaseListFemale.add((Student)p7);
		testCaseListFemale.add((Student)p10);
		assertEquals(testCaseListFemale.size(), classPeriod.getFemaleStudents().size());
		for(int i = 0; i<testCaseListFemale.size(); i++){
			assertTrue(testCaseListFemale.get(i).equals(classPeriod.getFemaleStudents().get(i)));
		}
	}
	@Test
	public void testSplitWorkerGood() {
		testCaseListWorker.add((Worker)p3);
		testCaseListWorker.add((Worker)p9);
		assertEquals(testCaseListWorker.size(), classPeriod.getWorkers().size());
		for(int i = 0; i<testCaseListWorker.size(); i++){
			assertTrue(testCaseListWorker.get(i).equals(classPeriod.getWorkers().get(i)));
		}
	}
	@Test
	public void testEmptyList(){
		try{
			classPeriod = new ClassPeriod(new Date("1/1/2022"), new Date("1/31/2022"), new ArrayList<Person>());
		}catch(Exception e){
			assertEquals("Lista vac\u00eda, no se puede planificar guardia sin personal", e.getMessage());
		}
	}
	//--------------------------------------------------------------------------------------------------------------
	
	
}
