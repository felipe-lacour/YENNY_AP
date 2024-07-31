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

import controladores.ClienteControlador;
import modelos.Usuario;
import modelos.Cliente;

import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

public class ViewClients extends JDialog {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel model;
    private ClienteControlador controlador;
    private JLabel elemento;
    private JLabel lblNewLabel;
    private Cliente seleccionado;
    private JButton btnVolver;
    private Usuario userPasado;
    private JTextField searchField;
    private TableRowSorter<DefaultTableModel> sorter;

    public ViewClients(Usuario paseUsuario) {
    	super((JFrame)null, "View Clients", true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	setTitle("Administrar Clientes");
        setBounds(100, 100, 569, 446);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        controlador = new ClienteControlador();
        userPasado = paseUsuario;
        
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        lblNewLabel = new JLabel("Clientes:");
        lblNewLabel.setBounds(21, 18, 83, 14);
        contentPane.add(lblNewLabel);
        
        String[] columnNames = {"ID", "Nombre", "Apellido", "Edad", "Club del Libro"};
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
            	AddClient dialog;
            	if (userPasado != null) {
            		dialog = new AddClient(null, paseUsuario);
            	} else {
            		dialog = new AddClient(null, paseUsuario);
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
            	AddClient dialog;
            	if (userPasado != null) {
            		dialog = new AddClient(seleccionado, paseUsuario);
            	} else {
            		dialog = new AddClient(seleccionado, paseUsuario);
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
                controlador.deleteCliente(seleccionado.getClienteId());
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

                        seleccionado = controlador.getClienteById(id);
                        elemento.setText("Seleccionado: ID=" + seleccionado.getClienteId() + ", Nombre= " + seleccionado.getNombre() + ", Apellido= " + seleccionado.getApellido() 
                        		+ ", Genero= " +  seleccionado.getGenero() + ", Edad= " + seleccionado.getEdad() + ", Lugar de Compra= " + seleccionado.getLugar_de_compra() 
                        		+ ", Club de Libros= " + seleccionado.isClubLibros() + "]");
                    }
                }
            }
        });
    }

    private void actualizarTabla() {
        model.setRowCount(0);

        for (Cliente cliente : controlador.getAllClientes()) {
//        	"ID", "Nombre", "Apellido", "Edad", "Club del Libro"
        	model.addRow(new Object[]{cliente.getClienteId(), cliente.getNombre(), cliente.getApellido(), cliente.getEdad(), cliente.isClubLibros()});
        }
    }
}
