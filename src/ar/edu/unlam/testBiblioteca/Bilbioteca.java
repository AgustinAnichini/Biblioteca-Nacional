package ar.edu.unlam.testBiblioteca;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Bilbioteca {

	private String nombre;
	private Set<Libro> libros;
	private Map<Estudiante, Libro> prestamos;

	public Bilbioteca(String nombre) {
		this.nombre = nombre;
		this.libros = new HashSet<>();
		this.prestamos = new TreeMap<>();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void agregarLibro(Libro nuevoLibro) throws LibroExistenteExeption {
		if (!(existeLibroEnBiblioteca(nuevoLibro))) {
			this.libros.add(nuevoLibro);
		}
	}

	private boolean existeLibroEnBiblioteca(Libro nuevoLibro) throws LibroExistenteExeption {
		if (this.libros.contains(nuevoLibro)) {
			throw new LibroExistenteExeption();
		}
		return false;
	}

	public Integer cantidadDeLibros() {
		return this.libros.size();
	}

	public boolean prestamoDeLibro(Libro LibroAPrestar, Estudiante estudiante)
			throws NopuedeAdquirirMasLibrosExeption, LibroSolicitadoEnPrestamoExeption, LibroInexistenteExeption {

		if (this.libros.contains(LibroAPrestar)) {
			estudiante.adquirirLibro(LibroAPrestar);
			this.libros.remove(LibroAPrestar);
			this.prestamos.put(estudiante, LibroAPrestar);
			return true;
		}

		if (this.prestamos.get(estudiante).equals(LibroAPrestar)) {
			throw new LibroSolicitadoEnPrestamoExeption();
		} else {
			throw new LibroInexistenteExeption();
		}
	}

	public void devolucionDeLibro(Libro libroDevuelto, Estudiante nuevoEstudiante) {
		this.libros.add(libroDevuelto);
		this.prestamos.remove(nuevoEstudiante);// remueve por la KEY
		nuevoEstudiante.getLibros().remove(libroDevuelto);
	}

}
