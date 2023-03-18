package com.jakarta.usuarios.Repository;

import com.jakarta.usuarios.Model.Role;

import jakarta.persistence.*;
import java.util.List;

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
