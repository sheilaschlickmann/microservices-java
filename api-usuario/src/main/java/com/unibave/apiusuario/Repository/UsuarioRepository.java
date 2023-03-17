package com.unibave.apiusuario.Repository;

import com.unibave.apiusuario.Model.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import javax.enterprise.context.ApplicationScoped;

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
