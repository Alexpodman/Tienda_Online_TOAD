<%@page import="com.emergentes.entidades.Producto"%>
<%@page import="java.util.List"%>
<% List<Producto> productos = (List<Producto>)request.getAttribute("productos"); %>
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
            <h1>Tienda online TOAD</h1>
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
            <div class="product-list">
                <% for (Producto producto : productos) {%>
                <div class="product-card">
                    <img src="imagenes/producto.jpg" alt="<%= producto.getNombre() %>"/>
                    <h3><%= producto.getNombre() %></h3>
                    <p><b>Categoria:</b> <%= producto.getCategoria() %></p>
                    <p><b>Descripción:</b> <%= producto.getDescripcion() %></p>
                    <p><b>Precio:</b> <%= producto.getPrecio() %> Bs.</p>
                    <button>Agregar al carrito</button>
                </div>
                <% } %>
            </div>
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
