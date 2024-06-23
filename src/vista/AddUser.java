package vista;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JPasswordField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;

import controladores.SucuControlador;
import controladores.UsuarioControlador;
import interfaces.Auxiliaries;
import modelos.Sucursal;
import modelos.Usuario;


public class AddUser extends JDialog implements Auxiliaries{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JPasswordField passwordField;
	private Usuario usuarito;

	public AddUser(Usuario paseUsuario, int rol) {
		super((JFrame)null, "Add User", true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		String action = "Añadir";
		if (paseUsuario != null) {
			action = "Actualizar";
		}
		setTitle(action + " Usuarios");
		setBounds(100, 100, 569, 446);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		UsuarioControlador controlador = new UsuarioControlador();
		usuarito = paseUsuario;

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
        
		if (((usuarito != null) && (usuarito.getRol() == 1)) || rol == 2) {
			rdbtnNewRadioButton_1.setSelected(true);
		}
	
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
		
		if (usuarito != null) {
			textField.setText(usuarito.getNombre());
			textField_1.setText(usuarito.getUserName());
			passwordField.setText(usuarito.getPass());
			lblPassword.setEnabled(false);
			passwordField.setEnabled(false);
		}
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(31, 313, 223, 22);
		contentPane.add(comboBox);
		
		SucuControlador sucuControlador = new SucuControlador();
		comboBox.addItem("...");
		for (Sucursal sucursal : sucuControlador.getAllBranches()) {
			comboBox.addItem(sucursal);
			if (usuarito != null && usuarito.getSucursalId() == sucursal.getSucursalId()) { 
				comboBox.setSelectedItem(sucursal);
			}
        }
		
		JLabel lblIdDeLa = new JLabel("Sucursal del usuario:");
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
		
		JLabel sucursa = new JLabel("Este campo debe ser seleccionado");
		sucursa.setForeground(new Color(204, 37, 13));
		sucursa.setBounds(279, 321, 237, 14);
		contentPane.add(sucursa);
		sucursa.setVisible(false);
		
		JButton btnNewButton = new JButton(action);
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
		        
		        if (comboBox.getSelectedItem().equals("...")) {
		        	sucursa.setVisible(true);
		        	valid = false;
		        }
		        
		        if (valid) {
		        	int rol, suculi;
		        	if (rdbtnNewRadioButton.isSelected()) {
		        		rol = 2;
		        	} else {
		        		rol = 1;
		        	}
					suculi = ((Sucursal)comboBox.getSelectedItem()).getSucursalId();


		        	Usuario nuevoUsuario = new Usuario(0, textField.getText(), rol, suculi, new String(passwordField.getPassword()), textField_1.getText());
		        	if (usuarito != null) {
		        		nuevoUsuario.setUsuarioId(usuarito.getUsuarioId());
			        	controlador.updateUser(nuevoUsuario);
			        	JOptionPane.showMessageDialog(null, "Usuario actualizado exitosamente!");
			        } else {
			        	controlador.addUser(nuevoUsuario);
			        	JOptionPane.showMessageDialog(null, "Usuario agregado exitosamente!");
			        }
		        	dispose();
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
		
		if (rol == 2) {
			rdbtnNewRadioButton.setVisible(false);
			rdbtnNewRadioButton_1.setVisible(false);
			lblSeleccioneElRol.setVisible(false);
			lblIdDeLa.setBounds(51, 204, 217, 14);
			comboBox.setBounds(31, 225, 223, 22);
			sucursa.setBounds(279, 233, 237, 14);
			btnNewButton.setBounds(31, 273, 96, 23);
			btnCancelar.setBounds(158, 273, 96, 23);
			setBounds(100, 100, 569, 358);
		}
	}
}
