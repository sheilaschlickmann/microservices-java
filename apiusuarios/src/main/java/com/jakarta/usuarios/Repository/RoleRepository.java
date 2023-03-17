package com.jakarta.usuarios.Repository;

import com.jakarta.usuarios.Model.Role;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.*;
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
