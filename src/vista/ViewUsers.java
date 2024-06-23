package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JDialog;

import controladores.SucuControlador;
import controladores.UsuarioControlador;
import modelos.Usuario;

import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

public class ViewUsers extends JDialog {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel model;
    private UsuarioControlador controlador;
    private JLabel elemento;
    private JLabel lblNewLabel;
    private Usuario seleccionado;
    private JButton btnVolver;
    private Usuario userPasado;
    private JTextField searchField;
    private TableRowSorter<DefaultTableModel> sorter;

    public ViewUsers(Usuario paseUsuario) {
    	super((JFrame)null, "View Users", true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	setTitle("Administrar Usuarios");
        setBounds(100, 100, 569, 446);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        controlador = new UsuarioControlador();
        userPasado = paseUsuario;
        
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        lblNewLabel = new JLabel("Usuarios:");
        lblNewLabel.setBounds(21, 18, 83, 14);
        contentPane.add(lblNewLabel);
        
        String[] columnNames = {"ID", "Nombre", "Rol","Sucursal", "Username"};
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);
        actualizarTabla();
        contentPane.setLayout(null);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 43, 533, 239);
        contentPane.add(scrollPane);

        elemento = new JLabel("Seleccionado:");
        elemento.setVerticalAlignment(SwingConstants.TOP);
        elemento.setBounds(20, 293, 533, 53);
        contentPane.add(elemento);
        
        searchField = new JTextField();
        searchField.setBounds(327, 10, 216, 25);
        contentPane.add(searchField);
        
        searchField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String searchText = searchField.getText();
                if (searchText.trim().length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText));
                }
            }
        });

        JButton Agregar = new JButton("Agregar");
        Agregar.setBounds(10, 361, 126, 35);
        Agregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	AddUser dialog;
            	if (userPasado != null) {
            		dialog = new AddUser(null, 2);
            	} else {
            		dialog = new AddUser(null, 3);
            	}
                dialog.setVisible(true);
                actualizarTabla();
            }
        });
        contentPane.add(Agregar);
        
        JButton Cambiar = new JButton("Cambiar");
        Cambiar.setBounds(145, 361, 126, 35);
        Cambiar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	AddUser dialog;
            	if (userPasado != null) {
            		dialog = new AddUser(seleccionado, 2);
            	} else {
            		dialog = new AddUser(seleccionado, 3);
            	}
                dialog.setVisible(true);
                actualizarTabla();
            }
        });
        contentPane.add(Cambiar);
        
        JButton Eliminar = new JButton("Eliminar");
        Eliminar.setBounds(281, 361, 126, 35);
        Eliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controlador.deleteUser(seleccionado.getUsuarioId());
                actualizarTabla();
            }
        });
        contentPane.add(Eliminar);
        
        btnVolver = new JButton("Volver");
        btnVolver.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		dispose();
        		return;
        	}
        });
        btnVolver.setBounds(417, 361, 126, 35);
        contentPane.add(btnVolver);

        ListSelectionModel selectionModel = table.getSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        selectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        int id = (int) table.getValueAt(selectedRow, 0);

                        seleccionado = controlador.getUserById(id);
                        elemento.setText("Seleccionado: ID=" + seleccionado.getUsuarioId() + ", Nombre=" + seleccionado.getNombre() + 
                        				 ", Rol=" + seleccionado.getRol() + ", Sucursal=" + seleccionado.getSucursalId() + ", Username=" + seleccionado.getUserName());
                    }
                }
            }
        });
    }

    private void actualizarTabla() {
        model.setRowCount(0);
        SucuControlador sucuControlador = new SucuControlador();

        for (Usuario usuario : controlador.getAllUsers()) {
        	String rol;
            if (usuario.getRol() == 2) {
            	rol = "Grte. sucursal";
            } else {
            	rol = "Vendedor";
            }
            
        	if (userPasado != null) {
        		if ((usuario.getSucursalId() == userPasado.getSucursalId()) && (usuario.getRol() != userPasado.getRol())) {
        			model.addRow(new Object[]{usuario.getUsuarioId(), usuario.getNombre(), rol, (sucuControlador.getBranchById(usuario.getSucursalId())).getNombre(), usuario.getUserName()});
        		}
        	} else {
        		if (usuario.getSucursalId() != 0) {
        			model.addRow(new Object[]{usuario.getUsuarioId(), usuario.getNombre(), rol, (sucuControlador.getBranchById(usuario.getSucursalId())).getNombre(), usuario.getUserName()});
        		}
        	}
        }
    }
}