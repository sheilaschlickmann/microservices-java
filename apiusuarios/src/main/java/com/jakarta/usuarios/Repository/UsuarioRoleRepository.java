package com.jakarta.usuarios.Repository;

import com.jakarta.usuarios.Model.UsuarioRole;

import jakarta.persistence.*;
import java.util.List;

public class UsuarioRoleRepository {

    EntityManager em;

    public List getAllUsuarioRole() {
        return em.createNamedQuery("usuarioRole.findAll", UsuarioRole.class).getResultList();
    }


    public UsuarioRole create(UsuarioRole usuarioRole) {
        em.persist(usuarioRole);
        return usuarioRole;
    }
}
