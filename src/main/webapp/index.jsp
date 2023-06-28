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
                <a href="#">TOAD</a>
            </div>
            <ul class="nav-links">
                <li><a href="#">Inicio</a></li>
                <li><a href="#">Productos</a></li>
                <li><a href="#">Carrito</a></li>
                <li><a href="#">Pedidos</a></li>
                <li><a href="#">Perfil</a></li>
                <li><a href="#">Cerrar sesión</a></li>
            </ul>
        </nav>
        <main>
            <div class="product-list">
                <% for (Producto producto : productos) {%>
                <div class="product-card">
                    <img src="producto1.jpg" alt="Producto 1">
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
                    <a href="inicio.html">Inicio</a>
                    <a href="productos.html">Productos</a>
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
