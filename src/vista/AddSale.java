package vista;

import java.awt.EventQueue;
import java.awt.Menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComponent;
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
import controladores.ClienteControlador;
import controladores.EjemplarControlador;
import controladores.LibroControlador;
import controladores.MetodoPagoControlador;
import controladores.SagaControlador;
import controladores.UsuarioControlador;
import controladores.VentaControlador;
import modelos.Cliente;
import modelos.Ejemplar;
import modelos.Libro;
import modelos.MetodoPago;
import modelos.Usuario;
import modelos.Venta;

public class AddSale extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable tableC, tableS, tableM;
    private DefaultTableModel modelC, modelS, modelM;
    private ClienteControlador controladorClientes;
    private MetodoPagoControlador controladorMetodos;
    private JLabel elementoC, elementoS, elementoM;
    private JLabel lblNewLabel;
    private Cliente seleccionadoC;
    private Ejemplar seleccionadoS;
    private MetodoPago seleccionadoM;
    private JButton btnVolver;
    private Usuario userPasado;
    private JTextField searchFieldC, searchFieldS, searchFieldM;
    private TableRowSorter<DefaultTableModel> sorterC, sorterS, sorterM;
    private Usuario tipazado;
    private EjemplarControlador controladorEjemplares;
    private LibroControlador libroControlador;
    private VentaControlador ventaControlador;
    private JButton btnFinalizarVenta_1;
    private JButton btnFinalizarVenta_2;
    private JButton btnAgregar;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AddSale frame = new AddSale(new Usuario(0, "dkjfhskfh", 1, 1, "kfjakjfha", "kjfajkfha"));
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
    public AddSale(Usuario paseUsuario) {
        super();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Administrar Clientes");
        setBounds(100, 100, 569, 889);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        controladorClientes = new ClienteControlador();
        userPasado = paseUsuario;
        controladorEjemplares = new EjemplarControlador();
        controladorMetodos = new MetodoPagoControlador();
        libroControlador = new LibroControlador();
        ventaControlador = new VentaControlador();
        
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        lblNewLabel = new JLabel("Clientes:");
        lblNewLabel.setBounds(10, 21, 83, 14);
        contentPane.add(lblNewLabel);
        
        String[] columnNamesClient = {"ID", "Nombre", "Apellido", "Edad", "Club del Libro"};
        modelC = new DefaultTableModel(columnNamesClient, 0);
        tableC = new JTable(modelC);
        sorterC = new TableRowSorter<>(modelC);
        tableC.setRowSorter(sorterC);
        actualizarTablaClientes();
        
        
        JScrollPane scrollPaneClientes = new JScrollPane(tableC);
        scrollPaneClientes.setBounds(10, 44, 533, 140);
        contentPane.add(scrollPaneClientes);
        
        elementoC = new JLabel("Seleccionado:");
        elementoC.setVerticalAlignment(SwingConstants.TOP);
        elementoC.setBounds(10, 196, 406, 53);
        contentPane.add(elementoC);
        
        searchFieldC = new JTextField();
        searchFieldC.setBounds(327, 15, 216, 25);
        contentPane.add(searchFieldC);
        
        searchFieldC.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String searchText = searchFieldC.getText();
                if (searchText.trim().length() == 0) {
                    sorterC.setRowFilter(null);
                } else {
                    sorterC.setRowFilter(RowFilter.regexFilter("(?i)" + searchText));
                }
            }
        });
        
        JButton AgregarC = new JButton("Agregar");
        AgregarC.setBounds(417, 195, 126, 35);
        AgregarC.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AddClient dialog;
                if (userPasado != null) {
                    dialog = new AddClient(null, paseUsuario);
                } else {
                    dialog = new AddClient(null, paseUsuario);
                }
                dialog.setVisible(true);
                actualizarTablaClientes();
            }
        });
        contentPane.add(AgregarC);
        
        ListSelectionModel selectionModelC = tableC.getSelectionModel();
        selectionModelC.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        selectionModelC.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = tableC.getSelectedRow();
                    if (selectedRow != -1) {
                        int id = (int) tableC.getValueAt(selectedRow, 0);

                        seleccionadoC = controladorClientes.getClienteById(id);
                        elementoC.setText("Seleccionado: ID=" + seleccionadoC.getClienteId() + ", Nombre= " + seleccionadoC.getNombre() + ", Apellido= " + seleccionadoC.getApellido() 
                                + ", Genero= " +  seleccionadoC.getGenero() + ", Edad= " + seleccionadoC.getEdad() + ", Lugar de Compra= " + seleccionadoC.getLugar_de_compra() 
                                + ", Club de Libros= " + seleccionadoC.isClubLibros());
                        actualizarTablaMetodos();
                        btnAgregar.setVisible(true);
                        seleccionadoM = null;
                        elementoM.setText("Seleccionado:");
                    }
                }
            }
        });
        
        
        String[] columnNamesSpecimen = {"ID", "Titulo", "Autor", "Saga", "Precio", "Tapa", "Edicion", "N Edicion", "Firmado", "Idioma"};
        ArrayList<Ejemplar> ejemplaresSeleccionados = new ArrayList<>();

        modelS = new DefaultTableModel(columnNamesSpecimen, 0);
        tableS = new JTable(modelS);
        sorterS = new TableRowSorter<>(modelS);
        tableS.setRowSorter(sorterS);
        actualizarTablaEjemplares();

        JScrollPane scrollPaneEjemplares = new JScrollPane(tableS);
        scrollPaneEjemplares.setBounds(10, 297, 533, 140);
        contentPane.add(scrollPaneEjemplares);

        elementoS = new JLabel("Seleccionado:");
        elementoS.setVerticalAlignment(SwingConstants.TOP);
        elementoS.setBounds(10, 447, 533, 100);  // Aumenta la altura del JLabel para mostrar más contenido

        JScrollPane scrollPaneSeleccionados = new JScrollPane(elementoS);
        scrollPaneSeleccionados.setBounds(10, 447, 533, 100);  // Asegúrate de que las dimensiones coincidan con las de JLabel
        scrollPaneSeleccionados.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        contentPane.add(scrollPaneSeleccionados);

        searchFieldS = new JTextField();
        searchFieldS.setBounds(327, 260, 216, 25);
        contentPane.add(searchFieldS);

        searchFieldS.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String searchText = searchFieldS.getText();
                if (searchText.trim().length() == 0) {
                    sorterS.setRowFilter(null);
                } else {
                    sorterS.setRowFilter(RowFilter.regexFilter("(?i)" + searchText));
                }
            }
        });

        JLabel ejemplaresLabel = new JLabel("Ejemplares:");
        ejemplaresLabel.setBounds(10, 266, 104, 14);
        contentPane.add(ejemplaresLabel);

        ListSelectionModel selectionModelS = tableS.getSelectionModel();
        selectionModelS.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        selectionModelS.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = tableS.getSelectedRow();
                    if (selectedRow != -1) {
                        int id = (int) tableS.getValueAt(selectedRow, 0);
                        int index = -1;
                        
                        seleccionadoS = controladorEjemplares.getEjemplarById(id);
                        
                        for (int i = 0; i < ejemplaresSeleccionados.size(); i++) {
							if(seleccionadoS.getEjemplarId() == ejemplaresSeleccionados.get(i).getEjemplarId()) {
								index = i;
								break;
							}
						}
                        
                        if(index >= 0) {
                        	ejemplaresSeleccionados.remove(index);
                        } else {
                            ejemplaresSeleccionados.add(seleccionadoS);
                        }

                        StringBuilder stringAux = new StringBuilder("<html>Seleccionado:<br/>");

                        for (Ejemplar ejemplar : ejemplaresSeleccionados) {
                            stringAux.append("ID: ").append(ejemplar.getEjemplarId())
                                    .append(", Libro: ").append(libroControlador.getBookById(ejemplar.getLibroId()).getTitulo())
                                    .append(", Precio: $").append(ejemplar.getPrecio())
                                    .append(", ISBN: ").append(ejemplar.getIsbn())
                                    .append("<br/>");
                        }

                        stringAux.append("</html>");
                        elementoS.setText(stringAux.toString());
                    }
                }
            }
        });


        
        JLabel metodosLabel = new JLabel("Metodos de Pago:");
        metodosLabel.setBounds(10, 564, 260, 14);
        contentPane.add(metodosLabel);
        
        String[] columnNamesMethods = {"ID", "Cliente ID", "Tipo", "Detalles"};
        
        modelM = new DefaultTableModel(columnNamesMethods, 0);
        tableM = new JTable(modelM);
        sorterM = new TableRowSorter<>(modelM);
        tableM.setRowSorter(sorterM);
        actualizarTablaMetodos();
        
        JScrollPane scrollPaneMetodos = new JScrollPane(tableM);
        scrollPaneMetodos.setBounds(10, 590, 533, 140);
        contentPane.add(scrollPaneMetodos);
        
        elementoM = new JLabel("Seleccionado:");
        elementoM.setVerticalAlignment(SwingConstants.TOP);
        elementoM.setBounds(10, 747, 406, 53);
        contentPane.add(elementoM);
        
        searchFieldM = new JTextField();
        searchFieldM.setBounds(327, 559, 216, 25);
        contentPane.add(searchFieldM);
        
        JButton btnFinalizarVenta = new JButton("Finalizar Venta");
        btnFinalizarVenta.setBounds(417, 812, 126, 35);
        
        btnFinalizarVenta.addActionListener(new ActionListener() {
            @SuppressWarnings("null")
			public void actionPerformed(ActionEvent e) {
            	int metodoSeleccionado = -1;
            	
            	if(seleccionadoM != null) {
            		metodoSeleccionado = seleccionadoM.getMetodoPagoId();
            	}
            	
        		Venta currentSale = new Venta(0, metodoSeleccionado, LocalDate.now(), paseUsuario.getSucursalId());
        		int ventaId = ventaControlador.addSale(currentSale);
        		
                for (Ejemplar ejemplar : ejemplaresSeleccionados) {
                    try {
                        ejemplar.setVentaId(ventaId);
                        ejemplar.setSucursalId(0);
                        controladorEjemplares.updateEjemplar(ejemplar);
                    } catch(Exception e1) {
                        JOptionPane.showMessageDialog(null, "Error al actualizar los ejemplares: " + e1.getMessage());
                    }
                }
                
                actualizarTablaEjemplares();
                actualizarTablaClientes();
                actualizarTablaMetodos();
                
                seleccionadoS = null;
                seleccionadoM = null;
                seleccionadoC = null;
                
                ejemplaresSeleccionados.clear();
                
                elementoS.setText("Seleccionado: ");
                elementoM.setText("Seleccionado: ");
                elementoC.setText("Seleccionado: ");
                
                btnAgregar.setVisible(false);
                
            }
        });
        
        contentPane.add(btnFinalizarVenta);
        
        btnVolver = new JButton("Volver");
        btnVolver.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		dispose();
        		return;
        	}
        });
        btnVolver.setBounds(279, 815, 126, 35);
        contentPane.add(btnVolver);
        
        btnAgregar = new JButton("Agregar");
        btnAgregar.setBounds(417, 742, 126, 35);
        contentPane.add(btnAgregar);
        btnAgregar.setVisible(false);
        
        btnAgregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AddMetodoPago dialog;
                dialog = new AddMetodoPago(seleccionadoC);
                dialog.setVisible(true);
                actualizarTablaMetodos();
            }
        });
        
        searchFieldM.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String searchText = searchFieldM.getText();
                if (searchText.trim().length() == 0) {
                    sorterM.setRowFilter(null);
                } else {
                    sorterM.setRowFilter(RowFilter.regexFilter("(?i)" + searchText));
                }
            }
        });
        
        ListSelectionModel selectionModelM = tableM.getSelectionModel();
        selectionModelM.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        selectionModelM.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = tableM.getSelectedRow();
                    if (selectedRow != -1) {
                        int id = (int) tableM.getValueAt(selectedRow, 0);

                        seleccionadoM = controladorMetodos.getMethodById(id);
                        elementoM.setText("Seleccionado: ID=" + seleccionadoM.getMetodoPagoId() + ", Cliente ID=" + seleccionadoM.getClienteId() + 
                                          ", Tipo= " + seleccionadoM.getTipo() + ", Detalles= " + seleccionadoM.getDetalles());
                    }
                }
            }
        });
        
    }
    
    private void actualizarTablaClientes() {
        modelC.setRowCount(0);
        for (Cliente cliente : controladorClientes.getAllClientes()) {
            modelC.addRow(new Object[]{cliente.getClienteId(), cliente.getNombre(), cliente.getApellido(), cliente.getEdad(), cliente.isClubLibros()});
        }
    }
    
    private void actualizarTablaEjemplares() {
        modelS.setRowCount(0);
        LibroControlador controlaLibro = new LibroControlador();
        AutorControlador controlaAutor = new AutorControlador();
        SagaControlador controlaSaga = new SagaControlador();

        for (Ejemplar ejemplo : controladorEjemplares.getAllEjemplar()) {
            if ((ejemplo.getSucursalId() != 0) && ejemplo.getSucursalId() == userPasado.getSucursalId() && ejemplo.getVentaId() == null) {
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
                
                modelS.addRow(new Object[] {
                    ejemplo.getEjemplarId(), 
                    libruli.getTitulo(), 
                    controlaAutor.getAutorById(libruli.getAutorId()).getNombre(), 
                    saguli, 
                    ejemplo.getPrecio(), 
                    tapa, 
                    edicion, 
                    ejemplo.getNumeroEdicion(), 
                    firma, 
                    ejemplo.getIdioma()
                });
            }
        }    
    }

    
    private void actualizarTablaMetodos() {
        modelM.setRowCount(0);
        if(seleccionadoC != null) {
            for (MetodoPago metodo : controladorMetodos.getAllMethods()) {
            	if(metodo.getClienteId() == seleccionadoC.getClienteId()) {
                    modelM.addRow(new Object[]{metodo.getMetodoPagoId(), metodo.getClienteId(), metodo.getTipo(), metodo.getDetalles()});
            	}
            }
        }
    }
}
