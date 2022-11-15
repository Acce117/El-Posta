package classes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class VacationPeriod extends PlanningPeriod{

    public VacationPeriod(Date start, Date end) {
        super(start, end);
        
    }

	@Override
	public void replan(Date pointReference) {
		// TODO Auto-generated method stub
		
	}

	public Collection<? extends Worker> getWorkers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean canMatch(Person person, Date date) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void match(Person actualPerson, Date actualDate) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addAsignment() {
		// TODO Auto-generated method stub
		
	}
    
}
