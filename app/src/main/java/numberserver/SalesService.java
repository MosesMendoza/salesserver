package numberserver;

import java.io.IOException;
import java.util.stream.Collectors;
import org.json.JSONObject;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SalesService extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String data = request.getReader().lines().collect(Collectors.joining());
        JSONObject input = new JSONObject(data);
        String valueToStore = input.get("data").toString();

        System.out.println(data);
        response.setStatus(200);
        response.setContentType("text/html; charset=UTF-8");
    }
}
