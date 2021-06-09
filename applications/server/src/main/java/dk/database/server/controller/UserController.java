package dk.database.server.controller;

import dk.database.server.domain.UserCreation;
import dk.database.server.domain.UserKeywordCreation;
import dk.database.server.domain.UserStockCreation;
import dk.database.server.entities.User;
import dk.database.server.entities.UserKeyword;
import dk.database.server.facade.DataFacadeImpl;
import org.springframework.web.bind.annotation.RequestBody;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.sql.SQLException;
import java.util.Map;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserController {

    private final DataFacadeImpl data = new DataFacadeImpl();

    /**
     *
     * @param uriInfo returns url path in header
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @Path("/")
    @GET
    public Response getAllUsers(@Context UriInfo uriInfo) throws SQLException, ClassNotFoundException {
        Map<Integer, User> users = data.getAllUsers();

        URI uri = uriInfo.getAbsolutePathBuilder().build();
        return Response
                .created(uri)
                .status(Response.Status.OK)
                .entity(users)
                .build();
    }

    /**
     *
     * @param userid
     * @param uriInfo returns url path in header
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @Path("/{userId}")
    @GET
    public Response getUserById(@PathParam("userId") int userid, @Context UriInfo uriInfo) throws SQLException, ClassNotFoundException {
        User user = data.getUserById(userid);

        URI uri = uriInfo.getAbsolutePathBuilder()
                .build();
        return Response
                .created(uri)
                .status(Response.Status.OK)
                .entity(user)
                .build();
    }

    /**
     *
     * @param userid
     * @param uriInfo returns url path in header
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @Path("/{userId}/keyword")
    @GET
    public Response getUserKeyword(@PathParam("userId") int userid, @Context UriInfo uriInfo) throws SQLException, ClassNotFoundException {
        UserKeyword user = data.getUserKeyword(userid);

        URI uri = uriInfo.getAbsolutePathBuilder()
                .build();
        return Response
                .created(uri)
                .status(Response.Status.OK)
                .entity(user)
                .build();
    }

    /**
     *
     * @param userCreation
     * @param uriInfo returns url path in header
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @Path("/")
    @POST
    public Response addUser(@RequestBody UserCreation userCreation, @Context UriInfo uriInfo) throws SQLException, ClassNotFoundException {
        URI uri = uriInfo.getAbsolutePathBuilder()
                .build();
        User user = data.addUser(userCreation);

        return Response
                .created(uri)
                .status(Response.Status.CREATED)
                .entity(user)
                .build();
    }

    /**
     *
     * @param userStockCreation
     * @param uriInfo returns url path in header
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @Path("/stock")
    @POST
    public Response applyUserStock(@RequestBody UserStockCreation userStockCreation, @Context UriInfo uriInfo) throws SQLException, ClassNotFoundException {
        Boolean ditInsert = data.applyStock(userStockCreation);

        URI uri = uriInfo.getAbsolutePathBuilder()
                .build();
        return Response
                .created(uri)
                .status(Response.Status.CREATED)
                .entity(ditInsert)
                .build();
    }

    /**
     *
     * @param userKeywordCreation
     * @param uriInfo returns url path in header
     * @return boolean
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @Path("/keyword")
    @POST
    public Response applyUserKeyword(@RequestBody UserKeywordCreation userKeywordCreation, @Context UriInfo uriInfo) throws SQLException, ClassNotFoundException {
        Boolean ditInsert = data.applyKeyword(userKeywordCreation);

        URI uri = uriInfo.getAbsolutePathBuilder()
                .build();
        return Response
                .created(uri)
                .status(Response.Status.CREATED)
                .entity(ditInsert)
                .build();
    }


    /**
     * 
     * @param userId
     * @param keyword
     * @param stock
     * @param uriInfo
     * @return boolean
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @Path("/user/{userId}/keyword/{keyword}/stock/{stock}")
    @POST
    public Response applyUserKeywordStock(
            @PathParam("userId") int userId,
            @PathParam("keyword") String keyword,
            @PathParam("stock") String stock ,
            @Context UriInfo uriInfo) throws SQLException, ClassNotFoundException {

        Boolean ditInsert = data.applyUserKeywordsStock(userId, keyword, stock);

        URI uri = uriInfo.getAbsolutePathBuilder()
                .build();
        return Response
                .created(uri)
                .status(Response.Status.OK)
                .entity(ditInsert)
                .build();
    }

}
