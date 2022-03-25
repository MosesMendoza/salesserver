package salesserver;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;

public interface AppServer {
    public void listen(Server server, ServerConnector connector, ServletContextHandler contextHandler, SalesService service);
}
