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
						out.print( p.toString() );
				
				%>

		</div>
	</div>

<%@include file="../includes/footer.jsp" %>