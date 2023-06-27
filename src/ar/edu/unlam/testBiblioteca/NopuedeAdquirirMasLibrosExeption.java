package ar.edu.unlam.testBiblioteca;

public class NopuedeAdquirirMasLibrosExeption extends Exception {
	
	public NopuedeAdquirirMasLibrosExeption() {
		super("Llegaste a la cantidad maxima de libros adquiridos");
	}
}
