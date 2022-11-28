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
	private JMenuItem mntmReporte_1;
	private JMenuItem mntmReporte_2;
	private JLabel lblNewLabel;
	private JPanel panel;	
	private JPanel panel_1;
	private JLabel lblSalir;
	private JPanel panel_2;

	/**
	 * Create the application.
	 */
	public MainMenu() {
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
		this.setUndecorated(true);
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
			menuBar.add(getPanel_1());
		}
		return menuBar;
	}
	private JMenu getMnEditar() {
		if (mnEditar == null) {
			mnEditar = new JMenu("Gesti\u00F3n");
			mnEditar.setBackground(Color.WHITE);
			mnEditar.setFont(new Font("Book Antiqua", Font.PLAIN, 20));
			mnEditar.add(getMntmPeriodos());
			mnEditar.add(getMntmListado());
		}
		return mnEditar;
	}
	private JMenu getMnAyuda() {
		if (mnAyuda == null) {
			mnAyuda = new JMenu("Ayuda");
			mnAyuda.setBackground(Color.WHITE);
			mnAyuda.setFont(new Font("Book Antiqua", Font.PLAIN, 20));
			mnAyuda.add(getMntmAcercaDe());
		}
		return mnAyuda;
	}
	private JMenuItem getMntmAcercaDe() {
		if (mntmAcercaDe == null) {
			mntmAcercaDe = new JMenuItem("Acerca de ...");
			mntmAcercaDe.setFont(new Font("Book Antiqua", Font.PLAIN, 15));
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
			mntmListado.setFont(new Font("Book Antiqua", Font.BOLD, 15));
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
			mntmPeriodos.setFont(new Font("Book Antiqua", Font.BOLD, 15));
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
			mnConsultar.setBackground(Color.WHITE);
			mnConsultar.setFont(new Font("Book Antiqua", Font.PLAIN, 20));
			mnConsultar.add(getMntmReport());
			mnConsultar.add(getMntmReporte());
			mnConsultar.add(getMntmReporte_1());
			mnConsultar.add(getMntmReporte_2());
		}
		return mnConsultar;
	}
	private JMenuItem getMntmReport() {
		if (mntmReport == null) {
			mntmReport = new JMenuItem("Cantidad de ausentes");
			mntmReport.setFont(new Font("Book Antiqua", Font.BOLD, 15));
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
			mntmReporte.setFont(new Font("Book Antiqua", Font.BOLD, 15));
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
	private JMenuItem getMntmReporte_1() {
		if (mntmReporte_1 == null) {
			mntmReporte_1 = new JMenuItem("Conocer los d\u00EDas de guardia de...");
			mntmReporte_1.setFont(new Font("Book Antiqua", Font.BOLD, 15));
			mntmReporte_1.setBackground(Color.WHITE);
			mntmReporte_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					WatchDay dias = new WatchDay();
					dias.setModal(true);
					dias.setVisible(true);
				}
			});
		}
		return mntmReporte_1;
	}
	private JMenuItem getMntmReporte_2() {
		if (mntmReporte_2 == null) {
			mntmReporte_2 = new JMenuItem("Trabajadores en vacaciones");
			mntmReporte_2.setFont(new Font("Book Antiqua", Font.BOLD, 15));
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
	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setBackground(Color.WHITE);
			panel_1.setLayout(null);
			panel_1.add(getPanel_2());
		}
		return panel_1;
	}
	private JLabel getLblSalir() {
		if (lblSalir == null) {
			lblSalir = new JLabel("");
			lblSalir.setIcon(new ImageIcon(MainMenu.class.getResource("/img/pngwing.com (13).png")));			
		}
		return lblSalir;
	}
	private JPanel getPanel_2() {
		if (panel_2 == null) {
			panel_2 = new JPanel();
			panel_2.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent arg0) {
					panel_2.setBackground(Color.RED);
				}
				@Override
				public void mouseExited(MouseEvent arg0) {
					panel_2.setBackground(Color.WHITE);
				}
				@Override
				public void mouseClicked(MouseEvent arg0) {
					dispose();
				}
			});
			panel_2.setBackground(Color.WHITE);
			panel_2.setBounds(Toolkit.getDefaultToolkit().getScreenSize().width-290, -2, 42, 42);
			panel_2.add(getLblSalir());
		}
		return panel_2;
	}
}
