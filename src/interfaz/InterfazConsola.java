package interfaz;

import java.io.File;//Import
import java.io.FileNotFoundException;//Import
import java.io.FileReader;//Import
import java.io.PrintStream;//Import
import java.io.UnsupportedEncodingException;//Import
import java.util.Collections;//Import
import java.util.Scanner;//Import
import java.util.Vector;//Import

import gestorLibros.Libro;//Import

public class InterfazConsola {

	private static PrintStream out;//Declaracion variables scope clase
	private static Scanner teclado;//Declaracion variables scope clase
	private String ruta;//Declaracion variables scope clase
	private Libro libro; //Declaracion varialbe scope clase
	private Libro dato; //Declaracion varialbe scope clase
	private Vector<Libro> vectorLibros; //Declaracion varialbe scope clase

	public InterfazConsola(String ruta) {
		this.ruta = ruta; //ruta
		this.vectorLibros = new Vector<Libro>(); //defino variables locales
		this.dato = null;
		this.libro = new Libro();
	}

	public void startInterface() {
		definirStandarsInOut();
		int opcion;//defino variables locales
		cargarLibros(ruta, vectorLibros); //cargo libro

		// Pregunto por la opcion a elegir
		do {
			out.println("MENU\n" + "1.- Altas\n" + "2.- Consultas\n" + "3.- Actualizaciones\n" + "4.- Bajas\n"//print pantalla//muestro por pantalla
					+ "5.- Ordenar registros\n" + "6.- Listar registros\n" + "7.- Salir\n");//print pantalla

			// valido las opciones
			opcion = validarOpciones(1,7);

			out.println(); //linea en blanco//muestro por pantalla

			// no hay libros
			verificarExistenciaLibro(vectorLibros, opcion);

			// pregunto por el isbn y lo muestro
			if (opcion < 5) {
				libro = verificarISBNLibro(libro, vectorLibros, dato);
			}

			if (opcion == 1 && dato != null)
				out.println("El registro ya existe."); //ya existe el registro//muestro por pantalla
			else if (opcion >= 2 && opcion <= 4 && dato == null) //opcion entre 2 y 4
				out.println("\nRegistro no encontrado."); //registro no encontrado, sad face//muestro por pantalla
			else
				realizarOperaciones(opcion);
			if (opcion < 7 && opcion >= 1)
				pausar("");//pausa y print en pantalla
		} while (opcion != 7);
		PrintStream salida = null;
		try {
			salida = new PrintStream(ruta);
		} catch (FileNotFoundException e) {
			System.out.println("Ruta de salida incorrecta");//muestro por pantalla
			e.printStackTrace();
		}
		generarOutputLibros(vectorLibros, salida);
		salida.close();
	}

	/**
	 * Realizar las operaciones relacionadas a los libros
	 * 
	 * @param opcion
	 */
	private void realizarOperaciones(int opcion) {
		int subopcion = 0;
		
		switch (opcion) {
		case 1://case del switch
			libro = altaLibro(dato);
			vectorLibros.add(libro);//agrego libro al vector
			libro = new Libro();//nuevo libro
			break;
		case 3://case del switch
			out.println("Men£ de modificaci¢n de campos\n" + "1.- titulo\n" + "2.- autor\n" + "3.- editorial\n"//muestro por pantalla
					+ "4.- edicion\n" + "5.- anno de publicacion\n");
			subopcion = validarOpciones(1, 5);
			dato = modificacionLibro(libro, subopcion);
			break;
		case 4://case del switch
			vectorLibros = bajaLibro(vectorLibros, dato);
			break;
		case 5://case del switch
			vectorLibros = ordenarLibros(vectorLibros);
			break;
		case 6://case del switch
			mostrarLibros(vectorLibros);
			break;
		}
	}

