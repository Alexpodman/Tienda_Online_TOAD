<%@page import="com.emergentes.entidades.Producto"%>
<%@page import="java.util.List"%>
<% List<Producto> productos = (List<Producto>) request.getAttribute("productos");%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tienda TOAD</title>
        <link href="css/estilos.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <header>
            <h1>Lista de todos los productos de la tienda</h1>
        </header>
        <nav>
            <div class="logo">
                <h3><b>TOAD</b> - Freddy Alejandro Ticona Alanoca</h3>
            </div>
            <ul class="nav-links">
                <li><a href="MainController?opcion=inicio">Inicio</a></li>
                <li><a href="MainController?opcion=productos">Productos</a></li>
                <li><a href="#">Carrito</a></li>
                <li><a href="#">Pedidos</a></li>
            </ul>
        </nav>
        <main>
            <div>
                <form class="agregar" method="get" action="MainController">
                    <input type="hidden" name="opcion" value="agregarProducto">
                    <input type="submit" value="Agregar Producto">
                </form>
            </div>
            <table class="product-table">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Nombre</th>
                        <th>Descripcion</th>
                        <th>Precio</th>
                        <th>Categoria</th>
                        <th>Stock</th>
                        <th colspan="2">Modificar</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (Producto producto : productos) {%>
                    <tr>
                        <td><%= producto.getId()%></td>
                        <td><%= producto.getNombre()%></td>
                        <td><%= producto.getDescripcion()%></td>
                        <td><%= producto.getPrecio()%></td>
                        <td><%= producto.getCategoria() %></td>
                        <td><%= producto.getStock()%></td>
                        <td>
                            <form class="editar" method="get" action="MainController">
                                <input type="hidden" name="option" value="editarProducto">
                                <input type="hidden" name="id" value="<%= producto.getId() %>">
                                <input type="submit" value="Editar">
                            </form>
                        </td>
                        <td>
                            <form class="eliminar" method="get" action="MainController">
                                <input type="hidden" name="option" value="eliminarProducto">
                                <input type="hidden" name="id" value="<%= producto.getId() %>">
                                <input type="submit" value="Eliminar">
                            </form>
                        </td>
                    </tr>
                    <% }%>
                </tbody>
            </table>
        </main>
        <footer>
            <div class="footer-container">
                <div class="footer-contact">
                    <p>Correo: freddyyque@gmail.com</p>
                    <p>Teléfono: +591 69798419</p>
                </div>
                <div class="footer-links">
                    <a href="MainController?opcion=inicio">Inicio</a>
                    <a href="MainController?opcion=productos">Productos</a>
                    <a href="carrito.html">Carrito</a>
                    <a href="pedidos.html">Pedidos</a>
                </div>
                <div class="footer-info">
                    <p>&copy; 2023 - Todos los derechos reservados</p>
                    <p><a href="politica-cookies.html">Política de cookies</a></p>
                </div>
            </div>
        </footer>
    </body>
</html>
