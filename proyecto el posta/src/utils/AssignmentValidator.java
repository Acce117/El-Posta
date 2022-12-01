package utils;

import java.util.Date;

import classes.Person;

public class AssignmentValidator 
{
    private static boolean validateSchedule(String schedule)
    {
        boolean check = true;
        
        if(schedule == null || schedule.equals(""))
            check = false;
        
        return check;
    }

    public static void checkSchedule(String schedule) 
    {
        if(!validateSchedule(schedule))
            throw new IllegalArgumentException("Horario no válido");
        
    }

    public static void checkPersonOnWatch(Person personOnWatch)
    {
        if(personOnWatch == null)
            throw new IllegalArgumentException("Persona vacia");
    }

    public static void checkDay(Date day) 
    {
        if(day == null)
            throw new IllegalArgumentException("Dia vacio");
    }
}
