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
   *  `DELETE` /pets - Delete all pets 
