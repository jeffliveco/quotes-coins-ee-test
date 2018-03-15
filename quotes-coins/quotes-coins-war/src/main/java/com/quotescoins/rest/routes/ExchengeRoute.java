package com.quotescoins.rest.routes;

import com.quotescoins.core.BusinessFacade;
import com.quotescoins.dto.ExchangeDto;
import com.quotescoins.dto.RateDto;
import com.quotescoins.model.Exchange;
import com.quotescoins.model.Rate;
import com.quotescoins.util.model.rest.MessageResponseRest;
import com.quotescoins.util.rest.HelperRestService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Logger;

@RequestScoped
@Path("exchange")
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
public class ExchengeRoute {

    /**
     * Default attribute that print messages in server console
     */
    private final static Logger logger = Logger.getLogger(ExchengeRoute.class.getName());

    private HelperRestService helper;

    @Inject
    private BusinessFacade service;

    /**
     * Default constructor
     */
    public ExchengeRoute(){
        helper = new HelperRestService();
    }

    @GET
    @Path("/{base}")
    public Response getBase(@PathParam("base") String base){
        logger.info("getBase()");
        ExchangeDto response = service.getBase(base);
        return helper.responseSucessBuilder(Response.Status.OK, MessageResponseRest.TYPE_SUCCESS, Exchange.class.getSimpleName(), response);
    }

    @GET
    @Path("/{base}/{rate}")
    public Response getBaseByRate(@PathParam("base") String base, @PathParam("rate") String rate){
        logger.info("getBaseByRate()");
        ExchangeDto response = service.getBaseByRate(base, rate);
        return helper.responseSucessBuilder(Response.Status.OK, MessageResponseRest.TYPE_SUCCESS, Exchange.class.getSimpleName(), response);
    }

    @POST
    @Path("/{base}/{rate}/{value}")
    public Response calculateBaseByRate(@PathParam("base") String base, @PathParam("rate") String rate, @PathParam("value") Float value){
        logger.info("getBaseByRate()");
        RateDto response = service.getValueByBaseAndRate(base, rate, value);
        return helper.responseSucessBuilder(Response.Status.OK, MessageResponseRest.TYPE_SUCCESS, Rate.class.getSimpleName(), response);
    }

    @OPTIONS
    @Path("/{base}/{rate}/{value}")
    public Response optionsCalculateBaseByRate() {
        return Response.ok().header(HttpHeaders.ALLOW, HttpMethod.POST).build();
    }
}
