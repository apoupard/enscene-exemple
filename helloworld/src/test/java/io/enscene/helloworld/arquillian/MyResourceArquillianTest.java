package io.enscene.helloworld.arquillian;

import io.enscene.helloworld.HelloWorldMicroApplication;
import io.enscene.helloworld.resource.HelloWorldResource;

import javax.ws.rs.core.Response;

import org.fest.assertions.Assertions;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.extension.rest.client.ArquillianResteasyResource;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class MyResourceArquillianTest {

//	@ArquillianResource
//	private URL deploymentURL;

	@Deployment
	public static WebArchive createDeployment() {
		WebArchive webArchive = ShrinkWrap
				.create(WebArchive.class)
				.addClass(HelloWorldResource.class)
				.addClass(HelloWorldMicroApplication.class)
				.addAsLibraries(
						Maven.resolver()
								.resolve(
									"io.enscene.core:enscene-core-impl-resteasy:0.0.1-SNAPSHOT",
									"org.eclipse.jetty.orbit:org.objectweb.asm:3.1.0.v200803061910"
								)
								.withTransitivity().asFile());
		return webArchive;
	}
	
	@Test
	@RunAsClient
	public void should_create_greeting(@ArquillianResteasyResource("helloworld/") ResteasyWebTarget webTarget) {
		final Response response = webTarget.request()
									.get();
		Assertions.assertThat(response.getStatus()).isEqualTo(200);
	}

}
