package vista;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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

import controladores.AutorControlador;
import controladores.EditorialControlador;
import controladores.LibroControlador;
import controladores.PromocionControlador;
import controladores.PromocionLibroControlador;
import controladores.SagaControlador;
import interfaces.Auxiliaries;
import modelos.Libro;
import modelos.Promocion;
import modelos.PromocionLibro;
import modelos.Usuario;

public class AddPromo extends JDialog implements Auxiliaries{
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField;
    private JTextField textField1;
    private JTable table1;
    private DefaultTableModel model1;
    private JTable table2;
    private DefaultTableModel model2;
    private LibroControlador controlador;
    private PromocionLibroControlador miniContrololador;
    private JLabel elemento1;
    private Libro seleccionado1;
    private JLabel elemento2;
    private Libro seleccionado2;
    private JTextField searchField1;
    private TableRowSorter<DefaultTableModel> sorter1;
    private JTextField searchField2;
    private TableRowSorter<DefaultTableModel> sorter2;
    private Promocion promosota;
    private Usuario userio;

	public AddPromo(Promocion promoPass, Usuario user) {
		super((JFrame)null, "View Books", true);
		String action = "Añadir";
		if (promoPass != null) {
			action = "Actualizar";
		}
		setTitle(action + " Promos");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 569, 580);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        controlador = new LibroControlador();
        miniContrololador = new PromocionLibroControlador();
        promosota = promoPass;
        userio = user;
        
		JLabel lblNewLabel = new JLabel("Nombre:");
		lblNewLabel.setBounds(51, 22, 217, 14);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(31, 41, 223, 20);
		contentPane.add(textField);
		textField.setColumns(10);
        
        JLabel lblIdDeLab = new JLabel("Descuento:");
		lblIdDeLab.setBounds(51, 78, 217, 14);
		contentPane.add(lblIdDeLab);
        
        textField1 = new JTextField();
		textField1.setBounds(31, 103, 223, 20);
		contentPane.add(textField1);
		textField1.setColumns(10);
		
		if (promosota != null) {
			textField.setText(promosota.getNombre());
			textField1.setText(String.valueOf(promosota.getDescuento()));
		}
		
        String[] columnNames = {"ID", "Titulo", "Autor", "Editorial", "Saga"};
        model1 = new DefaultTableModel(columnNames, 0);
        table1 = new JTable(model1);
        sorter1 = new TableRowSorter<>(model1);
        table1.setRowSorter(sorter1);
        actualizarTabla1();
        model2 = new DefaultTableModel(columnNames, 0);
        table2 = new JTable(model2);
        sorter2 = new TableRowSorter<>(model2);
        table2.setRowSorter(sorter2);
        actualizarTabla2();
        
        JScrollPane scrollPane1 = new JScrollPane(table1);
        scrollPane1.setBounds(10, 167, 533, 87);
        contentPane.add(scrollPane1);
        
        JScrollPane scrollPane2 = new JScrollPane(table2);
        scrollPane2.setBounds(10, 350, 533, 87);
        contentPane.add(scrollPane2);

        elemento2 = new JLabel("Seleccionado:");
        elemento2.setVerticalAlignment(SwingConstants.TOP);
        elemento2.setBounds(51, 448, 492, 20);
        contentPane.add(elemento2);
        
        searchField2 = new JTextField();
        searchField2.setBounds(400, 322, 143, 20);
        contentPane.add(searchField2);
        
        elemento1 = new JLabel("Seleccionado:");
        elemento1.setVerticalAlignment(SwingConstants.TOP);
        elemento1.setBounds(51, 265, 492, 20);
        contentPane.add(elemento1);
        
        searchField1 = new JTextField();
        searchField1.setBounds(400, 139, 143, 20);
        contentPane.add(searchField1);
        
        JLabel lblLibrosIncluidos = new JLabel("Libros incluidos:");
        lblLibrosIncluidos.setBounds(51, 142, 217, 14);
        contentPane.add(lblLibrosIncluidos);
        
        JLabel lblLibrosDisponibles = new JLabel("Libros disponibles:");
        lblLibrosDisponibles.setBounds(51, 325, 217, 14);
        contentPane.add(lblLibrosDisponibles);
        
		JLabel formato = new JLabel("El formato ingresado es invalido");
		formato.setForeground(new Color(204, 37, 13));
		formato.setBounds(278, 44, 195, 14);
		contentPane.add(formato);
		formato.setVisible(false);
		
		JLabel formato1 = new JLabel("El formato ingresado es invalido");
		formato1.setForeground(new Color(204, 37, 13));
		formato1.setBounds(278, 106, 195, 14);
		contentPane.add(formato1);
		formato1.setVisible(false);
        
		JLabel selected = new JLabel("Debe tener un item seleccionado para realizar esta operacion");
		selected.setForeground(new Color(214, 37, 13));
		selected.setBounds(51, 287, 393, 14);
		contentPane.add(selected);
		selected.setVisible(false);
        
		JLabel selected1 = new JLabel("Debe tener un item seleccionado para realizar esta operacion");
		selected1.setForeground(new Color(214, 37, 13));
		selected1.setBounds(51, 468, 393, 14);
		contentPane.add(selected1);
		selected1.setVisible(false);
        
        JButton btnNewButton = new JButton("Quitar");
        btnNewButton.setBounds(454, 283, 89, 23);
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	selected.setVisible(false);
            	
