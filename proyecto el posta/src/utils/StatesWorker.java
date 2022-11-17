package utils;

import interfaces.GeneralState;

import java.util.Date;

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
