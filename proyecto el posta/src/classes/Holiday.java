package classes;

import java.util.ArrayList;
import java.util.Date;

public class Holiday{
	private static Holiday instance;
    private ArrayList<Date> daysList;

    private Holiday(){
        this.daysList = new ArrayList<>();
        daysList.add(new Date("12/24/2022"));
        daysList.add(new Date("12/10/2022"));
        daysList.add(new Date("12/15/2022"));
    }

    public boolean isHoliday(Date day){
    	if(day == null)
    		throw new IllegalArgumentException("La fecha introducida no existe");
        boolean check = false;
        Date actualDate;
        int toCheckDay = day.getDate();
        int toCheckMonth = day.getMonth();
        int actualDay;
        int actualMonth;
        for(int i = 0; i < daysList.size() && !check; i++)
        {
        	actualDate = daysList.get(i);
        	actualDay = actualDate.getDate();
        	actualMonth = actualDate.getMonth();
        	check = ((actualDay == toCheckDay) && (actualMonth == toCheckMonth));
        }
        return check;
    }

    public void addDay(Date day){
        if(isHoliday(day))
            throw new IllegalArgumentException("Ya se encuentra registrado como día festivo");
        
        this.daysList.add(day);
    }

    public void removeDay(Date day){
        if(!isHoliday(day))
            throw new IllegalArgumentException("No se encuentra registrado como día festivo, no se puede eliminar");
        
        daysList.remove(day);
    }
    
    public static Holiday getInstance(){
    	if(instance == null){
    		instance = new Holiday();
    	}
    	
    	return instance;
    }
}