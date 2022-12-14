package classes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.omg.PortableServer.POAManagerPackage.State;

import utils.DateManager;
import utils.PeriodValidator;
import utils.PersonalValidator;
import utils.Schedule;
import utils.StatesWorker;
import utils.TakedDay;

public class VacationPeriod extends PlanningPeriod
{
	private Schedule possibleSchedules[] = {Schedule.WORKER_SCHEDULE_1,Schedule.WORKER_SCHEDULE_2};
	private ArrayList<Worker> workers;
    public VacationPeriod(Date start, Date end, ArrayList<WorkerWithDates> datesToPlanning) 
    {
        super(start, end);        
        workers = new ArrayList<Worker>();
        addWorkers(datesToPlanning);
        organize(datesToPlanning);
        TakedDay.getInstance().refresher();
    }

	private void addWorkers(ArrayList<WorkerWithDates> datesToPlanning) 
	{
		Worker actualWorker = null;
		for(WorkerWithDates i : datesToPlanning)
		{
			actualWorker = i.getToAsignWorker();
			if(workers.indexOf(actualWorker) == -1)
			{
				workers.add(actualWorker);
			}
		}
	}

	private void organize(ArrayList<WorkerWithDates> datesToPlanning) 
	{
		for(WorkerWithDates i : datesToPlanning)
		{
			asign(i.getToAsignWorker(),i.getListVacationWatch());
		}
		
	}


	public ArrayList<Worker> getWorkers() 
	{
		return workers;
	}

	public boolean canAsign(Person person, Date date, Schedule schedule) 
	{
		boolean can = person.canMatch(date, schedule);//La variable que retorno
		
		if(date.before(start) || date.after(end)) //si no se encuentra en el periodo no puede
			can = false;
		
		if(can)
		{
			Asignment actualAsignment;
			for(int i = 0; i < asignments.size() && can; i++)
			{
				actualAsignment = asignments.get(i);								
				if(DateManager.sameDate(actualAsignment.getDay(), date) && actualAsignment.getSchedule().equals(schedule))
				 can = false;
			}						
		}

		return can;		
	}
	

	@Override
	public void match(Person actualPerson, Date actualDate, Schedule schedule) 
	{
		if(actualPerson != null && actualDate != null)
		{
			asignments.add(new Asignment(actualPerson,actualDate,schedule));
		}
	}
	
	public void asign(Worker newWorker, ArrayList<Date> watches)
	{
		

		boolean asigned = false;
		Date actualDate;
		//Se recorren las fechas que decidio el trabajador
		for(int i = 0; i < watches.size() && !asigned; i++)
		{
			actualDate = watches.get(i);
			asigned = false;
			//Si no puede hacerlo en un turno lo prueba con otro 
			for(int j = 0; j < possibleSchedules.length && !asigned; j++)
			{
				if(canAsign(newWorker,actualDate,possibleSchedules[j]))
				{
					asigned = true;
					match(newWorker,actualDate,possibleSchedules[j]);
				}				
			}
		}
	}
	
	public void addDay(Worker actualWorker, Date newDate)
	{
		if(actualWorker==null || newDate == null)
			throw new IllegalArgumentException("Parametros vacios");
		boolean asigned = false;
		for(int i = 0; i < possibleSchedules.length && !asigned; i++)
		{
			if(canAsign(actualWorker, newDate, possibleSchedules[i]))
			{
				asigned = true;
				match(actualWorker,newDate,possibleSchedules[i]);
			}
		}
	}
	
	public void updateDate(Worker actualWorker, Date oldDate, Date newDate)
	{
		if(actualWorker == null)
			throw new IllegalArgumentException("Parametro vacio");
		boolean deleted = false;		
		//Buscar fecha vieja
		for(int i = 0; i < asignments.size() && !deleted; i++)
		{
			if(asignments.get(i).equals(oldDate))
			{
				//Verificar si se puede hacer match con la fecha vieja
				//Matchear la nueva
				addDay(actualWorker,newDate);//TODO Pensar mejor esta parte para no repetir codigo
				//Eliminar la vieja
				asignments.remove(i);
				deleted = true;
			}
			
		}
	}
	//TODO volverlo a hacer private
	public Worker searchWorker(Person newPerson)
	{
		Worker toSearch = null;
		
		if(newPerson instanceof Worker)
		{
			boolean find = false;
			for(int i = 0; i < workers.size() && !find; i++)
			{
				if(workers.get(i).equals(newPerson))
				{
					toSearch = workers.get(i);
					find = true;
				}
			}
		}
		
		return toSearch;
	}
	
	private void delete(Date pointReferenceStart, Date pointReferenceEnd, Person newPerson)
	{
		//Validar las fechas
		
		if(DateManager.betweenDates(start, end, pointReferenceStart) || DateManager.betweenDates(start, end, pointReferenceEnd))
		{
			//Buscar la persona 
			Worker toFind = searchWorker(newPerson);
			
			//Encontrar persona
			if(toFind != null)
			{
				//Revisar el estado de la persona				
				//Si no es activo se quita del listado
				if(!toFind.isActive())
				{
					//Para quitar del listado tengo que ver que asignments tienen esa persona y una fecha mayor q la que doy de referencia
					
					for(int i = 0; i < asignments.size(); i++)
					{
						Date actualDate = asignments.get(i).getDay();
						Person actualWorker = asignments.get(i).getPersonOnWatch();
						//Si se puede tomar esa fecha
						//Para tomar la fecha tiene que estar despues de la fecha inicial de punto de referencia y menor que el punto la fecha final de punto de referencia
						//Comprobar tambien que sea ese el trabajador que esta asignado
						if(DateManager.betweenDates(pointReferenceStart, pointReferenceEnd, actualDate) && actualWorker.equals(newPerson))
						{
							//Borro ese asignment
							asignments.remove(i);
							i--;
						}
					}				
				}				
			}				
		}
	}
	
	@Override
	public void replan(Date pointReference, Person newPerson) 
	{
		delete(pointReference,end,newPerson);
	}


	@Override
	public void replan(Date pointReferenceStart, Date pointReferenceEnd,Person changedPerson) 
	{		
		delete(pointReferenceStart, pointReferenceEnd, changedPerson);
	}
}
