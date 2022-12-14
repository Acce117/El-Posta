package test;
/*
 * canAssign
 * searchWorker
 *
 */
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import utils.Genre;
import utils.Schedule;
import utils.StatesWorker;
import classes.VacationPeriod;
import classes.Worker;
import classes.WorkerWithDates;

public class testVacationPeriod {

	VacationPeriod vacationPeriod;
	ArrayList<WorkerWithDates> list;
	WorkerWithDates w1;
	WorkerWithDates w2;
	WorkerWithDates w3;
	WorkerWithDates w4;
	@Before
	public void setUp() throws Exception {
		w1 = new WorkerWithDates(new Worker("04050778976", "Carmen", "Esperanza", Genre.FEMALE, StatesWorker.ACTIVE));
		w2 = new WorkerWithDates(new Worker("06050778996", "Marisel", "Conde", Genre.FEMALE, StatesWorker.LICENCE));
		w3 = new WorkerWithDates(new Worker("07050778986", "Armando", "Esponto", Genre.MALE, StatesWorker.LICENCE));
		w4 = new WorkerWithDates(new Worker("01050778986", "Jesus", "Manuel", Genre.MALE, StatesWorker.ACTIVE));
		w1.addVacationWatch(new Date("12/10/2022 "));
		list = new ArrayList<>();
		
		list.add(w1);
		list.add(w2);
		list.add(w3);
		list.add(w4);
		
		vacationPeriod = new VacationPeriod(new Date("12/1/2022"), new Date("12/31/2022"), list);
	}

	@After
	public void tearDown() throws Exception {
		vacationPeriod = null;
		list = null;
	}

	@Test
	public void testCanAssignFalse() {
		assertFalse(vacationPeriod.canAsign(w1.getToAsignWorker(), new Date("12/10/2022"), Schedule.WORKER_SCHEDULE_1));
	}

	@Test
	public void testCanAssignTrue() {
		assertFalse(vacationPeriod.canAsign(w2.getToAsignWorker(), new Date("12/10/2022"), Schedule.WORKER_SCHEDULE_1));
	}
	
	@Test
	public void testSearchWorker() {
		assertSame(w2.getToAsignWorker(), vacationPeriod.searchWorker(w2.getToAsignWorker()));
	}
	@Test
	public void testSearchWorkerBad() {
		assertSame(null, vacationPeriod.searchWorker(new Worker("04040578986", "Pepe", "Antonio", Genre.FEMALE, StatesWorker.ACTIVE)));
	}
}
