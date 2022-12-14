package test;
/*
 * isHoliday
 */
import static org.junit.Assert.*;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import classes.Holiday;

public class testHoliday {
	
	private Holiday holiday;
	private Date testCase;
	@Before
	public void setUp() throws Exception {
		holiday = Holiday.getInstance();
	}

	@After
	public void tearDown() throws Exception {
		holiday = null;
	}

	@Test
	public void testIsHolidayTrue() {
		//holiday = Holiday.getInstance();
		testCase = new Date("12/24/2022");
		assertTrue(holiday.isHoliday(testCase));
	}
	
	@Test
	public void testIsHolidayFalseByDay() {
		//holiday = Holiday.getInstance();
		testCase = new Date("12/23/2022");
		assertFalse(holiday.isHoliday(testCase));
	}
	
	@Test
	public void testIsHolidayFalseByMonth() {
		//holiday = Holiday.getInstance();
		testCase = new Date("05/24/2022");
		assertFalse(holiday.isHoliday(testCase));
	}
	
	@Test
	public void testIsHolidayFalseByDayAndMonth() {
		//holiday = Holiday.getInstance();
		testCase = new Date("05/07/2022");
		assertFalse(holiday.isHoliday(testCase));
	}
}

