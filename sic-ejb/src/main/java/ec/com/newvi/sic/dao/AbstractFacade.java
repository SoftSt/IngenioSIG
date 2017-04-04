package ec.com.newvi.sic.dao;

/*
 * Copyright (c) 2010, Oracle and/or its affiliates. All rights reserved.
 *
 * You may not modify, use, reproduce, or distribute this software
 * except in compliance with the terms of the license at:
 * http://developer.sun.com/berkeley_license.html
 */
import ec.com.newvi.sic.enums.EnumNewviExcepciones;
import ec.com.newvi.sic.util.excepciones.NewviExcepcion;
import java.util.List;
import javax.annotation.security.PermitAll;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TransactionRequiredException;

/**
 *
 * @author Newvi
 * @param <T>
 * @param <E>
 */
@PermitAll
public abstract class AbstractFacade<T, E> {

    private final Class<T> entityClass;
    private final Class<E> primaryKeyClass;

    @PersistenceContext(unitName = "SIC_PU")
    private EntityManager em;

    public EntityManager getEntityManager() {
        return em;
    }

    public AbstractFacade(Class<T> entityClass, Class<E> primaryKeyClass) {
        this.entityClass = entityClass;
        this.primaryKeyClass = primaryKeyClass;
    }

    public T create(T entity) throws NewviExcepcion {
        try {
            getEntityManager().persist(entity);
            em.flush();
            return entity;
        } catch (Exception e) {
            throw new NewviExcepcion(EnumNewviExcepciones.ERR000, e);
        }
    }

    public T edit(T entity) throws NewviExcepcion {
        try {
            getEntityManager().merge(entity);
            em.flush();
            return entity;
        } catch (TransactionRequiredException e) {
            throw new NewviExcepcion(EnumNewviExcepciones.ERR120, e);
        } catch (Exception e) {
            throw new NewviExcepcion(EnumNewviExcepciones.ERR000, e);
        }
    }

    public void remove(T entity) throws NewviExcepcion {
        try {
            getEntityManager().remove(getEntityManager().merge(entity));
        } catch (TransactionRequiredException e) {
            throw new NewviExcepcion(EnumNewviExcepciones.ERR120, e);
        } catch (Exception e) {
            throw new NewviExcepcion(EnumNewviExcepciones.ERR000, e);
        }
    }

    public T find(E id) {
        return getEntityManager().find(entityClass, id);
    }

    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager()
                .getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager()
                .getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager()
                .getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    /**
     * Revierte un proceso de base de datos
     *
     * @throws NewviExcepcion
     */
    public void rollBackTransaccion() throws NewviExcepcion {
        try {
            this.getEntityManager().getTransaction().rollback();
        } catch (Exception e) {
            throw new NewviExcepcion(EnumNewviExcepciones.ERR000, e);
        }

    }
    
}
