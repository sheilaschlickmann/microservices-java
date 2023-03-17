package com.unibave.apiusuario.Repository;

import com.unibave.apiusuario.model.UsuarioRole;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@ApplicationScoped
public class UsuarioRoleRepository {
    @PersistenceContext
    EntityManager em;

    public List getAllUsuarioRole() {
        return em.createNamedQuery("usuarioRole.findAll", UsuarioRole.class).getResultList();
    }


    public UsuarioRole create(UsuarioRole usuarioRole) {
        em.persist(usuarioRole);
        return usuarioRole;
    }
}
