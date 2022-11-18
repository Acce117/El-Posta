package classes;

import java.util.ArrayList;
import java.util.Date;

public class WorkerWithDates 
{
	private Worker toAsignWorker;
	private ArrayList<Date> listVacationWatch;
	
	public WorkerWithDates()
	{
		listVacationWatch = new ArrayList<Date>();
	}

	public Worker getToAsignWorker() 
	{
		return toAsignWorker;
	}

	public void setToAsignWorker(Worker toAsignWorker) 
	{
		this.toAsignWorker = toAsignWorker;
	}

	public ArrayList<Date> getListVacationWatch() 
	{
		return listVacationWatch;
	}

	public void addVacationWatch(Date newDate)
	{
		
	}
	
	
	
}
