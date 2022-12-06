package utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import classes.Assignment;

public class PeriodAssignModel extends DefaultTableModel{
	String object[];
	
	public PeriodAssignModel(){
		super();
	}
	
	public void addCheckBox(int column, JTable table)
	{
		TableColumn tc = table.getColumnModel().getColumn(column);
		tc.setCellEditor(table.getDefaultEditor(Boolean.class));
		tc.setCellRenderer(table.getDefaultRenderer(Boolean.class));				
	}
	
	public void refresh(ArrayList<Assignment> list){
		object = new String[4];
		
		SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yy");
		for(Assignment asign:list){
			object[0] = df.format(asign.getDay());
			object[1] = asign.getSchedule().getSchedule();
			object[2] = asign.getPersonOnWatch().getName() + " " + asign.getPersonOnWatch().getLastName();
			object[3] = (asign.isDone())? "SI" : "NO";
			
			addRow(object);
		}
	}
	
	public void refreshAbsents(ArrayList<Assignment> list){
		object = new String[4];
		
		SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yy");
		for(Assignment asign:list){
			if(asign.isDone() == false){
				object[0] = df.format(asign.getDay());
				object[1] = asign.getSchedule().getSchedule();
				object[2] = asign.getPersonOnWatch().getName() + " " + asign.getPersonOnWatch().getLastName();
				object[3] = (asign.isDone())? "SI" : "NO";
			
				addRow(object);
			}
		}
	}
	@Override
	public boolean isCellEditable(int row, int column)
	{
		return false;
	}
}
