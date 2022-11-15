package utils;

import classes.Person;

public class FacultyValidator 
{
    public static void checkPersonOnWatch(Person person)
    {
        if(person == null)
            throw new IllegalArgumentException("Persona vacia");
    }
}
