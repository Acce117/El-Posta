package utils;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import classes.Person;
import classes.Student;
import classes.Worker;

public class PersonTableModel extends DefaultTableModel{
	
	public PersonTableModel(){
		super();
	}
	
	public void refreshStudent(ArrayList<Student> list){
		setRowCount(0);
		String student[] = new String[5];
		for(Student s:list){
			student[0] = s.getId();
			student[1] = s.getName();
			student[2] = s.getName();
			student[3] = s.getSex().getName();
			student[4] = s.getActualState().getName();
			addRow(student);
		}
	}
	
	public void refreshWorker(ArrayList<Worker> list){
		setRowCount(0);
		
		String worker[] = new String[6];
		for(Worker w:list){
			worker[0] = w.getId();
			worker[1] = w.getName();
			worker[2] = w.getName();
			worker[3] = w.getSex().getName();
			worker[4] = "";
			//worker[4] = w.getActualState().getName();
			if(w.getComebackDate() != null)
				worker[5] = "" + w.getComebackDate();
			else
				worker[5] = "";
			addRow(worker);
		}
	}
}
