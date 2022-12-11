package visual;


import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ButtonGroup;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import utils.Genre;
import utils.PersonTableModel;
import utils.PersonalValidator;
import utils.StatesWorker;
import utils.StatesStudent;
import utils.StatesWorkerWithComebackDate;

import javax.swing.JScrollPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.border.TitledBorder;

import java.awt.Color;
import java.awt.Toolkit;

import classes.Faculty;
import classes.GeneralState;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import javax.swing.border.LineBorder;

import java.awt.CardLayout;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.DropMode;
import javax.swing.border.EmptyBorder;
import javax.swing.JSeparator;

import java.awt.SystemColor;
import java.awt.Font;

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JCheckBox;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;


public class Personal extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTabbedPane tabbedPane;
	private JPanel studentPanel;
	private JPanel workerPanel;
	private JLabel lblStudentName;
	private JLabel lblLastName;
	private JLabel lblStudentID;
	private JTextField studentName;
	private JTextField studentLastName;
	private JTextField studentID;
	private JRadioButton studentFemale;
	private JRadioButton studentMale;
	private ButtonGroup studentsGroupFyM;
	private ButtonGroup workersGroupFyM;
	private JLabel lblWorkerLastName;
	private JTextField workerName;
	private JTextField workerLastName;
	private JLabel lblWorkerName;
	private JLabel lblWorkerID;
	private JTextField workerID;
	private JComboBox<Object> workerState;
	private JComboBox<Object> studentState;
	private JPanel studentListPanel;
	private JScrollPane scrollPane;
	private JTable studentListTable;
	private JButton newStudent;
	private JPanel newStudentPanel;
	private JLabel lblStudentState;
	private JPanel newWorkerPanel;
	private JLabel lblWorkerState;
	private JRadioButton workerFemale;
	private JRadioButton workerMale;
	private JButton newWorker;
	private JPanel workerListPanel;
	private JScrollPane scrollPane_1;
	private JTable workerListTable;
	private JButton btnEliminar;
	private JButton btnNewButton;
	private JDateChooser comeBackDateChooser;
	private JLabel lblcomeBackDate;
	private JPanel panel_1;
	private JButton btnCancelar_1;
	private PersonTableModel studentModel;
	private static Faculty faculty = Faculty.getInstance();
	private static PersonTableModel workerModel;
	private JSeparator separator;
	private JSeparator separator_1;
	private JSeparator separator_3;
	
	private final Color backgroundColor;
	private JSeparator separator_4;
	private JSeparator separator_5;
	private JSeparator separator_7;
	private JPanel panel;
	private JButton button;
	private JButton button_1;
	private JButton button_2;


	public Personal() {
		getContentPane().setBackground(Color.WHITE);
		setFont(new Font("Book Antiqua", Font.PLAIN, 14));
		setBackground(Color.WHITE);
		backgroundColor = getBackground();
		setIconImage(Toolkit.getDefaultToolkit().getImage(".\\src\\img\\logo mejorado.png"));
		setTitle("Gesti\u00F3n del personal");
		setBounds(100, 100, 740, 443);
		setResizable(false);
		getContentPane().setLayout(null);
		getContentPane().add(getTabbedPane());
	}
	private JTabbedPane getTabbedPane() {
		if (tabbedPane == null) {
			tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			tabbedPane.setBounds(0, 0, 734, 426);
			tabbedPane.addTab("Estudiante", null, getStudentPanel(), null);
			tabbedPane.addTab("Trabajador", null, getWorkerPanel(), null);
			tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 14));
			tabbedPane.setBackground(backgroundColor);
		}
		return tabbedPane;
	}
	private JPanel getStudentPanel() {
		if (studentPanel == null) {
			setLocationRelativeTo(null);
			studentPanel = new JPanel();
			studentPanel.setLayout(null);
			studentPanel.add(getNewStudentPanel());
			studentPanel.add(getStudentListPanel());
			studentsGroupFyM();
			studentPanel.setBackground(backgroundColor);
			studentPanel.add(getPanel());
		}
		return studentPanel;
	}
	private JPanel getWorkerPanel() {
		if (workerPanel == null) {
			workerPanel = new JPanel();
			workerPanel.setLayout(null);
			workerPanel.add(getPanel_2());
			workerPanel.add(getPanel_5());
			workerPanel.add(getPanel_1());
			workersGroupFyM();
			workerPanel.setBackground(backgroundColor);
		}
		return workerPanel;
	}
	private JLabel getLblStudentName() {
		if (lblStudentName == null) {
			lblStudentName = new JLabel("Nombre:");
			lblStudentName.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblStudentName.setForeground(Color.BLACK);			
			lblStudentName.setBounds(20, 30, 71, 14);
			lblStudentName.setHorizontalAlignment(SwingConstants.LEFT);
		}
		return lblStudentName;
	}
	private JLabel getLblLastName() {
		if (lblLastName == null) {
			lblLastName = new JLabel("Apellidos:");
			lblLastName.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblLastName.setBounds(20, 76, 86, 14);
			lblLastName.setHorizontalAlignment(SwingConstants.LEFT);
		}
		return lblLastName;
	}
	private JLabel getLblStudentID() {
		if (lblStudentID == null) {
			lblStudentID = new JLabel("CI:");
			lblStudentID.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblStudentID.setBounds(20, 130, 59, 14);
			lblStudentID.setHorizontalAlignment(SwingConstants.LEFT);
		}
		return lblStudentID;
	}
	private JTextField getStudentName() {
		if (studentName == null) {
			studentName = new JTextField();
			studentName.setFont(new Font("Tahoma", Font.PLAIN, 12));
			studentName.setBackground(backgroundColor);
			studentName.setBounds(20, 46, 200, 20);
			studentName.setBorder(null);
			studentName.setHorizontalAlignment(SwingConstants.LEFT);
			studentName.setColumns(10);
		}
		return studentName;
	}
	private JTextField getStudentLastName() {
		if (studentLastName == null) {
			studentLastName = new JTextField();
			studentLastName.setFont(new Font("Tahoma", Font.PLAIN, 12));
			studentLastName.setBackground(backgroundColor);
			studentLastName.setBounds(20, 92, 200, 20);
			studentLastName.setHorizontalAlignment(SwingConstants.LEFT);
			studentLastName.setColumns(10);
			studentLastName.setBorder(null);
		}
		return studentLastName;
	}
	private JTextField getStudentID() {
		if (studentID == null) {
			studentID = new JTextField();
			studentID.setFont(new Font("Tahoma", Font.PLAIN, 12));
			studentID.setBackground(backgroundColor);
			studentID.setBounds(20, 148, 200, 20);
			studentID.setHorizontalAlignment(SwingConstants.LEFT);
			studentID.setColumns(10);
			studentID.setBorder(null);
			
		}
		return studentID;
	}
	private JRadioButton getStudentFemale() {
		if (studentFemale == null) {
			studentFemale = new JRadioButton("Femenina");
			studentFemale.setFont(new Font("Tahoma", Font.PLAIN, 12));
			studentFemale.setBounds(20, 180, 86, 23);
			studentFemale.setBackground(backgroundColor);
		}
		return studentFemale;
	}
	private JRadioButton getStudentMale() {
		if (studentMale == null) {
			studentMale = new JRadioButton("Masculino");
			studentMale.setFont(new Font("Tahoma", Font.PLAIN, 12));
			studentMale.setBounds(107, 180, 95, 23);
			studentMale.setBackground(backgroundColor);
		}
		return studentMale;
	}
	private ButtonGroup studentsGroupFyM(){
		if(studentsGroupFyM == null){
			studentsGroupFyM = new ButtonGroup();
			studentsGroupFyM.add(studentFemale);
			studentsGroupFyM.add(studentMale);
		}
		return studentsGroupFyM;
	}

	private ButtonGroup workersGroupFyM(){
		if(workersGroupFyM == null){
			workersGroupFyM = new ButtonGroup();
			workersGroupFyM.add(workerFemale);
			workersGroupFyM.add(workerMale);
		}
		return workersGroupFyM;
	}
	private JLabel getLblWorkerLastName() {
		if (lblWorkerLastName == null) {
			lblWorkerLastName = new JLabel("Apellidos:");
			lblWorkerLastName.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblWorkerLastName.setBounds(20, 76, 86, 14);
			lblWorkerLastName.setHorizontalAlignment(SwingConstants.LEFT);
		}
		return lblWorkerLastName;
	}
	private JTextField getWorkerName() {
		if (workerName == null) {
			workerName = new JTextField();
			workerName.setFont(new Font("Tahoma", Font.PLAIN, 12));
			workerName.setBounds(20, 46, 200, 20);
			workerName.setColumns(10);
			workerName.setBorder(null);
			workerName.setBackground(backgroundColor);
		}
		return workerName;
	}
	private JTextField getWorkerLastName() {
		if (workerLastName == null) {
			workerLastName = new JTextField();
			workerLastName.setFont(new Font("Tahoma", Font.PLAIN, 12));
			workerLastName.setBounds(20, 92, 200, 20);
			workerLastName.setColumns(10);
			workerLastName.setBorder(null);		
			workerLastName.setBackground(backgroundColor);
		}
		return workerLastName;
	}
	private JLabel getLblWorkerName() {
		if (lblWorkerName == null) {
			lblWorkerName = new JLabel("Nombre: ");
			lblWorkerName.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblWorkerName.setBounds(20, 30, 71, 14);
			lblWorkerName.setHorizontalAlignment(SwingConstants.LEFT);
		}
		return lblWorkerName;
	}
	private JLabel getLblWorkerID() {
		if (lblWorkerID == null) {
			lblWorkerID = new JLabel("CI:");
			lblWorkerID.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblWorkerID.setBounds(20, 130, 59, 14);
			lblWorkerID.setHorizontalAlignment(SwingConstants.LEFT);
		}
		return lblWorkerID;
	}
	private JTextField getWorkerID() {
		if (workerID == null) {
			workerID = new JTextField();
			workerID.setFont(new Font("Tahoma", Font.PLAIN, 12));
			workerID.setBounds(20, 148, 200, 20);
			workerID.setColumns(10);
			workerID.setBorder(null);
			workerID.setBackground(backgroundColor);;
		}
		return workerID;
	}
	private JComboBox<Object> getWorkerState() {
		if (workerState == null) {
			workerState = new JComboBox<Object>();
			workerState.setFont(new Font("Tahoma", Font.PLAIN, 12));
			workerState.addItemListener(new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent e) {
					if(workerState.getSelectedIndex() != -1){
						if(e.getItem().equals("Extranjero"))					
							comeBackDateChooser.setEnabled(true);					
						else
							comeBackDateChooser.setEnabled(false);	
					}
				}
			});
			workerState.setBounds(20, 235, 200, 20);
			//Converti el arreglo de enums a String
			ArrayList<GeneralState> list = new ArrayList<>();
			
			StatesWorker statesWorkerList[] = StatesWorker.values();
			
			for(int i = 0; i < statesWorkerList.length; i++)
			{
				list.add(statesWorkerList[i]);
			}
			
			list.add(new StatesWorkerWithComebackDate());
			
			String listShow[] = new String[list.size()];
			//TODO De momento esta hecho a fuerza bruta. Buscar un metodo para simplificar

			for(int i = 0; i < list.size(); i++) 
			{
				listShow[i] = list.get(i).getName();
			}
			
			workerState.setModel(new DefaultComboBoxModel<Object>(listShow));
			workerState.setSelectedIndex(-1);			
			workerState.setBackground(backgroundColor);
		}		
		return workerState;
	}
	private JComboBox<Object> getStudentState() {
		if (studentState == null) {
			studentState = new JComboBox<Object>();
			studentState.setFont(new Font("Tahoma", Font.PLAIN, 12));
			studentState.setBounds(20, 235, 200, 20);
			//Converti el arreglo de enums a String			
			StatesStudent list[] = StatesStudent.values();			
			String listShow[] = new String[list.length];
			//De momento esta hecho a fuerza bruta. Buscar un metodo para simplificar
			for(int i = 0; i < list.length; i++) 
			{
				listShow[i] = list[i].getName();
			}
			studentState.setModel(new DefaultComboBoxModel<Object>(listShow));
			studentState.setSelectedIndex(-1);
			studentState.setBackground(backgroundColor);
		}
		return studentState;
	}
	private JPanel getStudentListPanel() {
		if (studentListPanel == null) {
			studentListPanel = new JPanel();
			studentListPanel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Listado de estudiantes", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			studentListPanel.setBounds(270, 10, 450, 297);
			studentListPanel.setLayout(new CardLayout(0, 0));
			studentListPanel.add(getScrollPane(), "name_1304743514853800");
			studentListPanel.setBackground(backgroundColor);
		}
		return studentListPanel;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getStudentListTable());

		}
		return scrollPane;
	}

	//Tabla de Estudiantes--------------------------------------------------------------------------------------
	private JTable getStudentListTable() {
		if (studentListTable == null) {

			studentListTable = new JTable();
			studentListTable.setFillsViewportHeight(true);
			studentListTable.setModel(getStudentModel());
			studentListTable.setFont(new Font("Tahoma", Font.PLAIN, 12));
			studentListTable.getTableHeader().setBackground(backgroundColor);
			studentListTable.getTableHeader().setReorderingAllowed(false);
			//studentListTable.getColumnModel().getColumn(3).setPreferredWidth(40);
			//studentListTable.getColumnModel().getColumn(4).setPreferredWidth(60);
		}
		return studentListTable;
	}
	
	private PersonTableModel getStudentModel(){
		if(studentModel == null){
			studentModel = new PersonTableModel();
			studentModel.addColumn("CI");
			studentModel.addColumn("Nombre");
			studentModel.addColumn("Apellido");
			studentModel.addColumn("Sexo");
			studentModel.addColumn("Estado");
			studentModel.refreshStudent(faculty.getStudents());
			
		}
		
		return studentModel;
	}
	private void clearStudent()
	{
		studentName.setText("");
		studentLastName.setText("");
		studentID.setText("");
		studentsGroupFyM.clearSelection();
		studentState.setSelectedIndex(-1);
		
	}
	
	private void clearWorker()
	{
		workerName.setText("");
		workerLastName.setText("");
		workerID.setText("");
		workerState.setSelectedIndex(-1);		
		workerFemale.setSelected(false);
		workersGroupFyM.clearSelection();
		comeBackDateChooser.setCalendar(null);
		comeBackDateChooser.setEnabled(false);
		
	}
	
	//Entrada de datos de estudiantes------------------------------------------------------------------------------------------------------------------
	
	private JButton getNewStudent() {

		if (newStudent == null) {
			newStudent = new JButton("Agregar");
			newStudent.setFont(new Font("Tahoma", Font.PLAIN, 13));
			newStudent.setBackground(Color.WHITE);
			newStudent.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String name = null;
					String lastName = null;
					String id = null;
					Genre sex = null;
					StatesStudent state = null;
					//Student student;
					try{
						name = studentName.getText();
						lastName = studentLastName.getText();
						id = studentID.getText();
						
						
						PersonalValidator.checkName(name);
						PersonalValidator.checkName(lastName);
						
						sex = PersonalValidator.checkSex(studentMale, studentFemale);
						PersonalValidator.checkID(id, sex);	
						
						if(studentState.getSelectedIndex() == -1)
							throw new Exception("No se ha elegido el estado");
						switch((String)studentState.getSelectedItem()){
							case "Activo":
								state = StatesStudent.ACTIVE;
								break;
							case "Licencia":
								state = StatesStudent.LICENCE;
								break;
							case "Baja":
								state = StatesStudent.DROPPED_OUT;
								break;
							default:
								throw new Exception("No se ha elegido el estado");
						}
						
						
						//name = name + " " + lastName + " " + secLastName;
						faculty.addStudent(id, name, lastName, sex, state);
						studentModel.refreshStudent(faculty.getStudents());
						clearStudent();
					}catch(Exception error){
						error.printStackTrace();
						JOptionPane.showMessageDialog(null, error.getMessage());
					}
				}
			});
			newStudent.setBounds(135, 316, 85, 23);
		}
		return newStudent;
	}
	//------------------------------------------------------------------------------------------------------------------------------------------------

	private JPanel getNewStudentPanel() {
		if (newStudentPanel == null) {
			newStudentPanel = new JPanel();
			newStudentPanel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Nuevo estudiante", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			newStudentPanel.setBounds(10, 10, 240, 353);
			newStudentPanel.setLayout(null);
			newStudentPanel.add(getLblStudentName());
			newStudentPanel.add(getLblLastName());
			newStudentPanel.add(getLblStudentID());
			newStudentPanel.add(getStudentName());
			newStudentPanel.add(getStudentLastName());
			newStudentPanel.add(getStudentID());
			newStudentPanel.add(getStudentFemale());
			newStudentPanel.add(getStudentMale());
			newStudentPanel.add(getStudentState());
			newStudentPanel.add(getNewStudent());
			newStudentPanel.add(getLblStudentState());
			newStudentPanel.add(getSeparator());
			newStudentPanel.add(getSeparator_1());
			newStudentPanel.add(getSeparator_3());
			newStudentPanel.setBackground(backgroundColor);
		}
		return newStudentPanel;
	}
	private JLabel getLblStudentState() {
		if (lblStudentState == null) {
			lblStudentState = new JLabel("Estado:");
			lblStudentState.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblStudentState.setBounds(20, 210, 59, 14);
		}
		return lblStudentState;
	}
	private JPanel getPanel_2() {
		if (newWorkerPanel == null) {
			newWorkerPanel = new JPanel();
			newWorkerPanel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Nuevo trabajador", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			newWorkerPanel.setBounds(10, 10, 240, 353);
			newWorkerPanel.setLayout(null);
			newWorkerPanel.add(getLblWorkerLastName());
			newWorkerPanel.add(getWorkerName());
			newWorkerPanel.add(getWorkerLastName());
			newWorkerPanel.add(getLblWorkerName());
			newWorkerPanel.add(getLblWorkerID());
			newWorkerPanel.add(getWorkerID());
			newWorkerPanel.add(getWorkerState());
			newWorkerPanel.add(getLblWorkerState());
			newWorkerPanel.add(getWorkerFemale());
			newWorkerPanel.add(getWorkerMale());
			newWorkerPanel.add(getNewWorker());
			newWorkerPanel.add(getComeBackDateChooser());
			newWorkerPanel.add(getLblcomeBackDate());
			newWorkerPanel.setBackground(backgroundColor);
			newWorkerPanel.add(getSeparator_4_1());
			newWorkerPanel.add(getSeparator_5());
			newWorkerPanel.add(getSeparator_7());
		}
		return newWorkerPanel;
	}
	private JLabel getLblWorkerState() {
		if (lblWorkerState == null) {
			lblWorkerState = new JLabel("Estado:");
			lblWorkerState.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblWorkerState.setBounds(20, 210, 59, 14);
		}
		return lblWorkerState;
	}
	private JRadioButton getWorkerFemale() {
		if (workerFemale == null) {
			workerFemale = new JRadioButton("Femenina");
			workerFemale.setFont(new Font("Tahoma", Font.PLAIN, 12));
			workerFemale.setBounds(20, 180, 86, 23);
			workerFemale.setBackground(backgroundColor);
		}
		return workerFemale;
	}
	private JRadioButton getWorkerMale() {
		if (workerMale == null) {
			workerMale = new JRadioButton("Masculino");
			workerMale.setFont(new Font("Tahoma", Font.PLAIN, 12));
			workerMale.setBounds(107, 180, 95, 23);
			workerMale.setBackground(backgroundColor);
		}
		return workerMale;
	}
	private JButton getNewWorker() {
		if (newWorker == null) {
			newWorker = new JButton("Agregar");
			newWorker.setFont(new Font("Tahoma", Font.PLAIN, 13));
			newWorker.setBackground(Color.WHITE);
			newWorker.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String name = null;
					String lastName = null;
					String id = null;
					Genre sex = null;
					GeneralState state = null;
					//Student student;
					Date comeBackDate = null;
					try{
						name = workerName.getText();
						lastName = workerLastName.getText();
						id = workerID.getText();
						sex = PersonalValidator.checkSex(workerMale, workerFemale);
						
						PersonalValidator.checkName(name);
						PersonalValidator.checkName(lastName);
						
						PersonalValidator.checkID(id, sex);	
						
						if(workerState.getSelectedIndex() == -1)
							throw new Exception("No se ha elegido el estado");
						switch((String)workerState.getSelectedItem()){
							case "Activo":
								state = StatesWorker.ACTIVE;
								break;
							case "Licencia":
								state = StatesWorker.LICENCE;
								break;
							case "Baja":
								state = StatesWorker.DROPPED_OUT;
								break;
							case "Extranjero":
								state = new StatesWorkerWithComebackDate();
								break;
							default:
								throw new Exception("No se ha elegido el estado");
						}
						
						if(comeBackDateChooser.isEnabled()){
							comeBackDate = comeBackDateChooser.getDate();
						}
						
						//name = name + " " + lastName + " " + secLastName;
						//faculty.addWorker(id, name, lastName, sex, state, comeBackDate);
						if(comeBackDate!=null)
							faculty.addWorker(id, name, lastName, sex, state, comeBackDate);
						else
							faculty.addWorker(id, name, lastName, sex, state);
						
						workerModel.refreshWorker(faculty.getWorkers());
						clearWorker();
					}catch(Exception error){
						JOptionPane.showMessageDialog(null, error.getMessage());
					}
				}
			});
			newWorker.setBounds(135, 316, 85, 23);
		}
		return newWorker;
	}
	private JPanel getPanel_5() {
		if (workerListPanel == null) {
			workerListPanel = new JPanel();
			workerListPanel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Lista de trabajadores", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			workerListPanel.setBounds(270, 10, 450, 297);
			workerListPanel.setLayout(new CardLayout(0, 0));
			workerListPanel.add(getScrollPane_1_1(), "name_1304922936460400");
			workerListPanel.setBackground(backgroundColor);
		}
		return workerListPanel;
	}
	private JScrollPane getScrollPane_1_1() {
		if (scrollPane_1 == null) {
			scrollPane_1 = new JScrollPane();
			scrollPane_1.setViewportView(getTable_1());
		}
		return scrollPane_1;
	}

	//Tabla de Trabajadores-------------------------------------------------------------------------------------
	private JTable getTable_1() {
		if (workerListTable == null) {
			workerListTable = new JTable();
			workerListTable.setFillsViewportHeight(true);
			workerListTable.setModel(getWorkerModel());
			workerListTable.getColumnModel().getColumn(0).setPreferredWidth(67);
			workerListTable.getColumnModel().getColumn(3).setPreferredWidth(35);
			workerListTable.getColumnModel().getColumn(4).setPreferredWidth(44);
			workerListTable.getColumnModel().getColumn(5).setPreferredWidth(95);
			workerListTable.setFont(new Font("Tahoma", Font.PLAIN, 12));
			workerListTable.getTableHeader().setBackground(backgroundColor);
			workerListTable.getTableHeader().setReorderingAllowed(false);
		}
		return workerListTable;
	}
	
	public static PersonTableModel getWorkerModel(){
		if(workerModel == null){
			workerModel = new PersonTableModel();
			workerModel.addColumn("CI");
			workerModel.addColumn("Nombre");
			workerModel.addColumn("Apellido");
			workerModel.addColumn("Sexo");
			workerModel.addColumn("Estado");
			workerModel.addColumn("Fecha de retorno");
			
			workerModel.refreshWorker(faculty.getWorkers());
		}
		return workerModel;
	}
	//----------------------------------------------------------------------------------------------------------

	private JButton getBtnEliminar() {
		if (btnEliminar == null) {
			btnEliminar = new JButton("Eliminar");
			btnEliminar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					int index =	workerListTable.getSelectedRow();
					Faculty fac = Faculty.getInstance();
					PersonTableModel model = getWorkerModel();
					String s = (String) model.getValueAt(index, 0);
					fac.removePerson(s);
					workerModel.refreshWorker(fac.getWorkers());
				}
			});
			btnEliminar.setFont(new Font("Tahoma", Font.PLAIN, 13));
			btnEliminar.setBackground(Color.WHITE);
			btnEliminar.setBounds(248, 11, 91, 23);
		}
		return btnEliminar;
	}
	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("Editar");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int index = workerListTable.getSelectedRow();
					if(index != -1){
						EditWorker window = new EditWorker(Faculty.getInstance().getWorkers().get(index),workerModel);
						window.setVisible(true);
					}
				}
			});
			btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
			btnNewButton.setBackground(Color.WHITE);
			btnNewButton.setBounds(147, 11, 91, 23);
		}
		return btnNewButton;
	}
	private JDateChooser getComeBackDateChooser() {
		if (comeBackDateChooser == null) {
			comeBackDateChooser = new JDateChooser();

			comeBackDateChooser.setEnabled(false);
			comeBackDateChooser.setBounds(20, 284, 200, 20);
			comeBackDateChooser.setBackground(backgroundColor);
			comeBackDateChooser.setBorder(null);
			comeBackDateChooser.setFont(new Font("Tahoma", Font.PLAIN, 12));
			JTextFieldDateEditor editor = (JTextFieldDateEditor)comeBackDateChooser.getDateEditor();
			editor.setEditable(false);
			
		}
		return comeBackDateChooser;
	}
	private JLabel getLblcomeBackDate() {
		if (lblcomeBackDate == null) {
			lblcomeBackDate = new JLabel("D\u00EDa de retorno");
			lblcomeBackDate.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblcomeBackDate.setBounds(20, 266, 99, 14);
		}
		return lblcomeBackDate;
	}
	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
			panel_1.setBounds(270, 318, 450, 41);
			panel_1.setLayout(null);
			panel_1.add(getBtnNewButton());
			panel_1.add(getBtnEliminar());
			panel_1.add(getBtnCancelar_1());
			panel_1.setBackground(backgroundColor);
		}
		
		return panel_1;
	}
	private JButton getBtnCancelar_1() {
		if (btnCancelar_1 == null) {
			btnCancelar_1 = new JButton("Cancelar");
			btnCancelar_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
			btnCancelar_1.setBackground(Color.WHITE);
			btnCancelar_1.setBounds(349, 11, 91, 23);
		}
		return btnCancelar_1;
	}
	private JSeparator getSeparator() {
		if (separator == null) {
			separator = new JSeparator();
			separator.setForeground(Color.BLACK);
			separator.setBounds(20, 69, 200, 2);
			separator.setBackground(backgroundColor);
		}
		return separator;
	}
	private JSeparator getSeparator_1() {
		if (separator_1 == null) {
			separator_1 = new JSeparator();
			separator_1.setForeground(Color.BLACK);
			separator_1.setBounds(20, 117, 200, 2);
			separator_1.setBackground(backgroundColor);
		}
		return separator_1;
	}
	private JSeparator getSeparator_3() {
		if (separator_3 == null) {
			separator_3 = new JSeparator();
			separator_3.setForeground(Color.BLACK);
			separator_3.setBounds(20, 171, 200, 2);
			separator_3.setBackground(backgroundColor);
		}
		return separator_3;
	}
	private JSeparator getSeparator_4_1() {
		if (separator_4 == null) {
			separator_4 = new JSeparator();
			separator_4.setForeground(Color.BLACK);
			separator_4.setBackground(SystemColor.menu);
			separator_4.setBounds(20, 69, 200, 2);
		}
		return separator_4;
	}
	private JSeparator getSeparator_5() {
		if (separator_5 == null) {
			separator_5 = new JSeparator();
			separator_5.setForeground(Color.BLACK);
			separator_5.setBackground(SystemColor.menu);
			separator_5.setBounds(20, 117, 200, 2);
		}
		return separator_5;
	}
	private JSeparator getSeparator_7() {
		if (separator_7 == null) {
			separator_7 = new JSeparator();
			separator_7.setForeground(Color.BLACK);
			separator_7.setBackground(SystemColor.menu);
			separator_7.setBounds(20, 171, 200, 2);
		}
		return separator_7;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(null);
			panel.setBorder(new LineBorder(new Color(0, 0, 0)));
			panel.setBackground(Color.WHITE);
			panel.setBounds(270, 318, 450, 41);
			panel.add(getButton());
			panel.add(getButton_1());
			panel.add(getButton_2());
		}
		return panel;
	}
	private JButton getButton() {
		if (button == null) {
			button = new JButton("Editar");
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int index = studentListTable.getSelectedRow();
					if(index != -1){
						EditStudent window = new EditStudent(Faculty.getInstance().getStudents().get(index),studentModel);
						window.setVisible(true);
					}
				}
			});
			button.setFont(new Font("Tahoma", Font.PLAIN, 13));
			button.setBackground(Color.WHITE);
			button.setBounds(147, 11, 91, 23);
		}
		return button;
	}
	private JButton getButton_1() {
		if (button_1 == null) {
			button_1 = new JButton("Eliminar");
			button_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					int index =	studentListTable.getSelectedRow();
					Faculty fac = Faculty.getInstance();
					PersonTableModel model = getStudentModel();
					String s = (String) model.getValueAt(index, 0);
					fac.removePerson(s);
					studentModel.refreshStudent(fac.getStudents());					
				}
			});
			button_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
			button_1.setBackground(Color.WHITE);
			button_1.setBounds(248, 11, 91, 23);
		}
		return button_1;
	}
	private JButton getButton_2() {
		if (button_2 == null) {
			button_2 = new JButton("Cancelar");
			button_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
			button_2.setBackground(Color.WHITE);
			button_2.setBounds(349, 11, 91, 23);
		}
		return button_2;
	}
}
