package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JDialog;

import controladores.UsuarioControlador;
import modelos.Usuario;

import java.awt.event.ActionListener;
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

    public ViewUsers(JFrame parent) {
    	super(parent, "View Users", true);
    	setTitle("Administrar Usuarios");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 569, 446);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        
        lblNewLabel = new JLabel("Usuarios:");
        lblNewLabel.setBounds(21, 18, 46, 14);
        contentPane.add(lblNewLabel);

        setContentPane(contentPane);
        controlador = new UsuarioControlador();

        String[] columnNames = {"ID", "Nombre", "Rol","Sucursal", "Username"};
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        actualizarTabla();
        contentPane.setLayout(null);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 43, 533, 239);
        contentPane.add(scrollPane);

        elemento = new JLabel("Seleccionado:");
        elemento.setVerticalAlignment(SwingConstants.TOP);
        elemento.setBounds(20, 293, 533, 53);
        contentPane.add(elemento);

        JButton Eliminar = new JButton("Eliminar");
        Eliminar.setBounds(274, 361, 126, 35);
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

        for (Usuario usuario : controlador.getAllUsers()) {
        	
            model.addRow(new Object[]{usuario.getUsuarioId(), usuario.getNombre(), usuario.getRol(), usuario.getSucursalId(), usuario.getUserName()});
        }
    }
}