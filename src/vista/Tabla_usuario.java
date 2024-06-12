package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import controladores.UsuarioControlador;
import modelos.Usuario;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;

public class Tabla_usuario extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel model;
    private UsuarioControlador controlador;
    private JLabel elemento;
    private JButton Editar;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Tabla_usuario frame = new Tabla_usuario();
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
    public Tabla_usuario() {
        this.setVisible(true);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 909, 452);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        // Inicializar controlador y usuario seleccionado
        controlador = new UsuarioControlador();
        Usuario seleccionado = new Usuario();

        // Crear la tabla y el modelo
        String[] columnNames = {"ID", "Nombre", "Rol", "Sucursal ID", "Usuario", "Contraseña"};
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        actualizarTabla();
        contentPane.setLayout(null);

        // Crear el JScrollPane y agregar la tabla
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(5, 19, 911, 190);
        contentPane.add(scrollPane);

        // Crear el JLabel para mostrar la selección
        elemento = new JLabel("Seleccionado:");
        elemento.setBounds(5, 5, 911, 14);
        contentPane.add(elemento);
        
        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (seleccionado.getUsuarioId() != 0) {
                    controlador.deleteUser(seleccionado.getUsuarioId());
                    JOptionPane.showMessageDialog(null, "Eliminado");
                    actualizarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione un usuario");
                }
            }
        });
        btnEliminar.setBounds(53, 280, 187, 58);
        contentPane.add(btnEliminar);
        
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBounds(15, 220, 101, 22);
        contentPane.add(menuBar);

        // Configurar el modelo de selección
        ListSelectionModel selectionModel = table.getSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Agregar un escuchador de selección
        selectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        int id = (int) table.getValueAt(selectedRow, 0);
                        String nombre = (String) table.getValueAt(selectedRow, 1);
                        int rol = (int) table.getValueAt(selectedRow, 2);
                        int sucursalId = (int) table.getValueAt(selectedRow, 3);
                        String userName = (String) table.getValueAt(selectedRow, 4);
                        String pass = (String) table.getValueAt(selectedRow, 5);
                        elemento.setText("Seleccionado: ID=" + id + ", Nombre=" + nombre + ", Rol=" + rol + ", Sucursal ID=" + sucursalId + ", Usuario=" + userName);
                        seleccionado.setUsuarioId(id);
                        seleccionado.setNombre(nombre);
                        seleccionado.setRol(rol);
                        seleccionado.setSucursalId(sucursalId);
                        seleccionado.setUserName(userName);
                        seleccionado.setPass(pass);
                    }
                }
            }
        });
    }

    private void actualizarTabla() {
        // Limpiar el modelo de la tabla
        model.setRowCount(0);

        // Obtener la lista actualizada de usuarios
        List<Usuario> usuarios = controlador.getAllUsers();

        // Agregar los datos al modelo
        for (Usuario usuario : usuarios) {
            model.addRow(new Object[] {
                usuario.getUsuarioId(),
                usuario.getNombre(),
                usuario.getRol(),
                usuario.getSucursalId(),
                usuario.getUserName(),
                usuario.getPass()
            });
        }
    }
}

