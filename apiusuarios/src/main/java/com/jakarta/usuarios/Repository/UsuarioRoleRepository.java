package com.jakarta.usuarios.Repository;

import com.jakarta.usuarios.Model.UsuarioRole;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;
@Transactional
@ApplicationScoped
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
