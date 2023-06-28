package com.emergentes.bean;

import com.emergentes.dao.UsuarioJpaController;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class BeanAcceso {
    private EntityManagerFactory emf;
    private UsuarioJpaController daoUsuarios;
    
    public BeanAcceso() {
        emf = Persistence.createEntityManagerFactory("PersistenciaUnidad");
        daoUsuarios = new UsuarioJpaController(emf);
    }

    public UsuarioJpaController getDaoUsuarios() {
        return daoUsuarios;
    }
    
}
