package interfaces;

import java.util.Date;

import utils.Schedules;
import classes.Person;

public interface IMatcherVerify 
{
	public boolean canMatch(Person person, Date date, Schedules schedule);
}
