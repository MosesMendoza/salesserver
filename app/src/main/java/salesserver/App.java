package salesserver;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;

public class App {
    private AppServer appServer;

    public App(AppServer api) {
        appServer = api;
    }

    public void start(Server server, ServerConnector connector, ServletContextHandler contextHandler, SalesService service) {
        appServer.listen(server, connector, contextHandler, service);
    }

    public static void main(String[] args) {
        Server server = new Server();
        new App(new ApiServer()).start(
            server,
            new ServerConnector(server),
            new ServletContextHandler(ServletContextHandler.NO_SECURITY | ServletContextHandler.NO_SESSIONS),
            new SalesService()
        );
    }
}