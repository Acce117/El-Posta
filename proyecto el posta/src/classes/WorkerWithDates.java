package classes;

import java.util.ArrayList;
import java.util.Date;

import utils.DateManager;
import utils.TakedDay;

public class WorkerWithDates 
{
	private Worker toAsignWorker;
	private ArrayList<Date> listVacationWatch;
	
	public WorkerWithDates(Worker toAsignWorker)
	{
		setToAsignWorker(toAsignWorker);
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
		if(!DateManager.isWeekend(newDate))
			throw new IllegalArgumentException("Tiene que ser fin de semana");
		if(TakedDay.getInstance().isTaked(newDate) || listVacationWatch.indexOf(newDate)!= -1)
			throw new IllegalArgumentException("Ya la fecha fue tomada");
			
		listVacationWatch.add(newDate);
		TakedDay.getInstance().addDay(newDate);
	}
	
}
