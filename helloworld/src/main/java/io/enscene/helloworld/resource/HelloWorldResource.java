package io.enscene.helloworld.resource;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("helloworld")
public class HelloWorldResource {

  @Inject
  public HelloWorldResource() {}

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public String helloWorld() {
    return "Hello world!";
  }
}
