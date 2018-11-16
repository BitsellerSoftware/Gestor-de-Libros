package interfaz;

import java.awt.EventQueue;//Imports
import javax.swing.JFrame;//Imports
import javax.swing.JLabel;//Imports
import java.awt.Font;//Imports
import javax.swing.JTable;//Imports
import gestorLibros.Libro;//Imports
import javax.swing.ImageIcon;//Imports
import javax.swing.table.DefaultTableModel;//Imports
import javax.swing.table.TableModel;//Imports
import javax.swing.table.TableRowSorter;//Imports
import javax.swing.JTextField;//Imports
import javax.swing.RowFilter;//Imports
import javax.swing.RowSorter;//Imports
import javax.swing.SortOrder;//Imports
import javax.swing.WindowConstants;//Imports
import java.awt.Image;//Imports
import java.io.File;//Imports
import java.io.FileNotFoundException;//Imports
import java.io.FileOutputStream;//Imports
import java.io.FileReader;//Imports
import java.io.IOException;//Imports
import java.io.PrintStream;//Imports
import java.util.ArrayList;//Imports
import java.util.List;//Imports
import java.util.Scanner;//Imports
import java.util.Vector;//Imports
import java.awt.event.MouseAdapter;//Imports
import java.awt.event.MouseEvent;//Imports//cuando sale el mouse
import java.awt.event.KeyAdapter;//Imports
import java.awt.event.KeyEvent;//Imports
import javax.swing.JScrollPane;//Imports

public class InterfazGrafica {

