package com.sodonnell.jerseyBind;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.internal.inject.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class Setup {

  private static Logger LOG = LoggerFactory.getLogger(Setup.class);

  public static void main(String[] args) throws Exception {
    Setup app = new Setup();
    app.start();
  }

  public Setup() throws IOException {
  }

  /**
   * Used to start the local http server to host the application.
   */
  public void start() throws Exception {
    int port = 9998;
    Server server = new Server(port);

    ServletContextHandler ctx =
        new ServletContextHandler(ServletContextHandler.NO_SESSIONS);
    ctx.setContextPath("/");
    server.setHandler(ctx);
    ResourceConfig config = new JerseyConfig();
    ServletHolder servlet = new ServletHolder(new ServletContainer(config));
    servlet.setInitOrder(1);
    ctx.addServlet(servlet, "/*");

    server.start();
    server.join();
  }

  public static class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
      packages("com.sodonnell.jerseyBind", "jersey.config.server.provider.packages");
      register(new MyBinder());
    }
  }

  /* Bind is used to let jersey know what can be injected */
  public static class MyBinder extends AbstractBinder {
    @Override
    protected void configure() {
      LOG.info("Attempting to configure binder");
      bind(new SimpleClass()).to(SimpleClass.class); //.in(Singleton.class);
    }
  }

}