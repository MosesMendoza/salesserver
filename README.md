# numberserver

This actually isn't a number server. This is a playground app for bridging a
REST API -> kafka topic in java. The idea is that this service takes incoming
requests, and publishes them to a kafka topic, where presumably other work can
be done from it.

The "service" we're building here is a sales tracker. When a sale happens, that
sale event is posted to this service, which writes the sale to a kafka topic.
Other services can then use the sale to do things like calculate tax (see the
taxservice repo)

This uses a basic Jetty servlet API which expects an HTTP POST with a JSON body
that looks like this:

    {
      amount: < Decimal or money-safe value >,
      state_abbreviation: < a standard state abbreviation, like OR or WA >
    }

The servlet binds to 8080 at /app.

### Build

    gradlew build

### Running

    Run the jar

### Example

    curl localhost:8080/app/ -d "{\"amount\":\"32.22\", \"state_abbreviation\": \"WA\"}"
