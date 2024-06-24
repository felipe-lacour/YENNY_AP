package vista;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controladores.SucuControlador;
import interfaces.Auxiliaries;
import modelos.Sucursal;

public class AddBranch extends JDialog implements Auxiliaries{
	private static final long serialVersionUID = 1L;
	private SucuControlador controlador;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField1;
	private Sucursal sucursalada;

	public AddBranch(Sucursal sucu) {
		super((JFrame)null, "Add Brach", true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		String action = "Añadir";
		if (sucu != null) {
			action = "Actualizar";
		}
		setTitle(action + " Usuarios");
		setBounds(100, 100, 569, 232);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		controlador = new SucuControlador();
		sucursalada = sucu;
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre:");
		lblNewLabel.setBounds(51, 22, 217, 14);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(31, 41, 223, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblUsername = new JLabel("Ubicación:");
		lblUsername.setBounds(51, 82, 217, 14);
		contentPane.add(lblUsername);
		
		textField1 = new JTextField();
		textField1.setColumns(10);
		textField1.setBounds(31, 101, 223, 20);
		contentPane.add(textField1);
		
		if (sucursalada != null) {
			textField.setText(sucursalada.getNombre());
			textField1.setText(sucursalada.getUbicacion());
		}

		JLabel formato1 = new JLabel("El formato ingresado es invalido");
		formato1.setForeground(new Color(204, 37, 13));
		formato1.setBounds(278, 104, 237, 14);
		contentPane.add(formato1);
		formato1.setVisible(false);
		
		JLabel formato = new JLabel("El formato ingresado es invalido");
		formato.setForeground(new Color(204, 37, 13));
		formato.setBounds(278, 44, 237, 14);
		contentPane.add(formato);
		formato.setVisible(false);
		
		JLabel usu = new JLabel("El nombre ingresado ya esta registrado");
		usu.setForeground(new Color(204, 37, 13));
		usu.setBounds(278, 44, 237, 14);
		contentPane.add(usu);
		usu.setVisible(false);

		
		JButton btnNewButton = new JButton(action);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				formato.setVisible(false);
				formato1.setVisible(false);
				usu.setVisible(false);
				boolean valid = true; 
					
				if (!verifyStrInput(textField1.getText())){
					formato1.setVisible(true);
					valid = false;
				}
				
				if (!verifyStrInput(textField.getText())) {
					formato.setVisible(true);
					valid = false;
				} else {
					for (Sucursal sucursa : controlador.getAllBranches()) {
			            if (sucursa.getNombre().equalsIgnoreCase(textField.getText()) && (sucursalada != null && sucursa.getSucursalId() != sucursalada.getSucursalId())) {
			            	usu.setVisible(true);
			        		valid = false;
			                break;
			            }
			        }
				}

		        if (valid) {

		        	Sucursal nuevoSucusucu = new Sucursal(0, textField.getText(), textField1.getText());
		        	if (sucursalada != null) {
		        		nuevoSucusucu.setSucursalId(sucursalada.getSucursalId());
			        	controlador.updateBranch(nuevoSucusucu);
			        	JOptionPane.showMessageDialog(null, "Sucursal actualizada exitosamente!");
			        } else {
			        	controlador.addBranch(nuevoSucusucu);
			        	JOptionPane.showMessageDialog(null, "Sucursal agregada exitosamente!");
			        }
		        	dispose();
		        	return;
		        }
			}
		});
		btnNewButton.setBounds(31, 144, 96, 23);
		contentPane.add(btnNewButton);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		dispose();
        		return;
        	}
        });
		btnCancelar.setBounds(158, 144, 96, 23);
		contentPane.add(btnCancelar);

	}
}
