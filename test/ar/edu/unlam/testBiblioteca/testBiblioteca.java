package ar.edu.unlam.testBiblioteca;

import static org.junit.Assert.*;

import org.junit.Test;

public class testBiblioteca {

	private Bilbioteca crearBiblioteca(String nombre) {
		return new Bilbioteca(nombre);
	}

	private Libro crearLibroDeMatematica(Integer codigo, String nombre, String autor) {
		return new LibroDeMatematica(codigo, nombre, autor);
	}

	private Libro crearLibroDeHistoria(Integer codigo, String nombre, String autor) {
		return new LibroDeHistoria(codigo, nombre, autor);
	}

	private Libro crearLibroDeGeografia(Integer codigo, String nombre, String autor) {
		return new LibroDeGeografia(codigo, nombre, autor);
	}

	private Estudiante crearEstudiante(Integer dni, String nombre, String apellido) {
		return new Estudiante(dni, nombre, apellido);
	}

	@Test
	public void queSePuedaCrearUnaBiblioteca() {
		String nombre = "Biblioteca Nacional";

		Bilbioteca nuevaBiblioteca = crearBiblioteca(nombre);

		assertNotNull(nuevaBiblioteca);
	}

	@Test
	public void queSePuedaCrearUnLibro() {
		String nombre = "Matematica Aplicada";
		String autor = "Jose Hernandez";
		Integer codigo = 1;

		Libro nuevoLibro = crearLibroDeMatematica(codigo, nombre, autor);

		assertNotNull(nuevoLibro);
	}

	@Test
	public void queSePuedaCrearUnEstudiante() {
		String nombre = "Agustin";
		String apellido = "Hernandez";
		Integer dni = 43598592;

		Estudiante nuevoEstudiante = crearEstudiante(dni, nombre, apellido);

		assertNotNull(nuevoEstudiante);
	}

	@Test
	public void queSePuedaAgregarLibrosALaBiblioteca() throws LibroExistenteExeption {
		String nombre = "Biblioteca Nacional";
		Bilbioteca nuevaBiblioteca = crearBiblioteca(nombre);

		String nombreLibro = "Matematica Aplicada";
		String autor = "Jose Hernandez";
		Integer codigo = 1;
		Libro nuevoLibro = crearLibroDeMatematica(codigo, nombreLibro, autor);

		nuevaBiblioteca.agregarLibro(nuevoLibro);

		assertEquals((Integer) 1, nuevaBiblioteca.cantidadDeLibros());

	}

	@Test(expected = LibroExistenteExeption.class)
	public void queSeNoPuedaAgregarLibrosDeIgualCodigoALaBiblioteca() throws LibroExistenteExeption {
		String nombre = "Biblioteca Nacional";
		Bilbioteca nuevaBiblioteca = crearBiblioteca(nombre);

		String nombreLibro = "Matematica Aplicada";
		String autor = "Jose Hernandez";
		Integer codigo = 1;
		Libro nuevoLibro = crearLibroDeMatematica(codigo, nombreLibro, autor);

		String nombreDuplicado = "Matematica Aplicada";
		String autorDuplicado = "Jose Hernandez";
		Integer codigoDuplicado = 1; ///// MISMO CODIGO
		Libro nuevoDuplicado = crearLibroDeMatematica(codigo, nombreLibro, autor);

		nuevaBiblioteca.agregarLibro(nuevoLibro);
		nuevaBiblioteca.agregarLibro(nuevoDuplicado);
	}

	@Test
	public void queUnEstudiantePuedaHacerUnPrestemoEnLaBibliotecaDeMaximoDosLibros() throws LibroExistenteExeption,
			NopuedeAdquirirMasLibrosExeption, LibroSolicitadoEnPrestamoExeption, LibroInexistenteExeption {
		String nombre = "Biblioteca Nacional";
		Bilbioteca nuevaBiblioteca = crearBiblioteca(nombre);

		String nombreLibro = "Matematica Aplicada";
		String autor = "Jose Hernandez";
		Integer codigo = 1;
		Libro nuevoLibro = crearLibroDeMatematica(codigo, nombreLibro, autor);

		String nombrePrestado = "Matematica Aplicada";
		String autorPrestado = "Jose Hernandez";
		Integer codigoPrestado = 2;
		Libro nuevoPrestado = crearLibroDeMatematica(codigoPrestado, nombrePrestado, nombrePrestado);

		String nombreEstudiante = "Agustin";
		String apellido = "Hernandez";
		Integer dni = 43598592;
		Estudiante nuevoEstudiante = crearEstudiante(dni, nombre, apellido);

		nuevaBiblioteca.agregarLibro(nuevoLibro);
		nuevaBiblioteca.agregarLibro(nuevoPrestado);

		nuevaBiblioteca.prestamoDeLibro(nuevoLibro, nuevoEstudiante);
		nuevaBiblioteca.prestamoDeLibro(nuevoPrestado, nuevoEstudiante);

		assertEquals((Integer) 2, nuevoEstudiante.cantidadDeLibrosAdquiridos());
	}

