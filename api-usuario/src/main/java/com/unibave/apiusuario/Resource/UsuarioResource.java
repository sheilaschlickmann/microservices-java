package com.unibave.apiusuario.Resource;

import com.unibave.apiusuario.Repository.UsuarioRepository;
import com.unibave.apiusuario.model.Usuario;
import jakarta.inject.Inject;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

@RequestScoped
@Path("/usuarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioResource {

    @Inject
    UsuarioRepository usuarioRepository;

    @GET
    public Response getAllUsuarios() {
        return Response.ok().entity(usuarioRepository.getAllUsuarios()).build();

    }

    @POST
    public Response create(Usuario usuario, @Context UriInfo uriInfo) {
        Usuario usuarioId = usuarioRepository.create(usuario);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(Long.toString(usuarioId.getUserID()));
        return Response.created(builder.build()).build();
    }
}
