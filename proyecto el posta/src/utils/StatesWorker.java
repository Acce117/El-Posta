package utils;

public enum StatesWorker {
	ACTIVE("Activo"),
    LICENCE("Licencia"),
    DROPPED_OUT("Baja"),
    AWARE("Extranjero");
	
    private String name;
    
	StatesWorker(String s)
	{
		name = s;
	}
	
	public String getName()
	{
		return name;
	}	
}
