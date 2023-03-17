package com.jakarta.usuarios.Resource;

import com.jakarta.usuarios.Model.Usuario;
import com.jakarta.usuarios.Repository.UsuarioRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

@RequestScoped
@Path("/usuarios")
public class UsuarioResource {

    @Inject
    UsuarioRepository usuarioRepository;

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getAllUsuarios() {
        return Response.ok().entity(usuarioRepository.getAllUsuarios()).build();

    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(Usuario usuario, @Context UriInfo uriInfo) {
        Usuario usuarioId = usuarioRepository.create(usuario);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(Long.toString(usuarioId.getUserID()));
        return Response.created(builder.build()).build();
    }
}
