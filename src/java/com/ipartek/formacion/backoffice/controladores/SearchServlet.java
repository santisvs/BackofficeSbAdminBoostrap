package com.ipartek.formacion.backoffice.controladores;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.backoffice.modelo.PersonaDAO;
import com.ipartek.formacion.backoffice.pojo.Persona;

/**
 * Servlet implementation class SearchServlet
 */
public class SearchServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static final String VIEW_SEARCH = "pages/searchResult.jsp";
	
	private static PersonaDAO daoPersona;
       
   
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		System.out.println("1 Peticion a este servlet");
		daoPersona = new PersonaDAO();
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		System.out.println("servlet destruido");
		daoPersona = null;
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("Antes de servir por POST o GET");
		super.service(request, response);
		System.out.println("Despues de servir por POST o GET");
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}

	/**
	 * Hacemos lo mismo venga la peticion por GET o POST
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void doProcess(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String criterio = request.getParameter("criterio");
		
		System.out.println("Buscando criterio:" + criterio );
		Persona p = null;
		try{
			
			p = daoPersona.getByDni(criterio);
		}catch(Exception e){
			
			e.printStackTrace();
			System.out.println("Excepcion PersoanDao " + e.getMessage() );
		}	
		
		request.setAttribute("persona", p);
		request.setAttribute("criterio", criterio);
		request.getRequestDispatcher(VIEW_SEARCH).forward(request, response);
		
	}
	
	

}
