package com.jakarta.usuarios.Resource;

import com.jakarta.usuarios.Model.Role;
import com.jakarta.usuarios.Repository.RoleRepository;
import org.springframework.web.context.annotation.RequestScope;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("/role")
public class RoleResource {

    @Inject
    RoleRepository roleRepository;

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    public Response get() {
        return Response.ok().entity(roleRepository.getAllRoles()).build();

    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(Role roles, @Context UriInfo uriInfo) {
        Role roleId = roleRepository.create(roles);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(Long.toString(roleId.getRole_id()));
        return Response.created(builder.build()).build();
    }
}
