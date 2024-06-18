package vista;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelos.Sucursal;
import controladores.SucuControlador;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Editar extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private SucuControlador controlador;
	private Sucursal seleccionado;
	private JTextField inpNombre;
	private JTextField inpUbicacion;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Editar frame = new Editar();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	/**
	 * Create the frame.
	 */
	public Editar() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		//controladores
		controlador= new SucuControlador();
		Sucursal seleccionado = new Sucursal();
		
		//ubicacion
		JLabel lblUbicacion = new JLabel("Ubicacion");
		lblUbicacion.setBounds(10, 32, 46, 14);
		contentPane.add(lblUbicacion);
		
		inpUbicacion = new JTextField();
		inpUbicacion.setBounds(10, 53, 86, 20);
		contentPane.add(inpUbicacion);
		inpUbicacion.setColumns(10);
		
		//nombre
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 84, 46, 14);
		contentPane.add(lblNombre);
		
		inpNombre = new JTextField();
		inpNombre.setBounds(10, 113, 86, 20);
		contentPane.add(inpNombre);
		inpNombre.setColumns(10);
		
		//btn editar
		
		JButton btnEditar = new JButton("Editar");
		/*btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (Sucursal.getNombre().equals(inpNombre.getText())) {
					JOptionPane.showMessageDialog(null, "Debe poner un nombre distinto");
				} else {
					Sucursal.setUbicacion(inpUbicacion.getText());
					Sucursal.setNombre(inpNombre.getText());
					if (controlador.updateBranch(Sucursal)) {
						JOptionPane.showMessageDialog(null, "Se pudo editar");
						dispose();
					}
					} 
			}
		});*/
		
		btnEditar.setBounds(122, 195, 89, 23);
		contentPane.add(btnEditar);
		
		
		
		
		
	}
}
