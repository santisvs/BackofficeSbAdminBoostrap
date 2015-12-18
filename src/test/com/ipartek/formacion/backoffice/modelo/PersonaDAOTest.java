package com.ipartek.formacion.backoffice.modelo;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.ipartek.formacion.backoffice.pojo.Persona;

public class PersonaDAOTest {
	
	static DbConnection db;
	static Connection conn;
	static PersonaDAO dao;
	static Persona pMock;
	static int id; //identificador de la ultima operacion realizada en el DAO

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		db = new DbConnection();
		conn = db.getConnection();
		conn.setAutoCommit(false);
		dao = new PersonaDAO();
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
		conn.rollback();
		db.desconectar();
		db = null;
		dao = null;		
	}

	@Before
	public void setUp() throws Exception {
		
		pMock = new Persona();
		pMock.setNombre("mock");
		//TODO resto de atributos en Persona: pass, email, obseravciones,...
		
		id = dao.insert(pMock);
		assertTrue ("No se ha realizado inserccion", id > 0);
		
	}

	@After
	public void tearDown() throws Exception {
		
		assertTrue("No se pudo eliminar",dao.delete(id));
		
	}

	@Test
	public void testGetAll() {
		
		try{
			ArrayList<Persona> personas = (ArrayList<Persona>) dao.getAll();
			assertTrue("Deberia recuperar almenos una persona", personas.size() > 0 );
		}catch(SQLException e){
			fail("Tenemos una cagado la implemtacion en nuestro DAO " + e.getMessage());
		}	

	}

	@Test
	public void testGetById() {
		try{
			Persona p = dao.getById(id);				
			assertTrue("No tienen los mismos atributos", pMock.equals(p) );
		}catch(SQLException e){
			fail("Tenemos una cagado la implemtacion en nuestro DAO " + e.getMessage());
		}
		
		
		try{
			assertNull("No deberia exsitir", dao.getById(0) );
		}catch(SQLException e){
			fail("Tenemos una cagado la implemtacion en nuestro DAO " + e.getMessage());
		}	
		
	}
	

	@Test
	public void testDelete() {
		//comprobar caso de id inexistente
		try{
			assertFalse("No se puede eliminar lo que no existe",dao.delete(0));
		}catch(SQLException e){
			fail("Tenemos una cagado la implemtacion en nuestro DAO " + e.getMessage());
		}	
	}

	@Test
	public void testUpdate() {
		
		//test todo funciona bien
		//TODO comprobar resto de atributos
		String nombreNuevo = "Mockito";
		pMock.setNombre(nombreNuevo);
		try{
			assertTrue("No modifica",dao.update(pMock));
		}catch(SQLException e){
			fail("Tenemos una cagado la implemtacion en nuestro DAO " + e.getMessage());
		}	
		
		assertEquals( nombreNuevo, pMock.getNombre() );
		
		
		//test null
		try{
			assertFalse("No modifica persona NULL",dao.update(null));
		}catch(SQLException e){
			fail("Tenemos una cagado la implemtacion en nuestro DAO " + e.getMessage());
		}	
		
		//test no existe persona a modificar
		Persona pNoInsertada = new Persona();
		try{
			assertFalse("No modifica una persona que no exite",dao.update(pNoInsertada));
		}catch(SQLException e){
			fail("Tenemos una cagado la implemtacion en nuestro DAO " + e.getMessage());
		}	
		
		
	}

	@Test
	public void testInsert() {
		fail("Not yet implemented");
	}

}
