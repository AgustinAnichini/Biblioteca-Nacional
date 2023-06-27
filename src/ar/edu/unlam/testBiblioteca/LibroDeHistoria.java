package ar.edu.unlam.testBiblioteca;

public class LibroDeHistoria extends Libro implements fotocopiable{

	public LibroDeHistoria(Integer codigo, String nombre, String autor) {
		super(codigo, nombre, autor);
	}

	@Override
	public String puedeFotocopiarse() {
		return "Este Libro se puede fotocopiar";
	}

}
