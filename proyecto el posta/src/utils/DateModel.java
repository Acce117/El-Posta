package utils;

import java.text.SimpleDateFormat;
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
		SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yy");
		
		for(Date date:dates){
			object[0] = df.format(date);
			addRow(object);
		}
	}
	@Override
	public boolean isCellEditable(int row, int column)
	{
		return false;
	}
}
