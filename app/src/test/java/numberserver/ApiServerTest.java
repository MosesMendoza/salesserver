package numberserver;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ApiServerTest {

    @Test
    void listenSaysHello() {
        ApiServer server = new ApiServer();
        assertEquals(server.listen(), "hello");
    }
}
