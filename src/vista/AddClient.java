package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controladores.ClienteControlador;
import controladores.MetodoPagoControlador;
import interfaces.Auxiliaries;
import modelos.Cliente;
import modelos.MetodoPago;
import modelos.Usuario;

import javax.swing.JRadioButton;

public class AddClient extends JDialog implements Auxiliaries{
	private static final long serialVersionUID = 1L;
	private ClienteControlador controlador;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private Cliente clientuli;
	
	
	public AddClient(Cliente client, Usuario user) {
		super((JFrame)null, "Add Client", true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		String action = "Añadir";
		if (client != null) {
			action = "Actualizar";
		}
		setTitle(action + " Cliente");
		setBounds(100, 100, 569, 420);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		controlador = new ClienteControlador();
		clientuli = client;

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre:");
		lblNewLabel.setBounds(51, 22, 217, 14);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(31, 41, 223, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblLastName = new JLabel("Apellido:");
		lblLastName.setBounds(51, 84, 217, 14);
		contentPane.add(lblLastName);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(31, 103, 223, 20);
		contentPane.add(textField_1);
		

		
		JLabel lblGenero = new JLabel("Genero:");
		lblGenero.setBounds(51, 143, 217, 14);
		contentPane.add(lblGenero);
		
		JComboBox<String> comboBox_2 = new JComboBox<String>();
		comboBox_2.setBounds(31, 161, 223, 22);
		contentPane.add(comboBox_2);
		
		comboBox_2.addItem("...");
		
		String[] generos = {"Hombre", "Mujer", "Otro"};
		
		for (String string : generos) {
			comboBox_2.addItem(string);
			if (client != null && client.getGenero().equalsIgnoreCase(string)) {
				comboBox_2.setSelectedItem(string);
			}
        }
		

		
		JLabel lblEditorial = new JLabel("Edad:");
		lblEditorial.setBounds(51, 204, 217, 14);
		contentPane.add(lblEditorial);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(31, 224, 223, 20);
		contentPane.add(textField_2);
		
		if (client != null) {
			textField.setText(client.getNombre());
			textField_1.setText(client.getApellido());
			textField_2.setText("" + client.getEdad());
		}
		
		
		JLabel booklet = new JLabel("El cliente ingresado ya esta registrado");
		booklet.setForeground(new Color(204, 37, 13));
		booklet.setBounds(279, 356, 272, 14);
		contentPane.add(booklet);
		booklet.setVisible(false);
		
		JLabel formato = new JLabel("El formato ingresado es invalido");
		formato.setForeground(new Color(204, 37, 13));
		formato.setBounds(279, 44, 272, 14);
		contentPane.add(formato);
		formato.setVisible(false);
		
		JLabel apellidoFormato = new JLabel("El formato ingresado es invalido");
		apellidoFormato.setForeground(new Color(204, 36, 13));
		apellidoFormato.setBounds(279, 105, 272, 16);
		contentPane.add(apellidoFormato);
		apellidoFormato.setVisible(false);
		
		JLabel edadFormato = new JLabel("El formato ingresado es invalido");
		edadFormato.setForeground(new Color(204, 36, 13));
		edadFormato.setBounds(279, 226, 272, 16);
		contentPane.add(edadFormato);
		edadFormato.setVisible(false);
		
		JLabel genObl = new JLabel("Debe seleccionar un genero");
		genObl.setForeground(new Color(204, 36, 13));
		genObl.setBounds(279, 163, 272, 16);
		contentPane.add(genObl);
		genObl.setVisible(false);
		
		JRadioButton trueRadio = new JRadioButton("True");
		trueRadio.setBounds(31, 282, 141, 23);
		contentPane.add(trueRadio);
		
		JRadioButton falseRadio = new JRadioButton("False");
		falseRadio.setSelected(true);
		falseRadio.setBounds(31, 317, 141, 23);
		contentPane.add(falseRadio);	
		
		ButtonGroup group = new ButtonGroup();
        group.add(trueRadio);
        group.add(falseRadio);
		

		JButton btnNewButton = new JButton(action);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				formato.setVisible(false);
				booklet.setVisible(false);
				apellidoFormato.setVisible(false);
				edadFormato.setVisible(false);
				boolean valid = true; 

				if (!verifyStrInput(textField.getText())){
					formato.setVisible(true);
					valid = false;
				}
				
				if (!verifyStrInput(textField_1.getText())){
					apellidoFormato.setVisible(true);
					valid = false;
				}
				
				if (!verifyIntInput(textField_2.getText())){
					edadFormato.setVisible(true);
					valid = false;
				}
				
				if (comboBox_2.getSelectedItem().equals("...")){
					genObl.setVisible(true);
					valid = false;
				}
				
				if(genObl.isVisible() || edadFormato.isVisible() || apellidoFormato.isVisible() || formato.isVisible()) {
					for (Cliente cliente : controlador.getAllClientes()) {
			            if (cliente.getNombre().equalsIgnoreCase(textField.getText()) && 
			            	cliente.getApellido().equalsIgnoreCase(textField_1.getText()) && 
			            	cliente.getEdad() == Integer.parseInt(textField_2.getText()) &&
			            	cliente.getGenero().equalsIgnoreCase((String) comboBox_2.getSelectedItem())) {
							booklet.setVisible(true);
			        		valid = false;
			                break;
			            }
			        }
				}
		        
		        if (valid) {
		        	MetodoPagoControlador mpControlador = new MetodoPagoControlador();
		        	
		        	boolean cL = false;
		        	
		        	if(trueRadio.isSelected()) {
		        		cL = true;
		        	}
		        	
			        Cliente nuevoCliente = new Cliente(0, textField.getText(), 
			        		textField_1.getText(), (String) comboBox_2.getSelectedItem(), 
			        		Integer.parseInt(textField_2.getText()), user.getSucursalId(), cL);
			        if (clientuli != null) {
			        	boolean mpExist = false;
			        	nuevoCliente.setClienteId(clientuli.getClienteId());
			        	
			        	if(cL) {
				        	for (MetodoPago mp : mpControlador.getAllMethods()) {
								if(mp.getClienteId() == nuevoCliente.getClienteId()) {
									mpExist = true;
								}
							}
				        	
				        	if(mpExist) {
			                    AddMetodoPago frame = new AddMetodoPago(nuevoCliente);
			                    frame.setVisible(true);
				        	}
			        	}
			        	
			        	controlador.updateCliente(nuevoCliente);
			        	JOptionPane.showMessageDialog(null, "Cliente actualizado exitosamente!");
			        } else {

			        	
			        	int nuevoClienteId = controlador.addCliente(nuevoCliente);
			        	
			        	if(cL) {
		                    AddMetodoPago frame = new AddMetodoPago(controlador.getClienteById(nuevoClienteId));
		                    frame.setVisible(true);
			        	}
			        	
			        	JOptionPane.showMessageDialog(null, "Cliente agregado exitosamente!");
			        }
		        	dispose();
		        	return;
		        }
			}
		});
		btnNewButton.setBounds(31, 352, 97, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
		        return;
			}
		});
		btnNewButton_1.setBounds(157, 352, 97, 23);
		contentPane.add(btnNewButton_1);
		
		
		JLabel lblClubDelLibro = new JLabel("Club del Libro:");
		lblClubDelLibro.setBounds(51, 256, 217, 14);
		contentPane.add(lblClubDelLibro);
		
	}
}
