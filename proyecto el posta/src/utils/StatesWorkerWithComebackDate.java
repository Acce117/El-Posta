package utils;

import java.util.Date;

import interfaces.GeneralState;

public class StatesWorkerWithComebackDate implements GeneralState 
{
    private final String name = "Extranjero";
    private Date date;

    public void setCombackDate(Date d)
    {
        this.date = d;
    }

    public Date getCombackDate()
    {
        return date;
    }

    @Override
    public String getName() 
    {
        return name;
    }

}
