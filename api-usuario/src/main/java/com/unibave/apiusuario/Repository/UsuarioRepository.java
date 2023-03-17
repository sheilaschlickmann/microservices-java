package com.unibave.apiusuario.Repository;

import com.unibave.apiusuario.model.Usuario;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;


@Transactional
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
