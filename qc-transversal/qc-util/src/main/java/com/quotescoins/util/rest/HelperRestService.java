package com.quotescoins.util.rest;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.Response.StatusType;

import com.quotescoins.util.model.rest.ErrorRestService;
import com.quotescoins.util.model.rest.MessageResponseRest;

public class HelperRestService {
	
	public Response validateTokenAPI(String token) {
		Status status = Response.Status.UNAUTHORIZED;
		
		MessageResponseRest unauth = new MessageResponseRest();
		unauth.setCode(status.getStatusCode());
		unauth.setStatus(status.toString());
		unauth.setType(MessageResponseRest.TYPE_ERROR);
		unauth.setMessage(ErrorRestService.NO_API_KEY.message());
		
		if(token == null) {
			return Response.status(status).entity(unauth).build();
		} else if(token.isEmpty()) {
			return Response.status(status).entity(unauth).build();
		}
		
		return null;
	}
	
	public Response responseSucessBuilder(Status status, String type, String model, Object data) {
		MessageResponseRest success = new MessageResponseRest();
		success.setCode(status.getStatusCode());
		success.setStatus(status.toString());
		success.setType(type);
		success.setModel(model);
		success.setData(data);
		
		return Response.status(status).entity(success).build();
	}
	
	public Response responseErrorBuilder(StatusType status, String type, Throwable error) {
		MessageResponseRest response = new MessageResponseRest();
		response.setCode(status.getStatusCode());
		response.setStatus(status.toString());
		response.setType(type);
		response.setMessage(error.getMessage());
		//response.setCausesError(error.getStackTrace());
		
		return Response.status(status).entity(response).type(MediaType.APPLICATION_JSON).build();
	}
}
