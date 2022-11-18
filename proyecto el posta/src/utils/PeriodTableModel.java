package utils;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import classes.ClassPeriod;
import classes.Student;
import classes.VacationPeriod;
import classes.Worker;

public class PeriodTableModel extends DefaultTableModel{
	String object[];
	
	public PeriodTableModel(){
		super();
	}
	
	public void refreshClassPeriod(ArrayList<ClassPeriod> list){
		setRowCount(0);
		object = new String[5];
		int n = 1;
		for(ClassPeriod s:list){
			object[0] = n + "";
			object[1] = s.getStart() + "";
			object[2] = s.getEnd() + "";
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
			object[1] = s.getStart() + "";
			object[2] = s.getEnd() + "";
			addRow(object);
			n++;
		}
	}
}