package gestorLibros;

import autenticacion.Login;
import interfaz.InterfazConsola;
import interfaz.InterfazGrafica;

/**
 * Clase GestorLibros
 * Clase principal
 */
public class GestorLibros {

	private static String ruta = "libros.txt";

	/**
	 * Metodo estatico main
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		/*
		 * Leo los argumentos y decido como interpretarlos
		 * */
		switch (args.length) {
		case 0:
			//InterfazGrafica.startInterface(ruta); //comienzo de la interfaz grafica
			Login.startInterface(ruta); //comienzo de la interfaz del login
			break;//salto
		case 1:
			switch (args[0]) {
			case "-cli": //caso interfaz consola
				InterfazConsola interfaz = new InterfazConsola(ruta); 
				interfaz.startInterface(); //comienzo de interfaz por consola
				break;//salto
			case "-help":	//caso de ayuda
				System.out.println("Nombre programa: GESTOR DE LIBROS\n"
						+ "Descripcion: Herramienta de gestion de libros\n" + "Parametros:"
						+ "\n-gui para interfaz grafica, opcion por defecto si no se ingresan parametros\n"
						+ "-cli para interfaz por linea de comando\n" + "-help para ayuda de uso");
				break;//salto
			case "-gui": //casi interfaz grafica
				InterfazGrafica.startInterface(ruta); //comienzo interfaz grafica
				break;//salto
			default:
				System.out.println(
						"Parametro incorrecto, lea documentacion anexada para instrucciones de uso o ingrese -help como parametro");
				break;//salto
			}
			break;//salto
		default: //caso default
			System.out.println(
					"Incorrecto numero de parametros, lea documentacion anexada para instrucciones de uso o ingrese -help como parametro");
			break;
		}

	}

}