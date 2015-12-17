package com.ipartek.formacion.backoffice.modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ipartek.formacion.backoffice.pojo.Persona;

public class PersonaDAO implements Persistable<Persona> {

	@Override
	public List<Persona> getAll() throws SQLException  {
		ArrayList<Persona> lista = new ArrayList<Persona>();
		
		//crear conexion
    	DbConnection conn = new DbConnection();
    	
    	//sentencia sql
    	String sql = "select * from `persona` order by `id` desc limit 500;";
    	//creamos consulta
    	PreparedStatement ps = conn.getConnection().prepareStatement(sql);
    	//ejecutar la consulta
    	ResultSet rs = ps.executeQuery();
    	
    	while( rs.next()){
    		lista.add( mapeo(rs) );
    	}
    	
    	//cerrar recursos en orden inversa
    	rs.close();
    	ps.close();
    	conn.desconectar();
    	
		return lista;
	}

	@Override
	public Persona getById(int id) throws SQLException {
		
		Persona p = null;
		
		//crear conexion
    	DbConnection conn = new DbConnection();
    	
    	//sentencia sql
    	String sql = "select * from `persona` where id = ? ;";
    	//creamos consulta
    	PreparedStatement pst = conn.getConnection().prepareStatement(sql);
    	pst.setInt(1, id);
    	//ejecutar la consulta
    	ResultSet rs = pst.executeQuery();
    	while( rs.next()){
    		p = mapeo(rs);   
    	}
    	
    	//cerrar recursos en orden inversa
    	rs.close();
    	pst.close();
    	conn.desconectar();
    	
		return p;
	}

	@Override
	public boolean delete(int id) throws SQLException {
		
		boolean resul = false;
		DbConnection conn = new DbConnection();
		String sql = "DELETE FROM `persona` WHERE  `id`= ? ;";
		PreparedStatement pst = conn.getConnection().prepareStatement(sql);
    	pst.setInt(1, id);
    	if ( pst.executeUpdate() == 1 ){
    		resul = true;
    	}    	
    	pst.close();
    	conn.desconectar();
		return resul;
	}

	@Override
	public boolean update( Persona p ) throws SQLException {
		boolean resul = false;
		DbConnection conn = new DbConnection();
		String sql ="UPDATE `persona` SET `nombre`= ? WHERE  `id`= ? ;";
		PreparedStatement pst = conn.getConnection().prepareStatement(sql);
		pst.setString(1, p.getNombre() );
		pst.setInt(2, p.getId() );
		if ( pst.executeUpdate() == 1 ){
    		resul = true;
    	}    	
    	pst.close();
    	conn.desconectar();
		return resul;
	}

	@Override
	public int insert(Persona p ) throws SQLException {
		int resul = -1;
		DbConnection conn = new DbConnection();
		String sql ="INSERT INTO `persona` (`nombre`) VALUES ( ? );";
		PreparedStatement pst = conn.getConnection().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS );
		pst.setString(1, p.getNombre() );
		if ( pst.executeUpdate() == 1 ){
			
			ResultSet generatedKeys = pst.getGeneratedKeys();
			 if (generatedKeys.next()) {				 
				 p.setId( generatedKeys.getInt(1) );
				 resul = p.getId();
			 }else{
				 throw new SQLException("Creating user failed, no ID obtained.");
			 }
			
		}
		pst.close();
    	conn.desconectar();
		return resul;
	}
	
	
	private Persona mapeo ( ResultSet rs ) throws SQLException{
		
		 Persona p = new Persona();
		 p.setId( rs.getInt("id") );
		 p.setNombre( rs.getString("nombre"));
		 return p;
	}

}
