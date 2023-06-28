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
import java.util.ArrayList;
import java.util.List;
import com.emergentes.entidades.Pedido;
import com.emergentes.entidades.Usuario;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Alex
 */
public class UsuarioJpaController implements Serializable {

    public UsuarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuario usuario) {
        if (usuario.getCarritoCompraList() == null) {
            usuario.setCarritoCompraList(new ArrayList<CarritoCompra>());
        }
        if (usuario.getPedidoList() == null) {
            usuario.setPedidoList(new ArrayList<Pedido>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<CarritoCompra> attachedCarritoCompraList = new ArrayList<CarritoCompra>();
            for (CarritoCompra carritoCompraListCarritoCompraToAttach : usuario.getCarritoCompraList()) {
                carritoCompraListCarritoCompraToAttach = em.getReference(carritoCompraListCarritoCompraToAttach.getClass(), carritoCompraListCarritoCompraToAttach.getIdCarrito());
                attachedCarritoCompraList.add(carritoCompraListCarritoCompraToAttach);
            }
            usuario.setCarritoCompraList(attachedCarritoCompraList);
            List<Pedido> attachedPedidoList = new ArrayList<Pedido>();
            for (Pedido pedidoListPedidoToAttach : usuario.getPedidoList()) {
                pedidoListPedidoToAttach = em.getReference(pedidoListPedidoToAttach.getClass(), pedidoListPedidoToAttach.getIdPedido());
                attachedPedidoList.add(pedidoListPedidoToAttach);
            }
            usuario.setPedidoList(attachedPedidoList);
            em.persist(usuario);
            for (CarritoCompra carritoCompraListCarritoCompra : usuario.getCarritoCompraList()) {
                Usuario oldIdUsuarioOfCarritoCompraListCarritoCompra = carritoCompraListCarritoCompra.getIdUsuario();
                carritoCompraListCarritoCompra.setIdUsuario(usuario);
                carritoCompraListCarritoCompra = em.merge(carritoCompraListCarritoCompra);
                if (oldIdUsuarioOfCarritoCompraListCarritoCompra != null) {
                    oldIdUsuarioOfCarritoCompraListCarritoCompra.getCarritoCompraList().remove(carritoCompraListCarritoCompra);
                    oldIdUsuarioOfCarritoCompraListCarritoCompra = em.merge(oldIdUsuarioOfCarritoCompraListCarritoCompra);
                }
            }
            for (Pedido pedidoListPedido : usuario.getPedidoList()) {
                Usuario oldIdUsuarioOfPedidoListPedido = pedidoListPedido.getIdUsuario();
                pedidoListPedido.setIdUsuario(usuario);
                pedidoListPedido = em.merge(pedidoListPedido);
                if (oldIdUsuarioOfPedidoListPedido != null) {
                    oldIdUsuarioOfPedidoListPedido.getPedidoList().remove(pedidoListPedido);
                    oldIdUsuarioOfPedidoListPedido = em.merge(oldIdUsuarioOfPedidoListPedido);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuario usuario) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario persistentUsuario = em.find(Usuario.class, usuario.getId());
            List<CarritoCompra> carritoCompraListOld = persistentUsuario.getCarritoCompraList();
            List<CarritoCompra> carritoCompraListNew = usuario.getCarritoCompraList();
            List<Pedido> pedidoListOld = persistentUsuario.getPedidoList();
            List<Pedido> pedidoListNew = usuario.getPedidoList();
            List<CarritoCompra> attachedCarritoCompraListNew = new ArrayList<CarritoCompra>();
            for (CarritoCompra carritoCompraListNewCarritoCompraToAttach : carritoCompraListNew) {
                carritoCompraListNewCarritoCompraToAttach = em.getReference(carritoCompraListNewCarritoCompraToAttach.getClass(), carritoCompraListNewCarritoCompraToAttach.getIdCarrito());
                attachedCarritoCompraListNew.add(carritoCompraListNewCarritoCompraToAttach);
            }
            carritoCompraListNew = attachedCarritoCompraListNew;
            usuario.setCarritoCompraList(carritoCompraListNew);
            List<Pedido> attachedPedidoListNew = new ArrayList<Pedido>();
            for (Pedido pedidoListNewPedidoToAttach : pedidoListNew) {
                pedidoListNewPedidoToAttach = em.getReference(pedidoListNewPedidoToAttach.getClass(), pedidoListNewPedidoToAttach.getIdPedido());
                attachedPedidoListNew.add(pedidoListNewPedidoToAttach);
            }
            pedidoListNew = attachedPedidoListNew;
            usuario.setPedidoList(pedidoListNew);
            usuario = em.merge(usuario);
            for (CarritoCompra carritoCompraListOldCarritoCompra : carritoCompraListOld) {
                if (!carritoCompraListNew.contains(carritoCompraListOldCarritoCompra)) {
                    carritoCompraListOldCarritoCompra.setIdUsuario(null);
                    carritoCompraListOldCarritoCompra = em.merge(carritoCompraListOldCarritoCompra);
                }
            }
            for (CarritoCompra carritoCompraListNewCarritoCompra : carritoCompraListNew) {
                if (!carritoCompraListOld.contains(carritoCompraListNewCarritoCompra)) {
                    Usuario oldIdUsuarioOfCarritoCompraListNewCarritoCompra = carritoCompraListNewCarritoCompra.getIdUsuario();
                    carritoCompraListNewCarritoCompra.setIdUsuario(usuario);
                    carritoCompraListNewCarritoCompra = em.merge(carritoCompraListNewCarritoCompra);
                    if (oldIdUsuarioOfCarritoCompraListNewCarritoCompra != null && !oldIdUsuarioOfCarritoCompraListNewCarritoCompra.equals(usuario)) {
                        oldIdUsuarioOfCarritoCompraListNewCarritoCompra.getCarritoCompraList().remove(carritoCompraListNewCarritoCompra);
                        oldIdUsuarioOfCarritoCompraListNewCarritoCompra = em.merge(oldIdUsuarioOfCarritoCompraListNewCarritoCompra);
                    }
                }
            }
            for (Pedido pedidoListOldPedido : pedidoListOld) {
                if (!pedidoListNew.contains(pedidoListOldPedido)) {
                    pedidoListOldPedido.setIdUsuario(null);
                    pedidoListOldPedido = em.merge(pedidoListOldPedido);
                }
            }
            for (Pedido pedidoListNewPedido : pedidoListNew) {
                if (!pedidoListOld.contains(pedidoListNewPedido)) {
                    Usuario oldIdUsuarioOfPedidoListNewPedido = pedidoListNewPedido.getIdUsuario();
                    pedidoListNewPedido.setIdUsuario(usuario);
                    pedidoListNewPedido = em.merge(pedidoListNewPedido);
                    if (oldIdUsuarioOfPedidoListNewPedido != null && !oldIdUsuarioOfPedidoListNewPedido.equals(usuario)) {
                        oldIdUsuarioOfPedidoListNewPedido.getPedidoList().remove(pedidoListNewPedido);
                        oldIdUsuarioOfPedidoListNewPedido = em.merge(oldIdUsuarioOfPedidoListNewPedido);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = usuario.getId();
                if (findUsuario(id) == null) {
                    throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.");
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
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, id);
                usuario.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.", enfe);
            }
            List<CarritoCompra> carritoCompraList = usuario.getCarritoCompraList();
            for (CarritoCompra carritoCompraListCarritoCompra : carritoCompraList) {
                carritoCompraListCarritoCompra.setIdUsuario(null);
                carritoCompraListCarritoCompra = em.merge(carritoCompraListCarritoCompra);
            }
            List<Pedido> pedidoList = usuario.getPedidoList();
            for (Pedido pedidoListPedido : pedidoList) {
                pedidoListPedido.setIdUsuario(null);
                pedidoListPedido = em.merge(pedidoListPedido);
            }
            em.remove(usuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuario> findUsuarioEntities() {
        return findUsuarioEntities(true, -1, -1);
    }

    public List<Usuario> findUsuarioEntities(int maxResults, int firstResult) {
        return findUsuarioEntities(false, maxResults, firstResult);
    }

    private List<Usuario> findUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuario.class));
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

    public Usuario findUsuario(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuario> rt = cq.from(Usuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
