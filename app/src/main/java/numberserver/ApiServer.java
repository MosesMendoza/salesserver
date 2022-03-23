package numberserver;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
//import org.eclipse.jetty.servlet.ServletHolder;

public class ApiServer implements AppServer {
    public void listen(Server server, ServerConnector connector, ServletContextHandler contextHandler, SalesService service) {
        connector.setPort(8080);
        server.addConnector(connector);
        contextHandler.setContextPath("/app");
        contextHandler.addServlet(SalesService.class, "/");
        server.setHandler(contextHandler);

        try {
            server.start();
        }
        catch ( Exception error ) {
            System.out.println(error.toString());
        }
    }
}