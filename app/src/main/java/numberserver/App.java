package numberserver;

public class App {
    private AppServer server;
    public App(AppServer api) {
        server = api;
    }

    public void start() {
        server.listen();
    }

    public static void main(String[] args) {
        new App(new ApiServer()).start();
    }
}