package com.unibave.apiusuario.Repository;

import com.unibave.apiusuario.Model.Role;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import javax.enterprise.context.ApplicationScoped;

import java.util.List;

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
