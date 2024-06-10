package vista;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import controladores.SucuControlador;
import modelos.Sucursal;
import javax.swing.JButton;

public class Tabla_Sucursal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private SucuControlador controlador;
	private Sucursal seleccionado;
	private DefaultTableModel model;
	private JLabel elemento;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tabla_Sucursal frame = new Tabla_Sucursal();
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
	public Tabla_Sucursal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 569, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		//controladores
		controlador= new SucuControlador();
		Sucursal seleccionado = new Sucursal();
		
		//tabla y modelo
		String[] columnNames = {"Sucursal_id", "Direccion", "Nombre"};
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        actualizarTabla();
        contentPane.setLayout(null);
        
        
        // Crear el JScrollPane y agregar la tabla
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(5, 19, 500, 190);
        contentPane.add(scrollPane);

     // Crear el JLabel para mostrar la selección
        elemento = new JLabel("Seleccionado:");
        elemento.setBounds(5, 5, 911, 14);
        contentPane.add(elemento);
        
        JButton btnEditar = new JButton("Editar");
        btnEditar.setBounds(252, 227, 89, 23);
        contentPane.add(btnEditar);
        
        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(367, 227, 89, 23);
        contentPane.add(btnEliminar);
        
        
		//configuracion Seleccionar
		ListSelectionModel selectionModel= table.getSelectionModel();
		selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		 selectionModel.addListSelectionListener(new ListSelectionListener() {
	            @Override
	            public void valueChanged(ListSelectionEvent e) {
	                if (!e.getValueIsAdjusting()) {
	                    int selectedRow = table.getSelectedRow();
	                    if (selectedRow != -1) {
	                        int id = (int) table.getValueAt(selectedRow, 0);
	                        String ubicacion = (String) table.getValueAt(selectedRow, 1);
	                        String direccion = (String) table.getValueAt(selectedRow, 2);
	                        elemento.setText("Seleccionado: ID=" + id + ", ubicacion=" + ubicacion + ", direccion=" + direccion );
	                        seleccionado.setSucursalId(id);;
	                        seleccionado.setUbicacion(ubicacion);
	                        seleccionado.setNombre(direccion);;
	                        
	                    }
	                }
	            }
	        });
			}

			
		
		
	

	private void actualizarTabla() {
		// TODO Auto-generated method stub
		model.setRowCount(0);
		
		List<Sucursal> sucursales = controlador.getAllBranches();
		
		for (Sucursal sucursal : sucursales) {
			model.addRow(new Object[] { 
					sucursal.getSucursalId(),
					sucursal.getUbicacion(),
					sucursal.getNombre()
			});
		}
	}
}
