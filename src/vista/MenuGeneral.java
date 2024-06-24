package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JDialog;

import modelos.Usuario;

public class MenuGeneral extends JDialog {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel elemento;
	private JButton btnVentas;
	private JButton btnUsers;
	private JButton btnBranch;
	private JButton btnPromo;
	private JButton btnExpo;
	private JButton btnBooks;
	private JButton btnSalir;

	public MenuGeneral(Usuario usuario) {
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
		
		btnVentas = new JButton("Ver Ventas");
		btnVentas.setBounds(65, 150, 166, 39);
		btnVentas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	ViewSales frame4 = new ViewSales(null);
				frame4.setVisible(true);
            }
        });
		contentPane.add(btnVentas);
        
        btnUsers = new JButton("Administrar usuarios");
        btnUsers.setBounds(65, 50, 166, 39);
        btnUsers.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	ViewUsers frame1 = new ViewUsers(null);
				frame1.setVisible(true);
            }
        });
        contentPane.add(btnUsers);
        
        btnBranch = new JButton("Administrar sucursales");
        btnBranch.setBounds(65, 100, 166, 39);
        btnBranch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	ViewBranches frame3 = new ViewBranches(null);
				frame3.setVisible(true);
            }
        });
        contentPane.add(btnBranch);
        
        btnPromo = new JButton("Administrar beneficios");
        btnPromo.setBounds(305, 100, 166, 39);
        btnPromo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	ViewPromos frame2 = new ViewPromos(null);
				frame2.setVisible(true);
            }
        });
        contentPane.add(btnPromo);
        
        btnExpo = new JButton("Pedir Exportacion");
        btnExpo.setBounds(305, 150, 166, 39);
        btnExpo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	ViewExports frame5 = new ViewExports(null);
				frame5.setVisible(true);
            }
        });
        contentPane.add(btnExpo);
        
        btnBooks = new JButton("Ver libros");
        btnBooks.setBounds(305, 50, 166, 39);
        btnBooks.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	ViewBooks frame = new ViewBooks(null);
				frame.setVisible(true);
            }
        });
        contentPane.add(btnBooks);
        
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
