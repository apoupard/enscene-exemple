package io.enscene.exemple.websocket.server;

import io.enscene.core.MicroService;

import javax.ws.rs.ApplicationPath;

import com.google.inject.Binder;

@ApplicationPath("other")
public class HelloWorldMicroApplication extends MicroService {

  @Override
  public void configure(Binder binder) {}

}
