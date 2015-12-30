<%@page import="com.ipartek.formacion.backoffice.controladores.ControladorConstantes"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<%@page import="com.ipartek.formacion.backoffice.pojo.Persona"%>

<%@include file="../includes/head.jsp" %>       
<%@include file="../includes/nav.jsp" %>     


<div id="page-wrapper">
	<div id="search-result">
	
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Criterio buscado: ${requestScope.criterio}</h1>
                </div>
                <div class="panel panel-default">
                
                	<%
                		Persona p = (Persona)request.getAttribute("persona");
                		if ( p != null ){
                			%>
                			<a href="usuarios?op=<%=ControladorConstantes.OP_DETALLE%>&id=<%=p.getId()%>" title="ir al detalle"><%=p.getNombre()%></a>
                			<%
                		}else{
                			out.print("No se encontraron personas");
                		}
                	%>
                	
				</div>
            </div>
            
   </div> <!--  <div id="search-result"> -->       
</div>
<!-- /#page-wrapper -->

<%@include file="../includes/footer.jsp" %>   