	@Test(expected = LibroSolicitadoEnPrestamoExeption.class)
	public void queNoSePuedePrestarUnLibroQueSeEncuentraEnPrestamos() throws LibroExistenteExeption,
			NopuedeAdquirirMasLibrosExeption, LibroSolicitadoEnPrestamoExeption, LibroInexistenteExeption {

		String nombre = "Biblioteca Nacional";
		Bilbioteca nuevaBiblioteca = crearBiblioteca(nombre);

		String nombreLibro = "Matematica Aplicada";
		String autor = "Jose Hernandez";
		Integer codigo = 1;
		Libro nuevoLibro = crearLibroDeMatematica(codigo, nombreLibro, autor);

		String nombreEstudiante = "Agustin";
		String apellido = "Hernandez";
		Integer dni = 43598592;
		Estudiante nuevoEstudiante = crearEstudiante(dni, nombre, apellido);

		String nombrePrestamo = "Ariel";
		String apellidoPrestamo = "Hernandez";
		Integer dniPrestamo = 43598592;
		Estudiante EstudiantePrestamo = crearEstudiante(dniPrestamo, nombrePrestamo, apellidoPrestamo);

		nuevaBiblioteca.agregarLibro(nuevoLibro);

		nuevaBiblioteca.prestamoDeLibro(nuevoLibro, nuevoEstudiante);// lo prestamos a Agustin
		nuevaBiblioteca.prestamoDeLibro(nuevoLibro, EstudiantePrestamo);// no podemos a Ariel

	}

	@Test(expected = NopuedeAdquirirMasLibrosExeption.class)
	public void queUnEstudianteNoPuedaHacerUnPrestemoEnLaBibliotecaYaQueTieneDosPrestamos()
			throws LibroExistenteExeption, NopuedeAdquirirMasLibrosExeption, LibroSolicitadoEnPrestamoExeption,
			LibroInexistenteExeption {

		String nombre = "Biblioteca Nacional";
		Bilbioteca nuevaBiblioteca = crearBiblioteca(nombre);

		String nombreLibro = "Matematica Aplicada";
		String autor = "Jose Hernandez";
		Integer codigo = 1;
		Libro nuevoLibro = crearLibroDeMatematica(codigo, nombreLibro, autor);

		String nombrePrestado = "Matematica Aplicada";
		String autorPrestado = "Jose Hernandez";
		Integer codigoPrestado = 2;
		Libro nuevoPrestado = crearLibroDeMatematica(codigoPrestado, nombrePrestado, autorPrestado);

		String nombreIntento = "Matematica Avanzada";
		String autorIntento = "Jose Hernandez";
		Integer codigoIntento = 3;
		Libro nuevoIntento = crearLibroDeMatematica(codigoIntento, nombreIntento, autorIntento);

		String nombreEstudiante = "Agustin";
		String apellido = "Hernandez";
		Integer dni = 43598592;
		Estudiante nuevoEstudiante = crearEstudiante(dni, nombre, apellido);

		nuevaBiblioteca.agregarLibro(nuevoLibro);
		nuevaBiblioteca.agregarLibro(nuevoPrestado);
		nuevaBiblioteca.agregarLibro(nuevoIntento);

		nuevaBiblioteca.prestamoDeLibro(nuevoLibro, nuevoEstudiante);// 1
		nuevaBiblioteca.prestamoDeLibro(nuevoPrestado, nuevoEstudiante);// 2
		nuevaBiblioteca.prestamoDeLibro(nuevoIntento, nuevoEstudiante);// 3 NO PUEDE

	}

	@Test
	public void queUnEstudiantePuedaDevolverUnLibroYQuedeDisponible() throws LibroExistenteExeption,
			NopuedeAdquirirMasLibrosExeption, LibroSolicitadoEnPrestamoExeption, LibroInexistenteExeption {
		String nombre = "Biblioteca Nacional";
		Bilbioteca nuevaBiblioteca = crearBiblioteca(nombre);

		String nombreLibro = "Matematica Aplicada";
		String autor = "Jose Hernandez";
		Integer codigo = 1;
		Libro nuevoLibro = crearLibroDeMatematica(codigo, nombreLibro, autor);

		String nombrePrestado = "Matematica Aplicada";
		String autorPrestado = "Jose Hernandez";
		Integer codigoPrestado = 2;
		Libro nuevoPrestado = crearLibroDeMatematica(codigoPrestado, nombrePrestado, nombrePrestado);

		String nombreEstudiante = "Agustin";
		String apellido = "Hernandez";
		Integer dni = 43598592;
		Estudiante nuevoEstudiante = crearEstudiante(dni, nombre, apellido);

		nuevaBiblioteca.agregarLibro(nuevoLibro);
		nuevaBiblioteca.agregarLibro(nuevoPrestado);

		nuevaBiblioteca.prestamoDeLibro(nuevoLibro, nuevoEstudiante);
		nuevaBiblioteca.devolucionDeLibro(nuevoLibro, nuevoEstudiante);

		assertEquals((Integer) 0, nuevoEstudiante.cantidadDeLibrosAdquiridos());
	}
}
