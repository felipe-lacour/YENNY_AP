package vista;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalDate;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
import javax.swing.JSpinner;
import javax.swing.JCheckBox;

import controladores.AutorControlador;
import controladores.EditorialControlador;
import controladores.EjemplarControlador;
import controladores.LibroControlador;
import controladores.SagaControlador;
import interfaces.Auxiliaries;
import modelos.Ejemplar;
import modelos.Libro;
import modelos.Usuario;

public class AddSpecimen extends JDialog implements Auxiliaries{
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel model;
    private LibroControlador controlador;
    private EjemplarControlador conTrola;
    private JLabel elemento;
    private Libro seleccionado;
    private JLabel lblNewLabel;
    private JTextField searchField;
    private TableRowSorter<DefaultTableModel> sorter;
    private JTextField textField;
    private Ejemplar ejemplitazo;
	private JTextField textField1;
	private JLabel precioObl;

	public AddSpecimen(Ejemplar ejemplazo, Usuario tipazo) {
		super((JFrame)null, "Add Specimen", true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		String action = "Pedir";
		if (ejemplazo != null) {
			action = "Modificar";
		}
		setTitle(action + " Ejemplares");
        setBounds(100, 100, 569, 519);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        controlador = new LibroControlador();
        ejemplitazo = ejemplazo;

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

		JLabel lblIdDeLa = new JLabel("Idioma:");
		lblIdDeLa.setBounds(51, 301, 217, 14);
		contentPane.add(lblIdDeLa);

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
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Tapa Dura");
		chckbxNewCheckBox.setBounds(31, 205, 113, 23);
		contentPane.add(chckbxNewCheckBox);
		
		if (ejemplazo != null) {
			chckbxNewCheckBox.setSelected(ejemplazo.isTapaDura());
		}
		
		JCheckBox chckbxEdicionEspecial = new JCheckBox("Edicion Especial");
		chckbxEdicionEspecial.setBounds(31, 231, 123, 23);
		contentPane.add(chckbxEdicionEspecial);
		
		if (ejemplazo != null) {
			chckbxEdicionEspecial.setSelected(ejemplazo.isEdicionEspecial());
		}
		
		JCheckBox chckbxFirmado = new JCheckBox("Firmado");
		chckbxFirmado.setBounds(31, 257, 113, 23);
		contentPane.add(chckbxFirmado);
		
		if (ejemplazo != null) {
			chckbxFirmado.setSelected(ejemplazo.isFirmado());
		}
		
		textField = new JTextField();
		textField.setBounds(31, 326, 223, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblCantidad = new JLabel("Cantidad:");
		lblCantidad.setBounds(51, 364, 217, 14);
		contentPane.add(lblCantidad);
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(31, 390, 223, 20);
		contentPane.add(spinner);
		
		JLabel formato = new JLabel("El formato ingresado es invalido");
		formato.setForeground(new Color(204, 37, 13));
		formato.setBounds(279, 329, 195, 14);
		contentPane.add(formato);
		formato.setVisible(false);
		
		JLabel libroObl = new JLabel("Este campo debe ser seleccionado");
		libroObl.setForeground(new Color(204, 37, 13));
		libroObl.setBounds(279, 173, 237, 14);
		contentPane.add(libroObl);
		libroObl.setVisible(false);
		
		JLabel autorObl = new JLabel("Debe ingresar una cantidad");
		autorObl.setForeground(new Color(204, 37, 13));
		autorObl.setBounds(279, 393, 237, 14);
		contentPane.add(autorObl);
		autorObl.setVisible(false);

		JButton btnNewButton = new JButton(action);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				formato.setVisible(false);
				libroObl.setVisible(false);
				autorObl.setVisible(false);
				if (ejemplazo != null) {
					precioObl.setVisible(false);
				}
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
				
				if ((ejemplazo != null) && (!verifyDouInput(textField1.getText()))){
					precioObl.setVisible(true);
					valid = false;
				}
		        
		        if (valid) {
		        	conTrola = new EjemplarControlador();
		        	
		        	if (ejemplitazo != null) {
		        		Ejemplar nuevoEjemplo = new Ejemplar(ejemplitazo.getEjemplarId(), seleccionado.getLibroId(), tipazo.getSucursalId(), ejemplitazo.getIsbn(), Double.parseDouble(textField1.getText()), chckbxNewCheckBox.isSelected(), 
								 chckbxEdicionEspecial.isSelected(),  ejemplitazo.getFechaEdicion(), ejemplitazo.getNumeroEdicion(), chckbxFirmado.isSelected(), 
								 ejemplitazo.getIdioma(), ejemplitazo.getFechaAdquisicion(), 0);
						conTrola.updateEjemplar(nuevoEjemplo);
						
						JOptionPane.showMessageDialog(null, "Libro actualizado exitosamente!");
		        	} else {
			        	double precio = generateRandomDouble(200, 5000);
			        	Random random = new Random();
			        	int editoriali = random.nextInt(100) + 1;
			        	
			        	
			        	for (int i = 0; i < (int)spinner.getValue(); i++) {
					        Ejemplar nuevoEjemplo = new Ejemplar(0, seleccionado.getLibroId(), tipazo.getSucursalId(), generateISBN(), precio, chckbxNewCheckBox.isSelected(), 
									 chckbxEdicionEspecial.isSelected(),  LocalDate.now().minusMonths(2), editoriali, chckbxFirmado.isSelected(), 
									 textField.getText(), LocalDate.now(), 0);
							conTrola.addEjemplar(nuevoEjemplo);
			        	}
			        	
			        	JOptionPane.showMessageDialog(null, "Libros encargados exitosamente!");
		        	}
		        	dispose();
		        	return;
		        }
			}
		});
		btnNewButton.setBounds(31, 434, 97, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
		        return;
			}
		});
		btnNewButton_1.setBounds(157, 434, 97, 23);
		contentPane.add(btnNewButton_1);
		
		
		if (ejemplazo != null) {
			scrollPane.setVisible(false);
			lblNewLabel.setVisible(false);
			elemento.setBounds(51, 22, 492, 36);
			chckbxNewCheckBox.setBounds(31, 54, 113, 23);
			chckbxEdicionEspecial.setBounds(31, 80, 123, 23);
			chckbxFirmado.setBounds(31, 106, 113, 23);
			lblIdDeLa.setBounds(51, 150, 217, 14);
			textField.setBounds(31, 175, 223, 20);
			lblCantidad.setVisible(false);
			spinner.setVisible(false);
			spinner.setValue(1);
			btnNewButton_1.setBounds(157, 282, 97, 23);
			btnNewButton.setBounds(31, 282, 97, 23);
			seleccionado = controlador.getBookById(ejemplazo.getLibroId());
            elemento.setText("Libro:  " + seleccionado.getTitulo() + "  -  " + seleccionado.getAutorId());
            textField.setText(ejemplazo.getIdioma());
            lblIdDeLa.setEnabled(false);
            textField.setEnabled(false);
            searchField.setVisible(false);
            setBounds(100, 100, 569, 374);
            
            JLabel lblIdDeLab = new JLabel("Precio:");
    		lblIdDeLab.setBounds(51, 213, 217, 14);
    		contentPane.add(lblIdDeLab);
            
            textField1 = new JTextField();
    		textField1.setBounds(31, 238, 223, 20);
    		contentPane.add(textField1);
    		textField1.setColumns(10);
    		textField1.setText(Double.toString(ejemplazo.getPrecio()));
    		
    		precioObl = new JLabel("El precio ingresado es invalido");
    		precioObl.setForeground(new Color(204, 37, 13));
    		precioObl.setBounds(279, 241, 237, 14);
    		contentPane.add(precioObl);
    		precioObl.setVisible(false);
		}
	}
	
    public static String generateISBN() {
        Random random = new Random();
        StringBuilder isbn = new StringBuilder();

        for (int i = 0; i < 9; i++) {
            isbn.append(random.nextInt(10));
        }

        if (random.nextBoolean()) {
            isbn.append(random.nextInt(10));
        } else {
            isbn.append('X');
        }

        return isbn.toString();
    }
    
    public static double generateRandomDouble(double min, double max) {
        Random random = new Random();
        double randomDouble = min + (max - min) * random.nextDouble();
        return Math.round(randomDouble * 100.0) / 100.0;
    }
}