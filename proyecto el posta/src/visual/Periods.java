package visual;



import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.TitledBorder;

import java.awt.Color;

import classes.ClassPeriod;
import classes.Faculty;

import classes.VacationPeriod;

import com.toedter.calendar.JDateChooser;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JScrollPane;


import java.awt.Toolkit;

import javax.swing.border.LineBorder;

import utils.PeriodTableModel;
import utils.PeriodValidator;



import utils.VolunteerWorkersModel;


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;
import java.util.ArrayList;
import java.util.Date;

public class Periods extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTabbedPane tabbedPane;
	private JPanel classPeriodPanel;
	private JPanel vacationPeriodPanel;
	private JPanel newClassPeriodPanel;
	private JPanel listClassPeriodPanel;
	private JDateChooser classPeriodStart;
	private JDateChooser classPeriodEnd;
	private JLabel lblFechaInicio;
	private JLabel lblFechaFin;
	private JButton btnOrganizar;
	private JPanel panel_1;
	private JDateChooser vacationPeriodStart;
	private JDateChooser vacationPeriodEnd;
	private JLabel label;
	private JLabel label_1;
	private JPanel panel_2;
	private JScrollPane scrollPane;
	private JTable table;
	private JButton btnAgregar;
	private JPanel panel_3;
	private JScrollPane scrollPane_1;
	private JTable table_1;
	private JScrollPane scrollPane_2;
	private JTable classPeriodTable;
	private JButton btnQuitar;
	private JButton btnEliminar;
	private JButton btnOrganizar_1;
	private JPanel panel;
	private JButton button;
	private JButton button_1;
	private JButton btnTurnos;
	private JPanel panel_4;
	private JButton button_3;
	private JButton btnEditar;

	private PeriodTableModel classPeriodModel;
	private PeriodTableModel vacationPeriodModel;


	private static Faculty faculty;
	private static VolunteerWorkersModel volunteerWorkersModel;


	/**
	 * Create the dialog.
	 */
	public Periods() {
		faculty = Faculty.getInstance();
		setIconImage(Toolkit.getDefaultToolkit().getImage(".\\src\\img\\logo mejorado.png"));
		setTitle("Gesti\u00F3n de periodos");
		setBounds(100, 100, 630, 460);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		getContentPane().add(getTabbedPane_1());
		
	}
	private JTabbedPane getTabbedPane_1() {
		if (tabbedPane == null) {
			tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			tabbedPane.setBounds(0, 0, 629, 422);
			tabbedPane.addTab("Periodo de Clases", null, getClassPeriodPanel(), null);
			tabbedPane.addTab("Periodo Vacacional", null, getVacationPeriodPanel(), null);
		}
		return tabbedPane;
	}
	private JPanel getClassPeriodPanel() {
		if (classPeriodPanel == null) {
			classPeriodPanel = new JPanel();
			classPeriodPanel.setLayout(null);
			classPeriodPanel.add(getNewClassPeriodPanel());
			classPeriodPanel.add(getListClassPeriodPanel());
			classPeriodPanel.add(getPanel());
		}
		return classPeriodPanel;
	}
	private JPanel getVacationPeriodPanel() {
		if (vacationPeriodPanel == null) {
			vacationPeriodPanel = new JPanel();
			vacationPeriodPanel.setLayout(null);
			vacationPeriodPanel.add(getPanel_1());
			vacationPeriodPanel.add(getPanel_3());
			vacationPeriodPanel.add(getPanel_4());
		}
		return vacationPeriodPanel;
	}
	private JPanel getNewClassPeriodPanel() {
		if (newClassPeriodPanel == null) {
			newClassPeriodPanel = new JPanel();
			newClassPeriodPanel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Nuevo periodo de clases", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			newClassPeriodPanel.setBounds(10, 11, 601, 104);
			newClassPeriodPanel.setLayout(null);
			newClassPeriodPanel.add(getClassPeriodStart());
			newClassPeriodPanel.add(getClassPeriodEnd());
			newClassPeriodPanel.add(getLblFechaInicio());
			newClassPeriodPanel.add(getLblFechaFin());
			newClassPeriodPanel.add(getBtnOrganizar());
		}
		return newClassPeriodPanel;
	}
	private JPanel getListClassPeriodPanel() {
		if (listClassPeriodPanel == null) {
			listClassPeriodPanel = new JPanel();
			listClassPeriodPanel.setBounds(10, 126, 601, 203);
			listClassPeriodPanel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Listado de periodos de clase", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			listClassPeriodPanel.setLayout(new CardLayout(0, 0));
			listClassPeriodPanel.add(getScrollPane_2(), "name_1302311777791100");
		}
		return listClassPeriodPanel;
	}
	private JDateChooser getClassPeriodStart() {
		if (classPeriodStart == null) {
			classPeriodStart = new JDateChooser();
			classPeriodStart.setBounds(101, 32, 190, 20);
		}
		return classPeriodStart;
	}
	private JDateChooser getClassPeriodEnd() {
		if (classPeriodEnd == null) {
			classPeriodEnd = new JDateChooser();
			classPeriodEnd.setBounds(376, 32, 190, 20);
		}
		return classPeriodEnd;
	}
	private JLabel getLblFechaInicio() {
		if (lblFechaInicio == null) {
			lblFechaInicio = new JLabel("Fecha Inicio:");
			lblFechaInicio.setBounds(20, 35, 81, 14);
		}
		return lblFechaInicio;
	}
	private JLabel getLblFechaFin() {
		if (lblFechaFin == null) {
			lblFechaFin = new JLabel("Fecha Fin:");
			lblFechaFin.setBounds(312, 35, 66, 14);
		}
		return lblFechaFin;
	}
	private JButton getBtnOrganizar() {
		if (btnOrganizar == null) {
			btnOrganizar = new JButton("Insertar");
			btnOrganizar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Date start = classPeriodStart.getDate();
					Date end = classPeriodEnd.getDate();
					try
					{
						PeriodValidator.checkPeriods(start, end);
						Faculty.getInstance().planningClassPeriod(start, end);
						
						classPeriodModel.refreshClassPeriod(faculty.getClassPeriods());
					}
					catch(IllegalArgumentException ex)
					{
						JOptionPane.showMessageDialog(null, ex.getMessage());
					}
				}
			});
			btnOrganizar.setBounds(500, 70, 91, 23);
		}
		return btnOrganizar;
	}
	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setLayout(null);
			panel_1.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Nuevo periodo vacacional", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel_1.setBounds(10, 11, 240, 372);
			panel_1.add(getVacationPeriodStart());
			panel_1.add(getVacationPeriodEnd());
			panel_1.add(getLabel());
			panel_1.add(getLabel_1());
			panel_1.add(getPanel_2());
			panel_1.add(getBtnOrganizar_1());
		}
		return panel_1;
	}
	private JDateChooser getVacationPeriodStart() {
		if (vacationPeriodStart == null) {
			vacationPeriodStart = new JDateChooser();
			vacationPeriodStart.setBounds(20, 48, 197, 20);
		}
		return vacationPeriodStart;
	}
	private JDateChooser getVacationPeriodEnd() {
		if (vacationPeriodEnd == null) {
			vacationPeriodEnd = new JDateChooser();
			vacationPeriodEnd.setBounds(20, 96, 197, 20);
		}
		return vacationPeriodEnd;
	}
	private JLabel getLabel() {
		if (label == null) {
			label = new JLabel("Fecha Inicio:");
			label.setBounds(20, 29, 91, 14);
		}
		return label;
	}
	private JLabel getLabel_1() {
		if (label_1 == null) {
			label_1 = new JLabel("Fecha Fin:");
			label_1.setBounds(20, 78, 91, 14);
		}
		return label_1;
	}
	private JPanel getPanel_2() {
		if (panel_2 == null) {
			panel_2 = new JPanel();
			panel_2.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Listado trabajadores", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel_2.setBounds(10, 139, 220, 157);
			panel_2.setLayout(null);
			panel_2.add(getScrollPane_1());
			panel_2.add(getBtnAgregar());
			panel_2.add(getBtnQuitar());
		}
		return panel_2;
	}
	private JScrollPane getScrollPane_1() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 20, 200, 98);
			scrollPane.setViewportView(getTable());
		}
		return scrollPane;
	}
	//Tabla de trabajadores voluntarios-----------------------------------------------------------------------------------------------------------------
	private JTable getTable() {
		if (table == null) {
			table = new JTable();
			table.setFillsViewportHeight(true);
			table.setModel(getVolunteerWorkersModel());
		}
		return table;
	}
	public static VolunteerWorkersModel getVolunteerWorkersModel(){
		if(volunteerWorkersModel == null){
			volunteerWorkersModel = new VolunteerWorkersModel();
			volunteerWorkersModel.addColumn("CI");
			volunteerWorkersModel.addColumn("Nombre");
			
			volunteerWorkersModel.refresh(faculty.getVacationWatches());
		}
		
		return volunteerWorkersModel;
	}
	//------------------------------------------------------------------------------------------------------------------
	private JButton getBtnAgregar() {
		if (btnAgregar == null) {
			btnAgregar = new JButton("Agregar");
			btnAgregar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					WorkersToVacationPeriod wtvp = new WorkersToVacationPeriod();
					wtvp.setVisible(true);
				}
			});
			btnAgregar.setBounds(44, 129, 80, 23);
		}
		return btnAgregar;
	}
	private JPanel getPanel_3() {
		if (panel_3 == null) {
			panel_3 = new JPanel();
			panel_3.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Listado de periodos vacacionales", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel_3.setBounds(264, 11, 347, 332);
			panel_3.setLayout(new CardLayout(0, 0));
			panel_3.add(getScrollPane_1_1(), "name_1304084981799700");
		}
		return panel_3;
	}
	private JScrollPane getScrollPane_1_1() {
		if (scrollPane_1 == null) {
			scrollPane_1 = new JScrollPane();
			scrollPane_1.setViewportView(getTable_1());
			
		}
		return scrollPane_1;
	}
	//Tabla de peridos vacacionales------------------------------------------------------------------------------------------------------------------------
	private JTable getTable_1() {
		if (table_1 == null) {
			table_1 = new JTable();
			table_1.setFillsViewportHeight(true);
			table_1.setModel(getVacationPeriodModel());
		}
		return table_1;
	}
	
	private PeriodTableModel getVacationPeriodModel(){
		if(vacationPeriodModel == null){
			vacationPeriodModel = new PeriodTableModel();
			vacationPeriodModel.addColumn("No");
			vacationPeriodModel.addColumn("Fecha de inicio");
			vacationPeriodModel.addColumn("Fecha de fin");
			
			ArrayList<VacationPeriod> periods= faculty.getVacationPeriods();
			
			vacationPeriodModel.refreshVacationPeriod(periods);
		}
		
		return vacationPeriodModel;
	}
	//-----------------------------------------------------------------------------------------------------------------------------------------------------
	private JScrollPane getScrollPane_2() {
		if (scrollPane_2 == null) {
			scrollPane_2 = new JScrollPane();
			scrollPane_2.setViewportView(getClassPeriodTable());
		}
		return scrollPane_2;
	}
	
	//Tabla de Periodos lectivos--------------------------------------------------------------------------------
	private JTable getClassPeriodTable() {
		if (classPeriodTable == null) {
			classPeriodTable = new JTable();
			classPeriodTable.setFillsViewportHeight(true);
			classPeriodTable.setModel(getClassPeriodModel());
		}
		return classPeriodTable;
	}
	
	private PeriodTableModel getClassPeriodModel(){
		if(classPeriodModel == null){
			classPeriodModel = new PeriodTableModel();
			classPeriodModel.addColumn("No");
			classPeriodModel.addColumn("Fecha de inicio");
			classPeriodModel.addColumn("Fecha de fin");		
			
			ArrayList<ClassPeriod> classPeriods = faculty.getClassPeriods(); 
			
			classPeriodModel.refreshClassPeriod(classPeriods);
		}
		return classPeriodModel;
	}
		
	
	//----------------------------------------------------------------------------------------------------------
	private JButton getBtnQuitar() {
		if (btnQuitar == null) {
			btnQuitar = new JButton("Eliminar");
			btnQuitar.setBounds(130, 129, 80, 23);
		}
		return btnQuitar;
	}
	private JButton getBtnEliminar() {
		if (btnEliminar == null) {
			btnEliminar = new JButton("Cancelar");
			btnEliminar.setBounds(249, 6, 91, 23);
		}
		return btnEliminar;
	}
	private JButton getBtnOrganizar_1() {
		if (btnOrganizar_1 == null) {
			btnOrganizar_1 = new JButton("Organizar");
			btnOrganizar_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Date start = vacationPeriodStart.getDate();
					Date end = vacationPeriodEnd.getDate();
					try
					{
						PeriodValidator.checkPeriods(start, end);
						Faculty.getInstance().planningVacationPeriod(start, end);
						vacationPeriodModel.refreshVacationPeriod(faculty.getVacationPeriods());
					}
					catch(IllegalArgumentException ex)
					{
						JOptionPane.showMessageDialog(null, ex.getMessage());
					}
				}
			});
			btnOrganizar_1.setBounds(139, 338, 91, 23);
		}
		return btnOrganizar_1;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBorder(new LineBorder(new Color(0, 0, 0)));
			panel.setBounds(10, 340, 601, 40);
			panel.setLayout(null);
			panel.add(getButton());
			panel.add(getButton_1());
			panel.add(getBtnTurnos());
		}
		return panel;
	}
	private JButton getButton() {
		if (button == null) {
			button = new JButton("Cancelar");
			button.setBounds(500, 11, 91, 23);
		}
		return button;
	}
	private JButton getButton_1() {
		if (button_1 == null) {
			button_1 = new JButton("Eliminar");
			button_1.setBounds(399, 11, 91, 23);
		}
		return button_1;
	}
	private JButton getBtnTurnos() {
		if (btnTurnos == null) {
			btnTurnos = new JButton("Ver turnos");
			btnTurnos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int index = classPeriodTable.getSelectedRow();
					if(index != -1){		
						PeriodAsignmentList window = new PeriodAsignmentList(faculty.getClassPeriods().get(index));
						window.setVisible(true);
					}
				}
			});
			//btnEditar_1.setEnabled(false);
			btnTurnos.setBounds(298, 11, 91, 23);
		}
		return btnTurnos;
	}
	private JPanel getPanel_4() {
		if (panel_4 == null) {
			panel_4 = new JPanel();
			panel_4.setBorder(new LineBorder(new Color(0, 0, 0)));
			panel_4.setBounds(260, 347, 351, 36);
			panel_4.setLayout(null);
			panel_4.add(getBtnEliminar());
			panel_4.add(getButton_3());
			panel_4.add(getBtnEditar());
		}
		return panel_4;
	}
	private JButton getButton_3() {
		if (button_3 == null) {
			button_3 = new JButton("Eliminar");
			button_3.setBounds(148, 6, 91, 23);
		}
		return button_3;
	}
	private JButton getBtnEditar() {
		if (btnEditar == null) {
			btnEditar = new JButton("Vet turnos");
			btnEditar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int index = table_1.getSelectedRow();
					if(index != -1){		
						PeriodAsignmentList window = new PeriodAsignmentList(faculty.getVacationPeriods().get(index));
						window.setVisible(true);
					}
				}
			});
			btnEditar.setBounds(47, 6, 91, 23);
		}
		return btnEditar;
	}

}
