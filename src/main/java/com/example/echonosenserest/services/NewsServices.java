    package com.example.echonosenserest.services;

    import com.example.echonosenserest.models.News;
    import com.example.echonosenserest.database.NewsDBUtils;
    import jakarta.ws.rs.*;
    import jakarta.ws.rs.core.MediaType;
    import jakarta.ws.rs.core.Response;

    import java.sql.SQLException;
    import java.util.List;

    @Path("/news")
    public class NewsServices {

        @GET
        @Produces(MediaType.APPLICATION_JSON)
        public Response getAllNews() throws SQLException {
            List<News> newsList = NewsDBUtils.getAllNews();
            return Response.ok(newsList).build();
        }



        @GET
        @Path("/impact/{impact}")
        @Produces(MediaType.APPLICATION_JSON)
        public Response getNewsByImpact(@PathParam("impact") String impact) throws SQLException {
            List<News> newsList = NewsDBUtils.getNewsByImpact(impact);
            return Response.ok(newsList).build();
        }

        // Other methods...

        @GET
        @Path("/coin/{relatedCoin}/impact/{impact}")
        @Produces(MediaType.APPLICATION_JSON)
        public Response getNewsByCoinAndImpact(@PathParam("relatedCoin") String relatedCoin, @PathParam("impact") String impact) throws SQLException {
            List<News> newsList = NewsDBUtils.getNewsByCoinAndImpact(relatedCoin, impact);
            return Response.ok(newsList).build();
        }

    // Other methods...


        // Add more methods as needed...
    }
