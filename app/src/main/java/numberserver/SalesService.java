package numberserver;

import java.io.IOException;
import java.util.stream.Collectors;
import org.json.JSONObject;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;
import java.util.HashMap;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import java.util.stream.*;
import java.util.Arrays;

public class SalesService extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String body = request.getReader().lines().collect(Collectors.joining());
        JSONObject jsonBody = new JSONObject(body);
        String numberValue = jsonBody.get("amount").toString();

        produceToTopic(numberValue);

        response.setStatus(200);
        response.setContentType("text/html; charset=UTF-8");
    }

    private Properties getProperties() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        return props;
    }

    private void produceToTopic(String value) {
        final String topicName = "streams-plaintext-input";
        final String key = "sale";

        final Map<String, String> valueDictionary = new HashMap<String, String>();

        valueDictionary.put("amount", value);
        valueDictionary.put("state_abbreviation", "WA");

        Properties props = getProperties();
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(props);
        try {
            ProducerRecord<String, String> record = new ProducerRecord<>(topicName, key, new JSONObject(valueDictionary).toString());
            producer.send(record);
            producer.close();
        }
        catch ( Exception error ) {
            System.out.print(error);
        }
    }
}
