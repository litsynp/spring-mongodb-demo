# Spring MongoDB Demo

## About

This project introduces ways to work with [MongoDB](https://www.mongodb.com/) on Spring (Boot) MVC applications.

This project currently provides 2 APIs to handle "post" resource, each with different method of using MongoDB.

- Method 1: `MongoTemplate` (package: `com.litsynp.ex1mongotemplate`)
- Method 2: `MongoRepository` (package: `com.litsynp.ex2mongorepo`)

## Versions and Dependencies

- Java 17
- Gradle 7
- Spring Boot Starter Web
- Spring Boot Starter MongoDB
- Spring Boot Starter Validation
- Spring Boot Starter Test
- Spring Boot Configuration Processor
- [Flapdoodle's Embedded MongoDB](https://github.com/flapdoodle-oss/de.flapdoodle.embed.mongo)
- Project Lombok

## How to Run

1. Navigate to docker directory.
   ```sh
   $ cd docker
   ```

2. Grant permissions to execute shell scripts. (Assuming you are on a Mac or a Linux machine.)
   ```sh
   $ chmod 700 *.sh
   ```

3. Create MongoDB container using Docker.
   ```sh
   $ ./run-mongodb.sh
   ```

    - If you want to stop the server and remove the container, run this:
      ```sh
      $ ./stop-mongodb.sh
      ```

4. Access MongoDB shell to play with MongoDB.
   ```sh
   $ ./exec-mongodb.sh
   ```

5. Run the Spring application with Gradle to play with APIs.
   ```sh
   $ ./gradlew bootRun
   ```
