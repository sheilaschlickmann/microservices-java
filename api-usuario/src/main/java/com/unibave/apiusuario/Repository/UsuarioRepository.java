package com.unibave.apiusuario.Repository;

import com.unibave.apiusuario.Model.Usuario;
import javax.persistence.*;
import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

import java.util.List;


@ApplicationScoped
public class UsuarioRepository {
        @PersistenceContext
        EntityManager em;
        public List getAllUsuarios() {
            return em.createNamedQuery("usuario.findAll", Usuario.class).getResultList();
        }

        public Usuario create(Usuario usuario) {
            em.persist(usuario);
            return usuario;
        }
}
