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
            <h1>Agregar un nuevo producto a la tienda</h1>
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
            <div class="form-container">
                <form method="post" action="MainController" class="form-agregar">
                    <input type="hidden" name="option" value="agregarProducto">
                    <label for="nombre">Nombre:</label>
                    <input type="text" id="nombre" name="nombre" required>

                    <label for="descripcion">Descripción:</label>
                    <textarea id="descripcion" name="descripcion" required></textarea>

                    <label for="precio">Precio:</label>
                    <input type="number" id="precio" name="precio" step="0.01" required>

                    <label for="categoria">Categoría:</label>
                    <input type="text" id="categoria" name="categoria" required>

                    <label for="stock">Stock:</label>
                    <input type="number" id="stock" name="stock" required>

                    <input type="submit" value="Agregar">
                </form>
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
