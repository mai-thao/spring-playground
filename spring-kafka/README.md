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
4) You should see a terminal log output and an API response (in Insomnia): `"Order successfully processed with ID fb484afe-ef28-4d33-b787-8b4107b02e8a!"`
5) Terminate the app with: Ctrl + C
6) Stop the Docker containers with: `docker compose down`
