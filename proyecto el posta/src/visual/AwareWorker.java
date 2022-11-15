package visual;

import java.awt.Toolkit;


import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import javax.swing.table.DefaultTableModel;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.CardLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;

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
	/**
	 * Create the dialog.
	 */
	public AwareWorker() {
		setBounds(100, 100, 520, 450);
		setIconImage(Toolkit.getDefaultToolkit().getImage(".\\src\\img\\logo mejorado.png"));
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		getContentPane().add(getPanel());
		contentPanel.setBounds(10, 125, 494, 285);
		contentPanel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Trabajadores de viaje", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		contentPanel.setLayout(new CardLayout(0, 0));
		contentPanel.add(getScrollPane(), "name_1306759437216300");
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
			table.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null, null},
					{null, null, null, null},
					{null, null, null, null},
					{null, null, null, null},
					{null, null, null, null},
					{null, null, null, null},
					{null, null, null, null},
					{null, null, null, null},
					{null, null, null, null},
					{null, null, null, null},
					{null, null, null, null},
					{null, null, null, null},
					{null, null, null, null},
					{null, null, null, null},
					{null, null, null, null},
					{null, null, null, null},
					{null, null, null, null},
					{null, null, null, null},
					{null, null, null, null},
					{null, null, null, null},
					{null, null, null, null},
					{null, null, null, null},
					{null, null, null, null},
					{null, null, null, null},
					{null, null, null, null},
					{null, null, null, null},
					{null, null, null, null},
					{null, null, null, null},
					{null, null, null, null},
					{null, null, null, null},
				},
				new String[] {
					"ID", "Nombre", "Apellidos", "Fecha de Retorno"
				}
			));
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
