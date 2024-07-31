package vista;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import controladores.AutorControlador;
import controladores.EditorialControlador;
import controladores.LibroControlador;
import controladores.SagaControlador;
import modelos.Libro;


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
    private JTextField searchField;
    private TableRowSorter<DefaultTableModel> sorter;
    private JLabel imagenLabel;

    public ViewBooks(JFrame parent) {
        super(parent, "View Books", true);
        setTitle("Administrar Libros");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 735, 446);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        controlador = new LibroControlador();

        String[] columnNames = {"ID", "Titulo", "Autor", "Editorial", "Saga"};
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
        
		imagenLabel = new JLabel("");
		imagenLabel.setBackground(new Color(255, 255, 255));
		imagenLabel.setBounds(553, 86, 156, 196);
		imagenLabel.setBorder(new LineBorder(Color.GRAY, 1));
		contentPane.add(imagenLabel);
        
        searchField = new JTextField();
        searchField.setBounds(553, 45, 156, 25);
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
                AddBook frame = new AddBook(null);
                frame.setVisible(true);
                actualizarTabla();
            }
        });
        contentPane.add(Agregar);
        
        JButton Cambiar = new JButton("Cambiar");
        Cambiar.setBounds(145, 361, 126, 35);
        Cambiar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AddBook frame = new AddBook(seleccionado);
                frame.setVisible(true);
                actualizarTabla();
            }
        });
        contentPane.add(Cambiar);
        
        lblNewLabel = new JLabel("Libros:");
        lblNewLabel.setBounds(21, 18, 102, 14);
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
                controlador.deleteBook(seleccionado.getLibroId());
                actualizarTabla();
            }
        });
        contentPane.add(Eliminar);
        
        JLabel lblBuscar = new JLabel("Buscar:");
        lblBuscar.setBounds(553, 18, 84, 14);
        contentPane.add(lblBuscar);

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
                        mostrarImagen(seleccionado.getImg());
                    }
                }
            }
        });
    }
    
    private void mostrarImagen(byte[] imagen) {
	    if (imagen != null) {
	    	ImageIcon icon = new ImageIcon (imagen);
	    	Image img = icon.getImage();
	    	Image scaledImg = img.getScaledInstance(imagenLabel.getWidth(), imagenLabel.getHeight(), Image.SCALE_SMOOTH);
	    	imagenLabel.setIcon(new ImageIcon(scaledImg));
	    }else {
	    	imagenLabel.setIcon(null);
	    }
    }

    private void actualizarTabla() {
        model.setRowCount(0);
        AutorControlador controlaAutor = new AutorControlador();
        SagaControlador controlaSaga = new SagaControlador();
        EditorialControlador controlaEdit = new EditorialControlador();

        for (Libro libro : controlador.getAllBooks()) {
            String editoriali, saguli;
            if (libro.getEditorialId() == 0) {
                editoriali = "0";
            } else {
                editoriali = controlaEdit.getEditorialById(libro.getEditorialId()).getNombre();
            }
            
            if (libro.getSagaId() == 0) {
                saguli = "0";
            } else {
                saguli = controlaSaga.getSagaById(libro.getSagaId()).getNombre();
            }
            
            model.addRow(new Object[] { libro.getLibroId(), libro.getTitulo(), controlaAutor.getAutorById(libro.getAutorId()).getNombre(), editoriali, saguli });
        }
    }
}
