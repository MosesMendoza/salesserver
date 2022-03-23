package numberserver;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

class AppTest {
    ApiServer mockedServer = mock(ApiServer.class);

    @Test
    void startAsksServerToListen() {
        App myApp = new App(mockedServer);
        myApp.start();
        verify(mockedServer).listen();
    }
}
