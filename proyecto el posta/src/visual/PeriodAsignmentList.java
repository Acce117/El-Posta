package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.RowFilter;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import java.awt.Color;
import java.awt.CardLayout;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import classes.ClassPeriod;
import classes.VacationPeriod;
import utils.PeriodAsignModel;
import utils.PersonTableModel;

import java.awt.Toolkit;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSeparator;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PeriodAsignmentList extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JPanel panel;
	private JScrollPane scrollPane;
	private JTable table;
	private PeriodAsignModel periodAsignModel;
	private ClassPeriod classPeriod;
	private VacationPeriod vacationPeriod;
	private JPanel panel_1;
	private JTextField textField;
	private TableRowSorter<PeriodAsignModel> tr;
	private JSeparator separator;
	private JCheckBox chckbxAusentes;
	/**
	 * Create the dialog.
	 * @wbp.parser.constructor
	 */
	public PeriodAsignmentList(VacationPeriod vacationPeriod) {
		setTitle("Guardias asignadas");
		setBackground(Color.WHITE);
		getContentPane().setBackground(Color.WHITE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(PeriodAsignmentList.class.getResource("/img/logo mejorado (1).png")));
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
		getContentPane().add(getPanel_1());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));		
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBackground(Color.WHITE);
			panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Turnos de guardia", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel.setBounds(10, 78, 464, 322);
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
			table.setFont(new Font("Tahoma", Font.PLAIN, 12));
			table.getTableHeader().setReorderingAllowed(false);
			periodAsignModel.addCheckBox(3, table);
		}
		return table;
	}
	
	private PeriodAsignModel getPeriodAsignModel(){
		if(periodAsignModel == null){
			periodAsignModel = new PeriodAsignModel();
			periodAsignModel.addColumn("Fecha");
			periodAsignModel.addColumn("Turno");
			periodAsignModel.addColumn("Nombre");
			periodAsignModel.addColumn("Ausente");			
			if(vacationPeriod == null)
				periodAsignModel.refresh(classPeriod.getAsignments());
			else
				periodAsignModel.refresh(vacationPeriod.getAsignments());
		}
		return periodAsignModel;
	}
	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setBackground(Color.WHITE);
			panel_1.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Buscar por", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel_1.setBounds(10, 11, 464, 56);
			panel_1.setLayout(null);
			panel_1.add(getTextField());
			panel_1.add(getSeparator());
			panel_1.add(getChckbxAusentes());
		}
		return panel_1;
	}
	private JTextField getTextField() {
		if (textField == null) {
			textField = new JTextField();
			textField.setFont(new Font("Tahoma", Font.PLAIN, 12));
			textField.setBorder(null);
			textField.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent arg0) {
					String filter = textField.getText();
					tr.setRowFilter(RowFilter.regexFilter(filter, 2));
				}
			});
			tr = new TableRowSorter<>(periodAsignModel);
			table.setRowSorter(tr);
			textField.setBounds(221, 25, 233, 20);
			textField.setColumns(10);
		}
		return textField;
	}
	private JSeparator getSeparator() {
		if (separator == null) {
			separator = new JSeparator();
			separator.setForeground(Color.BLACK);
			separator.setBounds(221, 45, 233, 2);
		}
		return separator;
	}
	private JCheckBox getChckbxAusentes() {
		if (chckbxAusentes == null) {
			chckbxAusentes = new JCheckBox("Ausentes");
			chckbxAusentes.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String search = chckbxAusentes.isSelected()? "true" : "";
					tr.setRowFilter(RowFilter.regexFilter(search,3));
				}
			});
			chckbxAusentes.setBackground(Color.WHITE);
			chckbxAusentes.setBounds(87, 24, 97, 23);
		}
		return chckbxAusentes;
	}
}
