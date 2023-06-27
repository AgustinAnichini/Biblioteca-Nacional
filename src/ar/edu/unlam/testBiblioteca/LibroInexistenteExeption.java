package ar.edu.unlam.testBiblioteca;

public class LibroInexistenteExeption extends Exception {

	
	public LibroInexistenteExeption() {
		super("Libro Inexistente en Biblioteca");
	}
}
