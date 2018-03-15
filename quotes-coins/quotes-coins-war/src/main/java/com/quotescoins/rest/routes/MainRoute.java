package com.quotescoins.rest.routes;

import com.quotescoins.util.model.rest.MessageResponseRest;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RequestScoped
@Path("")
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
public class MainRoute {

    @GET
    public Response init() {
        MessageResponseRest message = new MessageResponseRest();
        message.setCode(Response.Status.OK.getStatusCode());
        message.setStatus(Response.Status.OK.toString());
        message.setType(MessageResponseRest.TYPE_SUCCESS);
        message.setMessage("API by QuotesCoins");
        return Response.ok(message).build();
    }
}
