package ar.edu.unlam.testBiblioteca;

import java.util.Objects;

public class Libro implements Comparable<Libro> {

	private Integer codigo;
	private String nombre;
	private String autor; // puede ser otra clase

	public Libro(Integer codigo, String nombre, String autor) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.autor = autor;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Libro other = (Libro) obj;
		return Objects.equals(codigo, other.codigo);
	}

	@Override
	public String toString() {
		return "Libro [nombre=" + nombre + ", autor=" + autor + "]";
	}

	@Override
	public int compareTo(Libro otroLibro) {
		return this.getCodigo().compareTo(otroLibro.getCodigo());
	}

}
