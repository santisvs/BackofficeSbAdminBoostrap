<%@page import="com.ipartek.formacion.backoffice.controladores.ControladorConstantes"%>
<%@page import="com.ipartek.formacion.backoffice.pojo.Persona"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="com.ipartek.formacion.backoffice.modelo.DbConnection"%>

<%@include file="../includes/head.jsp" %>
<%@include file="../includes/nav.jsp" %>

        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Usuarios</h1>
                </div>
                <div class="panel panel-default">
                        <div class="panel-heading">
                            <i class="fa fa-user fa-fw"></i> Usuarios inscritos
                            <div class="pull-right">
                               
                                    <button type="button" class="btn btn-default btn-xs" >
                                        <a href="usuarios?op=<%=ControladorConstantes.OP_NUEVO%>"
                                           title="Crear nuevo registro">
                                        	<i class="fa fa-plus fa-fw"></i> 
                                        	Añadir Usuario
                                        </a>
                                    </button>
                                    
                               
                            </div>
                        </div>                      
                        
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-8">
                                    <div class="table-responsive">
                                        <table class="table table-bordered table-hover table-striped">
                                            <thead>
                                                <tr>
                                                    <th>#</th>
                                                    <th>Nombre</th>
                                                    <th>Apellido</th>
                                                    <th>Curso</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                            
                                            <% 
                                            	//recoger "atributo" listado personas de la request
                                            	ArrayList<Persona> lista = (ArrayList<Persona>)request.getAttribute("listaUsuarios");
                                            	if ( lista == null ) {
                                            		lista = new ArrayList<Persona>();
                                            	}
                                            	for ( Persona persona : lista ){ 
                                            %>
                                                <tr>
                                                    <td><%=persona.getId()%></td>
                                                    <td><a href="usuarios?op=<%=ControladorConstantes.OP_DETALLE%>&id=<%=persona.getId()%>" title="ir al detalle"><%=persona.getNombre()%></a></td>
                                                    <td>Vallejo</td>
                                                    <td>Java J2EE</td>
                                                </tr>
                                            <% } //end for %>    
                                            
                                            </tbody>
                                        </table>
                                    </div>
                                    <!-- /.table-responsive -->
                                </div>
                                <!-- /.col-lg-4 (nested) -->
                                <div class="col-lg-8">
                                    <div id="morris-bar-chart"></div>
                                </div>
                                <!-- /.col-lg-8 (nested) -->
                            </div>
                            <!-- /.row -->
                        </div>
                        <!-- /.panel-body -->
                    </div>
            </div>
        </div>
        <!-- /#page-wrapper -->

<%@include file="../includes/footer.jsp" %>