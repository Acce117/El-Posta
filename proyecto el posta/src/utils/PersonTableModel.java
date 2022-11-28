package utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import classes.Person;
import classes.Student;
import classes.Worker;

public class PersonTableModel extends DefaultTableModel{
	String object[];
	
	public PersonTableModel(){
		super();
	}
	
	public void refreshStudent(ArrayList<Student> list){
		setRowCount(0);
		object = new String[5];
		for(Student s:list){
			object[0] = s.getId();
			object[1] = s.getName();
			object[2] = s.getLastName();
			object[3] = s.getSex().getName();
			object[4] = s.getActualState().getName();
			addRow(object);
		}
	}
	

	public void refreshWorker(ArrayList<Worker> list){
		setRowCount(0);
		SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yy");
		object = new String[6];
		for(Worker w:list){
			object[0] = w.getId();
			object[1] = w.getName();
			object[2] = w.getLastName();
			object[3] = w.getSex().getName();
			object[4] = w.getActualState().getName();
			if(w.getComebackDate() != null)
				object[5] = "" + df.format(w.getComebackDate());
			else
				object[5] = "";
			addRow(object);
		}
	}
	
	@Override
	public boolean isCellEditable(int row, int column)
	{
		return false;
	}
}
