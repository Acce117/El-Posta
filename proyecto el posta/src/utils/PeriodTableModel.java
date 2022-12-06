package utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import classes.ClassPeriod;
import classes.Student;
import classes.VacationPeriod;
import classes.Worker;

public class PeriodTableModel extends DefaultTableModel{
	String object[];
	private SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yy");
	
	public PeriodTableModel(){
		super();
	}
	
	public void refreshClassPeriod(ArrayList<ClassPeriod> list){
		setRowCount(0);
		
		object = new String[5];
		int n = 1;
		for(ClassPeriod s:list){
			object[0] = n + "";
			object[1] = df.format(s.getStart());
			object[2] = df.format(s.getEnd());
			addRow(object);
			n++;
		}
	}
	
	public void refreshVacationPeriod(ArrayList<VacationPeriod> list){
		setRowCount(0);
		object = new String[6];
		int n = 1;
		for(VacationPeriod s:list){
			object[0] = n + "";
			object[1] = df.format(s.getStart()) + "";
			object[2] = df.format(s.getEnd()) + "";
			addRow(object);
			n++;
		}
	}
	
	@Override
	public boolean isCellEditable(int row, int column)
	{
		return false;
	}
}