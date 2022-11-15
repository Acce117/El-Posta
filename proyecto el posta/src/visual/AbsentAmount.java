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

import com.toedter.calendar.JDateChooser;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.SwingConstants;

public class AbsentAmount extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JPanel panel_1;
	private JDateChooser dateChooser;
	private JDateChooser dateChooser_1;
	private JLabel lblFechaInicio;
	private JLabel lblFechaFinal;
	private JScrollPane scrollPane;
	private JTable table;
	private JButton btnMostrarAusentes;
	private JLabel lblCantidadTotalDe;
	private JLabel lblNewLabel;
	private final JPanel contentPanel = new JPanel();
	/**
	 * Create the dialog.
	 */
	public AbsentAmount() {
		setTitle("Cantidad de ausentes");
		setBounds(100, 100, 680, 415);
		setIconImage(Toolkit.getDefaultToolkit().getImage(".\\src\\img\\logo mejorado.png"));
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		contentPanel.setLayout(null);
		contentPanel.add(getPanel());
		contentPanel.add(getPanel_1());
		setResizable(false);
		
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Rango de periodos", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel.setBounds(10, 11, 653, 108);
			panel.setLayout(null);
			panel.add(getDateChooser());
			panel.add(getDateChooser_1());
			panel.add(getLblFechaInicio());
			panel.add(getLblFechaFinal());
			panel.add(getBtnMostrarAusentes());
		}
		return panel;
	}
	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Listado de ausentes", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel_1.setBounds(10, 130, 653, 235);
			panel_1.setLayout(null);
			panel_1.add(getScrollPane());
			panel_1.add(getLblCantidadTotalDe());
			panel_1.add(getLblNewLabel());
		}
		return panel_1;
	}
	private JDateChooser getDateChooser() {
		if (dateChooser == null) {
			dateChooser = new JDateChooser();
			dateChooser.setBounds(93, 29, 230, 20);
		}
		return dateChooser;
	}
	private JDateChooser getDateChooser_1() {
		if (dateChooser_1 == null) {
			dateChooser_1 = new JDateChooser();
			dateChooser_1.setBounds(400, 29, 230, 20);
		}
		return dateChooser_1;
	}
	private JLabel getLblFechaInicio() {
		if (lblFechaInicio == null) {
			lblFechaInicio = new JLabel("Fecha inicio:");
			lblFechaInicio.setBounds(13, 32, 70, 14);
		}
		return lblFechaInicio;
	}
	private JLabel getLblFechaFinal() {
		if (lblFechaFinal == null) {
			lblFechaFinal = new JLabel("Fecha final:");
			lblFechaFinal.setBounds(333, 32, 70, 14);
		}
		return lblFechaFinal;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 23, 633, 159);
			scrollPane.setViewportView(getTable());
		}
		return scrollPane;
	}
	private JTable getTable() {
		if (table == null) {
			table = new JTable();
			table.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null},
					{null, null, null},
					{null, null, null},
					{null, null, null},
					{null, null, null},
					{null, null, null},
					{null, null, null},
					{null, null, null},
					{null, null, null},
					{null, null, null},
				},
				new String[] {
					"ID", "Nombre", "Apellidos"
				}
			));
			table.getColumnModel().getColumn(0).setResizable(false);
			table.getColumnModel().getColumn(1).setResizable(false);
			table.getColumnModel().getColumn(2).setResizable(false);
		}
		return table;
	}
	private JButton getBtnMostrarAusentes() {
		if (btnMostrarAusentes == null) {
			btnMostrarAusentes = new JButton("Aceptar");
			btnMostrarAusentes.setBounds(539, 74, 91, 23);
		}
		return btnMostrarAusentes;
	}
	private JLabel getLblCantidadTotalDe() {
		if (lblCantidadTotalDe == null) {
			lblCantidadTotalDe = new JLabel("Cantidad total de ausentes: ");
			lblCantidadTotalDe.setHorizontalAlignment(SwingConstants.CENTER);
			lblCantidadTotalDe.setBounds(10, 203, 165, 14);
		}
		return lblCantidadTotalDe;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("");
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setBorder(new LineBorder(Color.black, 1));
			lblNewLabel.setBounds(185, 198, 93, 24);
		}
		return lblNewLabel;
	}
}
