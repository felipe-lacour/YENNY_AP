package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controladores.UsuarioControlador;
import interfaces.Menu;
import modelos.Usuario;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class LogIn extends JFrame implements Menu {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	@Override
	public void Menu() {
	}

	public LogIn() {
		UsuarioControlador controlador = new UsuarioControlador();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 569, 301);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setBounds(311, 29, 103, 14);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(304, 54, 213, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setBounds(311, 113, 103, 14);
		contentPane.add(lblNewLabel_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(304, 138, 213, 20);
		contentPane.add(passwordField);
		
		JLabel lblNewLabel_2 = new JLabel("La contraseña ingresada es incorrecta");
		lblNewLabel_2.setForeground(new Color(204, 37, 13));
		lblNewLabel_2.setBounds(304, 163, 239, 14);
		contentPane.add(lblNewLabel_2);
		lblNewLabel_2.setVisible(false);
		
		JLabel lblNewLabel_2_1 = new JLabel("El ususario ingresado no esta registrado");
		lblNewLabel_2_1.setForeground(new Color(204, 37, 13));
		lblNewLabel_2_1.setBounds(304, 78, 239, 14);
		contentPane.add(lblNewLabel_2_1);
		lblNewLabel_2_1.setVisible(false);
		
		JButton btnNewButton = new JButton("Ingresar");
		btnNewButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        boolean found = false;
		        lblNewLabel_2_1.setVisible(false);

		        for (Usuario user : controlador.getAllUsers()) {
		            if (user.getUserName().equalsIgnoreCase(textField.getText())) {
		                found = true;
		                if (user.getPass().equals(new String(passwordField.getPassword()))) {
		                    dispose();
		                    
		                    if (user.getRol() == 1) {
		                    	MenuVendedor frame = new MenuVendedor(user);
		    					frame.setVisible(true);
		                    } else if (user.getRol() == 2) {
		                    	MenuManager frame = new MenuManager(user);
		    					frame.setVisible(true);
		                    } else {
		                    	MenuGeneral frame = new MenuGeneral(user);
		    					frame.setVisible(true);
		                    }
		                    
		                    return;
		                } else {
		                    lblNewLabel_2.setVisible(true);
		                }
		                break;
		            }
		        }

		        if (!found) {
		            lblNewLabel_2_1.setVisible(true);
		        }
		    }
		});
		btnNewButton.setBounds(428, 198, 89, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_3 = new JLabel("Image");
		lblNewLabel_3.setIcon(new ImageIcon(LogIn.class.getResource("/img/Yenny.png")));
		lblNewLabel_3.setBounds(29, 11, 234, 240);
		contentPane.add(lblNewLabel_3);
	}
}
