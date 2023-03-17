package com.unibave.apiusuario.Resource;

import com.unibave.apiusuario.Repository.UsuarioRoleRepository;
import com.unibave.apiusuario.model.UsuarioRole;
import jakarta.inject.Inject;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

@RequestScoped
@Path("/usuario-role")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioRoleResource {
    @Inject
    UsuarioRoleRepository usuarioRoleRepository;

    @GET
    public Response getAllUsuarios() {
        return Response.ok().entity(usuarioRoleRepository.getAllUsuarioRole()).build();

    }

    @POST
    public Response create(UsuarioRole usuario, @Context UriInfo uriInfo) {
        UsuarioRole usuarioRoleId = usuarioRoleRepository.create(usuario);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(Long.toString(usuarioRoleId.getRoleId()));
        return Response.created(builder.build()).build();
    }
}
