<%@ page contentType="text/html; charset=UTF-8" %>

<div class="navbar-default sidebar" role="navigation">
                <div class="sidebar-nav navbar-collapse">
                    <ul class="nav" id="side-menu">
                        <li class="sidebar-search">
                            <div class="input-group custom-search-form">
                            
                            	<form method="get" action="search">
	                                <input type="text" 
	                                	   id="criterio"
	                                       name="criterio" 
	                                       class="form-control" 
	                                       placeholder="Search..."
	                                       autofocus
	                                       required
	                                       min="1"
	                                       >
	                                <span class="input-group-btn">
	                                <button class="btn btn-default" type="submit">
	                                    <i class="fa fa-search"></i>
	                                </button>
                                </form>
                                
                            	</span>
                            </div>
                            <!-- /input-group -->
                        </li>
                        <li>
                            <a href="index.jsp"><i class="fa fa-dashboard fa-fw"></i> Dashboard</a>
                        </li>
                        <li>
                            <a href="usuarios"><i class="fa fa-user fa-fw"></i> Usuarios</a>
                        </li>
                        <li>
                            <a href="pages/cursos.jsp"><i class="fa fa-graduation-cap fa-fw"></i> Cursos</a>
                        </li>
                    </ul>
                </div>
                <!-- /.sidebar-collapse -->
            </div>