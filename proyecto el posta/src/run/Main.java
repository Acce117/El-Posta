package run;

import java.awt.EventQueue;

import javax.swing.UIManager;

import classes.Faculty;

import com.formdev.flatlaf.FlatLightLaf;

import visual.MainMenu;

public class Main {

	public static void main(String[] args) {
		//FlatLightLaf.setup();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu window = new MainMenu();
					window.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
