package utils;

import java.util.ArrayList;
import java.util.Date;

import javax.swing.table.DefaultTableModel;

public class DateModel extends DefaultTableModel{
	String object[];
	public DateModel(){
		super();
	}
	
	public void refresh(ArrayList<Date> dates){
		setRowCount(0);
		object = new String[1];
		for(Date date:dates){
			object[0] = date.toString();
			addRow(object);
		}
	}
}
