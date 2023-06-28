package com.emergentes.bean;

import com.emergentes.dao.CarritoCompraJpaController;
import com.emergentes.dao.PedidoJpaController;
import com.emergentes.dao.ProductoJpaController;
import com.emergentes.dao.UsuarioJpaController;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class BeanAcceso {
    private EntityManagerFactory emf;
    private UsuarioJpaController daoUsuarios;
    private ProductoJpaController daoProductos;
    private PedidoJpaController daoPedidos;
    private CarritoCompraJpaController daoCarritoCompras;
    
    public BeanAcceso() {
        emf = Persistence.createEntityManagerFactory("PersistenciaUnidad");
        daoUsuarios = new UsuarioJpaController(emf);
        daoProductos = new ProductoJpaController(emf);
        daoPedidos = new PedidoJpaController(emf);
        daoCarritoCompras = new CarritoCompraJpaController(emf);
    }

    public UsuarioJpaController getDaoUsuarios() {
        return daoUsuarios;
    }

    public ProductoJpaController getDaoProductos() {
        return daoProductos;
    }

    public PedidoJpaController getDaoPedidos() {
        return daoPedidos;
    }

    public CarritoCompraJpaController getDaoCarritoCompras() {
        return daoCarritoCompras;
    }
    
}
