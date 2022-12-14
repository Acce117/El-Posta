package utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import classes.Assignment;

public class PeriodAsignModel extends DefaultTableModel{
	Object object[];
	
	public PeriodAsignModel(){
		super();
	}
	
	public void addCheckBox(int column, JTable table)
	{
		TableColumn tc = table.getColumnModel().getColumn(column);
		tc.setCellEditor(table.getDefaultEditor(Boolean.class));
		tc.setCellRenderer(table.getDefaultRenderer(Boolean.class));				
	}
	
	public boolean isSelected(int row, int column, JTable table)
	{
		return table.getValueAt(row, column) != null;
	}
	
	public void refresh(ArrayList<Assignment> list){
		object = new Object[4];
		
		SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yy");
		for(Assignment asign:list){
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
		return column == 3;
	}
}
