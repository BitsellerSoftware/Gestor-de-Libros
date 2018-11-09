package interfaz;

import javax.swing.JFrame;//imports
import javax.swing.JPanel;//imports
import javax.swing.border.EmptyBorder;//imports
import javax.swing.table.DefaultTableModel;//imports
import javax.swing.text.PlainDocument;
import javax.swing.JLabel;//imports
import java.awt.Font;//imports
import javax.swing.JTextField;//imports
import javax.swing.WindowConstants;//imports
import javax.swing.JButton;//imports
import java.awt.event.MouseAdapter;//imports
import java.awt.event.MouseEvent;//imports
import java.io.FileNotFoundException;//imports
import java.io.FileOutputStream;//imports
import java.io.PrintStream;//imports

public class InterfazAdd extends JFrame {

	private static final long serialVersionUID = -1195234025311038881L;//declaracion de atributos
	private JPanel contentPane;//declaracion de atributos
	private JTextField textFieldAddISBN;//declaracion de atributos
	private JTextField textFieldAddTitulo;//declaracion de atributos
	private JTextField textFieldAddAutor;//declaracion de atributos
	private JTextField textFieldAddEditorial;//declaracion de atributos
	private JTextField textFieldAddEdicion;//declaracion de atributos
	private JTextField textFieldAddPublicacion;//declaracion de atributos
	private PrintStream salida;//declaracion de atributos

