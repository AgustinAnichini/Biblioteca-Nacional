package ar.edu.unlam.testBiblioteca;

public class LibroExistenteExeption extends Exception {

	public LibroExistenteExeption() {
		super("El libro ya existe en la Biblioteca");
	}

}
