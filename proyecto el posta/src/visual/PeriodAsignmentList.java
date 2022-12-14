package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
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

import classes.Assignment;
import classes.ClassPeriod;
import classes.VacationPeriod;
import utils.PeriodAsignModel;
import utils.PeriodAssignModel;
import utils.PersonTableModel;
import utils.Schedule;

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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

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
	private JButton btnSave;
	private JLabel lblNombre;
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
		setBounds(100, 100, 500, 480);
		getContentPane().setLayout(null);
		getContentPane().add(getPanel());
		getContentPane().add(getPanel_1());
		getContentPane().add(getBtnSave());
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
			panel_1.add(getLblNombre());
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
			chckbxAusentes.setBounds(58, 24, 97, 23);
		}
		return chckbxAusentes;
	}
	private JButton getBtnSave() {
		if (btnSave == null) {
			btnSave = new JButton("Aceptar");
			btnSave.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						
						for(int index = 0; index < table.getRowCount(); index++)
						{
							boolean absent = ((Boolean)table.getValueAt(index, 3)).booleanValue();
							if(absent)
							{
								String s = (String)table.getValueAt(index, 0);
								String turn = (String)table.getValueAt(index, 1);
								Schedule sched;
								if(turn.equals("20:00pm - 8:00am"))
									sched = Schedule.MALE_STUDENT_SCHEDULE;
								else if(turn.equals("8:00am - 20:00pm"))
									sched = Schedule.FEMALE_STUDENT_SCHEDULE;
								else if(turn.equals("9:00am - 14:00pm"))
									sched = Schedule.WORKER_SCHEDULE_1;
								else
									sched = Schedule.WORKER_SCHEDULE_2;
								SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yy");
								Date d = df.parse(s);
								//System.out.println(d);
								Assignment fail = classPeriod.findAsignment(d,sched);
								fail.setFail(true);
								dispose();
								
							}							
												
						}
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, e.getMessage());
						e.printStackTrace();
					}
	
				}
			});
			btnSave.setBounds(385, 407, 89, 23);
		}
		return btnSave;
	}
	private JLabel getLblNombre() {
		if (lblNombre == null) {
			lblNombre = new JLabel("Nombre:");
			lblNombre.setHorizontalAlignment(SwingConstants.TRAILING);
			lblNombre.setBounds(127, 28, 90, 14);
		}
		return lblNombre;
	}
}
