package gestorLibros;

public class Libro implements Comparable<Libro> {

	private String ISBN;
	private String titulo;
	private String autor;
	private String editorial;
	private int edicion;
	private int anioPub;

	/**
	 * Metodo setISBN
	 * 
	 * @param isbn
	 */
	public void setISBN(String isbn) {
		this.ISBN = isbn; //seteo isbn
	}

	/**
	 * Metodo setTitulo
	 * 
	 * @param titulo
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo; //seteo titulo
	}

	/**
	 * Metodo setAutor
	 * 
	 * @param autor
	 */
	public void setAutor(String autor) {
		this.autor = autor; //seteo autor

	}

	/**
	 * Metodo setEditorial
	 * 
	 * @param editorial
	 */
	public void setEditorial(String editorial) {
		this.editorial = editorial; //seteo editorial
	}

	/**
	 * Meotdo setEdicion
	 * 
	 * @param edicion
	 */
	public void setEdicion(int edicion) {
		this.edicion = edicion; //seteo edicion
	}

	/**
	 * Metodo setAnno_de_publicacion
	 * 
	 * @param anio
	 */
	public void setAnno_de_publicacion(int anio) {
		this.anioPub = anio; //seteo a¤o de publicacion
	}

	/**
	 * hashcode Genera un hashcode para cada libro.
	 * 
	 * @return hashcode
	 */
	@Override
	public int hashCode() {
		final int prime = 31; //numero primo
		int result = 1; //resultado autogenerado por java
		result = prime * result + ((ISBN == null) ? 0 : ISBN.hashCode()); //calculo de hashcode
		return result;
	}

	/**
	 * Metodo equals
	 * 
	 * @return boolean
	 * @param obj objeto a comparar
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) //evaluo que sean del mismo tipo
			return true;
		if (obj == null) //evaluo que el objeto a comparar no sea null
			return false;
		if (getClass() != obj.getClass()) //evaluo que sean misma clase
			return false;
		Libro other = (Libro) obj; 
		if (ISBN == null) { //evaluo isbn
			if (other.ISBN != null) //pregunto si es null
				return false;
		} else if (!ISBN.equals(other.ISBN)) //comparo finalmente si son iguales
			return false;
		return true;
	}

	/**
	 * Metodo compareTo
	 *
	 * @param Libro libro a comparar por ISBN
	 */
	@Override
	public int compareTo(Libro libro) {
		return this.ISBN.compareTo(libro.ISBN); //comparo por isbn
	}

	/**
	 * Metodo toString Convierte a string tabeado
	 */
	@Override
	public String toString() {
		return ISBN + "\t" + titulo + "\t" + autor + "\t" + editorial + "\t" + edicion + "\t" + anioPub; //muestro libro tabulado
	}

	/**
	 * Metodo getISBN
	 */
	public String getISBN() {
		return ISBN; //isbn
	}

	/**
	 * Metodo getTitulo
	 * 
	 * @return titulo
	 */
	public String getTitulo() {
		return titulo; //titulo
	}

	/**
	 * Metodo getAutor
	 * 
	 * @return autor
	 */
	public String getAutor() {
		return autor; //autor
	}

	/**
	 * Metodo getEditorial
	 * 
	 * @return editorial
	 */
	public String getEditorial() {
		return editorial; //editorial
	}

	/**
	 * Metodo getEdicion
	 * 
	 * @return edicion
	 */
	public int getEdicion() {
		return edicion; //edicion
	}

	/**
	 * Metodo getAnioPub
	 * 
	 * @return a¤o de publicacion
	 */
	public int getAnioPub() {
		return anioPub; //anio de publicacicon
	}

	/**
	 * Medoto toStringLista
	 * 
	 * @return lista de atributos del libro
	 */
	public String toStringLista() {
		return "ISBN: " + ISBN + "\n" + "TITULO: " + titulo + "\n" + "AUTOR: " + autor + "\n" + "EDITORIAL: " //muestro string como lista
				+ editorial + "\n" + "EDICION: " + edicion + "\n" + "ANIO: " + anioPub;
	}

}