	/**
	 * Create the frame.
	 * 
	 * @param tableModel
	 * @param ruta
	 */
	public InterfazAdd(DefaultTableModel tableModel, String ruta) {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);//Seteo bounds para elemento jswing
		contentPane = new JPanel();//Seteo del panel principal
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));//Seteo del panel principal
		setContentPane(contentPane);//Seteo del panel principal
		contentPane.setLayout(null);//Seteo del panel principal

		JLabel lblAgregarLibro = new JLabel("Agregar Libro");//Instancia de JLabel
		lblAgregarLibro.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAgregarLibro.setBounds(162, 3, 103, 32);//Seteo bounds para elemento jswing
		contentPane.add(lblAgregarLibro);//Agrego al panel el elemeto jswing

		JLabel lblIsbn = new JLabel("ISBN");//Instancia de JLabel
		lblIsbn.setBounds(10, 49, 46, 14);//Seteo bounds para elemento jswing
		contentPane.add(lblIsbn);//Agrego al panel el elemeto jswing

		JLabel lblNombre = new JLabel("Titulo");//Instancia de JLabel
		lblNombre.setBounds(10, 77, 46, 14);//Seteo bounds para elemento jswing
		contentPane.add(lblNombre);//Agrego al panel el elemeto jswing

		JLabel lblAutor = new JLabel("Autor");//Instancia de JLabel
		lblAutor.setBounds(10, 102, 46, 14);//Seteo bounds para elemento jswing
		contentPane.add(lblAutor);//Agrego al panel el elemeto jswing

		JLabel lblEditorial = new JLabel("Editorial");//Instancia de JLabel
		lblEditorial.setBounds(10, 129, 46, 14);//Seteo bounds para elemento jswing
		contentPane.add(lblEditorial);//Agrego al panel el elemeto jswing

		JLabel lblEdicion = new JLabel("Edicion");//Instancia de JLabel
		lblEdicion.setBounds(10, 154, 46, 14);//Seteo bounds para elemento jswing
		contentPane.add(lblEdicion);//Agrego al panel el elemeto jswing

		JLabel lblAoPublicacion = new JLabel("A\u00F1o Publicacion");//Instancia de JLabel
		lblAoPublicacion.setBounds(10, 179, 74, 14);//Seteo bounds para elemento jswing
		contentPane.add(lblAoPublicacion);//Agrego al panel el elemeto jswing

		textFieldAddISBN = new JTextField();
		textFieldAddISBN.setBounds(88, 46, 110, 20);//Seteo bounds para elemento jswing
		contentPane.add(textFieldAddISBN);//Agrego al panel el elemeto jswing
		textFieldAddISBN.setColumns(10);
		
		textFieldAddISBN.setInputVerifier(new ISBNVerified());

		textFieldAddTitulo = new JTextField();
		textFieldAddTitulo.setBounds(88, 74, 155, 20);//Seteo bounds para elemento jswing
		contentPane.add(textFieldAddTitulo);//Agrego al panel el elemeto jswing
		textFieldAddTitulo.setColumns(10);
		
		textFieldAddTitulo.setInputVerifier(new TituloVerified());

		textFieldAddAutor = new JTextField();
		textFieldAddAutor.setBounds(88, 99, 155, 20);//Seteo bounds para elemento jswing
		contentPane.add(textFieldAddAutor);//Agrego al panel el elemeto jswing
		textFieldAddAutor.setColumns(10);
		
		textFieldAddAutor.setInputVerifier(new AutorVerified());

		textFieldAddEditorial = new JTextField();
		textFieldAddEditorial.setBounds(88, 126, 116, 20);//Seteo bounds para elemento jswing
		contentPane.add(textFieldAddEditorial);//Agrego al panel el elemeto jswing
		textFieldAddEditorial.setColumns(10);
		
		textFieldAddEditorial.setInputVerifier(new EditorialVerified());

		textFieldAddEdicion = new JTextField();
		textFieldAddEdicion.setBounds(88, 151, 27, 20);//Seteo bounds para elemento jswing
		contentPane.add(textFieldAddEdicion);//Agrego al panel el elemeto jswing
		textFieldAddEdicion.setColumns(10);
		
		PlainDocument docEdicion = (PlainDocument) textFieldAddEdicion.getDocument();
	    docEdicion.setDocumentFilter(new IntFilterYear());
	    

		textFieldAddPublicacion = new JTextField();
		textFieldAddPublicacion.setBounds(88, 176, 46, 20);//Seteo bounds para elemento jswing
		contentPane.add(textFieldAddPublicacion);//Agrego al panel el elemeto jswing
		textFieldAddPublicacion.setColumns(10);
		
		 PlainDocument docPublicacion = (PlainDocument) textFieldAddPublicacion.getDocument();
	     docPublicacion.setDocumentFilter(new IntFilterYear());

		JButton btnAgregar = new JButton("Agregar"); //Creo un boton agregar

		btnAgregar.addMouseListener(new MouseAdapter() { //comienzo del listener del mouse para el boton agregar
			/**
			 * Cuando el boton agregar es presionado
			 */
			@Override
			public void mouseClicked(MouseEvent e) {
				String ISBN = textFieldAddISBN.getText();//Consigo el texto del label y lo guardo
				PlainDocument docAutor = (PlainDocument) textFieldAddAutor.getDocument();
			     docAutor.setDocumentFilter(new IntFilterYear());
				String autor = textFieldAddAutor.getText();//Consigo el texto del label y lo guardo
				String titulo = textFieldAddTitulo.getText();//Consigo el texto del label y lo guardo
				String editorial = textFieldAddEditorial.getText();//Consigo el texto del label y lo guardo
				int edicion = Integer.parseInt(textFieldAddEdicion.getText());//Consigo el texto del label y lo guardo
//				PlainDocument docPublicacion = (PlainDocument) textFieldAddPublicacion.getDocument();
//			    docPublicacion.setDocumentFilter(new IntFilterYear());
				int publicacion = Integer.parseInt(textFieldAddPublicacion.getText());//Consigo el texto del label y lo guardo
				Object[] obj = new Object[] { ISBN, titulo, autor, editorial, edicion, publicacion };
				tableModel.addRow(obj);

				try {//Comienzo try
					salida = new PrintStream(new FileOutputStream(ruta, true)); //declaro el archivo de salida
				} catch (FileNotFoundException e1) {//captura de excepcion
					System.out.println("Ruta incorrecta");//muestro por pantalla
					e1.printStackTrace();
				}
				String libro = ISBN + "\t" + titulo + "\t" + autor + "\t" + editorial + "\t" + edicion + "\t"
						+ publicacion + "\n";
				salida.append(libro); //gravo al final del archivo el nuevo libro
				salida.close(); //cierro archivo
				dispose();//Elimino ventana
			}
		});

		btnAgregar.setBounds(187, 207, 89, 23);//Seteo bounds para elemento jswing
		contentPane.add(btnAgregar);//Agrego al panel el elemeto jswing

		JButton btnCancelar = new JButton("Cancelar"); //creo el boton cancelar
		btnCancelar.addMouseListener(new MouseAdapter() { //listener del mouse del boton cancelar
			/**
			 * Cierra la ventana
			 */
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();//Elimino ventana
			}
		});
		btnCancelar.setBounds(306, 207, 89, 23);//Seteo bounds para elemento jswing
		contentPane.add(btnCancelar);//Agrego al panel el elemeto jswing
	}
}