	private JFrame frame;//declaracion de atributos en scope clase
	private JTextField textFieldISBN;//declaracion de atributos en scope clase
	private JTable table;//declaracion de atributos en scope clase
	private String ruta;//declaracion de atributos en scope clase
	private TableRowSorter<TableModel> rowSorter;//declaracion de atributos en scope clase
	private DefaultTableModel tableModel;//declaracion de atributos en scope clase
	
	
	/**
	 * Launch the application.
	 * 
	 * @param ruta
	 */
	public static void startInterface(String ruta) { //comienzo interfaz
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() { //corro
				try { //comienzo try
					InterfazGrafica window = new InterfazGrafica(ruta); //inicio nueva interfaz grafica//Instancio una de las interfazes creadas
					window.frame.setVisible(true); //la hago visible
				} catch (Exception e) {//atrapo excepcion
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public InterfazGrafica(String ruta) {
		this.ruta = ruta; //inicializo ruta
		initialize(); //inicializo la interfaz grafica
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame(); //creo nuevo grame
		frame.setBounds(100, 100, 608, 415);//seteo bounds para elemento de jswing
		frame.setResizable(false);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //operacion default de cierre
		frame.getContentPane().setLayout(null); //ni idea
		

		JLabel lblGestorDeLibros = new JLabel("Gestor de Libros");//Nuevo jlabel
		lblGestorDeLibros.setBounds(224, 0, 157, 46);//seteo bounds para elemento de jswing
		lblGestorDeLibros.setFont(new Font("Tahoma", Font.BOLD, 16));//seteo la fuente
		frame.getContentPane().add(lblGestorDeLibros);//agrego elementos jswing al panel

		textFieldISBN = new JTextField();
		textFieldISBN.addKeyListener(new KeyAdapter() {
			/**
			 * Filtra la tabla al apretar enter
			 */
			@Override
			public void keyReleased(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					filter_table();
				}
			}
		});
		textFieldISBN.setBounds(67, 62, 163, 20);//seteo bounds para elemento de jswing
		frame.getContentPane().add(textFieldISBN);//agrego elementos jswing al panel
		textFieldISBN.setColumns(10);

		JLabel lblIsbn = new JLabel("ISBN:");//Nuevo jlabel
		lblIsbn.setFont(new Font("Tahoma", Font.PLAIN, 15));//seteo la fuente
		lblIsbn.setBounds(19, 63, 38, 14);//seteo bounds para elemento de jswing
		frame.getContentPane().add(lblIsbn);//agrego elementos jswing al panel

		cargarTabla();

		JLabel JLabelAdd = new JLabel();//Nuevo jlabel
		JLabelAdd.addMouseListener(new MouseAdapter() {//Agrego listener del mouse para elemento de jswing
			@Override
			public void mouseClicked(MouseEvent e) {//Cuando hacen click//cuando sale el mouse
				try {//comienzo try
					InterfazAdd frame = new InterfazAdd(tableModel, ruta);//Instancio una de las interfazes creadas
					frame.setVisible(true);
				} catch (Exception e2) {//atrapo excepcion
					e2.printStackTrace();
				}
			}
		});
		Image iconAdd = new ImageIcon("Imagenes/add.png").getImage().getScaledInstance(30, 30,//Nuevo icono de iumagen//Hago un resize de la imagen antes de agregarla
				java.awt.Image.SCALE_SMOOTH);
		JLabelAdd.setIcon(new ImageIcon(iconAdd));//seteo el icono segun imagen pasada//Nuevo icono de iumagen
		JLabelAdd.setBounds(533, 111, 30, 30);//seteo bounds para elemento de jswing
		frame.getContentPane().add(JLabelAdd);//agrego elementos jswing al panel
		prettify_hover_icon(JLabelAdd, "add");//Agrego efecto de volverse gris al pasar por arriba

		JLabel JLabelDel = new JLabel();//Nuevo jlabel
		JLabelDel.addMouseListener(new MouseAdapter() {//Agrego listener del mouse para elemento de jswing
			@Override
			public void mouseClicked(MouseEvent arg0) {//Cuando hacen click//cuando sale el mouse
				int[] rows = table.getSelectedRows(); //consigo todas las lineas seleecionadas
				for (int i = 0; i < rows.length; i++) //por cada seleccionada
					tableModel.removeRow(rows[i] - i); //elimino linea de la tabla
				actualizar_archivo(); //actualizo archivo segun model
			}
		});

		Image iconDel = new ImageIcon("Imagenes/del.png").getImage().getScaledInstance(30, 30,//Nuevo icono de iumagen//Hago un resize de la imagen antes de agregarla
				java.awt.Image.SCALE_SMOOTH);
		JLabelDel.setIcon(new ImageIcon(iconDel));//seteo el icono segun imagen pasada//Nuevo icono de iumagen
		JLabelDel.setBounds(533, 166, 30, 30);//seteo bounds para elemento de jswing
		frame.getContentPane().add(JLabelDel);//agrego elementos jswing al panel
		prettify_hover_icon(JLabelDel, "del");//Agrego efecto de volverse gris al pasar por arriba

		JLabel JLabelEdit = new JLabel();//Nuevo jlabel
		JLabelEdit.addMouseListener(new MouseAdapter() {//Agrego listener del mouse para elemento de jswing
			@Override
			public void mouseClicked(MouseEvent e) {//Cuando hacen click//cuando sale el mouse
				int[] rows = table.getSelectedRows();
				for (int i = 0; i < rows.length; i++)
					actualizarModel(rows[i], tableModel);
			}
		});
		Image iconEdit = new ImageIcon("Imagenes/edit.png").getImage().getScaledInstance(30, 30,//Nuevo icono de iumagen//Hago un resize de la imagen antes de agregarla
				java.awt.Image.SCALE_SMOOTH);
		JLabelEdit.setIcon(new ImageIcon(iconEdit));//seteo el icono segun imagen pasada//Nuevo icono de iumagen
		JLabelEdit.setBounds(531, 223, 30, 30);//seteo bounds para elemento de jswing
		frame.getContentPane().add(JLabelEdit);//agrego elementos jswing al panel
		prettify_hover_icon(JLabelEdit, "edit");//Agrego efecto de volverse gris al pasar por arriba

		JLabel JLabelSort = new JLabel();//Nuevo jlabel
		JLabelSort.addMouseListener(new MouseAdapter() {//Agrego listener del mouse para elemento de jswing
			@Override
			public void mouseClicked(MouseEvent e) {//Cuando hacen click//cuando sale el mouse
				TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tableModel);
				table.setRowSorter(sorter);
				List<RowSorter.SortKey> sortKeys = new ArrayList<RowSorter.SortKey>();
				sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
				sorter.setSortKeys(sortKeys);
			}
		});
		Image iconSort = new ImageIcon("Imagenes/sort.png").getImage().getScaledInstance(30, 30,//Nuevo icono de iumagen//Hago un resize de la imagen antes de agregarla
				java.awt.Image.SCALE_SMOOTH);
		JLabelSort.setIcon(new ImageIcon(iconSort));//seteo el icono segun imagen pasada//Nuevo icono de iumagen
		JLabelSort.setBounds(533, 286, 30, 30);//seteo bounds para elemento de jswing
		frame.getContentPane().add(JLabelSort);//agrego elementos jswing al panel
		prettify_hover_icon(JLabelSort, "sort");//Agrego efecto de volverse gris al pasar por arriba

		JLabel JLabelSearch = new JLabel();//Nuevo jlabel
		JLabelSearch.addMouseListener(new MouseAdapter() {//Agrego listener del mouse para elemento de jswing
			@Override
			public void mouseClicked(MouseEvent e) {//Cuando hacen click//cuando sale el mouse
				cargarTabla();
				filter_table();
			}
		});
		Image iconSearch = new ImageIcon("Imagenes/search.png").getImage().getScaledInstance(30, 30,//Nuevo icono de iumagen//Hago un resize de la imagen antes de agregarla
				java.awt.Image.SCALE_SMOOTH);
		JLabelSearch.setIcon(new ImageIcon(iconSearch));//seteo el icono segun imagen pasada//Nuevo icono de iumagen
		JLabelSearch.setBounds(240, 52, 30, 30);//seteo bounds para elemento de jswing
		frame.getContentPane().add(JLabelSearch);//agrego elementos jswing al panel
		prettify_hover_icon(JLabelSearch, "search");//Agrego efecto de volverse gris al pasar por arriba

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(19, 93, 485, 251);//seteo bounds para elemento de jswing
		frame.getContentPane().add(scrollPane);//agrego elementos jswing al panel
		table = new JTable(tableModel);//nueva jtable en el panel creada
		scrollPane.setViewportView(table);
	}

	/**
	 * Imprime el model por consola
	 */
	protected void printModel() {
		int filas = tableModel.getRowCount();//cantidad de filas
		int columnas = tableModel.getColumnCount();//cantidad de columnas
		for (int i = 1; i < filas; i++) { //recorro filas
			for (int j = 0; j < columnas; j++) { //recorro columnas
				System.out.print(tableModel.getValueAt(i, j) + "\t"); //imprimo el valor//salida por consola
			}
			System.out.print("\n"); //imrpimo salto de linea//salida por consola
		}

	}

	/**
	 * Muestra la interfaz de edicion
	 * 
	 * @param i
	 * @param tableModel2
	 */
	protected void actualizarModel(int i, DefaultTableModel tableModel2) {
		InterfazEdit frame = new InterfazEdit(i, tableModel, ruta);//Instancio una de las interfazes creadas
		frame.setVisible(true);//hago visible el frame
	}

	/**
	 * Actualiza el archivo con el table model actual
	 */
	protected void actualizar_archivo() {//actualizo archivo segun model
		System.out.println("Actualizando archivo...");//salida por consola
		int filas = tableModel.getRowCount();//cantidad de filas
		int columnas = tableModel.getColumnCount();//cantidad de columnas
		PrintStream salida = null;//declaro el stream de salida

		try {//comienzo try
			salida = new PrintStream(new FileOutputStream(ruta));
		} catch (FileNotFoundException e) {//atrapo excepcion
			System.out.println("Ruta erronea"); //ruta erronea//salida por consola
			e.printStackTrace();
		}

		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				salida.print(tableModel.getValueAt(i, j) + "\t");
				// System.out.print(tableModel.getValueAt(i, j)+"\t");//salida por consola
			}
			salida.print("\n");
			// System.out.print("\n");//salida por consola
		}
		salida.close();
		System.out.println("Archivo actualizado!");//salida por consola
	}

	/**
	 * filtra la tabla por ISBN de A-Z
	 */
	protected void filter_table() {
		rowSorter = new TableRowSorter<TableModel>(tableModel); //creo un filtro para la tabla, un sorter
		table.setRowSorter(rowSorter); //seteo el sorter a esa tabla
		String text = textFieldISBN.getText(); //levanto el isbn
		if (text.trim().length() == 0) { //si es nulo
			rowSorter.setRowFilter(null); //nada
		} else {
			rowSorter.setRowFilter(RowFilter.regexFilter("(?i)^" + text + "$", 0)); //muestro los que coincidan el regex
		}
	}

	/**
	 * Cuando se pasa el mouse por arriba de un icono, se torna grisaceo
	 * 
	 * @param label
	 * @param type
	 */
	private void prettify_hover_icon(JLabel label, String type) {//Agrego efecto de volverse gris al pasar por arriba
		label.addMouseListener(new MouseAdapter() {//Agrego listener del mouse para elemento de jswing
			ImageIcon icon = new ImageIcon("Imagenes/" + type + ".png");//Nuevo icono de iumagen
			ImageIcon iconLight = new ImageIcon("Imagenes/" + type + "Light.png");//Nuevo icono de iumagen

			@Override
			public void mouseEntered(MouseEvent arg0) {//cuando entra el mouse//cuando sale el mouse
				Image imgLight = iconLight.getImage().getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);//Hago un resize de la imagen antes de agregarla
				label.setIcon(new ImageIcon(imgLight));//seteo el icono segun imagen pasada//Nuevo icono de iumagen
			}

			@Override
			public void mouseExited(MouseEvent e) {//cuando sale el mouse
				Image img = icon.getImage().getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);//Hago un resize de la imagen antes de agregarla
				label.setIcon(new ImageIcon(img));//seteo el icono segun imagen pasada//Nuevo icono de iumagen
			}
		});

	}

	/**
	 * Cargo tabla mostrada en la interfaz
	 */
	private void cargarTabla() {
		String columnas[] = { "ISBN", "Titulo", "Autor", "Editorial", "Edicion", "A�o de publicacion" }; //declaracion columnas
		tableModel = new DefaultTableModel(columnas, 0) {
			/**
			 * 
			 */
			    @Override
			    public boolean isCellEditable (int row, int column) {
			       //all cells false
			       return false;
			    }
		};
		Object[] objs;
		// tableModel.addRow(columnas);//Agrego a la tabla
		Vector<Libro> vectorLibros = new Vector<Libro>();
		cargarLibros(ruta, vectorLibros);
		System.out.println(ruta);//salida por consola

		for (Libro libro : vectorLibros) {
			objs = new Object[] { libro.getISBN(), libro.getTitulo(), libro.getAutor(), libro.getEditorial(),//Nuevo objeto libro para grabar en vector
					libro.getEdicion(), libro.getAnioPub() };
			tableModel.addRow(objs);//Agrego a la tabla
		}
	}

	/**
	 * Cargo los libros en el archivo en el vector de libros
	 * 
	 * @param path
	 * @param vectorLibros
	 * @return
	 */
	private static int cargarLibros(String path, Vector<Libro> vectorLibros) {
		// Trato de cargar el archivo que tiene los libros
		Libro libro;//declaracion de variable
		String[] campos;//declaracion de variable
		Scanner entrada = null;//declaracion de variable
		try {//comienzo try
			entrada = new Scanner(new FileReader(path));
		} catch (FileNotFoundException e) {//atrapo excepcion
			File f = new File("libros.txt");
			try {//comienzo try
				f.createNewFile();
				entrada = new Scanner(new FileReader(path)); //nuevo scanner
			} catch (IOException e1) {//atrapo excepcion
				System.out.println("No existe ni se puede crear el archivo libros");//salida por consola
				e1.printStackTrace();
			}
		}

		// Cargo todos los libros de la entrada en el Vector vector.
		while (entrada.hasNextLine()) {//mientras haya algo en el archivo de entrada
			campos = entrada.nextLine().split("\t"); //parseo
			libro = new Libro();//seteo variables del libro
			libro.setISBN(campos[0]);//seteo variables del libro
			libro.setTitulo(campos[1]);//seteo variables del libro
			libro.setAutor(campos[2]);//seteo variables del libro
			libro.setEditorial(campos[3]);//seteo variables del libro
			libro.setEdicion(Integer.parseInt(campos[4]));//seteo variables del libro
			libro.setAnno_de_publicacion(Integer.parseInt(campos[5]));//seteo variables del libro
			vectorLibros.add(libro);//agrego libro al vector
		}
		entrada.close();//cierro el archivo de entrada
		return 1;//FIN
	}//FIN CARGAR LIBROS
}//FIN CLASE
