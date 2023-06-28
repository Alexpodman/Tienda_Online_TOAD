/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.emergentes.dao;

import com.emergentes.dao.exceptions.NonexistentEntityException;
import com.emergentes.entidades.CarritoCompra;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.emergentes.entidades.Producto;
import com.emergentes.entidades.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Alex
 */
public class CarritoCompraJpaController implements Serializable {

    public CarritoCompraJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CarritoCompra carritoCompra) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Producto idProducto = carritoCompra.getIdProducto();
            if (idProducto != null) {
                idProducto = em.getReference(idProducto.getClass(), idProducto.getId());
                carritoCompra.setIdProducto(idProducto);
            }
            Usuario idUsuario = carritoCompra.getIdUsuario();
            if (idUsuario != null) {
                idUsuario = em.getReference(idUsuario.getClass(), idUsuario.getId());
                carritoCompra.setIdUsuario(idUsuario);
            }
            em.persist(carritoCompra);
            if (idProducto != null) {
                idProducto.getCarritoCompraList().add(carritoCompra);
                idProducto = em.merge(idProducto);
            }
            if (idUsuario != null) {
                idUsuario.getCarritoCompraList().add(carritoCompra);
                idUsuario = em.merge(idUsuario);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CarritoCompra carritoCompra) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CarritoCompra persistentCarritoCompra = em.find(CarritoCompra.class, carritoCompra.getIdCarrito());
            Producto idProductoOld = persistentCarritoCompra.getIdProducto();
            Producto idProductoNew = carritoCompra.getIdProducto();
            Usuario idUsuarioOld = persistentCarritoCompra.getIdUsuario();
            Usuario idUsuarioNew = carritoCompra.getIdUsuario();
            if (idProductoNew != null) {
                idProductoNew = em.getReference(idProductoNew.getClass(), idProductoNew.getId());
                carritoCompra.setIdProducto(idProductoNew);
            }
            if (idUsuarioNew != null) {
                idUsuarioNew = em.getReference(idUsuarioNew.getClass(), idUsuarioNew.getId());
                carritoCompra.setIdUsuario(idUsuarioNew);
            }
            carritoCompra = em.merge(carritoCompra);
            if (idProductoOld != null && !idProductoOld.equals(idProductoNew)) {
                idProductoOld.getCarritoCompraList().remove(carritoCompra);
                idProductoOld = em.merge(idProductoOld);
            }
            if (idProductoNew != null && !idProductoNew.equals(idProductoOld)) {
                idProductoNew.getCarritoCompraList().add(carritoCompra);
                idProductoNew = em.merge(idProductoNew);
            }
            if (idUsuarioOld != null && !idUsuarioOld.equals(idUsuarioNew)) {
                idUsuarioOld.getCarritoCompraList().remove(carritoCompra);
                idUsuarioOld = em.merge(idUsuarioOld);
            }
            if (idUsuarioNew != null && !idUsuarioNew.equals(idUsuarioOld)) {
                idUsuarioNew.getCarritoCompraList().add(carritoCompra);
                idUsuarioNew = em.merge(idUsuarioNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = carritoCompra.getIdCarrito();
                if (findCarritoCompra(id) == null) {
                    throw new NonexistentEntityException("The carritoCompra with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CarritoCompra carritoCompra;
            try {
                carritoCompra = em.getReference(CarritoCompra.class, id);
                carritoCompra.getIdCarrito();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The carritoCompra with id " + id + " no longer exists.", enfe);
            }
            Producto idProducto = carritoCompra.getIdProducto();
            if (idProducto != null) {
                idProducto.getCarritoCompraList().remove(carritoCompra);
                idProducto = em.merge(idProducto);
            }
            Usuario idUsuario = carritoCompra.getIdUsuario();
            if (idUsuario != null) {
                idUsuario.getCarritoCompraList().remove(carritoCompra);
                idUsuario = em.merge(idUsuario);
            }
            em.remove(carritoCompra);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CarritoCompra> findCarritoCompraEntities() {
        return findCarritoCompraEntities(true, -1, -1);
    }

    public List<CarritoCompra> findCarritoCompraEntities(int maxResults, int firstResult) {
        return findCarritoCompraEntities(false, maxResults, firstResult);
    }

    private List<CarritoCompra> findCarritoCompraEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CarritoCompra.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public CarritoCompra findCarritoCompra(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CarritoCompra.class, id);
        } finally {
            em.close();
        }
    }

    public int getCarritoCompraCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CarritoCompra> rt = cq.from(CarritoCompra.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
