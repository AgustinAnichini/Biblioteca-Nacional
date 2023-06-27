package ar.edu.unlam.testBiblioteca;

public class LibroDeGeografia extends Libro implements fotocopiable {

	public LibroDeGeografia(Integer codigo, String nombre, String autor) {
		super(codigo, nombre, autor);
	}

	@Override
	public String puedeFotocopiarse() {
		return "Este Libro se puede fotocopiar";
	}
}
