package vista;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import controladores.EjemplarControlador;
import modelos.Ejemplar;

import javax.swing.JTextArea;

public class ViewSpecimens extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel model;
    private EjemplarControlador controlador;
    private JTextArea elemento;
    private Ejemplar seleccionado;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ViewSpecimens frame = new ViewSpecimens();
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
    public ViewSpecimens() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 569, 446);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        controlador = new EjemplarControlador();

        String[] columnNames = {"ID", "Libro", "Precio", "Idioma", "Tapa Dura", "ISBN", "Numero Edicion"};
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        actualizarTabla();
        contentPane.setLayout(null);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(6, 6, 558, 258);
        contentPane.add(scrollPane);

        // Crear el JTextArea para mostrar la selección
        elemento = new JTextArea("Seleccionado:");
        elemento.setLineWrap(true);
        elemento.setWrapStyleWord(true);
        elemento.setEditable(false);
        JScrollPane scrollPaneText = new JScrollPane(elemento);
        scrollPaneText.setBounds(6, 276, 558, 85);
        contentPane.add(scrollPaneText);

        // Crear el botón de eliminar
        JButton Eliminar = new JButton("Eliminar");
        Eliminar.setBounds(6, 373, 143, 39);
        Eliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controlador.deleteEjemplar(seleccionado.getEjemplarId());
                actualizarTabla();
            }
        });
        contentPane.add(Eliminar);

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

                        seleccionado = controlador.getEjemplarById(id);
                        //"ID", "Libro", "Precio", "Idioma", "Tapa Dura", "ISBN", "Numero Edicion"
                        elemento.setText("Seleccionado: ID: " + seleccionado.getEjemplarId() + ", Titulo: " + seleccionado.getLibroId() + 
                                         ", Precio: $" + seleccionado.getPrecio() + ", Tapa Dura: " + seleccionado.isTapaDura() 
                                         + ", ISBN: " + seleccionado.getIsbn()
                                         + ", Numero de Edicion: " + seleccionado.getNumeroEdicion());
                    }
                }
            }
        });
    }

    private void actualizarTabla() {
        model.setRowCount(0);

        for (Ejemplar ejemplar : controlador.getAllEjemplar()) {
            model.addRow(new Object[]{ejemplar.getEjemplarId(), 
                    ejemplar.getLibroId(), 
                    ejemplar.getPrecio(), 
                    ejemplar.isTapaDura(),
                    ejemplar.getIsbn(),
                    ejemplar.getNumeroEdicion()});
        }
    }
}

