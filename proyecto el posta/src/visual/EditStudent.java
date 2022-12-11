package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import classes.Faculty;
import classes.Student;

import java.awt.Color;

import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JSeparator;

import utils.DateManager;
import utils.Genre;
import utils.PersonTableModel;
import utils.PersonalValidator;
import utils.StatesStudent;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Date;

import com.toedter.calendar.JDateChooser;

public class EditStudent extends JDialog {
	private JPanel panel;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JTextField nameText;
	private JTextField firstLastName;
	private JTextField idText;
	private JRadioButton rbtnFemale;
	private JRadioButton rbtnMale;
	private JComboBox<Object> cmbxStates;
	private JButton button;
	private JLabel label_4;
	private JSeparator separator;
	private JSeparator separator_1;
	private JSeparator separator_3;
	private JDateChooser dateChooser;
	private JLabel lblFechaDelCambio;
	private Student studentReference;
	private PersonTableModel tableReference;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the dialog.
	 */
	public EditStudent(Student toEdit, PersonTableModel toRefresh) {
		studentReference = toEdit;
		tableReference = toRefresh;
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		getContentPane().add(getPanel());
		setBackground(Color.WHITE);
		setTitle("Editando a: " + toEdit.getName() + " " + toEdit.getLastName());
		setBounds(100, 100, 286, 429);
		setModal(true);
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(null);
			panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Estudiante a Editar", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel.setBackground(Color.WHITE);
			panel.setBounds(10, 11, 240, 367);
			panel.add(getLabel());
			panel.add(getLabel_1());
			panel.add(getLabel_2());
			panel.add(getNameText());
			panel.add(getFirstLastName());
			panel.add(getIdText());
			panel.add(getRbtnFemale());
			panel.add(getRbtnMale());
			panel.add(getCmbxStates());
			panel.add(getButton());
			panel.add(getLabel_4());
			panel.add(getSeparator());
			panel.add(getSeparator_1());
			panel.add(getSeparator_3());
			panel.add(getDateChooser());
			panel.add(getLblFechaDelCambio());
		}
		return panel;
	}
	private JLabel getLabel() {
		if (label == null) {
			label = new JLabel("Nombre:");
			label.setHorizontalAlignment(SwingConstants.LEFT);
			label.setForeground(Color.BLACK);
			label.setFont(new Font("Tahoma", Font.PLAIN, 12));
			label.setBounds(20, 30, 71, 14);
		}
		return label;
	}
	private JLabel getLabel_1() {
		if (label_1 == null) {
			label_1 = new JLabel("1er apellido:");
			label_1.setHorizontalAlignment(SwingConstants.LEFT);
			label_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
			label_1.setBounds(20, 76, 86, 14);
		}
		return label_1;
	}
	private JLabel getLabel_2() {
		if (label_2 == null) {
			label_2 = new JLabel("CI:");
			label_2.setHorizontalAlignment(SwingConstants.LEFT);
			label_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
			label_2.setBounds(20, 130, 71, 14);
		}
		return label_2;
	}
	private JTextField getNameText() {
		if (nameText == null) {
			nameText = new JTextField();
			nameText.setHorizontalAlignment(SwingConstants.LEFT);
			nameText.setFont(new Font("Tahoma", Font.PLAIN, 12));
			nameText.setColumns(10);
			nameText.setBorder(null);
			nameText.setBackground(Color.WHITE);
			nameText.setBounds(20, 46, 200, 20);
			nameText.setText(studentReference.getName());
		}
		return nameText;
	}
	private JTextField getFirstLastName() {
		if (firstLastName == null) {
			firstLastName = new JTextField();
			firstLastName.setHorizontalAlignment(SwingConstants.LEFT);
			firstLastName.setFont(new Font("Tahoma", Font.PLAIN, 12));
			firstLastName.setColumns(10);
			firstLastName.setBorder(null);
			firstLastName.setBackground(Color.WHITE);
			firstLastName.setBounds(20, 92, 200, 20);
			firstLastName.setText(studentReference.getLastName());
		}
		return firstLastName;
	}
	private JTextField getIdText() {
		if (idText == null) {
			idText = new JTextField();
			idText.setHorizontalAlignment(SwingConstants.LEFT);
			idText.setFont(new Font("Tahoma", Font.PLAIN, 12));
			idText.setColumns(10);
			idText.setBorder(null);
			idText.setBackground(Color.WHITE);
			idText.setBounds(20, 148, 200, 20);
			idText.setText(studentReference.getId());
		}
		return idText;
	}
	private JRadioButton getRbtnFemale() {
		if (rbtnFemale == null) {
			rbtnFemale = new JRadioButton("Femenina");
			rbtnFemale.setFont(new Font("Tahoma", Font.PLAIN, 12));
			rbtnFemale.setBackground(Color.WHITE);
			rbtnFemale.setBounds(20, 175, 86, 23);
			if(studentReference.getSex() == Genre.FEMALE)
				rbtnFemale.setSelected(true);
		}
		return rbtnFemale;
	}
	private JRadioButton getRbtnMale() {
		if (rbtnMale == null) {
			rbtnMale = new JRadioButton("Masculino");
			rbtnMale.setFont(new Font("Tahoma", Font.PLAIN, 12));
			rbtnMale.setBackground(Color.WHITE);
			rbtnMale.setBounds(110, 175, 95, 23);
			if(studentReference.getSex() == Genre.MALE)
				rbtnMale.setSelected(true);
		}
		return rbtnMale;
	}
	private JComboBox<Object> getCmbxStates() {
		if (cmbxStates == null) {
			cmbxStates = new JComboBox<Object>();
			cmbxStates.setFont(new Font("Tahoma", Font.PLAIN, 12));
			cmbxStates.setBounds(20, 223, 200, 20);
			//Converti el arreglo de enums a String			
			StatesStudent list[] = StatesStudent.values();			
			String listShow[] = new String[list.length];
			//De momento esta hecho a fuerza bruta. Buscar un metodo para simplificar
			for(int i = 0; i < list.length; i++) 
			{
				listShow[i] = list[i].getName();
			}
			cmbxStates.setModel(new DefaultComboBoxModel<Object>(listShow));
			int index = studentReference.getActualState().ordinal();
			cmbxStates.setSelectedIndex(index);
		}
		return cmbxStates;
	}
	private JButton getButton() {
		if (button == null) {
			button = new JButton("Agregar");
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String name = null;
					String lastName = null;
					String id = null;
					Genre sex = null;
					StatesStudent state = null;
					//Student student;
					try{
						name = nameText.getText();
						lastName = firstLastName.getText();
						id = idText.getText();

						if(!name.equals(studentReference.getName()))
							PersonalValidator.checkName(name);
						if(!lastName.equals(studentReference.getLastName()))
							PersonalValidator.checkName(lastName);							
						if(sex != studentReference.getSex())
							sex = PersonalValidator.checkSex(rbtnMale,rbtnFemale);
						if(!id.equals(studentReference.getId()))
							PersonalValidator.checkID(id, sex);	


						switch((String)cmbxStates.getSelectedItem()){
						case "Activo":
							state = StatesStudent.ACTIVE;
							break;
						case "Licencia":
							state = StatesStudent.LICENCE;
							break;
						case "Baja":
							state = StatesStudent.DROPPED_OUT;
							break;
						default:
							throw new Exception("No se ha elegido el estado");
						}


						//name = name + " " + lastName + " " + secLastName;

						Date changeState = dateChooser.getDate();
						Date today = new Date();


						studentReference.setName(name);
						studentReference.setLastName(lastName);
						studentReference.setId(id);
						if(changeState != null && changeState.after(today))							
							studentReference.setActualState(state, changeState);
						else
							studentReference.setActualState(state, today);
						tableReference.refreshStudent(Faculty.getInstance().getStudents());
						dispose();
					}catch(Exception error){
						JOptionPane.showMessageDialog(null, error.getMessage());
						error.printStackTrace();
					}
				}
			});
			button.setFont(new Font("Tahoma", Font.PLAIN, 13));
			button.setBackground(Color.WHITE);
			button.setBounds(135, 318, 85, 23);
		}
		return button;
	}
	private JLabel getLabel_4() {
		if (label_4 == null) {
			label_4 = new JLabel("Estado:");
			label_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
			label_4.setBounds(20, 205, 56, 14);
		}
		return label_4;
	}
	private JSeparator getSeparator() {
		if (separator == null) {
			separator = new JSeparator();
			separator.setForeground(Color.BLACK);
			separator.setBackground(Color.WHITE);
			separator.setBounds(20, 69, 200, 2);
		}
		return separator;
	}
	private JSeparator getSeparator_1() {
		if (separator_1 == null) {
			separator_1 = new JSeparator();
			separator_1.setForeground(Color.BLACK);
			separator_1.setBackground(Color.WHITE);
			separator_1.setBounds(20, 117, 200, 2);
		}
		return separator_1;
	}
	private JSeparator getSeparator_3() {
		if (separator_3 == null) {
			separator_3 = new JSeparator();
			separator_3.setForeground(Color.BLACK);
			separator_3.setBackground(Color.WHITE);
			separator_3.setBounds(20, 171, 200, 2);
		}
		return separator_3;
	}
	private JDateChooser getDateChooser() {
		if (dateChooser == null) {
			dateChooser = new JDateChooser();
			dateChooser.setBounds(20, 272, 200, 20);
		}
		return dateChooser;
	}
	private JLabel getLblFechaDelCambio() {
		if (lblFechaDelCambio == null) {
			lblFechaDelCambio = new JLabel("Fecha del cambio:");
			lblFechaDelCambio.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblFechaDelCambio.setBounds(20, 254, 108, 14);
		}
		return lblFechaDelCambio;
	}
}
