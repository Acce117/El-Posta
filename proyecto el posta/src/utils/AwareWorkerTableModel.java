package utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import classes.Worker;

public class AwareWorkerTableModel extends DefaultTableModel{
	String object[];
	
	public AwareWorkerTableModel(){
		super();
	}
	
	public void refresh(ArrayList<Worker> workers){
		//System.out.println(getRowCount());
		setRowCount(0);
		object = new String[4];
		SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yy");
		for(Worker w:workers){
			object[0] = w.getId();
			
			object[1] = w.getName();
			
			object[2] = w.getLastName();
			
			object[3] = df.format(w.getComebackDate());
			addRow(object);
			//System.out.println(getRowCount());
		}
	}

}
