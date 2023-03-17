package com.unibave.apiusuario.Repository;

import com.unibave.apiusuario.Model.UsuarioRole;
import javax.persistence.*;
import javax.enterprise.context.ApplicationScoped;
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
