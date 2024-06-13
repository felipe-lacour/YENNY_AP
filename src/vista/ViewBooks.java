package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;

import controladores.LibroControlador;
import modelos.Libro;
import javax.swing.SwingConstants;


public class ViewBooks extends JDialog {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel model;
    private LibroControlador controlador;
    private JLabel elemento;
    private Libro seleccionado;
    private JLabel lblNewLabel;
    private JButton btnVolver;

	public ViewBooks(JFrame parent) {
		super(parent, "View Books", true);
		setTitle("Administrar Libros");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 569, 446);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        controlador = new LibroControlador();

        String[] columnNames = {"ID", "Titulo", "Autor","Editorial", "Saga"};
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
                controlador.deleteBook(seleccionado.getLibroId());
                actualizarTabla();
            }
        });
        contentPane.add(Eliminar);
        
        lblNewLabel = new JLabel("Libros:");
        lblNewLabel.setBounds(21, 18, 46, 14);
        contentPane.add(lblNewLabel);
        
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

                        seleccionado = controlador.getBookById(id);
                        elemento.setText("Seleccionado: ID: " + seleccionado.getLibroId() + ", Titulo: " + seleccionado.getTitulo() + 
                        				 ", Autor: " + seleccionado.getAutorId() + ", Editorial: " + seleccionado.getEditorialId() + ", Saga: " + seleccionado.getSagaId());
                    }
                }
            }
        });
    }

    private void actualizarTabla() {
        model.setRowCount(0);

        for (Libro libro : controlador.getAllBooks()) {
            model.addRow(new Object[]{libro.getLibroId(), libro.getTitulo(), libro.getAutorId(), libro.getEditorialId(), libro.getSagaId()});
        }
    }
}