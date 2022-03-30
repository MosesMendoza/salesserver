package salesserver;

import java.io.IOException;
import java.util.stream.Collectors;
import org.json.JSONObject;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Properties;
import java.util.Map;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class SalesService extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String body = request.getReader().lines().collect(Collectors.joining());
        JSONObject jsonBody = new JSONObject(body);
        Sale sale = parseSaleFromBody(jsonBody);

        produceToTopic(sale.toMap());

        response.setStatus(200);
        response.setContentType("text/html; charset=UTF-8");
    }

    private static Properties getProperties() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        return props;
    }

    private Sale parseSaleFromBody(JSONObject body) {
        // leave these as strings since we're just putting them on kafka as strings for nwo
        String saleAmount = body.get("amount").toString();
        String stateAbbreviation = body.get("state_abbreviation").toString();

        return new Sale(saleAmount, stateAbbreviation);
    }

    private void produceToTopic(Map<String, String>map) {
        final String topicName = "streams-plaintext-input";
        final String key = "sale";

        Properties props = SalesService.getProperties();
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(props);
        try {
            ProducerRecord<String, String> record = new ProducerRecord<>(topicName, key, new JSONObject(map).toString());
            producer.send(record);
            producer.close();
        }
        catch ( Exception error ) {
            System.out.print(error);
        }
    }
}
