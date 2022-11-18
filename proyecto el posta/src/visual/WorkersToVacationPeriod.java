package visual;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.CardLayout;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;

import java.awt.Color;

import com.toedter.calendar.JDateChooser;

import javax.swing.JLabel;

import utils.DateModel;
import java.awt.Toolkit;


public class WorkersToVacationPeriod extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JScrollPane scrollPane_1;
	private JDateChooser dateChooser;
	private JLabel lblFechaPropuesta;
	private JButton btnAgregar;
	private JScrollPane scrollPane;
	private JTable table;
	private JTable table_1;
	private DateModel dateModel;
	/**
	 * Create the dialog.
	 */
	public WorkersToVacationPeriod() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(WorkersToVacationPeriod.class.getResource("/img/logo mejorado.png")));
		setBounds(100, 100, 655, 450);
		setModal(true);
		getContentPane().setLayout(null);
		getContentPane().add(getPanel());
		getContentPane().add(getPanel_1());
		getContentPane().add(getPanel_2());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Listado de trabajadores", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel.setBounds(10, 11, 409, 389);
			panel.setLayout(new CardLayout(0, 0));
			panel.add(getScrollPane_2(), "name_2078998409183700");
		}
		return panel;
	}
	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Insertar fecha", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel_1.setBounds(429, 11, 200, 110);
			panel_1.setLayout(null);
			panel_1.add(getDateChooser());
			panel_1.add(getLblFechaPropuesta());
			panel_1.add(getBtnAgregar());
		}
		return panel_1;
	}
	private JPanel getPanel_2() {
		if (panel_2 == null) {
			panel_2 = new JPanel();
			panel_2.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Listado de fechas propuestas por un trabajador", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel_2.setBounds(429, 132, 200, 268);
			panel_2.setLayout(new CardLayout(0, 0));
			panel_2.add(getScrollPane_1(), "name_2077712143291400");
		}
		return panel_2;
	}
	private JScrollPane getScrollPane_1() {
		if (scrollPane_1 == null) {
			scrollPane_1 = new JScrollPane();
			scrollPane_1.setViewportView(getTable_1_1());
		}
		return scrollPane_1;
	}
	//----------------------------------------------------------------------------------------------------------------------------------------------
	private JDateChooser getDateChooser() {
		if (dateChooser == null) {
			dateChooser = new JDateChooser();
			dateChooser.setBounds(10, 42, 180, 20);
		}
		return dateChooser;
	}
	private JLabel getLblFechaPropuesta() {
		if (lblFechaPropuesta == null) {
			lblFechaPropuesta = new JLabel("Fecha propuesta:");
			lblFechaPropuesta.setBounds(10, 24, 97, 14);
		}
		return lblFechaPropuesta;
	}
	private JButton getBtnAgregar() {
		if (btnAgregar == null) {
			btnAgregar = new JButton("Agregar");
			btnAgregar.setBounds(101, 76, 89, 23);
		}
		return btnAgregar;
	}
	private JScrollPane getScrollPane_2() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getTable_2());
		}
		return scrollPane;
	}
	private JTable getTable_2() {
		if (table == null) {
			table = new JTable();
			table.setModel(Personal.getWorkerModel());
			table.setFillsViewportHeight(true);
			table.getColumnModel().getColumn(0).setPreferredWidth(67);
			table.getColumnModel().getColumn(3).setPreferredWidth(35);
			table.getColumnModel().getColumn(4).setPreferredWidth(44);
			table.getColumnModel().getColumn(5).setPreferredWidth(95);
		}
		return table;
	}
	private JTable getTable_1_1() {
		if (table_1 == null) {
			table_1 = new JTable();
			table_1.setModel(getDateModel());
			table_1.setFillsViewportHeight(true);
		}
		return table_1;
	}
	
	private DateModel getDateModel(){
		if(dateModel == null){
			dateModel = new DateModel();
			dateModel.addColumn("Fecha");
			
			//dateModel.refresh(va algo);
		}
		return dateModel;
	}
}
