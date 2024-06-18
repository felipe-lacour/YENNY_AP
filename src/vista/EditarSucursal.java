package vista;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelos.Sucursal;
import controladores.SucuControlador;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

public class EditarSucursal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField inpNombre;
	private JTextField inpDireccion;
	private SucuControlador Controlador = new SucuControlador();
	//private Sucursal sucu;
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 * @param seleccionado 
	 */
	public EditarSucursal(Sucursal sucu) {
		this.setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Nombre
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(23, 22, 46, 14);
		contentPane.add(lblNombre);
		
		inpNombre = new JTextField();
		inpNombre.setBounds(23, 58, 86, 20);
		contentPane.add(inpNombre);
		inpNombre.setColumns(10);
		
		
		//Direccion
		JLabel lblDireccion = new JLabel("Direccion");
		lblDireccion.setBounds(23, 99, 46, 14);
		contentPane.add(lblDireccion);
		
		inpDireccion = new JTextField();
		inpDireccion.setBounds(23, 124, 86, 20);
		contentPane.add(inpDireccion);
		inpDireccion.setColumns(10);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(sucu.getNombre().equals(inpNombre.getText())) {
					JOptionPane.showMessageDialog(null, "Debe poner un nombre distinto");
					
				}else{
					sucu.setUbicacion(inpDireccion.getText());
					sucu.setNombre(inpNombre.getText());
					if(Controlador.updateBranch(sucu)) {
						JOptionPane.showMessageDialog(null, "Pudo editar");
						Tabla_Sucursal sucur = new Tabla_Sucursal();
						dispose();
						sucur.setVisible(true);
					}
				}
			}
		});
		btnEditar.setBounds(126, 199, 89, 23);
		contentPane.add(btnEditar);
	}

}
