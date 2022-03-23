package numberserver;

import org.eclipse.jetty.server.Server;

public class ApiServer implements AppServer {
    public String listen() {
        return "hello";
    }
}