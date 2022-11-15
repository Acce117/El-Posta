package utils;

public enum StatesStudent{
	ACTIVE("Activo"),
    LICENCE("Licencia"),
    DROPPED_OUT("Baja");
	
    private String name;
    
	StatesStudent(String s)
	{
		name = s;
	}
	
	public String getName()
	{
		return name;
	}
}
