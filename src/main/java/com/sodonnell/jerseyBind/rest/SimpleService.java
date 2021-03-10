package com.sodonnell.jerseyBind.rest;

import com.sodonnell.jerseyBind.SimpleClass;
import jakarta.ws.rs.core.HttpHeaders;

import jakarta.ws.rs.Path;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

@Path("/")
public class SimpleService {

  private static Logger LOG = LoggerFactory.getLogger(SimpleService.class);

  @Inject
  private SimpleClass simpleClass;

  @PUT
  @Path("{bucketName}")
  public Response createBucket(@PathParam("bucketName") String name) {

    if (simpleClass == null) {
      LOG.info("Simple class is null");
    } else {
      LOG.info("Simple class is not null");
    }

    return Response.status(200).build();
  }
}
