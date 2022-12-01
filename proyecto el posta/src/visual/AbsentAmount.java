package visual;

import java.awt.BorderLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import classes.Faculty;

import com.toedter.calendar.JDateChooser;

import javax.swing.border.LineBorder;

import java.awt.Color;

import javax.swing.SwingConstants;

import utils.AbsentModel;
import utils.PeriodTableModel;
import utils.PeriodValidator;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Date;
import java.awt.CardLayout;

import javax.swing.JTabbedPane;

public class AbsentAmount extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel_1;
	private JScrollPane scrollPane;
	private JTable table;
	private JLabel lblCantidadTotalDe;
	private JLabel lblAnswer;
	private final JPanel contentPanel = new JPanel();
	private JPanel panel;
	private JScrollPane scrollPane_1;
	private JTable table_1;
	private AbsentModel absentModel;
	private JTabbedPane tabbedPane;
	private JPanel panel_2;
	private JScrollPane scrollPane_2;
	private JTable table_2;
	private PeriodTableModel classModel;
	private PeriodTableModel vacationModel;
	/**
	 * Create the dialog.
	 */
	public AbsentAmount() {
		setTitle("Cantidad de ausentes");
		setBounds(100, 100, 680, 450);
		setIconImage(Toolkit.getDefaultToolkit().getImage(".\\src\\img\\logo mejorado.png"));
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		contentPanel.setLayout(null);
		contentPanel.add(getPanel_1());
		contentPanel.add(getTabbedPane());
		setResizable(false);
		
	}
	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Listado de ausentes", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel_1.setBounds(10, 190, 653, 205);
			panel_1.setLayout(null);
			panel_1.add(getScrollPane());
			panel_1.add(getLblCantidadTotalDe());
			panel_1.add(getLblAnswer());
		}
		return panel_1;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 21, 633, 140);
			scrollPane.setViewportView(getTable());
		}
		return scrollPane;
	}
	
	private JTable getTable() {
		if (table == null) {
			table = new JTable();
			table.setModel(getAbsentModel());
			table.getColumnModel().getColumn(0).setResizable(false);
			table.getColumnModel().getColumn(1).setResizable(false);
			table.getColumnModel().getColumn(2).setResizable(false);
		}
		return table;
	}
	
	private AbsentModel getAbsentModel(){
		if(absentModel == null){
			absentModel = new AbsentModel();
			absentModel.addColumn("Fecha");
			absentModel.addColumn("Turno");
			absentModel.addColumn("Persona");
		}
		return absentModel;
	}
	
	private JLabel getLblCantidadTotalDe() {
		if (lblCantidadTotalDe == null) {
			lblCantidadTotalDe = new JLabel("Cantidad total de ausentes: ");
			lblCantidadTotalDe.setHorizontalAlignment(SwingConstants.CENTER);
			lblCantidadTotalDe.setBounds(10, 177, 165, 14);
		}
		return lblCantidadTotalDe;
	}
	private JLabel getLblAnswer() {
		if (lblAnswer == null) {
			lblAnswer = new JLabel("");
			lblAnswer.setHorizontalAlignment(SwingConstants.CENTER);
			lblAnswer.setBorder(new LineBorder(Color.black, 1));
			lblAnswer.setBounds(186, 172, 93, 24);
		}
		return lblAnswer;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Lista de periodos de clases", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel.setLayout(new CardLayout(0, 0));
			panel.add(getScrollPane_1(), "name_183238331445600");
		}
		return panel;
	}
	private JScrollPane getScrollPane_1() {
		if (scrollPane_1 == null) {
			scrollPane_1 = new JScrollPane();
			scrollPane_1.setViewportView(getTable_1());
		}
		return scrollPane_1;
	}
	private JTable getTable_1() {
		if (table_1 == null) {
			table_1 = new JTable();
			table_1.setModel(getClassModel());
			table_1.setFillsViewportHeight(true);
		}
		return table_1;
	}
	private PeriodTableModel getClassModel() {
		if(classModel == null){
			classModel = new PeriodTableModel();
			classModel.setRowCount(0);
			classModel.addColumn("No");
			classModel.addColumn("Fecha inicio");
			classModel.addColumn("Fecha fin");
			classModel.refreshClassPeriod(Faculty.getInstance().getClassPeriods());
		}
		return classModel;
	}
	private JTabbedPane getTabbedPane() {
		if (tabbedPane == null) {
			tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			tabbedPane.setBounds(10, 11, 654, 178);
			tabbedPane.addTab("New tab", null, getPanel(), null);
			tabbedPane.addTab("New tab", null, getPanel_2(), null);
		}
		return tabbedPane;
	}
	private JPanel getPanel_2() {
		if (panel_2 == null) {
			panel_2 = new JPanel();
			panel_2.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Lista de periodos vacacionales", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel_2.setLayout(new CardLayout(0, 0));
			panel_2.add(getScrollPane_2(), "name_189871109574800");
		}
		return panel_2;
	}
	private JScrollPane getScrollPane_2() {
		if (scrollPane_2 == null) {
			scrollPane_2 = new JScrollPane();
			scrollPane_2.setViewportView(getTable_2());
		}
		return scrollPane_2;
	}
	private JTable getTable_2() {
		if (table_2 == null) {
			table_2 = new JTable();
			table_2.setModel(getVacationModel());
			table_2.setFillsViewportHeight(true);
		}
		return table_2;
	}
	
	private PeriodTableModel getVacationModel() {
		if(vacationModel == null){
			vacationModel = new PeriodTableModel();
			vacationModel.setRowCount(0);
			vacationModel.addColumn("No");
			vacationModel.addColumn("Fecha inicio");
			vacationModel.addColumn("Fecha fin");
			vacationModel.refreshVacationPeriod(Faculty.getInstance().getVacationPeriods());
		}
		return vacationModel;
	}
}
