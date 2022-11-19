package utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import classes.Asignment;

public class PeriodAsignModel extends DefaultTableModel{
	String object[];
	
	public PeriodAsignModel(){
		super();
	}
	
	public void refresh(ArrayList<Asignment> list){
		object = new String[4];
		
		SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yy");
		for(Asignment asign:list){
			object[0] = df.format(asign.getDay());
			object[1] = asign.getSchedule().getSchedule();
			object[2] = asign.getPersonOnWatch().getName();
			object[3] = "No";
			
			addRow(object);
		}
	}
}
