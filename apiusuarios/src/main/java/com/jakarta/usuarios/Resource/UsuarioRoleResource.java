package com.jakarta.usuarios.Resource;

import com.jakarta.usuarios.Model.UsuarioRole;
import com.jakarta.usuarios.Repository.UsuarioRoleRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

@RequestScoped
@Path("/usuario-role")
public class UsuarioRoleResource {
    @Inject
    UsuarioRoleRepository usuarioRoleRepository;

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getAllUsuarios() {
        return Response.ok().entity(usuarioRoleRepository.getAllUsuarioRole()).build();

    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(UsuarioRole usuario, @Context UriInfo uriInfo) {
        UsuarioRole usuarioRoleId = usuarioRoleRepository.create(usuario);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(Long.toString(usuarioRoleId.getRoleId()));
        return Response.created(builder.build()).build();
    }
}
