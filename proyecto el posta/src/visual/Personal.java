package visual;


import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.ButtonGroup;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JRadioButton;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import utils.StatesWorker;
import utils.StatesStudent;

import javax.swing.JScrollPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.border.TitledBorder;

import java.awt.Color;
import java.awt.Toolkit;

import classes.Faculty;
import classes.Student;

import com.toedter.calendar.JDateChooser;

import javax.swing.border.LineBorder;

import java.awt.CardLayout;
import java.util.ArrayList;

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
	private JTextField studentSecLastName;
	private JLabel lblSecLastName;
	private JRadioButton studentFemale;
	private JRadioButton studentMale;
	private ButtonGroup studentsGroupFyM;
	private ButtonGroup workersGroupFyM;
	private JLabel lblWorkerLastName;
	private JTextField workerName;
	private JLabel lblworkerSecLastName;
	private JTextField workerLastName;
	private JLabel lblWorkerName;
	private JLabel lblWorkerID;
	private JTextField workerSecLastName;
	private JTextField workerID;
	private JComboBox<Object> workerState;
	private JComboBox<Object> studentState;
	private JPanel studentListPanel;
	private JScrollPane scrollPane;
	private JTable studentListTable;
	private JButton newStudent;
	private JButton deleteStudent;
	private JButton btnEditar;
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
	private JPanel panel;
	private JButton btnCancelar;
	private JPanel panel_1;
	private JButton btnCancelar_1;
	DefaultTableModel studentModel;
	private Faculty faculty = Faculty.getInstance();
	
	public Personal() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(".\\src\\img\\logo mejorado.png"));
		setTitle("Gesti\u00F3n del personal");
		setBounds(100, 100, 740, 470);
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
			studentPanel.add(getPanel());
			studentsGroupFyM();
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
		}
		return workerPanel;
	}
	private JLabel getLblStudentName() {
		if (lblStudentName == null) {
			lblStudentName = new JLabel("Nombre:");
			lblStudentName.setBounds(20, 30, 71, 14);
			lblStudentName.setHorizontalAlignment(SwingConstants.LEFT);
		}
		return lblStudentName;
	}
	private JLabel getLblLastName() {
		if (lblLastName == null) {
			lblLastName = new JLabel("1er apellido:");
			lblLastName.setBounds(20, 76, 71, 14);
			lblLastName.setHorizontalAlignment(SwingConstants.LEFT);
		}
		return lblLastName;
	}
	private JLabel getLblStudentID() {
		if (lblStudentID == null) {
			lblStudentID = new JLabel("CI:");
			lblStudentID.setBounds(20, 170, 71, 14);
			lblStudentID.setHorizontalAlignment(SwingConstants.LEFT);
		}
		return lblStudentID;
	}
	private JTextField getStudentName() {
		if (studentName == null) {
			studentName = new JTextField();
			studentName.setBounds(20, 46, 200, 20);
			studentName.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent arg0) {
					studentName.setText("");
				}
			});
			
			studentName.setHorizontalAlignment(SwingConstants.LEFT);
			studentName.setColumns(10);
		}
		return studentName;
	}
	private JTextField getStudentLastName() {
		if (studentLastName == null) {
			studentLastName = new JTextField();
			studentLastName.setBounds(20, 92, 200, 20);
			studentLastName.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent arg0) {
					studentLastName.setText("");
				}
			});
			studentLastName.setHorizontalAlignment(SwingConstants.LEFT);
			studentLastName.setColumns(10);
		}
		return studentLastName;
	}
	private JTextField getStudentID() {
		if (studentID == null) {
			studentID = new JTextField();
			studentID.setBounds(20, 188, 200, 20);
			studentID.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent arg0) {
					studentID.setText("");
				}
			});
			studentID.setHorizontalAlignment(SwingConstants.LEFT);
			studentID.setColumns(10);
		}
		return studentID;
	}
	private JTextField getStudentSecLastName() {
		if (studentSecLastName == null) {
			studentSecLastName = new JTextField();
			studentSecLastName.setBounds(20, 140, 200, 20);
			studentSecLastName.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent arg0) {
					studentSecLastName.setText("");
				}
			});
			studentSecLastName.setHorizontalAlignment(SwingConstants.LEFT);
			studentSecLastName.setColumns(10);
		}
		return studentSecLastName;
	}
	private JLabel getLblSecLastName() {
		if (lblSecLastName == null) {
			lblSecLastName = new JLabel("2do apellido:");
			lblSecLastName.setBounds(20, 122, 71, 14);
			lblSecLastName.setHorizontalAlignment(SwingConstants.LEFT);
		}
		return lblSecLastName;
	}
	private JRadioButton getStudentFemale() {
		if (studentFemale == null) {
			studentFemale = new JRadioButton("Femenina");
			studentFemale.setBounds(20, 212, 86, 23);
		}
		return studentFemale;
	}
	private JRadioButton getStudentMale() {
		if (studentMale == null) {
			studentMale = new JRadioButton("Masculino");
			studentMale.setBounds(110, 212, 86, 23);
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
			lblWorkerLastName = new JLabel("1er apellido:");
			lblWorkerLastName.setBounds(20, 76, 71, 14);
			lblWorkerLastName.setHorizontalAlignment(SwingConstants.LEFT);
		}
		return lblWorkerLastName;
	}
	private JTextField getWorkerName() {
		if (workerName == null) {
			workerName = new JTextField();
			workerName.setBounds(20, 46, 200, 20);
			workerName.setColumns(10);
		}
		return workerName;
	}
	private JLabel getLblworkerSecLastName() {
		if (lblworkerSecLastName == null) {
			lblworkerSecLastName = new JLabel("2do apellido:");
			lblworkerSecLastName.setBounds(20, 122, 71, 14);
			lblworkerSecLastName.setHorizontalAlignment(SwingConstants.LEFT);
		}
		return lblworkerSecLastName;
	}
	private JTextField getWorkerLastName() {
		if (workerLastName == null) {
			workerLastName = new JTextField();
			workerLastName.setBounds(20, 92, 200, 20);
			workerLastName.setColumns(10);
		}
		return workerLastName;
	}
	private JLabel getLblWorkerName() {
		if (lblWorkerName == null) {
			lblWorkerName = new JLabel("Nombre: ");
			lblWorkerName.setBounds(20, 30, 59, 14);
			lblWorkerName.setHorizontalAlignment(SwingConstants.LEFT);
		}
		return lblWorkerName;
	}
	private JLabel getLblWorkerID() {
		if (lblWorkerID == null) {
			lblWorkerID = new JLabel("CI:");
			lblWorkerID.setBounds(20, 170, 59, 14);
			lblWorkerID.setHorizontalAlignment(SwingConstants.LEFT);
		}
		return lblWorkerID;
	}
	private JTextField getWorkerSecLastName() {
		if (workerSecLastName == null) {
			workerSecLastName = new JTextField();
			workerSecLastName.setBounds(20, 140, 200, 20);
			workerSecLastName.setColumns(10);
		}
		return workerSecLastName;
	}
	private JTextField getWorkerID() {
		if (workerID == null) {
			workerID = new JTextField();
			workerID.setBounds(20, 188, 200, 20);
			workerID.setColumns(10);
		}
		return workerID;
	}
	private JComboBox<Object> getWorkerState() {
		if (workerState == null) {
			workerState = new JComboBox<Object>();
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
			workerState.setBounds(20, 263, 200, 20);
			//Converti el arreglo de enums a String
			StatesWorker list[] = StatesWorker.values();			
			String listShow[] = new String[list.length];
			//De momento esta hecho a fuerza bruta. Buscar un metodo para simplificar
			
			for(int i = 0; i < list.length; i++) 
			{
				listShow[i] = list[i].getName();
			}
			
			workerState.setModel(new DefaultComboBoxModel<Object>(listShow));
			workerState.setSelectedIndex(-1);
		}
		return workerState;
	}
	private JComboBox<Object> getStudentState() {
		if (studentState == null) {
			studentState = new JComboBox<Object>();
			studentState.setBounds(20, 263, 200, 20);
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
		}
		return studentState;
	}
	private JPanel getStudentListPanel() {
		if (studentListPanel == null) {
			studentListPanel = new JPanel();
			studentListPanel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Listado de estudiantes", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			studentListPanel.setBounds(270, 10, 450, 325);
			studentListPanel.setLayout(new CardLayout(0, 0));
			studentListPanel.add(getScrollPane(), "name_1304743514853800");
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
	private JTable getStudentListTable() {
		if (studentListTable == null) {
			
			studentListTable = new JTable();
			studentListTable.setFillsViewportHeight(true);
			studentListTable.setModel(getStudentModel());
			
			studentListTable.getColumnModel().getColumn(3).setPreferredWidth(40);
			studentListTable.getColumnModel().getColumn(4).setPreferredWidth(60);
		}
		return studentListTable;
	}
	private DefaultTableModel getStudentModel(){
		if(studentModel == null){
			studentModel = new DefaultTableModel();
			studentModel.addColumn("CI");
			studentModel.addColumn("Nombre");
			studentModel.addColumn("Apellido");
			studentModel.addColumn("Sexo");
			studentModel.addColumn("Estado");
			String student[] = new String[5];
			
			ArrayList<Student> students= faculty.getStudents();
			
			for(Student s: students){
				student[0] = s.getId();
				student[1] = s.getName();
				student[2] = s.getName(); 
				student[3] = s.getSex().getName(); 
				student[4] = s.getActualState().getName();
				studentModel.addRow(student);
			}
		}
		
		return studentModel;
	}
	
	private JButton getNewStudent() {
		if (newStudent == null) {
			newStudent = new JButton("Agregar");
			newStudent.setBounds(135, 343, 85, 23);
		}
		return newStudent;
	}
	private JButton getDeleteStudent() {
		if (deleteStudent == null) {
			deleteStudent = new JButton("Eliminar");
			deleteStudent.setBounds(248, 11, 91, 23);
			deleteStudent.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				}
			});
		}
		return deleteStudent;
	}
	private JButton getBtnEditar() {
		if (btnEditar == null) {
			btnEditar = new JButton("Editar");
			btnEditar.setEnabled(false);
			btnEditar.setBounds(147, 11, 91, 23);
		}
		return btnEditar;
	}
	private JPanel getNewStudentPanel() {
		if (newStudentPanel == null) {
			newStudentPanel = new JPanel();
			newStudentPanel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Nuevo estudiante", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			newStudentPanel.setBounds(10, 10, 240, 377);
			newStudentPanel.setLayout(null);
			newStudentPanel.add(getLblStudentName());
			newStudentPanel.add(getLblLastName());
			newStudentPanel.add(getLblStudentID());
			newStudentPanel.add(getStudentName());
			newStudentPanel.add(getStudentLastName());
			newStudentPanel.add(getStudentID());
			newStudentPanel.add(getStudentSecLastName());
			newStudentPanel.add(getLblSecLastName());
			newStudentPanel.add(getStudentFemale());
			newStudentPanel.add(getStudentMale());
			newStudentPanel.add(getStudentState());
			newStudentPanel.add(getNewStudent());
			newStudentPanel.add(getLblStudentState());
		}
		return newStudentPanel;
	}
	private JLabel getLblStudentState() {
		if (lblStudentState == null) {
			lblStudentState = new JLabel("Estado:");
			lblStudentState.setBounds(20, 245, 46, 14);
		}
		return lblStudentState;
	}
	private JPanel getPanel_2() {
		if (newWorkerPanel == null) {
			newWorkerPanel = new JPanel();
			newWorkerPanel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Nuevo trabajador", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			newWorkerPanel.setBounds(10, 10, 240, 377);
			newWorkerPanel.setLayout(null);
			newWorkerPanel.add(getLblWorkerLastName());
			newWorkerPanel.add(getWorkerName());
			newWorkerPanel.add(getLblworkerSecLastName());
			newWorkerPanel.add(getWorkerLastName());
			newWorkerPanel.add(getLblWorkerName());
			newWorkerPanel.add(getLblWorkerID());
			newWorkerPanel.add(getWorkerSecLastName());
			newWorkerPanel.add(getWorkerID());
			newWorkerPanel.add(getWorkerState());
			newWorkerPanel.add(getLblWorkerState());
			newWorkerPanel.add(getWorkerFemale());
			newWorkerPanel.add(getWorkerMale());
			newWorkerPanel.add(getNewWorker());
			newWorkerPanel.add(getComeBackDateChooser());
			newWorkerPanel.add(getLblcomeBackDate());
		}
		return newWorkerPanel;
	}
	private JLabel getLblWorkerState() {
		if (lblWorkerState == null) {
			lblWorkerState = new JLabel("Estado:");
			lblWorkerState.setBounds(20, 245, 59, 14);
		}
		return lblWorkerState;
	}
	private JRadioButton getWorkerFemale() {
		if (workerFemale == null) {
			workerFemale = new JRadioButton("Femenina");
			workerFemale.setBounds(20, 212, 86, 23);
		}
		return workerFemale;
	}
	private JRadioButton getWorkerMale() {
		if (workerMale == null) {
			workerMale = new JRadioButton("Masculino");
			workerMale.setBounds(110, 212, 86, 23);
		}
		return workerMale;
	}
	private JButton getNewWorker() {
		if (newWorker == null) {
			newWorker = new JButton("Agregar");
			newWorker.setBounds(135, 343, 85, 23);
		}
		return newWorker;
	}
	private JPanel getPanel_5() {
		if (workerListPanel == null) {
			workerListPanel = new JPanel();
			workerListPanel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Lista de trabajadores", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			workerListPanel.setBounds(270, 10, 450, 325);
			workerListPanel.setLayout(new CardLayout(0, 0));
			workerListPanel.add(getScrollPane_1_1(), "name_1304922936460400");
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
	private JTable getTable_1() {
		if (workerListTable == null) {
			workerListTable = new JTable();
			workerListTable.setFillsViewportHeight(true);
			workerListTable.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null, null, null, null},
					{null, null, null, null, null, null},
					{null, null, null, null, null, null},
					{null, null, null, null, null, null},
					{null, null, null, null, null, null},
					{null, null, null, null, null, null},
					{null, null, null, null, null, null},
					{null, null, null, null, null, null},
					{null, null, null, null, null, null},
					{null, null, null, null, null, null},
					{null, null, null, null, null, null},
					{null, null, null, null, null, null},
					{null, null, null, null, null, null},
					{null, null, null, null, null, null},
					{null, null, null, null, null, null},
					{null, null, null, null, null, null},
					{null, null, null, null, null, null},
					{null, null, null, null, null, null},
				},
				new String[] {
					"ID", "Nombre", "Apellidos", "Sexo", "Estado", "Fecha de retorno"
				}
			));
			workerListTable.getColumnModel().getColumn(0).setPreferredWidth(67);
			workerListTable.getColumnModel().getColumn(3).setPreferredWidth(35);
			workerListTable.getColumnModel().getColumn(4).setPreferredWidth(44);
			workerListTable.getColumnModel().getColumn(5).setPreferredWidth(95);
		}
		return workerListTable;
	}
	private JButton getBtnEliminar() {
		if (btnEliminar == null) {
			btnEliminar = new JButton("Eliminar");
			btnEliminar.setBounds(248, 11, 91, 23);
		}
		return btnEliminar;
	}
	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("Editar");
			btnNewButton.setEnabled(false);
			btnNewButton.setBounds(147, 11, 91, 23);
		}
		return btnNewButton;
	}
	private JDateChooser getComeBackDateChooser() {
		if (comeBackDateChooser == null) {
			comeBackDateChooser = new JDateChooser();
			comeBackDateChooser.setEnabled(false);
			comeBackDateChooser.setBounds(20, 311, 200, 20);
		}
		return comeBackDateChooser;
	}
	private JLabel getLblcomeBackDate() {
		if (lblcomeBackDate == null) {
			lblcomeBackDate = new JLabel("D\u00EDa de retorno");
			lblcomeBackDate.setBounds(20, 293, 99, 14);
		}
		return lblcomeBackDate;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBorder(new LineBorder(new Color(0, 0, 0)));
			panel.setBounds(270, 340, 450, 45);
			panel.setLayout(null);
			panel.add(getBtnEditar());
			panel.add(getDeleteStudent());
			panel.add(getBtnCancelar());
		}
		return panel;
	}
	private JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton("Cancelar");
			btnCancelar.setBounds(349, 11, 91, 23);
		}
		return btnCancelar;
	}
	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
			panel_1.setBounds(270, 340, 450, 45);
			panel_1.setLayout(null);
			panel_1.add(getBtnNewButton());
			panel_1.add(getBtnEliminar());
			panel_1.add(getBtnCancelar_1());
		}
		return panel_1;
	}
	private JButton getBtnCancelar_1() {
		if (btnCancelar_1 == null) {
			btnCancelar_1 = new JButton("Cancelar");
			btnCancelar_1.setBounds(349, 11, 91, 23);
		}
		return btnCancelar_1;
	}
}
