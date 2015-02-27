package io.enscene.helloworld;

import javax.ws.rs.ApplicationPath;

import com.google.inject.Binder;

import io.enscene.core.MicroService;
import io.enscene.helloworld.resource.HelloWorldResource;

@ApplicationPath("")
public class HelloWorldMicroApplication extends MicroService {

  @Override
  public void configure(Binder binder) {
    binder.bind(HelloWorldResource.class);
  }

}
