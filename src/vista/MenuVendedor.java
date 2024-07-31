package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import modelos.Usuario;

public class MenuVendedor extends JDialog {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel elemento;
	private JButton btnStock;
	private JButton btnClient;
	private JButton btnSales;
	private JButton btnSalir;

	public MenuVendedor(Usuario usuario) {
    	super((JFrame)null, "Menu", true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 569, 301);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		elemento = new JLabel("Bienvenido  " + usuario.getNombre() + "! \n\n¿Que operacion desea realizar?");
        elemento.setVerticalAlignment(SwingConstants.TOP);
        elemento.setBounds(10, 11, 533, 28);
        contentPane.add(elemento);
        
        btnStock = new JButton("Ver ejemplares");
        btnStock.setBounds(305, 110, 166, 39);
        btnStock.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	ViewSpecimens frame2 = new ViewSpecimens(usuario);
				frame2.setVisible(true);
            }
        });
        contentPane.add(btnStock);
        
        btnClient = new JButton("Administrar Clientes");
        btnClient.setBounds(65, 50, 166, 39);
        btnClient.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	ViewClients frame = new ViewClients(usuario);
                frame.setVisible(true);
            }
        });
        contentPane.add(btnClient);
        
        btnSales = new JButton("Realizar Venta");
        btnSales.setBounds(305, 50, 166, 39);
        btnSales.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	AddSale frame1 = new AddSale(usuario);
                frame1.setVisible(true);
            }
        });
        contentPane.add(btnSales);
        
        btnSalir = new JButton("Salir");
        btnSalir.setBounds(357, 212, 114, 39);
        btnSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                return;
            }
        });
        contentPane.add(btnSalir);
	}
}
