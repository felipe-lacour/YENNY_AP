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
	private Sucursal sucur= new Sucursal();
	private SucuControlador sucu = new SucuControlador();

	/**
	 * Launch the application.
	 
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
	}*/

	/**
	 * Create the frame.
	 */
	public AgregarSucu() {
		this.setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//direccion
		textDireccion = new JTextField();
		textDireccion.setBounds(10, 40, 86, 20);
		contentPane.add(textDireccion);
		textDireccion.setColumns(10);
		
		
		JLabel lblDireccion = new JLabel("Direccion");
		lblDireccion.setBounds(10, 15, 46, 14);
		contentPane.add(lblDireccion);
		
		//Nombre
		textNombre = new JTextField();
		textNombre.setBounds(10, 102, 86, 20);
		contentPane.add(textNombre);
		textNombre.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre sucursal");
		lblNombre.setBounds(10, 77, 86, 14);
		contentPane.add(lblNombre);
		
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(74, 187, 89, 23);
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e ) {
				
				if(textNombre.getText().isEmpty()||textDireccion.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "error al cargar datos");
					
				}else{
					sucur.setUbicacion(textDireccion.getText());
					sucur.setNombre(textNombre.getText());
					sucu.addBranch(sucur);
						JOptionPane.showMessageDialog(null, "Pudo editar");
						Tabla_Sucursal sucur = new Tabla_Sucursal();
						sucur.setVisible(true);
						dispose();
					
				}
			}
		});
				//String rta = sucu.addBranch(new Sucursal());
				//Tabla_Sucursal sucur = new Tabla_Sucursal();
				//dispose();
			
		
		contentPane.add(btnAgregar);
	}
}
