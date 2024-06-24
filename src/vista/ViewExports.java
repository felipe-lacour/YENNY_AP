package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import controladores.ExportControlador;
import controladores.LibroControlador;
import modelos.Export;
import modelos.Libro;

public class ViewExports extends JDialog {
	private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel model;
    private ExportControlador controlador;
    private JLabel elemento;
    private Export seleccionado;
    private JLabel lblNewLabel;
    private JButton btnVolver;
    private JTextField searchField;
    private TableRowSorter<DefaultTableModel> sorter;

	public ViewExports(JFrame parent) {
        super(parent, "View Exports", true);
        setTitle("Administrar Exportaciones");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 569, 446);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        controlador = new ExportControlador();

        String[] columnNames = {"ID", "Libro", "Cantidad", "Destino", "Fecha"};
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
                AddExport frame = new AddExport(null);
				frame.setVisible(true);
                actualizarTabla();
            }
        });
        contentPane.add(Agregar);
        
        lblNewLabel = new JLabel("Exportacionas:");
        lblNewLabel.setBounds(21, 18, 126, 14);
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
        
        JButton Eliminar = new JButton("Cancelar");
        Eliminar.setBounds(281, 361, 126, 35);
        Eliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controlador.deleteExport(seleccionado.getExportId());
                actualizarTabla();
            }
        });
        contentPane.add(Eliminar);


        ListSelectionModel selectionModel = table.getSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        selectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        int id = (int) table.getValueAt(selectedRow, 0);

                        seleccionado = controlador.getExportById(id);
                        elemento.setText("Seleccionado: ID: " + seleccionado.getExportId() + ", Libro: " + seleccionado.getLibroId() + 
                                         ", Cantidad: " + seleccionado.getCantidad() + ", Destino: " + seleccionado.getDestino() + ", Fecha: " + seleccionado.getFecha());
                    }
                }
            }
        });
	}

    private void actualizarTabla() {
        model.setRowCount(0);
        LibroControlador controlaLibro = new LibroControlador();

        for (Export export : controlador.getAllExports()) {
        	Libro libro = controlaLibro.getBookById(export.getLibroId());
        	
            model.addRow(new Object[] {export.getExportId(), libro.getTitulo(), export.getCantidad(), export.getDestino(), export.getFecha()});
        }
    }
}
