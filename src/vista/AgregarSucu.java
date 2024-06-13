package vista;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import modelos.Sucursal;
import controladores.SucuControlador;

public class AgregarSucu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textDireccion;
	private JTextField textNombre;
	private Sucursal sucursal;
	private SucuControlador sucu;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AgregarSucu frame = new AgregarSucu();
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
	public AgregarSucu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textDireccion = new JTextField();
		textDireccion.setBounds(10, 40, 86, 20);
		contentPane.add(textDireccion);
		textDireccion.setColumns(10);
		textDireccion.setText(sucursal.getUbicacion());
		
		JLabel lblDireccion = new JLabel("Direccion");
		lblDireccion.setBounds(10, 15, 46, 14);
		contentPane.add(lblDireccion);
		
		textNombre = new JTextField();
		textNombre.setBounds(10, 102, 86, 20);
		contentPane.add(textNombre);
		textNombre.setColumns(10);
		textNombre.setText(sucursal.getNombre());
		
		JLabel lblNombre = new JLabel("Nombre sucursal");
		lblNombre.setBounds(10, 77, 86, 14);
		contentPane.add(lblNombre);
		
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(74, 187, 89, 23);
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e ) {
				for (Sucursal sucursal : sucu.getAllBranches()) {
					
				}
				//String rta = sucu.addBranch(new Sucursal());
			}
		});
		contentPane.add(btnAgregar);
	}
}
