package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controladores.AutorControlador;
import controladores.EditorialControlador;
import controladores.LibroControlador;
import controladores.SagaControlador;
import interfaces.Auxiliaries;
import modelos.Autor;
import modelos.Editorial;
import modelos.Libro;
import modelos.Saga;

public class AddBook extends JDialog implements Auxiliaries{
	private static final long serialVersionUID = 1L;
	private LibroControlador controlador;
	private JPanel contentPane;
	private JTextField textField;
	private Libro libruli;
	private JLabel imagenLabel;
	private byte[] imagenData;	
	
	public AddBook(Libro book) {
		super((JFrame)null, "Add Book", true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		String action = "Añadir";
		if (book != null) {
			action = "Actualizar";
		}
		setTitle(action + " Libros");
		setBounds(100, 100, 569, 411);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		controlador = new LibroControlador();
		libruli = book;

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Titulo:");
		lblNewLabel.setBounds(51, 22, 217, 14);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(31, 41, 223, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		if (book != null) {
			textField.setText(book.getTitulo());
		}

		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(31, 100, 223, 22);
		contentPane.add(comboBox);
		
		AutorControlador controlaAutor = new AutorControlador();
		comboBox.addItem("...");
		for (Autor autor : controlaAutor.getAllAutors()) {
			comboBox.addItem(autor);
			if (book != null && autor.getAutorId() == book.getAutorId()) { 
				comboBox.setSelectedItem(autor);
			}
        }
		
		JLabel lblSaga = new JLabel("Saga:");
		lblSaga.setBounds(51, 143, 217, 14);
		contentPane.add(lblSaga);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(31, 161, 223, 22);
		contentPane.add(comboBox_2);
		
		SagaControlador controlaSaga = new SagaControlador();
		comboBox_2.addItem("...");
		for (Saga saga : controlaSaga.getAllSagas()) {
			comboBox_2.addItem(saga);
			if (book != null && book.getSagaId() != 0 && saga.getSagaId() == book.getSagaId()) {
				comboBox_2.setSelectedItem(saga);
			}
        }
		
		JLabel lblEditorial = new JLabel("Editorial:");
		lblEditorial.setBounds(51, 204, 217, 14);
		contentPane.add(lblEditorial);
		
		JComboBox comboBox_3 = new JComboBox<>();
		comboBox_3.setBounds(31, 222, 223, 22);
		contentPane.add(comboBox_3);

		EditorialControlador controlaEdit = new EditorialControlador();
		comboBox_3.addItem("...");
		for (Editorial edit : controlaEdit.getAllEditorials()) {
			comboBox_3.addItem(edit);
			if (book != null && (book.getEditorialId() != 0) && (edit.getEditorialId() == book.getEditorialId())) {
				comboBox_3.setSelectedItem(edit);
			}
        }
		
		JLabel booklet = new JLabel("El libro ingresado ya esta registrado");
		booklet.setForeground(new Color(204, 37, 13));
		booklet.setBounds(279, 326, 237, 14);
		contentPane.add(booklet);
		booklet.setVisible(false);
		
		JLabel formato = new JLabel("El formato ingresado es invalido");
		formato.setForeground(new Color(204, 37, 13));
		formato.setBounds(279, 44, 195, 14);
		contentPane.add(formato);
		formato.setVisible(false);
		
		JLabel autorObl = new JLabel("Este campo debe ser seleccionado");
		autorObl.setForeground(new Color(204, 37, 13));
		autorObl.setBounds(279, 104, 237, 14);
		contentPane.add(autorObl);
		autorObl.setVisible(false);
		
		imagenLabel = new JLabel("");
		imagenLabel.setBounds(279, 276, 250, 14);
		contentPane.add(imagenLabel);
		
		JButton seleccionarImagenBtn = new JButton("Seleccionar Imagen");
		seleccionarImagenBtn.setBounds(31, 272, 223, 22);
		seleccionarImagenBtn.addActionListener (new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			seleccionarImagen();
			imagenLabel.setBounds(279, 276, 250, 14);
			contentPane.add(imagenLabel);
			}
		});
		contentPane.add(seleccionarImagenBtn);

		JButton btnNewButton = new JButton(action);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				formato.setVisible(false);
				booklet.setVisible(false);
				autorObl.setVisible(false);
				boolean valid = true; 

				if (!verifyStrInput(textField.getText())){
					formato.setVisible(true);
					valid = false;
				}
				
				if (comboBox.getSelectedItem().equals("...")){
					autorObl.setVisible(true);
					valid = false;
				}
				
				if (libruli == null) {
					for (Libro librito : controlador.getAllBooks()) {
			            if (librito.getTitulo().equalsIgnoreCase(textField.getText()) && 
			            	librito.getAutorId() == ((Autor)comboBox.getSelectedItem()).getAutorId() && 
			            	librito.getEditorialId() == ((Editorial)comboBox_3.getSelectedItem()).getEditorialId()) {
							booklet.setVisible(true);
			        		valid = false;
			                break;
			            }
			        }
				}

				if (comboBox.getSelectedItem().equals("...")){
					autorObl.setVisible(true);
					valid = false;
				}
		        
		        if (valid) {
		        	int saguli, editoriali;
			        if (comboBox_2.getSelectedItem().equals("...")){
						saguli = 0;
					} else {
						saguli = ((Saga)comboBox_2.getSelectedItem()).getSagaId();
					}
			        
			        if (comboBox_3.getSelectedItem().equals("...")){
						editoriali = 0;
					} else {
						editoriali = ((Editorial)comboBox_3.getSelectedItem()).getEditorialId();
					}
					
			        Libro nuevoLibro = new Libro(0, textField.getText(), saguli, editoriali, ((Autor)comboBox.getSelectedItem()).getAutorId(), imagenData);
			        if (libruli != null) {
			        	nuevoLibro.setLibroId(libruli.getLibroId());
			        	controlador.updateBook(nuevoLibro);
			        	JOptionPane.showMessageDialog(null, "Libro actualizado exitosamente!");
			        } else {
			        	controlador.addBook(nuevoLibro);
			        	JOptionPane.showMessageDialog(null, "Libro agregado exitosamente!");
			        }
		        	dispose();
		        	return;
		        }
			}
		});
		btnNewButton.setBounds(31, 322, 97, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
		        return;
			}
		});
		btnNewButton_1.setBounds(157, 322, 97, 23);
		contentPane.add(btnNewButton_1);
		
		JLabel lblAutor = new JLabel("Autor:");
		lblAutor.setBounds(51, 82, 217, 14);
		contentPane.add(lblAutor);
	}
	
    private void seleccionarImagen() {
    	JFileChooser fileChooser = new JFileChooser();
    	int result = fileChooser.showOpenDialog(this);
    	if (result == JFileChooser.APPROVE_OPTION) {
    		File file = fileChooser.getSelectedFile();
    		imagenLabel.setText(file.getName());
    		imagenData = leerImagen(file);
    	}
    }
    
    private byte[] leerImagen(File file) {
    	byte[] bFile = new byte[(int) file.length()];
    	try (FileInputStream fileInputStream = new FileInputStream(file)) {
    		fileInputStream.read(bFile);
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	return bFile;
    }
}