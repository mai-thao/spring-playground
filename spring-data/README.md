# spring-data
Learning about [Spring Data](https://spring.io/projects/spring-data) with a simple User CRUD application. 

### How to Run the App
1) Navigate to this `spring-data/` submodule directory and start the PostgreSQL Docker container with: `docker compose up -d`
2) Execute the command: `../gradlew bootRun`
   * The `..` means go up one directory to target the Gradle Wrapper in the root directory
3) Since this is an API with several endpoints that you can interact with, I recommend using [Insomnia](https://insomnia.rest/features/api-testing) (or [Postman](https://www.postman.com/product/what-is-postman/)) 
   1) To view a list of users, create a `GET` request to: http://localhost:8080/users
   2) To create a new user, create a `POST` request to: http://localhost:8080/users with the JSON request body:
   ```json
   {
       "name": "Jane",
       "gender": "Female",
       "email": "janesemail@company.com"
   }
   ```
   3) To update an existing user, create a `PUT` request to http://localhost:8080/users/{id} with the ID of the user and a JSON request body of the updated attribute
      * _Note: You can find the user's ID from the `GET` endpoint_
      ```json
      {
        "email" : "janesupdatedemail@company.com"
      }
      ```
   4) To delete a user, create a `DELETE` request to http://localhost:8080/users/{id} with the ID of the user
4) Terminate the app with: Ctrl + C 
5) Stop the Docker container with: `docker compose down`

### How to Build the app
1) Execute the command `../gradlew build`

### How to Test the app
1) Execute the command `../gradlew test`
