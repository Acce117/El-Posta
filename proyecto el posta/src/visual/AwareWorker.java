package visual;

import java.awt.Font;
import java.awt.Toolkit;






import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.border.TitledBorder;

import java.awt.Color;

import javax.swing.border.LineBorder;

import java.awt.CardLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import utils.AwareWorkerTableModel;
import utils.PeriodAsignModel;
import classes.Faculty;

public class AwareWorker extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JScrollPane scrollPane;
	private JTable table;
	private JPanel panel;
	private JLabel lblIntroduzcaElCi;
	private JTextField textField;
	private JButton btnNewButton;
	private AwareWorkerTableModel awareWorkerTableModel;
	private TableRowSorter<AwareWorkerTableModel> tr;
	/**
	 * Create the dialog.
	 */
	public AwareWorker() {
		setBounds(100, 100, 520, 450);
		setIconImage(Toolkit.getDefaultToolkit().getImage(".\\src\\img\\logo mejorado.png"));
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		contentPanel.setBounds(10, 125, 494, 285);
		contentPanel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Trabajadores de viaje", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		contentPanel.setLayout(new CardLayout(0, 0));
		contentPanel.add(getScrollPane(), "name_1306759437216300");
		getContentPane().add(getPanel());
		setResizable(false);
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
			table.setFillsViewportHeight(true);
			table.setModel(getAwareWorkerTableModel());
			table.getColumnModel().getColumn(3).setPreferredWidth(101);
		}
		return table;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "B\u00FAsqueda por CI", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel.setBounds(10, 11, 494, 100);
			panel.setLayout(null);
			panel.add(getLblIntroduzcaElCi());
			panel.add(getTextField());
			panel.add(getBtnNewButton());
		}
		return panel;
	}
	
	private AwareWorkerTableModel getAwareWorkerTableModel(){
		if(awareWorkerTableModel == null){
			awareWorkerTableModel = new AwareWorkerTableModel();
			awareWorkerTableModel.addColumn("ID");
			awareWorkerTableModel.addColumn("Nombre");
			awareWorkerTableModel.addColumn("Apellidos");
			awareWorkerTableModel.addColumn("D\u00eda de retorno");
			awareWorkerTableModel.refresh(Faculty.getInstance().getAwareWorkers());
			
		}
		return awareWorkerTableModel;
	}
	private JLabel getLblIntroduzcaElCi() {
		if (lblIntroduzcaElCi == null) {
			lblIntroduzcaElCi = new JLabel("Introduzca el CI:");
			lblIntroduzcaElCi.setHorizontalAlignment(SwingConstants.CENTER);
			lblIntroduzcaElCi.setBounds(40, 30, 110, 14);
		}
		return lblIntroduzcaElCi;
	}
	private JTextField getTextField() {
		if (textField == null) {
			textField = new JTextField();
			textField.setBounds(160, 27, 270, 20);
			textField.setColumns(10);
			textField.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent arg0) {
					//table.setRowSorter(tr);
					String filter = textField.getText();
					tr.setRowFilter(RowFilter.regexFilter(filter, 0));
				}
			});
			tr = new TableRowSorter<>(awareWorkerTableModel);
			table.setRowSorter(tr);
			table = getTable();
			table.setModel(getAwareWorkerTableModel());
			//awareWorkerTableModel.refresh(Faculty.getInstance().getAwareWorkers());
		}
		return textField;
	}
	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("Buscar");
			btnNewButton.setBounds(397, 66, 89, 23);
		}
		return btnNewButton;
	}
}
