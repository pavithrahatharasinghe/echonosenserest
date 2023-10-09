package com.example.echonosenserest.services;

import com.example.echonosenserest.models.AdminLog;
import com.example.echonosenserest.database.AdminLogDBUtils;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;
@Path("/adminlogs")
public class AdminLogServices {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllAdminLogs() throws SQLException {
        List<AdminLog> adminLogs = AdminLogDBUtils.getAllAdminLogs();
        return Response.ok(adminLogs).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addAdminLog(AdminLog adminLog) throws SQLException {
        boolean isAdded = AdminLogDBUtils.addAdminLog(adminLog);
        if (isAdded) {
            return Response.status(Response.Status.CREATED).entity(adminLog).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("Error adding admin log.").build();
        }
    }
}

