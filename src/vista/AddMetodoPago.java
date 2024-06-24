package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controladores.MetodoPagoControlador;
import interfaces.Auxiliaries;
import modelos.MetodoPago;
import modelos.Cliente;


public class AddMetodoPago extends JDialog implements Auxiliaries{
	private static final long serialVersionUID = 1L;
	private MetodoPagoControlador controlador;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	
	public AddMetodoPago(Cliente client) {
		super((JFrame)null, "Add Client", true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		String action = "Añadir";
		if (client != null) {
			action = "Actualizar";
		}
		setTitle(action + " Cliente");
		setBounds(100, 100, 569, 234);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		controlador = new MetodoPagoControlador();

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setBounds(31, 15, 217, 14);
		contentPane.add(lblTipo);
		
		textField = new JTextField();
		textField.setBounds(31, 41, 223, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblDetalles = new JLabel("Detalles:");
		lblDetalles.setBounds(31, 77, 217, 14);
		contentPane.add(lblDetalles);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(31, 103, 223, 20);
		contentPane.add(textField_1);
		
		JLabel booklet = new JLabel("El metodo ingresado ya esta registrado");
		booklet.setForeground(new Color(204, 37, 13));
		booklet.setBounds(279, 170, 272, 14);
		contentPane.add(booklet);
		booklet.setVisible(false);
		

		JButton btnNewButton = new JButton(action);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				booklet.setVisible(false);
				boolean valid = true;
				
				for (MetodoPago mp : controlador.getAllMethods()) {
		            if (client.getClienteId() == mp.getClienteId() &&
		            		mp.getTipo().equalsIgnoreCase(textField.getText()) &&
		            		mp.getDetalles().equalsIgnoreCase(textField_1.getText())) {
						booklet.setVisible(true);
		        		valid = false;
		                break;
		            }
		        }

		        
		        if (valid) {
			        MetodoPago nuevoMetodo = new MetodoPago(0, client.getClienteId(), 
			        		textField.getText(), textField_1.getText());
			        
		        	controlador.addMethod(nuevoMetodo);
		        	JOptionPane.showMessageDialog(null, "Metodo de Pago agregado exitosamente!");

		        	dispose();
		        	return;
		        }
			}
		});
		btnNewButton.setBounds(31, 166, 97, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
		        return;
			}
		});
		btnNewButton_1.setBounds(157, 166, 97, 23);
		contentPane.add(btnNewButton_1);
		
	}
}
