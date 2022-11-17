package classes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.omg.PortableServer.POAManagerPackage.State;

import utils.DateManager;
import utils.PeriodValidator;
import utils.Schedule;
import utils.StatesWorker;

public class VacationPeriod extends PlanningPeriod
{
	private Schedule possibleSchedules[] = {Schedule.WORKER_SCHEDULE_1,Schedule.WORKER_SCHEDULE_2};
	private ArrayList<Worker> workers;
    public VacationPeriod(Date start, Date end) 
    {
        super(start, end);        
        workers = new ArrayList<Worker>();
    }

	@Override
	public void replan(Date pointReference, Person newPerson) 
	{
		if(pointReference.before(start) || pointReference.after(end))
		{
			throw new IllegalArgumentException("La fecha no se encuentra en el periodo");
		}
			
		//Buscar la persona 
		int toFindWorkerPosition; 
		toFindWorkerPosition = workers.indexOf(newPerson);
		
		if(toFindWorkerPosition != -1)
		{
			//Revisar el estado de la persona
			
			//Si no es activo se quita del listado
			if(workers.get(toFindWorkerPosition).getActualState() != StatesWorker.ACTIVE)
			{
				//Para quitar del listado tengo que ver que asignments tienen esa persona y una fecha mayor q la que doy de referencia
				for(int i = 0; i < asignments.size(); i++)
				{
					if(asignments.get(i).getDay().after(pointReference) && asignments.get(i).getPersonOnWatch().equals(newPerson))
					{
						asignments.remove(i);
						i--;
					}
				}
			}
			
		}		
	}

	public ArrayList<Worker> getWorkers() 
	{
		return workers;
	}

	public boolean canAsign(Person person, Date date, Schedule schedule) 
	{
		boolean can = true;//La variable que retorno
		
		if(date.before(start) || date.after(end)) //si no se encuentra en el periodo no puede
			can = false;
		
		if(can)
		{
			Asignment actualAsignment;
			for(int i = 0; i < asignments.size() && can; i++)
			{
				actualAsignment = asignments.get(i);								
				can = (!person.canMatch(date, schedule) || !actualAsignment.getDay().equals(date));
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
			if(!asigned)
			{
				throw new IllegalArgumentException("No se pudo planificar para el dia" + i);
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
    

}
