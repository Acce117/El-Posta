package utils;

import java.util.Date;

import classes.GeneralState;

public class StatesWorkerWithComebackDate implements GeneralState 
{
    private final String NAME = "Extranjero";
    private Date ocmebackDate;

    public void setCombackDate(Date comebackDate)
    {
        this.ocmebackDate = comebackDate;
    }

    public Date getCombackDate()
    {
        return ocmebackDate;
    }

    @Override
    public String getName() 
    {
        return NAME;
    }

}
