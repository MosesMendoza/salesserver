package numberserver;

public class App {
    private Server server;
    public App(Server api) {
        server = api;
    }

    public void start() {
        server.listen();
    }

    public static void main(String[] args) {
        new App(new ApiServer()).start();
    }
}