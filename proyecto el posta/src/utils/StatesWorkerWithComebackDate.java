package utils;

import java.util.Date;

import interfaces.GeneralState;

public enum StatesWorkerWithComebackDate implements GeneralState 
{
    AWARE("Extranjero");
    
	private StatesWorkerWithComebackDate(String s)
	{
		name = s;
	}
	private String name;
    private Date comebackDate;
	
	public void setCombackDate(Date comebackDate)
	{
		this.comebackDate = comebackDate;		
	}
	
	public Date getCombackDate()
	{
		return comebackDate;
	}
	
	@Override
	public String getName() 
	{
		return name;
	}

}
