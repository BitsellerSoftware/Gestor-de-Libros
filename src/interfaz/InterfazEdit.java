package interfaz;

import javax.swing.JFrame;//Import
import javax.swing.JPanel;//Import
import javax.swing.border.EmptyBorder;//Import
import javax.swing.table.DefaultTableModel;//Import
import javax.swing.JLabel;//Import
import java.awt.Font;//Import
import javax.swing.JTextField;//Import
import javax.swing.WindowConstants;//Import
import javax.swing.JButton;//Import
import java.awt.event.MouseAdapter;//Import
import java.awt.event.MouseEvent;//Import
import java.io.FileNotFoundException;//Import
import java.io.FileOutputStream;//Import
import java.io.PrintStream;//Import
import java.awt.event.ActionListener;//Import
import java.awt.event.ActionEvent;//Import

public class InterfazEdit extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2315155147801732198L;//declaracion de atributos en scope clase
	private JPanel contentPane;//declaracion de atributos en scope clase
	private JTextField textFieldPublicacion;//declaracion de atributos en scope clase
	private JTextField textFieldEdicion;//declaracion de atributos en scope clase
	private JTextField textFieldEditorial;//declaracion de atributos en scope clase
	private JTextField textFieldAutor;//declaracion de atributos en scope clase
	private JTextField textFieldTitulo;//declaracion de atributos en scope clase
	private JTextField textFieldISBN;//declaracion de atributos en scope clase
	private DefaultTableModel tableModel;//declaracion de atributos en scope clase
	private String ruta;//declaracion de atributos en scope clase

	/**
	 * Create the frame.
	 * 
	 * @param tableModel
	 * @param i
	 */
	public InterfazEdit(int i, DefaultTableModel tableModelOri, String ruta) {
		this.ruta = ruta;
		this.tableModel = tableModelOri;
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);//Seteo bounds
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblEditarLibro = new JLabel("Editar Libro");//Instancia de jalabel
		lblEditarLibro.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEditarLibro.setBounds(164, 11, 83, 31);//Seteo bounds
		contentPane.add(lblEditarLibro);//Agrego elemento JSWING al panel

		JLabel labelISBN = new JLabel("ISBN");//Instancia de jalabel
		labelISBN.setBounds(14, 56, 46, 14);//Seteo bounds
		contentPane.add(labelISBN);//Agrego elemento JSWING al panel

		JLabel labelTitulo = new JLabel("Titulo");//Instancia de jalabel
		labelTitulo.setBounds(14, 84, 46, 14);//Seteo bounds
		contentPane.add(labelTitulo);//Agrego elemento JSWING al panel

		JLabel labelAutor = new JLabel("Autor");//Instancia de jalabel
		labelAutor.setBounds(14, 109, 46, 14);//Seteo bounds
		contentPane.add(labelAutor);//Agrego elemento JSWING al panel

		JLabel labelEditorial = new JLabel("Editorial");//Instancia de jalabel
		labelEditorial.setBounds(14, 136, 46, 14);//Seteo bounds
		contentPane.add(labelEditorial);//Agrego elemento JSWING al panel

		JLabel labelEdicion = new JLabel("Edicion");//Instancia de jalabel
		labelEdicion.setBounds(14, 161, 46, 14);//Seteo bounds
		contentPane.add(labelEdicion);//Agrego elemento JSWING al panel

		JLabel labelPublicacion = new JLabel("A\u00F1o Publicacion");//Instancia de jalabel
		labelPublicacion.setBounds(14, 186, 74, 14);//Seteo bounds
		contentPane.add(labelPublicacion);//Agrego elemento JSWING al panel

		textFieldPublicacion = new JTextField();
		textFieldPublicacion.setColumns(10);//seteo columnas de elemento de jswing
		textFieldPublicacion.setBounds(92, 183, 46, 20);//Seteo bounds
		contentPane.add(textFieldPublicacion);//Agrego elemento JSWING al panel
		textFieldPublicacion.setText(tableModel.getValueAt(i, 5).toString());

		textFieldEdicion = new JTextField();
		textFieldEdicion.setColumns(10);//seteo columnas de elemento de jswing
		textFieldEdicion.setBounds(92, 158, 27, 20);//Seteo bounds
		contentPane.add(textFieldEdicion);//Agrego elemento JSWING al panel
		textFieldEdicion.setText(tableModel.getValueAt(i, 4).toString());

		textFieldEditorial = new JTextField();
		textFieldEditorial.setColumns(10);//seteo columnas de elemento de jswing
		textFieldEditorial.setBounds(92, 133, 116, 20);//Seteo bounds
		contentPane.add(textFieldEditorial);//Agrego elemento JSWING al panel
		textFieldEditorial.setText(tableModel.getValueAt(i, 3).toString());

		textFieldAutor = new JTextField();
		textFieldAutor.setColumns(10);//seteo columnas de elemento de jswing
		textFieldAutor.setBounds(92, 106, 155, 20);//Seteo bounds
		contentPane.add(textFieldAutor);//Agrego elemento JSWING al panel
		textFieldAutor.setText(tableModel.getValueAt(i, 2).toString());

		textFieldTitulo = new JTextField();
		textFieldTitulo.setColumns(10);//seteo columnas de elemento de jswing
		textFieldTitulo.setBounds(92, 81, 155, 20);//Seteo bounds
		contentPane.add(textFieldTitulo);//Agrego elemento JSWING al panel
		textFieldTitulo.setText(tableModel.getValueAt(i, 1).toString());

		textFieldISBN = new JTextField();
		textFieldISBN.setColumns(10);//seteo columnas de elemento de jswing
		textFieldISBN.setBounds(92, 53, 110, 20);//Seteo bounds
		contentPane.add(textFieldISBN);//Agrego elemento JSWING al panel
		textFieldISBN.setText(tableModel.getValueAt(i, 0).toString());

		JButton btnAplicar = new JButton("Aplicar");//Creo nuevo boton
		btnAplicar.addMouseListener(new MouseAdapter() {//declaracion de eventos a escuchar de mouse en este jwing
			/**
			 * Aplica la modificacion al libro y cierra la ventana
			 */
			@Override
			public void mouseClicked(MouseEvent e) {
				tableModel.setValueAt(textFieldISBN.getText(), i, 0);//Seteo valor en columna y fila especificada
				tableModel.setValueAt(textFieldTitulo.getText(), i, 1);//Seteo valor en columna y fila especificada
				tableModel.setValueAt(textFieldAutor.getText(), i, 2);//Seteo valor en columna y fila especificada
				tableModel.setValueAt(textFieldEditorial.getText(), i, 3);//Seteo valor en columna y fila especificada
				tableModel.setValueAt(textFieldEdicion.getText(), i, 4);//Seteo valor en columna y fila especificada
				tableModel.setValueAt(textFieldPublicacion.getText(), i, 5);//Seteo valor en columna y fila especificada
				actualizar_archivo();//actualizo el archivo de libros
				dispose();//Elimino ventana
			}
		});
		btnAplicar.setBounds(202, 210, 89, 23);//Seteo bounds
		contentPane.add(btnAplicar);//Agrego elemento JSWING al panel

		JButton btnCancelar = new JButton("Cancelar");//Creo nuevo boton
		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();//Elimino ventana
			}
		});
		btnCancelar.setBounds(307, 210, 89, 23);//Seteo bounds
		contentPane.add(btnCancelar);//Agrego elemento JSWING al panel
	}

	/**
	 * Actualiza el archivo con el tablemodel actual//actualizo el archivo de libros
	 */
	protected void actualizar_archivo() {//actualizo el archivo de libros
		System.out.println("Actualizando archivo...");//actualizo el archivo de libros//Salida por pantalla
		int filas = tableModel.getRowCount();//consigo filas
		int columnas = tableModel.getColumnCount();//consigo columnas
		PrintStream salida = null; //declaro printstream de salida

		try {
			salida = new PrintStream(new FileOutputStream(ruta));
		} catch (FileNotFoundException e) {
			System.out.println("Ruta erronea");//Salida por pantalla
			e.printStackTrace();
		}

		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				salida.print(tableModel.getValueAt(i, j) + "\t");
				// System.out.print(tableModel.getValueAt(i, j)+"\t");//Salida por pantalla
			}
			salida.print("\n");
			// System.out.print("\n");//Salida por pantalla
		}
		salida.close();
		System.out.println("Archivo actualizado!");//actualizo el archivo de libros//Salida por pantalla
	}
}
