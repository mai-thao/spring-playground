# spring-security
Learning about [Spring Security](https://spring.io/projects/spring-security) with a simple Pet Adoption API.

### API Contract
#### Public Endpoints
* `GET` /pets – List all pet details
* `GET` /pets/{id} – View a pet detail

#### Secured (Auth Required) Endpoints
* Requires at least MANAGER role
   * `POST` /pets – Add a new pet
   * `PUT` /pets/{id} – Update pet info
   * `DELETE` /pets/{id} – Delete a pet
* Requires ADMIN role
   * `DELETE` /pets - Delete all pets 

### How to Run the App
1) Navigate to this `spring-security/` submodule directory
2) Execute the command: `../gradlew bootRun`
  * The `..` means go up one directory to target the Gradle Wrapper in the root directory
3) Since this is an API with several endpoints that you can interact with, I recommend using [Insomnia](https://insomnia.rest/features/api-testing) (or [Postman](https://www.postman.com/product/what-is-postman/))
  1) To view pet(s), create a `GET` request to: http://localhost:8080/pets or create a `GET` request to http://localhost:8080/pets/}id} to view one pet
  2) To create a new pet, create a `POST` request to: http://localhost:8080/pets with the JSON request body
     * This endpoint requires at least `USER` authentication, so set Basic Auth credentials to: `"username": "user"` and `"password": "userpassword"`
   ```json
   {
       "id": 321,
       "name": "Female",
       "age": 2,
       "breed": "Bulldog",
       "gender": "Female"
   }
   ```
  3) To update an existing pet, create a `PUT` request to http://localhost:8080/pets/{id} with the ID of the pet and a JSON request body and Basic `USER` (see example above for required fields)
  4) To delete a pet, create a `DELETE` request to http://localhost:8080/pets/{id} with the ID of the pet
     * This endpoint requires at least `MANAGER` role, so change Basic Auth credentials to: `"username": "manager"` and `"password": "managerpassword"`
  5) To delete all pets, create a `DELETE` request to http://localhost:8080/pets
     * This endpoint requires `ADMIN` role, so change Basic Auth credentials to: `"username": "admin"` and `"password": "adminpassword"`
4) Terminate the app with: Ctrl + C

### How to Build the app
1) Execute the command `../gradlew build`

### How to Test the app
1) Execute the command `../gradlew test`
