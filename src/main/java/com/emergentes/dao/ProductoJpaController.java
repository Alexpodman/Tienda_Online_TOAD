/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.emergentes.dao;

import com.emergentes.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.emergentes.entidades.CarritoCompra;
import com.emergentes.entidades.Producto;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Alex
 */
public class ProductoJpaController implements Serializable {

    public ProductoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Producto producto) {
        if (producto.getCarritoCompraList() == null) {
            producto.setCarritoCompraList(new ArrayList<CarritoCompra>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<CarritoCompra> attachedCarritoCompraList = new ArrayList<CarritoCompra>();
            for (CarritoCompra carritoCompraListCarritoCompraToAttach : producto.getCarritoCompraList()) {
                carritoCompraListCarritoCompraToAttach = em.getReference(carritoCompraListCarritoCompraToAttach.getClass(), carritoCompraListCarritoCompraToAttach.getIdCarrito());
                attachedCarritoCompraList.add(carritoCompraListCarritoCompraToAttach);
            }
            producto.setCarritoCompraList(attachedCarritoCompraList);
            em.persist(producto);
            for (CarritoCompra carritoCompraListCarritoCompra : producto.getCarritoCompraList()) {
                Producto oldIdProductoOfCarritoCompraListCarritoCompra = carritoCompraListCarritoCompra.getIdProducto();
                carritoCompraListCarritoCompra.setIdProducto(producto);
                carritoCompraListCarritoCompra = em.merge(carritoCompraListCarritoCompra);
                if (oldIdProductoOfCarritoCompraListCarritoCompra != null) {
                    oldIdProductoOfCarritoCompraListCarritoCompra.getCarritoCompraList().remove(carritoCompraListCarritoCompra);
                    oldIdProductoOfCarritoCompraListCarritoCompra = em.merge(oldIdProductoOfCarritoCompraListCarritoCompra);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Producto producto) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Producto persistentProducto = em.find(Producto.class, producto.getId());
            List<CarritoCompra> carritoCompraListOld = persistentProducto.getCarritoCompraList();
            List<CarritoCompra> carritoCompraListNew = producto.getCarritoCompraList();
            List<CarritoCompra> attachedCarritoCompraListNew = new ArrayList<CarritoCompra>();
            for (CarritoCompra carritoCompraListNewCarritoCompraToAttach : carritoCompraListNew) {
                carritoCompraListNewCarritoCompraToAttach = em.getReference(carritoCompraListNewCarritoCompraToAttach.getClass(), carritoCompraListNewCarritoCompraToAttach.getIdCarrito());
                attachedCarritoCompraListNew.add(carritoCompraListNewCarritoCompraToAttach);
            }
            carritoCompraListNew = attachedCarritoCompraListNew;
            producto.setCarritoCompraList(carritoCompraListNew);
            producto = em.merge(producto);
            for (CarritoCompra carritoCompraListOldCarritoCompra : carritoCompraListOld) {
                if (!carritoCompraListNew.contains(carritoCompraListOldCarritoCompra)) {
                    carritoCompraListOldCarritoCompra.setIdProducto(null);
                    carritoCompraListOldCarritoCompra = em.merge(carritoCompraListOldCarritoCompra);
                }
            }
            for (CarritoCompra carritoCompraListNewCarritoCompra : carritoCompraListNew) {
                if (!carritoCompraListOld.contains(carritoCompraListNewCarritoCompra)) {
                    Producto oldIdProductoOfCarritoCompraListNewCarritoCompra = carritoCompraListNewCarritoCompra.getIdProducto();
                    carritoCompraListNewCarritoCompra.setIdProducto(producto);
                    carritoCompraListNewCarritoCompra = em.merge(carritoCompraListNewCarritoCompra);
                    if (oldIdProductoOfCarritoCompraListNewCarritoCompra != null && !oldIdProductoOfCarritoCompraListNewCarritoCompra.equals(producto)) {
                        oldIdProductoOfCarritoCompraListNewCarritoCompra.getCarritoCompraList().remove(carritoCompraListNewCarritoCompra);
                        oldIdProductoOfCarritoCompraListNewCarritoCompra = em.merge(oldIdProductoOfCarritoCompraListNewCarritoCompra);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = producto.getId();
                if (findProducto(id) == null) {
                    throw new NonexistentEntityException("The producto with id " + id + " no longer exists.");
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
            Producto producto;
            try {
                producto = em.getReference(Producto.class, id);
                producto.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The producto with id " + id + " no longer exists.", enfe);
            }
            List<CarritoCompra> carritoCompraList = producto.getCarritoCompraList();
            for (CarritoCompra carritoCompraListCarritoCompra : carritoCompraList) {
                carritoCompraListCarritoCompra.setIdProducto(null);
                carritoCompraListCarritoCompra = em.merge(carritoCompraListCarritoCompra);
            }
            em.remove(producto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Producto> findProductoEntities() {
        return findProductoEntities(true, -1, -1);
    }

    public List<Producto> findProductoEntities(int maxResults, int firstResult) {
        return findProductoEntities(false, maxResults, firstResult);
    }

    private List<Producto> findProductoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Producto.class));
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

    public Producto findProducto(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Producto.class, id);
        } finally {
            em.close();
        }
    }

    public int getProductoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Producto> rt = cq.from(Producto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
