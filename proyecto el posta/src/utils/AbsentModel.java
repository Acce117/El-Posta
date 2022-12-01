package utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import classes.Assignment;

public class AbsentModel extends DefaultTableModel{
	String object[];
	
	public AbsentModel(){
		super();
	}
	
	public void refresh(ArrayList<Assignment> assignments){
		setRowCount(0);
		object = new String[3];
	
		SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yy");
		for(Assignment a: assignments){
			object[0] = df.format(a.getDay());
			object[1] = a.getSchedule().getSchedule();
			object[2] = a.getPersonOnWatch().getName() + a.getPersonOnWatch().getLastName();
			addRow(object);
		}
	}
}
