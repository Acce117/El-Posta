package utils;

import java.util.ArrayList;



import javax.swing.table.DefaultTableModel;

import classes.WorkerWithDates;

public class VolunteerWorkersModel extends DefaultTableModel{
	String[] object;
	
	public VolunteerWorkersModel(){
		super();
	}
	
	public void refresh(ArrayList<WorkerWithDates> workers){
		setRowCount(0);
		object = new String[2];
		
		for(WorkerWithDates worker:workers){
			object[0] = worker.getToAsignWorker().getId();
			object[1] = worker.getToAsignWorker().getName();
			addRow(object);
		}
	}
	@Override
	public boolean isCellEditable(int row, int column)
	{
		return false;
	}
}
