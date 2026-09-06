# Greeting Form Application

## Overview

This project is a Spring Boot web application that allows a user to enter a student ID, date, expected graduation year, and message through a web form. After the form is submitted, the application displays the entered information on a results page.

The project uses Maven to automate dependency management, compilation, testing, and application packaging.

## Technologies

* Java 17
* Spring Boot
* Thymeleaf
* Maven
* HTML/CSS

## Project Structure

* `src/main/java` - Java application source code
* `src/main/resources` - Thymeleaf templates and application resources
* `src/test/java` - Application test source code
* `pom.xml` - Maven project and build configuration
* `.mvn` - Maven Wrapper configuration
* `mvnw` / `mvnw.cmd` - Maven Wrapper scripts

## Building the Application

From the root directory of the project, build the application with Maven:

`mvn clean package`

On Windows, the Maven Wrapper can also be used:

`.\mvnw.cmd clean package`

A successful build generates the application JAR in the `target` directory.

## Running the Application

After a successful build, run the generated JAR from the command line:

`java -jar target/handling-form-submission-0.0.1-SNAPSHOT.jar`

Once the Spring Boot server has started, access the greeting form in a web browser at:

`http://localhost:8080/greeting`

## Testing

The project includes a Spring Boot context test. Maven automatically executes the test as part of the standard build process.

## Build Artifact

The Maven build generates:

`target/handling-form-submission-0.0.1-SNAPSHOT.jar`

The `target` directory contains generated build output and should not be included with the submitted source project.

## Modification Demonstrated

As part of the automated build exercise, the application was modified to include an Expected Graduation Year field. The `Greeting` model, form template, and results template were updated to process and display the new value. The application was then rebuilt and executed from the newly generated JAR to verify the modification.
