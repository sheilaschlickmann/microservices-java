package com.unibave.apiusuario.Resource;

import com.unibave.apiusuario.Repository.RoleRepository;
import com.unibave.apiusuario.Model.Role;
import jakarta.inject.Inject;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

@RequestScoped
@Path("/role")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RoleResource {

    @Inject
    RoleRepository roleRepository;

    @GET
    public Response get() {
        return Response.ok().entity(roleRepository.getAllRoles()).build();

    }

    @POST
    public Response create(Role roles, @Context UriInfo uriInfo) {
        Role roleId = roleRepository.create(roles);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(Long.toString(roleId.getRole_id()));
        return Response.created(builder.build()).build();
    }
}
