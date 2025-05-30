# spring-kafka
Learning about [Spring Kafka](https://spring.io/projects/spring-kafka) with a simple Order Tracking system that has a
controller, producer, and consumer.
* _I have another repo that covers the high-level Kafka ecosystem [here](https://github.com/mai-thao/kafka-playground)
  (just a simple Java app without Spring)._

### How to Run the App
1) Navigate to this `spring-kafka/` submodule directory and start the Kafka Docker containers with: `docker compose up -d`
2) Start the app with the command: `../gradlew bootRun`
    * The `..` means go up one directory to target the Gradle Wrapper in the root directory
3) Since this app has a POST endpoint, I recommend using [Insomnia](https://insomnia.rest/features/api-testing) (or 
[Postman](https://www.postman.com/product/what-is-postman/) or with [cURL](https://stackoverflow.com/a/7173011))
    1) Create a `POST` request to: http://localhost:8080/orders with the JSON request body:
   ```json
    {
    "productName": "Monopoly Boardgame",
    "totalQuantity": "1",
    "totalAmount": "19.99"
    }
   ```
3) Under the hood, the controller will accept the payload and passes it on to the producer. Then the producer will serialize it to JSON and publish it to the Kafka topic called `order-tracking-topic`
4) It considers that a successful path, and you should see a terminal log output and an API response (in Insomnia): `"Order successfully processed with ID fb484afe-ef28-4d33-b787-8b4107b02e8a!"`
5) A few seconds later, the event-driven consumer will consume the message, deserialize it, and then print the payload to the terminal: `Consumed message: Order(id=fb484afe-ef28-4d33-b787-8b4107b02e8a, productName=Monopoly Boardgame, totalQuantity=1, totalAmount=19.99)` 
7) That concludes the controller, producer, and consumer relationship of this very simple Kafka Order Tracking system
   1) `POST` a few more messages and see it instantaneously makes it way from producer to consumer
8) Terminate the app with: Ctrl + C
6) Stop the Docker containers with: `docker compose down`
