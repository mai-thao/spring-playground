# spring-boot
Learning about [Spring Boot](https://spring.io/projects/spring-boot) with a basic web Demo App. 

### How to Run the App
1) Navigate to the submodule directory and run: `../gradlew bootRun`
   * Alternatively, run it from the root directory: `./gradlew :spring-boot:bootRun`
2) Open your web browser and navigate to http://localhost:8080
3) You should see the pure String output: `"Hello Mai from Kotlin Spring Boot!"`
4) Terminate the app with: Ctrl + C

### How to Build the app
1) Execute the command `../gradlew build`

### How to Test the app
1) Execute the command `../gradlew test`

_Note: this submodule does not have a `build.gradle.kts` file because all its necessary plugins and dependencies are 
shared with and specified in the root `build.gradle.kts` file!_