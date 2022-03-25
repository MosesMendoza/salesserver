package salesserver;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;


class ApiServerTest {

    ApiServer mockedApiServer = mock(ApiServer.class);
    Server server = mock(Server.class);
    ServerConnector connector = mock(ServerConnector.class);
    ServletContextHandler handler = mock(ServletContextHandler.class);
    SalesService service = mock(SalesService.class);

    @Test
    void listenStartsAServer() {
        mockedApiServer.listen(server, connector, handler, service);
        try {
            verify(server).start();
        }
        catch ( Exception error ) {
            System.out.println(error.toString());
        }
    }
}
