package vista;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import controladores.EjemplarControlador;
import controladores.LibroControlador;
import modelos.Ejemplar;
import modelos.Libro;

import javax.swing.JLabel;

public class ViewSpecimens extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel model;
    private EjemplarControlador controlador;
    private JLabel elemento;
    private Ejemplar seleccionado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewSpecimens frame = new ViewSpecimens();
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
	public ViewSpecimens() {
		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setBounds(100, 100, 569, 446);
	        contentPane = new JPanel();
	        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	        setContentPane(contentPane);
	        controlador = new EjemplarControlador();

	        String[] columnNames = {"ID", "Libro", "Precio", "Idioma", "Tapa Dura", "ISBN", "Numero Edicion"};
	        model = new DefaultTableModel(columnNames, 0);
	        table = new JTable(model);
	        actualizarTabla();
	        contentPane.setLayout(null);

	        JScrollPane scrollPane = new JScrollPane(table);
	        scrollPane.setBounds(5, 19, 452, 190);
	        contentPane.add(scrollPane);

	        // Crear el JLabel para mostrar la selección
	        elemento = new JLabel("Seleccionado:");
	        elemento.setBounds(5, 5, 911, 14);
	        contentPane.add(elemento);

	        // Crear el botón de eliminar
	        JButton Eliminar = new JButton("Eliminar");
	        Eliminar.setBounds(5, 254, 143, 39);
	        Eliminar.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                controlador.deleteEjemplar(seleccionado.getEjemplarId());
	                actualizarTabla();
	            }
	        });
	        contentPane.add(Eliminar);

	        // Configurar el modelo de selección
	        ListSelectionModel selectionModel = table.getSelectionModel();
	        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	        // Agregar un escuchador de selección
	        selectionModel.addListSelectionListener(new ListSelectionListener() {
	            @Override
	            public void valueChanged(ListSelectionEvent e) {
	                if (!e.getValueIsAdjusting()) {
	                    int selectedRow = table.getSelectedRow();
	                    if (selectedRow != -1) {
	                        int id = (int) table.getValueAt(selectedRow, 0);

	                        seleccionado = controlador.getEjemplarById(id);
	                        //"ID", "Libro", "Precio", "Idioma", "Tapa Dura", "ISBN", "Numero Edicion"
	                        elemento.setText("Seleccionado: ID: " + seleccionado.getLibroId() + ", Titulo: " + seleccionado.getTitulo() + 
	                        				 ", Autor: " + seleccionado.getAutorId() + ", Editorial: " + seleccionado.getEditorialId() + ", Saga: " + seleccionado.getSagaId());
	                    }
	                }
	            }
	        });
	    }

	    private void actualizarTabla() {
	        model.setRowCount(0);

	        for (Ejemplar ejemplar : controlador.getAllEjemplar()) {
	            model.addRow(new Object[]{ejemplar.getLibroId(), ejemplar.getTitulo(), ejemplar.getAutorId(), ejemplar.getEditorialId(), ejemplar.getSagaId()});
	        }
	    }
}
