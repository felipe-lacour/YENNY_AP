package vista;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
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
import controladores.EditorialControlador;
import controladores.ExportControlador;
import controladores.LibroControlador;
import controladores.SagaControlador;
import interfaces.Auxiliaries;
import modelos.Export;
import modelos.Libro;

public class AddExport extends JDialog implements Auxiliaries {
	private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel model;
    private LibroControlador controlador;
    private JLabel elemento;
    private Libro seleccionado;
    private JLabel lblNewLabel;
    private JTextField searchField;
    private TableRowSorter<DefaultTableModel> sorter;
    private JTextField textField;

	public AddExport(JFrame parent) {
        super(parent, "Add Export", true);
        setTitle("Pedir Exportacion");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 569, 421);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        controlador = new LibroControlador();
        
        String[] columnNames = {"ID", "Titulo", "Autor", "Editorial", "Saga"};
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);
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
        contentPane.setLayout(null);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 41, 533, 119);
        contentPane.add(scrollPane);

        elemento = new JLabel("Libro:");
        elemento.setVerticalAlignment(SwingConstants.TOP);
        elemento.setBounds(51, 173, 492, 36);
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
        
        lblNewLabel = new JLabel("Seleccione el libro que desea encargar:");
        lblNewLabel.setBounds(51, 22, 249, 14);
        contentPane.add(lblNewLabel);
        
        ListSelectionModel selectionModel = table.getSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        selectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        int id = (int) table.getValueAt(selectedRow, 0);
                        AutorControlador controlaAutor = new AutorControlador();

                        seleccionado = controlador.getBookById(id);
                        elemento.setText("Libro:  " + seleccionado.getTitulo() + "  -  " + controlaAutor.getAutorById(seleccionado.getAutorId()));
                    }
                }
            }
        });
        
		JLabel lblIdDeLa = new JLabel("Destino:");
		lblIdDeLa.setBounds(51, 202, 217, 14);
		contentPane.add(lblIdDeLa);

		textField = new JTextField();
		textField.setBounds(31, 227, 223, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblCantidad = new JLabel("Cantidad:");
		lblCantidad.setBounds(51, 265, 217, 14);
		contentPane.add(lblCantidad);
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(31, 291, 223, 20);
		contentPane.add(spinner);
		
		JLabel formato = new JLabel("El formato ingresado es invalido");
		formato.setForeground(new Color(204, 37, 13));
		formato.setBounds(279, 230, 195, 14);
		contentPane.add(formato);
		formato.setVisible(false);
		
		JLabel libroObl = new JLabel("Este campo debe ser seleccionado");
		libroObl.setForeground(new Color(204, 37, 13));
		libroObl.setBounds(279, 173, 237, 14);
		contentPane.add(libroObl);
		libroObl.setVisible(false);
		
		JLabel autorObl = new JLabel("Debe ingresar una cantidad");
		autorObl.setForeground(new Color(204, 37, 13));
		autorObl.setBounds(279, 294, 237, 14);
		contentPane.add(autorObl);
		autorObl.setVisible(false);
		
		JButton btnNewButton = new JButton("Pedir");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				formato.setVisible(false);
				libroObl.setVisible(false);
				autorObl.setVisible(false);
				boolean valid = true; 

				if (!verifyStrInput(textField.getText())){
					formato.setVisible(true);
					valid = false;
				}
				
				if ((int)spinner.getValue() < 1){
					autorObl.setVisible(true);
					valid = false;
				}
				
				if (seleccionado == null){
					libroObl.setVisible(true);
					valid = false;
				}
		        
		        if (valid) {
		        	ExportControlador conTrola = new ExportControlador();
		        	
			        Export nuevaExport = new Export(0, seleccionado.getLibroId(), (int)spinner.getValue(), textField.getText(), LocalDate.now());
					conTrola.addExport(nuevaExport);
		        	JOptionPane.showMessageDialog(null, "Pedido de exportacion realizado exitosamente!");
		        	dispose();
		        	return;
		        }
			}
		});
		btnNewButton.setBounds(31, 335, 97, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
		        return;
			}
		});
		btnNewButton_1.setBounds(157, 335, 97, 23);
		contentPane.add(btnNewButton_1);
	}
}