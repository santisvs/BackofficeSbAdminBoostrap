package com.ipartek.formacion.backoffice.controladores;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.backoffice.modelo.PersonaDAO;
import com.ipartek.formacion.backoffice.pojo.Persona;

/**
 * Servlet implementation class UsuarioServlet
 */
public class UsuarioServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static PersonaDAO daoPersona;
	private static String pId; //parametro Identifacdor del usuario
	private RequestDispatcher dispatch;
	
	public static final String VISTA_USUARIOS_LISTAR  = "pages/usuarios.jsp";
	public static final String VISTA_USUARIOS_DETALLE = "pages/usuarioDetalle.jsp";
       
   
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try{
			daoPersona = new PersonaDAO();
			
			//recoger parametros
			pId = request.getParameter("id");		
		
			//determinar operacion a realizar
			if ( pId != null ){
				detalle(request);
			}else{
				listar(request);
			}
			
			//servir la JSP
			dispatch.forward(request, response);
			
			
			
		}catch(Exception e){
			//TODO mejor en un LOG
			e.printStackTrace();
			
			//TODO ir a pagina error 404.jsp o 500.jsp
		}
	}

	private void detalle(HttpServletRequest request) throws SQLException {
		
		int id = Integer.parseInt(pId);		
		Persona p = daoPersona.getById(id);
		
		request.setAttribute("persona", p);
		
		dispatch = request.getRequestDispatcher( VISTA_USUARIOS_DETALLE );
		
	}

	private void listar(HttpServletRequest request) throws SQLException {
		//llamar modelo para obtener listado		
		ArrayList<Persona> listaUsuarios = (ArrayList<Persona>)daoPersona.getAll();
		
		//guardar listado como atributo en request
		request.setAttribute("listaUsuarios", listaUsuarios);	
		
		//peticcion interna a la jsp		
		dispatch = request.getRequestDispatcher( VISTA_USUARIOS_LISTAR );
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
