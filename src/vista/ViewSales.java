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

import controladores.MetodoPagoControlador;
import controladores.SucuControlador;
import controladores.VentaControlador;
import modelos.Usuario;
import modelos.Venta;

public class ViewSales extends JDialog {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel model;
    private VentaControlador controlador;
    private JLabel elemento;
    private JLabel lblNewLabel;
    private Venta seleccionado;
    private JButton btnVolver;
    private Usuario userPasado;
    private JTextField searchField;
    private TableRowSorter<DefaultTableModel> sorter;

	public ViewSales(Usuario paseUsuario) {
    	super((JFrame)null, "View Users", true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	setTitle("Administrar Usuarios");
        setBounds(100, 100, 569, 446);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        controlador = new VentaControlador();
        userPasado = paseUsuario;
        
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        lblNewLabel = new JLabel("Ventas:");
        lblNewLabel.setBounds(21, 18, 83, 14);
        contentPane.add(lblNewLabel);
        
        String[] columnNames = {"ID", "Metodo de Pago", "Fecha", "Sucursal"};
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

                        seleccionado = controlador.getSaleById(id);
                        elemento.setText("Seleccionado: ID:" + seleccionado.getVentaId() + ", Metodo de Pago:" + seleccionado.getMetodoPagoId() + 
                        				 ", Fecha:" + seleccionado.getFecha() + ", Sucursal:" + seleccionado.getSucursalId());
                    }
                }
            }
        });
	}

    private void actualizarTabla() {
        model.setRowCount(0);
        SucuControlador sucuControlador = new SucuControlador();
        MetodoPagoControlador pagoControlador = new MetodoPagoControlador();

        for (Venta ventita : controlador.getAllSales()) {
        	if (userPasado != null) {
        		if (ventita.getSucursalId() == userPasado.getSucursalId()) {
        			model.addRow(new Object[]{ventita.getVentaId(), pagoControlador.getMethodById(ventita.getMetodoPagoId()).getTipo(), 
        									  ventita.getFecha(), (sucuControlador.getBranchById(ventita.getSucursalId())).getNombre()});
        		}
        	} else {
        		model.addRow(new Object[]{ventita.getVentaId(), pagoControlador.getMethodById(ventita.getMetodoPagoId()).getTipo(), 
        								  ventita.getFecha(), (sucuControlador.getBranchById(ventita.getSucursalId())).getNombre()});
        	}
        }
    }
}
