package salesserver;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;


class AppTest {
    ApiServer mockedServer = mock(ApiServer.class);
    Server server = mock(Server.class);
    ServerConnector connector = mock(ServerConnector.class);
    ServletContextHandler handler = mock(ServletContextHandler.class);
    SalesService service = mock(SalesService.class);

    @Test
    void startAsksServerToListen() {
        App myApp = new App(mockedServer);

        myApp.start(server, connector, handler, service);
        verify(mockedServer).listen(server, connector, handler, service);
    }
}
