# spring-data
Learning about [Spring Data](https://spring.io/projects/spring-data)

### How to Run the App
1) Navigate to the submodule directory and start the PostgreSQL Docker container with: `docker compose up -d`
2) Execute the command: `../gradlew bootRun`
   * The `..` means go up one directory to target the Gradle Wrapper in the root directory 
3) To view a list of users, in [Insomnia](https://insomnia.rest/features/api-testing) (or [Postman](https://www.postman.com/product/what-is-postman/)), create a `GET` request to: http://localhost:8080/users
4) To create a new user, in [Insomnia](https://insomnia.rest/features/api-testing) (or [Postman](https://www.postman.com/product/what-is-postman/)), create a `POST` request to: http://localhost:8080/users with the request body:
```json
{
	"name": "Jane",
	"gender": "Female"
}
```
