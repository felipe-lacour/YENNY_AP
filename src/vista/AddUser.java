package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controladores.SucuControlador;
import controladores.UsuarioControlador;
import interfaces.Auxiliaries;
import modelos.Usuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JPasswordField;
import javax.swing.JSpinner;
import java.awt.Color;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;

public class AddUser extends JDialog implements Auxiliaries{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JPasswordField passwordField;

	public AddUser(JFrame parent) {
		super(parent, "Add User", true);
		UsuarioControlador controlador = new UsuarioControlador();
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 569, 446);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre y apellido:");
		lblNewLabel.setBounds(51, 22, 217, 14);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(31, 41, 223, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Gerente de sucursal");
		rdbtnNewRadioButton.setSelected(true);
		rdbtnNewRadioButton.setBounds(31, 225, 146, 23);
		contentPane.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Vendedor");
		rdbtnNewRadioButton_1.setBounds(31, 251, 146, 23);
		contentPane.add(rdbtnNewRadioButton_1);
		
		ButtonGroup group = new ButtonGroup();
        group.add(rdbtnNewRadioButton);
        group.add(rdbtnNewRadioButton_1);
		
		JLabel lblSeleccioneElRol = new JLabel("Rol del usuario:");
		lblSeleccioneElRol.setBounds(51, 204, 217, 14);
		contentPane.add(lblSeleccioneElRol);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(31, 101, 223, 20);
		contentPane.add(textField_1);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(51, 82, 217, 14);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(51, 143, 217, 14);
		contentPane.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(31, 162, 223, 20);
		contentPane.add(passwordField);
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(31, 318, 223, 20);
		contentPane.add(spinner);
		
		JLabel lblIdDeLa = new JLabel("ID de la sucursal del usuario:");
		lblIdDeLa.setBounds(51, 292, 217, 14);
		contentPane.add(lblIdDeLa);
		
		JLabel formato = new JLabel("El formato ingresado es invalido");
		formato.setForeground(new Color(204, 37, 13));
		formato.setBounds(279, 44, 237, 14);
		contentPane.add(formato);
		formato.setVisible(false);
		
		JLabel userr = new JLabel("El usuario no puede estar vacio");
		userr.setForeground(new Color(204, 37, 13));
		userr.setBounds(279, 104, 237, 14);
		contentPane.add(userr);
		userr.setVisible(false);
		
		JLabel contra = new JLabel("La contraseña no puede estar vacia");
		contra.setForeground(new Color(204, 37, 13));
		contra.setBounds(279, 165, 237, 14);
		contentPane.add(contra);
		contra.setVisible(false);
		
		JLabel usu = new JLabel("El usuario ingresado ya esta registrado");
		usu.setForeground(new Color(204, 37, 13));
		usu.setBounds(279, 104, 237, 14);
		contentPane.add(usu);
		usu.setVisible(false);
		
		JLabel sucursa = new JLabel("El ID ingresado es invalido");
		sucursa.setForeground(new Color(204, 37, 13));
		sucursa.setBounds(279, 321, 237, 14);
		contentPane.add(sucursa);
		sucursa.setVisible(false);
		
		JButton btnNewButton = new JButton("Añadir");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				formato.setVisible(false);
				contra.setVisible(false);
				sucursa.setVisible(false);
				usu.setVisible(false);
				userr.setVisible(false);
				boolean valid = true; 
					
				if (!verifyStrInput(textField.getText())){
					formato.setVisible(true);
					valid = false;
				}
				
				if (textField_1.getText().isEmpty()) {
					userr.setVisible(true);
					valid = false;
				} else {
					for (Usuario user : controlador.getAllUsers()) {
			            if (user.getUserName().equalsIgnoreCase(textField_1.getText())) {
			            	usu.setVisible(true);
			        		valid = false;
			                break;
			            }
			        }
				}
				
				if (passwordField.getPassword().length == 0) {
					contra.setVisible(true);
					valid = false;
				}
		        
		        SucuControlador sucuControlador = new SucuControlador();
		        if (sucuControlador.getBranchById((Integer)spinner.getValue()) == null) {
		        	sucursa.setVisible(true);
		        	valid = false;
		        }
		        
		        if (valid) {
		        	int rol;
		        	if (rdbtnNewRadioButton.isSelected()) {
		        		rol = 2;
		        	} else {
		        		rol = 1;
		        	}

		        	Usuario nuevoUsuario = new Usuario(0, textField.getText(), rol, (Integer)spinner.getValue(), new String(passwordField.getPassword()), textField_1.getText());
		        	controlador.addUser(nuevoUsuario);
		        	dispose();
		        	JOptionPane.showMessageDialog(null, "Usuario agregado exitosamente!");
		        	return;
		        }
			}
		});
		btnNewButton.setBounds(31, 361, 96, 23);
		contentPane.add(btnNewButton);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		dispose();
        		return;
        	}
        });
		btnCancelar.setBounds(158, 361, 96, 23);
		contentPane.add(btnCancelar);
	}
}
