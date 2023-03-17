package com.jakarta.usuarios.Repository;

import com.jakarta.usuarios.Model.Usuario;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.*;
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
