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
import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;

/**
 * Servlet implementation class UsuarioServlet
 */
public class UsuarioServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static int operacion;
	
	private static Mensaje msj;
	
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
			//TODO intentar forzarlo para todos los servlets, filtros
			request.setCharacterEncoding("UTF-8");
			
			daoPersona = new PersonaDAO();
			msj = null;
			
			//determinar operacion a realizar
			if ( request.getParameter("op") != null  ){
				operacion = Integer.parseInt( request.getParameter("op") );
			}else{
				operacion = ControladorConstantes.OP_LISTAR;
			}	
						
			//realizar accion
			switch (operacion) {
			
				case ControladorConstantes.OP_LISTAR:
					listar(request);
					break;
	
				case ControladorConstantes.OP_DETALLE:
					detalle(request);
					break;
					
				case ControladorConstantes.OP_NUEVO:
					nuevo(request);
					break;	
					
				case ControladorConstantes.OP_ELIMINAR:
					eliminar(request);
					break;
					
				case ControladorConstantes.OP_MODIFICAR:
					modificarCrear(request);
					break;	
			}
			
			request.setAttribute("msj", msj);
			
			//servir la JSP
			dispatch.forward(request, response);
			
			
			
		}catch(Exception e){
			//TODO mejor en un LOG
			e.printStackTrace();
			
			//TODO ir a pagina error 404.jsp o 500.jsp
		}
	}

	/**
	 * Modifica o Crea una nueva persona	
	 * @param request
	 * @throws SQLException 
	 */
	private void modificarCrear(HttpServletRequest request) throws SQLException {

			//recoger parametros formulario
			pId = request.getParameter("id");
			int id = Integer.parseInt(pId);
		
			String pNombre = request.getParameter("nombre");
			
			
			//construir persona
			Persona p  = new Persona();
			p.setId(id);
			p.setNombre(pNombre);
			
			//persitir en la bbdd
			if ( p.getId() == -1 ){
				daoPersona.insert(p);
			}else{
				if ( daoPersona.update(p) ){
					msj = new Mensaje("Registro Modificado con Exito", Mensaje.TIPO_SUCCESS);
				}else{
					msj = new Mensaje("No Modificado registro", Mensaje.TIPO_WARNING);
				}
			}	
			//listar
			listar(request);
		
	}

	private void eliminar(HttpServletRequest request) throws SQLException {
		pId = request.getParameter("id");
		int id = Integer.parseInt(pId);
		
		if ( daoPersona.delete(id) ){
			msj = new Mensaje("Registro Eliminado con Exito", Mensaje.TIPO_SUCCESS);
		}else{
			msj = new Mensaje("No Eliminado registro", Mensaje.TIPO_DANGER);
		}
		listar(request);
		
	}

	/**
	 * Nos lleva a la vista del formulario para crear una persona
	 * @param request
	 */
	private void nuevo(HttpServletRequest request) {
		Persona p = new Persona();
		request.setAttribute("persona", p );		
		dispatch = request.getRequestDispatcher( VISTA_USUARIOS_DETALLE );		
	}

	private void detalle(HttpServletRequest request) throws SQLException {
		
		pId = request.getParameter("id");	
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
		doGet(request, response);
	}

}
