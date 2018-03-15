package com.quotescoins.util.mapper.rest;

import java.util.logging.Logger;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.quotescoins.util.exception.AttributeRequiredException;
import com.quotescoins.util.exception.DataNoExistException;
import com.quotescoins.util.exception.InvalidAttributeException;
import com.quotescoins.util.exception.business.BusinessCoreException;
import com.quotescoins.util.model.rest.MessageResponseRest;
import com.quotescoins.util.rest.HelperRestService;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {
	
	/**
	 * Default attribute that print messages in server console
	 */
	private final static Logger logger = Logger.getLogger(GenericExceptionMapper.class.getName());
	
	private HelperRestService helper;
	
	public GenericExceptionMapper() {
		helper = new HelperRestService();
	}
	
	public Response toResponse(Throwable ex) {
		Response.StatusType type = this.getStatusType(ex);
		if(ex.getCause() != null) {
			if(ex.getCause().getClass().getName().equals(BusinessCoreException.class.getName())) {
				if(ex.getCause().getCause() != null) {
					if(ex.getCause().getCause().getClass().getName().equals(DataNoExistException.class.getName())) {
						// TODO: entity class name aggregation
						return helper.responseSucessBuilder(Response.Status.NOT_FOUND, MessageResponseRest.TYPE_SUCCESS, null, null);
					}
					
					if(ex.getCause().getCause().getClass().getName().equals(AttributeRequiredException.class.getName()) || ex.getCause().getCause().getClass().getName().equals(InvalidAttributeException.class.getName())) {
						return helper.responseErrorBuilder(Response.Status.PRECONDITION_FAILED, MessageResponseRest.TYPE_ERROR, ex.getCause().getCause());
					}
					
					return helper.responseErrorBuilder(Response.Status.BAD_REQUEST, MessageResponseRest.TYPE_ERROR, ex.getCause());
				} else {
					return helper.responseErrorBuilder(Response.Status.BAD_REQUEST, MessageResponseRest.TYPE_ERROR, ex.getCause());					
				}
			} else {
				return helper.responseErrorBuilder(Response.Status.INTERNAL_SERVER_ERROR, MessageResponseRest.TYPE_ERROR, ex);
			}			
		} 
		
		return helper.responseErrorBuilder(type, MessageResponseRest.TYPE_ERROR, ex);	
	}
	
	private Response.StatusType getStatusType(Throwable ex) {
        if (ex instanceof WebApplicationException) {
            return((WebApplicationException) ex).getResponse().getStatusInfo();
        } else {
            return Response.Status.INTERNAL_SERVER_ERROR;
        }
    }
}
