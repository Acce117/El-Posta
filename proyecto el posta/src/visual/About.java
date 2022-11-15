package visual;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;


import javax.swing.JDialog;
import javax.swing.JPanel;

import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import java.awt.Color;


public class About extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JScrollPane scrollPane;
	private JTextPane txtpnElPostaEs;

	/**
	 * Create the dialog.
	 */
	public About() {
		setBounds(100, 100, 500, 500);
		setIconImage(Toolkit.getDefaultToolkit().getImage(".\\src\\img\\logo mejorado.png"));
		setLocationRelativeTo(null);
		setContentPane(getPanel());
		setResizable(false);
		getContentPane().setLayout(null);
		
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel(){
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				public void paintComponent(Graphics g){
					Image img = Toolkit.getDefaultToolkit().getImage(About.class.getResource("/img/pngwing(10).png")); 
					g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
				}
			};
			panel.setBounds(0, 0, 844, 439);
			panel.add(getScrollPane());
		}
		return panel;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(25, 191, 369, 163);
			scrollPane.setViewportView(getTxtpnElPostaEs());
		}
		return scrollPane;
	}
	private JTextPane getTxtpnElPostaEs() {
		if (txtpnElPostaEs == null) {
			txtpnElPostaEs = new JTextPane();
			txtpnElPostaEs.setEditable(false);
			txtpnElPostaEs.setBackground(Color.WHITE);
			txtpnElPostaEs.setText("El Posta es un sistema de gesti\u00F3n de asignaci\u00F3n de\r\nturnos de guardia para la facultad de Ingenier\u00EDa \r\nInform\u00E1tica en la CUJAE.\r\n\r\nFacilita la organizaci\u00F3n autom\u00E1tica de los periodos \r\ntanto lectivo como vacacional para estudiantes y \r\ntrabajadores, adem\u00E1s de una f\u00E1cil gestion de cada \r\nturno por separado.\r\n\r\nAutores:\r\n \u2666 Alfredo Hern\u00E1ndez Rodrigues g: 13\r\n \u2666 Ernesto A. Carralero Conde g: 13\r\n");
			txtpnElPostaEs.setBounds(30, 190, 360, 150);
		}
		return txtpnElPostaEs;
	}
}
