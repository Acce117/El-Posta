package utils;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class PeriodRenderer extends DefaultTableCellRenderer 
{
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
	{
		Component render;
		
		if(value instanceof JButton)
		{
			JButton btn = (JButton)value;
			render = btn;
		}
		else 
			render = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		
		return render;
	}
}
