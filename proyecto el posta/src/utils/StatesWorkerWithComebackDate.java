package utils;

import java.util.Date;

import classes.GeneralState;

public class StatesWorkerWithComebackDate implements GeneralState 
{
    private final String NAME = "Extranjero";
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
        return NAME;
    }

}
