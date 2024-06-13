package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controladores.AutorControlador;
import controladores.EditorialControlador;
import controladores.LibroControlador;
import controladores.SagaControlador;
import interfaces.Auxiliaries;
import modelos.Autor;
import modelos.Editorial;
import modelos.Libro;
import modelos.Saga;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JPasswordField;
import javax.swing.JSpinner;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JComboBox;

public class AddBook extends JFrame implements Auxiliaries{
	private static final long serialVersionUID = 1L;
	private LibroControlador controlador;
	private JPanel contentPane;
	private JTextField textField;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddBook frame = new AddBook();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AddBook() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 569, 353);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		JLabel lblNewLabel = new JLabel("Titulo:");
		lblNewLabel.setBounds(51, 22, 217, 14);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(31, 41, 223, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblAutor = new JLabel("Autor:");
		lblAutor.setBounds(51, 82, 217, 14);
		contentPane.add(lblAutor);

		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(31, 100, 223, 22);
		contentPane.add(comboBox);
		
		AutorControlador controlaAutor = new AutorControlador();
		for (Autor autor : controlaAutor.getAllAutors()) {
			comboBox.addItem(autor);
        }
		
		JLabel lblSaga = new JLabel("Saga:");
		lblSaga.setBounds(51, 143, 217, 14);
		contentPane.add(lblSaga);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(31, 161, 223, 22);
		contentPane.add(comboBox_2);
		
		SagaControlador controlaSaga = new SagaControlador();
		for (Saga saga : controlaSaga.getAllSagas()) {
			comboBox_2.addItem(saga);
        }
		
		JLabel lblEditorial = new JLabel("Editorial:");
		lblEditorial.setBounds(51, 204, 217, 14);
		contentPane.add(lblEditorial);
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setBounds(31, 222, 223, 22);
		contentPane.add(comboBox_3);

		EditorialControlador controlaEdit = new EditorialControlador();
		for (Editorial edit : controlaEdit.getAllEditorials()) {
			comboBox_3.addItem(edit);
        }
		
		JLabel formato = new JLabel("El formato ingresado es invalido");
		formato.setForeground(new Color(204, 37, 13));
		formato.setBounds(279, 44, 237, 14);
		contentPane.add(formato);
		formato.setVisible(false);
		
		JLabel usu = new JLabel("El usuario ingresado ya esta registrado");
		usu.setForeground(new Color(204, 37, 13));
		usu.setBounds(279, 104, 237, 14);
		contentPane.add(usu);
		usu.setVisible(false);
		
		JButton btnNewButton = new JButton("Añadir");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

			}
		});
		btnNewButton.setBounds(31, 265, 97, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        return;
			}
		});
		btnNewButton_1.setBounds(157, 265, 97, 23);
		contentPane.add(btnNewButton_1);
	}
}