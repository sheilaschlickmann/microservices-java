package com.jakarta.usuarios.Resource;

import com.jakarta.usuarios.Model.UsuarioRole;
import com.jakarta.usuarios.Repository.UsuarioRoleRepository;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

@Path("/usuario-role")
public class UsuarioRoleResource {

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
