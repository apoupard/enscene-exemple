package io.enscene.exemple.websocket.server;


import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.logging.Logger;

import javax.websocket.ClientEndpointConfig;
import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;

//@RunWith(Arquillian.class)
public class ChatEndPointTest {

  private static final Logger logger = Logger.getLogger(ChatEndPointTest.class.getName());

  // static ChatMessage testReply;
  // static ChatMessage testMessage;


  @Deployment
  public static WebArchive createDeployment() {
    WebArchive webArchive =
        ShrinkWrap
            .create(WebArchive.class)
            .addPackage(MyEndpoint.class.getPackage())
            .addAsLibraries(
                Maven.resolver().loadPomFromFile("pom.xml").importCompileAndRuntimeDependencies()
                    .resolve().withTransitivity().asFile());
    logger.info(webArchive.toString(true));
    return webArchive;
  }

  @ArquillianResource
  private URL deploymentURL;

//  @Test
//  @RunAsClient
  public void testChat() throws DeploymentException, IOException, URISyntaxException,
      InterruptedException {

    WebSocketContainer container = ContainerProvider.getWebSocketContainer();
    String uri =
        "ws://" + deploymentURL.getHost() + ":" + deploymentURL.getPort() + deploymentURL.getFile()
            + "websocket";
    container.connectToServer(new Endpoint() {

      @Override
      public void onOpen(Session session, EndpointConfig config) {
        logger.info("/////////////////");
      }
    }, ClientEndpointConfig.Builder.create().build(), URI.create(uri));
//      container.connectToServer(client1, configuration, URI.create(uri));
    
     // Wait for conversation to finish.
//     Thread.sleep(2000);

//     assertThat(testMessage.getMessage()).isEqualTo("Test message");
//     assertThat(testReply.getMessage()).isEqualTo("Test reply");
  }

}
