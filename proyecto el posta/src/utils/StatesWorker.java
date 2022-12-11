package utils;

import java.util.Date;

import classes.GeneralState;

public enum StatesWorker implements GeneralState 
{
	ACTIVE("Activo"),
    LICENCE("Licencia"),
    DROPPED_OUT("Baja");

	
    private String name;

    
	StatesWorker(String s)
	{
		name = s;
	}

	@Override
	public String getName()
	{
		return name;
	}

}