	/**
	 *Definir el encoding del programa.
	 *
	 */
	private void definirStandarsInOut() {
		// Defino la salida
		if (!System.getProperties().get("os.name").equals("Linux") && System.console() != null)
			try {
				out = new PrintStream(System.out, true, "Cp850"); // defino el tipo de encoding
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
		
		teclado = new Scanner(System.in, "Cp850");

	}

	/**
	 * Verifica que exista el libro en la lista.
	 * 
	 * @param vectorLibros
	 * @param opcion
	 */
	private void verificarExistenciaLibro(Vector<Libro> vectorLibros, int opcion) {
		
		if (vectorLibros.isEmpty() && opcion != 1 && opcion != 7) { //valido parametro ingresado
			pausar("No hay registros.\n"); //sad print//pausa y print en pantalla
		}
	}

	/**
	 * Verificar el c¢digo de un libro para ver que exista.
	 * 
	 * @param libro
	 * @return
	 */
	private Libro verificarISBNLibro(Libro libro, Vector<Libro> vectorLibros, Libro dato) {
		
		int[] contador = { 0 };
		int i = vectorLibros.indexOf(libro); //consigo el indice del libro con el isbn ingresado
		libro.setISBN(leer_cadena("Ingrese el ISBN del libro\n")); //leo cadena ingresada despues de mostrar texto// seteo campo de libro
		dato = i < 0 ? null : vectorLibros.get(i); //si i=0 no hay libro, sino lo busco
		if (dato != null) {
			out.println(); //imprimo linea vacia//muestro por pantalla
			imprimir(dato, contador);//muestro el libro
		}
		
		return libro;
	}

	/**
	 * Imprime todos los libros en el archivo de salida. 
	 * 
	 * @param vectorLibros
	 * @param salida
	 */
	private void generarOutputLibros(Vector<Libro> vectorLibros, PrintStream salida) {
		int n = vectorLibros.size();
		for (int i = 0; i < n; i++)
			imprimirEnArchivo(vectorLibros.get(i), salida);
	}

	/**
	 * Muestra todos los libros existentes.
	 * 
	 * @param vectorLibros
	 */
	private void mostrarLibros(Vector<Libro> vectorLibros) {
		int n = vectorLibros.size();
		int[] contador = { 0 };
		contador[0] = 0;
		for (int i = 0; i < n; i++)
			imprimir(vectorLibros.get(i), contador);
		out.println("Total de registros: " + contador[0] + ".");//muestro por pantalla
	}

	/**
	 * Ordena los libros existentes alfabeticamente.
	 * 
	 * @param vectorLibros
	 * @return
	 */
	private Vector<Libro> ordenarLibros(Vector<Libro> vectorLibros) {
		
		Collections.sort(vectorLibros);//Ordenamiento A-Z del vector libro
		out.println("Registros ordenados correctamente.");//muestro por pantalla
		
		return vectorLibros;
	}

	/**
	 * Da de baja un libro existente.
	 * 
	 * @param vectorLibros
	 * @return
	 */
	private Vector<Libro> bajaLibro(Vector<Libro> vectorLibros, Libro dato) {
		vectorLibros.remove(dato);
		out.println("Registro borrado correctamente.");//muestro por pantalla
		
		return vectorLibros;
	}

	/**
	 * Modifica un campo de un libro existente.
	 * 
	 * @param dato
	 * @param subopcion
	 * @return
	 */
	private Libro modificacionLibro(Libro libro, int subopcion) {
		switch (subopcion) {
		case 1://case del switch
			libro.setTitulo(leer_cadena("Ingrese el nuevo titulo"));// seteo campo de libro
			break;
		case 2://case del switch
			libro.setAutor(leer_cadena("Ingrese el nuevo autor"));// seteo campo de libro
			break;
		case 3://case del switch
			libro.setEditorial(leer_cadena("Ingrese el nuevo editorial"));// seteo campo de libro
			break;
		case 4://case del switch
			libro.setEdicion(leer_entero("Ingrese el nuevo edicion"));// seteo campo de libro
			break;
		case 5://case del switch
			libro.setAnno_de_publicacion(leer_entero("Ingrese el nuevo anno de publicacion"));// seteo campo de libro
			break;
		}
		out.println("\nRegistro actualizado correctamente.");//muestro por pantalla
		return libro;
	}

	/**
	 * Da de alta un nuevo libro.
	 * 
	 * @param libro
	 * @param vectorLibros
	 * @return el libro dado de alta.
	 */
	private Libro altaLibro(Libro libro) {
		libro.setTitulo(leer_cadena("Ingrese el titulo"));//Seteo datos del libro ingresados por teclado// seteo campo de libro
		libro.setAutor(leer_cadena("Ingrese el autor"));//Seteo datos del libro ingresados por teclado// seteo campo de libro
		libro.setEditorial(leer_cadena("Ingrese el editorial"));//Seteo datos del libro ingresados por teclado// seteo campo de libro
		libro.setEdicion(leer_entero("Ingrese el edicion"));//Seteo datos del libro ingresados por teclado// seteo campo de libro
		libro.setAnno_de_publicacion(leer_entero("Ingrese el anno de publicacion"));//Seteo datos del libro ingresados por teclado// seteo campo de libro
		out.println("\nRegistro agregado correctamente."); //muestro por pantalla
		
		return libro;
	}

	/**
	 * Valida opciones en base a limites indicados por par metro
	 * 
	 * @param minimo
	 * @param maximo
	 * 
	 * @return opcion elegida.
	 */
	private int validarOpciones(int min, int max) {
		int opcion;
		do {
			opcion = leer_entero("Seleccione una opci¢n"); //muestro y leo entero
			if (opcion < min || opcion > max)
				out.println("Opci¢n no v lida."); //muestro opcion no valida//muestro por pantalla
		} while (opcion < min || opcion > max);
		
		return opcion;
	}

	/**
	 * Muestra el libro por pantalla
	 * 
	 * @param dato
	 * @param contador
	 */
	private static void imprimir(Libro dato, int[] contador) {
		out.println(dato.toString());//muestro por pantalla
		contador[0]++; //contador incrementado
	}

	/**
	 * 
	 * @param libro
	 * @param salida
	 */
	private static void imprimirEnArchivo(Libro libro, PrintStream salida) {
		salida.println(libro.toString());//imprimo en salida una linea
	}

	/**
	 * Leo entrada por teclado
	 * 
	 * @param string
	 * @return
	 */
	private static String leer_cadena(String string) {
		out.println(string);//muestro por pantalla
		return teclado.nextLine();//retorno
	}

	/**
	 * Escribo en la consola una cadena
	 * 
	 * @param string
	 */
	private static void pausar(String string) {//pausa y print en pantalla
		out.println(string);//muestro por pantalla
		// teclado.nextLine();
	}

	/**
	 * Lee un numero entero ingresado por consola
	 * 
	 * @param string
	 * @return
	 */
	private static int leer_entero(String string) {
		out.println(string);//muestro por pantalla
		return Integer.parseInt(teclado.nextLine());//retorno
	}

	/**
	 * Carga libros del path en el vector pasado
	 * 
	 * @param path
	 * @param vectorLibros
	 * @return
	 */
	private static int cargarLibros(String path, Vector<Libro> vectorLibros) {
		// Trato de cargar el archivo que tiene los libros
		Libro libro;
		String[] campos;
		Scanner entrada = null;
		try {
			entrada = new Scanner(new FileReader(path));
		} catch (FileNotFoundException e) {
			out.println("Ruta de libros incorrecta en " + new File("").getAbsolutePath());//muestro por pantalla
			e.printStackTrace();
		}

		// Cargo todos los libros de la entrada en el Vector vector.
		while (entrada.hasNextLine()) {
			campos = entrada.nextLine().split("\t");
			libro = new Libro();
			libro.setISBN(campos[0]);// seteo campo de libro
			libro.setTitulo(campos[1]);// seteo campo de libro
			libro.setAutor(campos[2]);// seteo campo de libro
			libro.setEditorial(campos[3]);// seteo campo de libro
			libro.setEdicion(Integer.parseInt(campos[4]));// seteo campo de libro
			libro.setAnno_de_publicacion(Integer.parseInt(campos[5]));// seteo campo de libro
			vectorLibros.add(libro);
		}
		entrada.close();
		return 1;//retorno

	}
}
