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

import controladores.AutorControlador;
import controladores.EjemplarControlador;
import controladores.LibroControlador;
import controladores.SagaControlador;
import modelos.Ejemplar;
import modelos.Libro;
import modelos.Usuario;

public class ViewSpecimens extends JDialog {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    private JTable table;
    private DefaultTableModel model;
	private EjemplarControlador controlador;
	private JButton btnVolver;
	private JLabel lblNewLabel;
    private JTextField searchField;
    private JLabel elemento;
    private Ejemplar seleccionado;
	private TableRowSorter<DefaultTableModel> sorter;
	private Usuario tipazado;
	
	public ViewSpecimens(Usuario tipazo) {
		super((JFrame)null, "View Specimens", true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 569, 446);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        controlador = new EjemplarControlador();
        tipazado = tipazo;
        
        String[] columnNames = {"ID", "Titulo", "Autor", "Saga", "Precio", "Tapa", "Edicion", "N Edicion", "Firmado", "Idioma"};
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
        
        JButton Agregar = new JButton("Encargar");
        Agregar.setBounds(10, 361, 126, 35);
        Agregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AddSpecimen frame = new AddSpecimen(null, tipazo);
                frame.setVisible(true);
                actualizarTabla();
            }
        });
        contentPane.add(Agregar);
        
        JButton Cambiar = new JButton("Cambiar");
        Cambiar.setBounds(145, 361, 126, 35);
        Cambiar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	AddSpecimen frame = new AddSpecimen(seleccionado, tipazo);
                frame.setVisible(true);
                actualizarTabla();
            }
        });
        contentPane.add(Cambiar);
        
        lblNewLabel = new JLabel("Ejemplares:");
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
        
        JButton Eliminar = new JButton("Eliminar");
        Eliminar.setBounds(281, 361, 126, 35);
        Eliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controlador.deleteEjemplar(seleccionado.getEjemplarId());
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

                        seleccionado = controlador.getEjemplarById(id);
                        elemento.setText("Seleccionado: ID: " + seleccionado.getEjemplarId() + ", Libro: " + seleccionado.getLibroId() + 
                                         ", Precio: " + seleccionado.getPrecio() + ", Idioma: " + seleccionado.getIdioma() + 
                                         ", Edicion: " + seleccionado.getNumeroEdicion() + ", ISBN: " + seleccionado.getIsbn());
                    }
                }
            }
        });
    }
	
    private void actualizarTabla() {
        model.setRowCount(0);
        LibroControlador controlaLibro = new LibroControlador();
        AutorControlador controlaAutor = new AutorControlador();
        SagaControlador controlaSaga = new SagaControlador();

        for (Ejemplar ejemplo : controlador.getAllEjemplar()) {
        	if ((ejemplo.getSucursalId() != 0) && ejemplo.getSucursalId() == tipazado.getSucursalId()) {
                String tapa, edicion, firma;
                if (ejemplo.isEdicionEspecial()) {
                	edicion = "Especial";
                } else {
                	edicion = "Comun";
                }
                
                if (ejemplo.isFirmado()) {
                	firma = "✔";
                } else {
                	firma = "✘";
                }
                
                if (ejemplo.isTapaDura()) {
                	tapa = "Dura";
                } else {
                	tapa = "Blanda";
                }
                
                Libro libruli = controlaLibro.getBookById(ejemplo.getLibroId());
                String saguli;
                if (libruli.getSagaId() == 0) {
                    saguli = "0";
                } else {
                    saguli = controlaSaga.getSagaById(libruli.getSagaId()).getNombre();
                }
                
                model.addRow(new Object[] {ejemplo.getEjemplarId(), libruli.getTitulo(), controlaAutor.getAutorById(libruli.getAutorId()).getNombre(), 
                						   saguli, ejemplo.getPrecio(), tapa, edicion, ejemplo.getNumeroEdicion(), firma, ejemplo.getIdioma()});
        	}
        }	
    }
}
