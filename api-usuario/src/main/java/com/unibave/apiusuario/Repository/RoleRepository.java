package com.unibave.apiusuario.Repository;

import com.unibave.apiusuario.model.Role;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@ApplicationScoped
public class RoleRepository {
    @PersistenceContext
    EntityManager em;

    public List getAllRoles() {
        return em.createNamedQuery("role.findAll", Role.class).getResultList();
    }


    public Role create(Role roles) {
        em.persist(roles);
        return roles;
    }
}