            	if (seleccionado1 == null) {
            		selected.setVisible(true);
            	} else {
            		miniContrololador.detelePromoLibro(seleccionado1.getLibroId());
                    actualizarTabla1();
                    actualizarTabla2();
                    seleccionado1 = null;
            	}
            }
        });
        contentPane.add(btnNewButton);
        
        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.setBounds(454, 464, 89, 23);
        btnAgregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	selected1.setVisible(false);
            	
            	if (seleccionado2 == null) {
            		selected1.setVisible(true);
            	} else {
            		PromocionLibro newPromoLibro = new PromocionLibro(promosota.getPromocionId(), seleccionado2.getLibroId());
                    miniContrololador.addPromoLibro(newPromoLibro);
                    actualizarTabla1();
                    actualizarTabla2();
                    seleccionado2 = null;
            	}
            }
        });
        contentPane.add(btnAgregar);
        
        searchField1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String searchText = searchField1.getText();
                if (searchText.trim().length() == 0) {
                    sorter1.setRowFilter(null);
                } else {
                    sorter1.setRowFilter(RowFilter.regexFilter("(?i)" + searchText));
                }
            }
        });
        
        searchField2.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String searchText = searchField2.getText();
                if (searchText.trim().length() == 0) {
                    sorter2.setRowFilter(null);
                } else {
                    sorter2.setRowFilter(RowFilter.regexFilter("(?i)" + searchText));
                }
            }
        });
        
        ListSelectionModel selectionModel1 = table1.getSelectionModel();
        selectionModel1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        selectionModel1.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = table1.getSelectedRow();
                    if (selectedRow != -1) {
                        int id = (int) table1.getValueAt(selectedRow, 0);

                        seleccionado1 = controlador.getBookById(id);
                        elemento1.setText("Seleccionado: ID: " + seleccionado1.getLibroId() + ", Titulo: " + seleccionado1.getTitulo() + 
                                         ", Autor: " + seleccionado1.getAutorId() + ", Editorial: " + seleccionado1.getEditorialId() + ", Saga: " + seleccionado1.getSagaId());
                    }
                }
            }
        });
        
        ListSelectionModel selectionModel2 = table2.getSelectionModel();
        selectionModel2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        selectionModel2.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = table2.getSelectedRow();
                    if (selectedRow != -1) {
                        int id = (int) table2.getValueAt(selectedRow, 0);

                        seleccionado2 = controlador.getBookById(id);
                        elemento2.setText("Seleccionado: ID: " + seleccionado2.getLibroId() + ", Titulo: " + seleccionado2.getTitulo() + 
                                         ", Autor: " + seleccionado2.getAutorId() + ", Editorial: " + seleccionado2.getEditorialId() + ", Saga: " + seleccionado2.getSagaId());
                    }
                }
            }
        });
        
		JButton actionButton = new JButton(action);
		actionButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				formato.setVisible(false);
				formato1.setVisible(false);
				boolean valid = true; 

				if (!verifyStrInput(textField.getText())){
					formato.setVisible(true);
					valid = false;
				}
				
				if (!verifyDouInput(textField1.getText())){
					formato1.setVisible(true);
					valid = false;
				}
		        
		        if (valid) {
		        	boolean delClub = false;
		        	int sucur = 0;
		        	if (userio == null) {
		        		delClub = true;
		        	}
		        	
		        	if (userio != null) {
		        		sucur = userio.getSucursalId();
		        	}
					
		        	PromocionControlador nuevoControl = new PromocionControlador();
			        Promocion nuevaPromo = new Promocion(0, textField.getText(), delClub, sucur, Double.parseDouble(textField1.getText()));
			        if (promosota != null) {
			        	nuevaPromo.setPromocionId(promosota.getPromocionId());
			        	nuevoControl.updatePromo(nuevaPromo);
			        	JOptionPane.showMessageDialog(null, "Promocion actualizada exitosamente!");
			        } else {
			        	nuevoControl.addPromo(nuevaPromo);
			        	JOptionPane.showMessageDialog(null, "Promocion agregada exitosamente!");
			        	promosota = nuevoControl.getPromoById(nuevoControl.lastPromo());
			        	btnNewButton.setEnabled(true);
						btnAgregar.setEnabled(true);
						actionButton.setText("Actualizar");
			        }
		        }
			}
		});
		actionButton.setBounds(31, 500, 97, 23);
		contentPane.add(actionButton);
		
		JButton notButton = new JButton("Volver");
		notButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
		        return;
			}
		});
		notButton.setBounds(157, 500, 97, 23);
		contentPane.add(notButton);
		
		if (promosota == null) {
			btnNewButton.setEnabled(false);
			btnAgregar.setEnabled(false);
		}
	}

    private void actualizarTabla1() {
        model1.setRowCount(0);
        AutorControlador controlaAutor = new AutorControlador();
        SagaControlador controlaSaga = new SagaControlador();
        EditorialControlador controlaEdit = new EditorialControlador();

        for (Libro libro : controlador.getAllBooks()) {
        	PromocionLibro proLib = miniContrololador.getPromoLibroById(libro.getLibroId());
        	if ((promosota != null) && (proLib != null) && (proLib.getPromocionId() == promosota.getPromocionId())) {
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
                
                model1.addRow(new Object[] { libro.getLibroId(), libro.getTitulo(), controlaAutor.getAutorById(libro.getAutorId()).getNombre(), editoriali, saguli });
        	}
        }
    }
    
    private void actualizarTabla2() {
        model2.setRowCount(0);
        AutorControlador controlaAutor = new AutorControlador();
        SagaControlador controlaSaga = new SagaControlador();
        EditorialControlador controlaEdit = new EditorialControlador();

        for (Libro libro : controlador.getAllBooks()) {
        	if (miniContrololador.getPromoLibroById(libro.getLibroId()) == null) {
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
                
                model2.addRow(new Object[] { libro.getLibroId(), libro.getTitulo(), controlaAutor.getAutorById(libro.getAutorId()).getNombre(), editoriali, saguli });
        	}
        }
    }
}
