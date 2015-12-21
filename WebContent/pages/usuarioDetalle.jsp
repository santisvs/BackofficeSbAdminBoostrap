<%@page import="com.ipartek.formacion.backoffice.controladores.ControladorConstantes"%>
<%@page import="com.ipartek.formacion.backoffice.pojo.Persona"%>
<%@include file="../includes/head.jsp" %>
<%@include file="../includes/nav.jsp" %>


	<div id="page-wrapper">
        <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Detalle Usuario</h1>
                </div>
                
                <div class="panel panel-default">                	
				<% 
					Persona p = (Persona)request.getAttribute("persona");
					boolean isNew = (p.getId()==-1)?true:false;									
				%>
				
					<!-- formulario -->
					<form method="post" action="usuarios">
					
						
						<input type="text" 
						       name="nombre" 
						       value="<%=p.getNombre()%>"
						       placeholder="Escribe tu Nombre"
						       required
						       pattern="^[a-zA-Z][a-zA-Z0-9-_\.]{1,20}$"
						       >
					
							<input type="hidden" name="id" value="<%=p.getId()%>">
							<input type="hidden" name="op" value="<%=ControladorConstantes.OP_MODIFICAR%>">
					
						<% if (isNew){ %>							
							<input type="submit" value="Crear">
						<%}else{%>							
							<input type="submit" value="Modificar">
							<a href="usuarios?op=<%=ControladorConstantes.OP_ELIMINAR%>&id=<%=p.getId()%>">Eliminar</a>
						<%} %>
					</form>

				</div>
				
		</div><!-- <div class="row"> -->
	</div><!-- <div id="page-wrapper"> -->

<%@include file="../includes/footer.jsp" %>