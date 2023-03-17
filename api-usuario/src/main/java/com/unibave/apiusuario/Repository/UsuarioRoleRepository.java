package com.unibave.apiusuario.Repository;

import com.unibave.apiusuario.Model.UsuarioRole;
import jakarta.persistence.EntityManager;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

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
