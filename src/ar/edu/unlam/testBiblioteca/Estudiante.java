package ar.edu.unlam.testBiblioteca;

import java.util.ArrayList;
import java.util.List;

public class Estudiante implements Comparable<Estudiante> {

	private final int CANTIDAD_MAXIMA_DE_LIBROS = 2;
	private Integer dni;
	private String nombre;
	private String apellido;
	private List<Libro> libros;

	public Estudiante(Integer dni, String nombre, String apellido) {
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.libros = new ArrayList<>();
	}

	public Integer getDni() {
		return dni;
	}

	public void setDni(Integer dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public boolean adquirirLibro(Libro libroAPrestar) throws NopuedeAdquirirMasLibrosExeption {
		if (cantidadDeLibrosAdquiridos() >= CANTIDAD_MAXIMA_DE_LIBROS) {
			throw new NopuedeAdquirirMasLibrosExeption();
		}
		this.libros.add(libroAPrestar);
		return true;
	}

	public Integer cantidadDeLibrosAdquiridos() {
		return this.libros.size();
	}

	@Override
	public int compareTo(Estudiante otroEstudiante) {
		return this.getDni().compareTo(otroEstudiante.getDni());
	}

	@Override
	public String toString() {
		return "Estudiante [dni=" + dni + ", nombre=" + nombre + ", apellido=" + apellido + "]";
	}

	public List<Libro> getLibros() {
		return libros;
	}

	public void setLibros(List<Libro> libros) {
		this.libros = libros;
	}

}
