package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.CardLayout;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import classes.ClassPeriod;
import classes.VacationPeriod;
import utils.PeriodAsignModel;

public class PeriodAsignmentList extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JPanel panel;
	private JScrollPane scrollPane;
	private JTable table;
	private PeriodAsignModel periodAsignModel;
	private ClassPeriod classPeriod;
	private VacationPeriod vacationPeriod;
	/**
	 * Create the dialog.
	 * @wbp.parser.constructor
	 */
	public PeriodAsignmentList(VacationPeriod vacationPeriod) {
		this.vacationPeriod = vacationPeriod;
		setModal(true);
		initialize();
	}
	
	public PeriodAsignmentList(ClassPeriod classPeriod) {
		this.classPeriod = classPeriod;
		setModal(true);
		initialize();
	}
	
	private void initialize(){
		setModal(true);
		setLocationRelativeTo(null);
		setBounds(100, 100, 500, 450);
		getContentPane().setLayout(null);
		getContentPane().add(getPanel());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Turnos de guardia", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel.setBounds(10, 11, 464, 389);
			panel.setLayout(new CardLayout(0, 0));
			panel.add(getScrollPane(), "name_11768917792600");
		}
		return panel;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getTable());
		}
		return scrollPane;
	}
	private JTable getTable() {
		if (table == null) {
			table = new JTable();
			table.setModel(getPeriodAsignModel());
			table.setFillsViewportHeight(true);
		}
		return table;
	}
	
	private PeriodAsignModel getPeriodAsignModel(){
		if(periodAsignModel == null){
			periodAsignModel = new PeriodAsignModel();
			periodAsignModel.addColumn("Fecha");
			periodAsignModel.addColumn("Turno");
			periodAsignModel.addColumn("Nombre");
			periodAsignModel.addColumn("Hecha");
			if(vacationPeriod == null)
				periodAsignModel.refresh(classPeriod.getAsignments());
			else
				periodAsignModel.refresh(vacationPeriod.getAsignments());
		}
		return periodAsignModel;
	}
}
