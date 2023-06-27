package ar.edu.unlam.testBiblioteca;

public class LibroSolicitadoEnPrestamoExeption extends Exception {

	public LibroSolicitadoEnPrestamoExeption() {
		super("El libro se encuentra Actualmente en un prestamo");
	}
}
