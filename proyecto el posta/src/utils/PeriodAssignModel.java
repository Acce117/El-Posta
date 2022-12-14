package utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import classes.Asignment;

public class PeriodAssignModel extends DefaultTableModel{
	Object object[];
	
	public PeriodAssignModel(){
		super();
	}
		
	
	public void refresh(ArrayList<Asignment> list){
		object = new Object[4];
		
		SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yy");
		for(Asignment asign:list){
			object[0] = df.format(asign.getDay());
			object[1] = asign.getSchedule().getSchedule();
			object[2] = asign.getPersonOnWatch().getName() + " " + asign.getPersonOnWatch().getLastName();
			object[3] = (asign.isFailed())? "SI" : "NO";
			
			addRow(object);
		}
	}
	
	public void refreshAbsents(ArrayList<Asignment> list){
		object = new String[4];
		
		SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yy");
		for(Asignment asign:list){
				object[0] = df.format(asign.getDay());
				object[1] = asign.getSchedule().getSchedule();
				object[2] = asign.getPersonOnWatch().getName() + " " + asign.getPersonOnWatch().getLastName();
				object[3] = asign.isFailed();
			
				addRow(object);
		}
	}
	@Override
	public boolean isCellEditable(int row, int column)
	{
		return false;
	}

	public void addCheckBox(int column, JTable table) {
		// TODO Auto-generated method stub
		TableColumn tc = table.getColumnModel().getColumn(column);
		tc.setCellEditor(table.getDefaultEditor(Boolean.class));
		tc.setCellRenderer(table.getDefaultRenderer(Boolean.class));	
		
	}
}
