package utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import classes.Assignment;

public class PeriodAssignModel extends DefaultTableModel{
	String object[];
	
	public PeriodAssignModel(){
		super();
	}
	
	public void refresh(ArrayList<Assignment> list){
		object = new String[4];
		
		SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yy");
		for(Assignment asign:list){
			object[0] = df.format(asign.getDay());
			object[1] = asign.getSchedule().getSchedule();
			object[2] = asign.getPersonOnWatch().getName();
			object[3] = "No";
			
			addRow(object);
		}
	}
	@Override
	public boolean isCellEditable(int row, int column)
	{
		return false;
	}
}
