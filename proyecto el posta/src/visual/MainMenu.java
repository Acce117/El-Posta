package visual;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.JPanel;
import java.awt.Font;
import java.awt.Window.Type;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.SystemColor;

public class MainMenu extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JMenuBar menuBar;
	private JMenu mnEditar;
	private JMenu mnAyuda;
	private JMenuItem mntmAcercaDe;
	private JMenuItem mntmListado;
	private JMenuItem mntmPeriodos;
	private JMenu mnConsultar;
	private JMenuItem mntmReport;
	private JMenuItem mntmReporte;
	private JMenuItem mntmReporte_2;
	private JLabel lblNewLabel;
	private JPanel panel;
	public static final int MAIN_MENU_WITDTH = Toolkit.getDefaultToolkit().getScreenSize().width; 
	public static final int MAIN_MENU_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height; 

	/**
	 * Create the application.
	 */
	public MainMenu() {
		setBackground(SystemColor.control);
		setTitle("El Posta");
		getContentPane().setBackground(new Color(135, 206, 250));
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainMenu.class.getResource("/img/logo mejorado.png")));
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		//frame.setBounds(100, 100, 450, 300);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		//this.setUndecorated(true);
		setJMenuBar(getMenuBar_1());
		getContentPane().add(getPanel());
	}
	private JMenuBar getMenuBar_1() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.setBackground(Color.WHITE);
			menuBar.add(getMnEditar());
			menuBar.add(getMnConsultar());
			menuBar.add(getMnAyuda());
		}
		return menuBar;
	}
	private JMenu getMnEditar() {
		if (mnEditar == null) {
			mnEditar = new JMenu("Gesti\u00F3n");
			mnEditar.setBackground(SystemColor.control);
			mnEditar.setFont(new Font("Tahoma", Font.PLAIN, 14));
			mnEditar.add(getMntmPeriodos());
			mnEditar.add(getMntmListado());
		}
		return mnEditar;
	}
	private JMenu getMnAyuda() {
		if (mnAyuda == null) {
			mnAyuda = new JMenu("Ayuda");
			mnAyuda.setBackground(SystemColor.control);
			mnAyuda.setFont(new Font("Tahoma", Font.PLAIN, 14));
			mnAyuda.add(getMntmAcercaDe());
		}
		return mnAyuda;
	}
	private JMenuItem getMntmAcercaDe() {
		if (mntmAcercaDe == null) {
			mntmAcercaDe = new JMenuItem("Acerca de ...");
			mntmAcercaDe.setFont(new Font("Tahoma", Font.PLAIN, 14));
			mntmAcercaDe.setBackground(Color.WHITE);
			mntmAcercaDe.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					About ayuda = new About();
					ayuda.setModal(true);
					ayuda.setVisible(true);
				}
			});
		}
		return mntmAcercaDe;
	}
	private JMenuItem getMntmListado() {
		if (mntmListado == null) {
			mntmListado = new JMenuItem("Personal");
			mntmListado.setFont(new Font("Tahoma", Font.PLAIN, 14));
			mntmListado.setBackground(Color.WHITE);
			mntmListado.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Personal persona = new Personal();
					persona.setModal(true);
					persona.setVisible(true);
				}
			});
		}
		return mntmListado;
	}
	private JMenuItem getMntmPeriodos() {
		if (mntmPeriodos == null) {
			mntmPeriodos = new JMenuItem("Periodos");
			mntmPeriodos.setFont(new Font("Tahoma", Font.PLAIN, 14));
			mntmPeriodos.setBackground(Color.WHITE);
			mntmPeriodos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Periods period = new Periods();
					period.setModal(true);
					period.setVisible(true);
				}
			});
		}
		return mntmPeriodos;
	}
	private JMenu getMnConsultar() {
		if (mnConsultar == null) {
			mnConsultar = new JMenu("Consultar");
			mnConsultar.setBackground(SystemColor.control);
			mnConsultar.setFont(new Font("Tahoma", Font.PLAIN, 14));
			mnConsultar.add(getMntmReport());
			mnConsultar.add(getMntmReporte());
			mnConsultar.add(getMntmReporte_2());
		}
		return mnConsultar;
	}
	private JMenuItem getMntmReport() {
		if (mntmReport == null) {
			mntmReport = new JMenuItem("Cantidad de ausentes");
			mntmReport.setFont(new Font("Tahoma", Font.PLAIN, 14));
			mntmReport.setBackground(Color.WHITE);
			mntmReport.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					AbsentAmount cant = new AbsentAmount();
					cant.setModal(true);
					cant.setVisible(true);
				}
			});
		}
		return mntmReport;
	}
	private JMenuItem getMntmReporte() {
		if (mntmReporte == null) {
			mntmReporte = new JMenuItem("Cantidad de trabajadores en el extranjero");
			mntmReporte.setFont(new Font("Tahoma", Font.PLAIN, 14));
			mntmReporte.setBackground(Color.WHITE);
			mntmReporte.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					AwareWorker cant = new AwareWorker();
					cant.setModal(true);
					cant.setVisible(true);
				}
			});
		}
		return mntmReporte;
	}
	private JMenuItem getMntmReporte_2() {
		if (mntmReporte_2 == null) {
			mntmReporte_2 = new JMenuItem("Trabajadores en vacaciones");
			mntmReporte_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
			mntmReporte_2.setBackground(Color.WHITE);
			mntmReporte_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					WorkerOnVacation cant = new WorkerOnVacation();
					cant.setModal(true);
					cant.setVisible(true);
				}
			});
		}
		return mntmReporte_2;
	}
	private JLabel getLabel_1() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("");
			lblNewLabel.setBounds(483, 144, 380, 380);
			lblNewLabel.setIcon(new ImageIcon(MainMenu.class.getResource("/img/logo mejorado (1).png")));
		}
		return lblNewLabel;
	}
	
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBounds(0, 0, 1366, 684);
			panel.setLayout(null);
			panel.add(getLabel_1());
			panel.setOpaque(false);
		}
		return panel;
	}
}
