# CEN 4802 Java Web Application

## Brianna Allen

## Description

This project is a Java web application originally created for a previous course, COP3330. The application provides a greeting form that allows a user to enter a student ID, date information, expected graduation year, and a message. The application processes the submitted information and displays the results to the user.

The application also includes validation for required fields, a live character counter that limits the Message field to 100 characters, and a graduation countdown feature that calculates the number of years remaining until the expected graduation year.

## Build and Testing

The project uses Maven for the automated build process. The command:

```bash
mvn clean package
```

compiles the application, executes the configured unit tests, and packages the application into a JAR file.

Unit tests are located under the `src/test/java` directory and include testing of the graduation countdown feature.

## Docker Environment

Docker Desktop is used to provide the containerized environment for Jenkins. The setup was completed using the [official Jenkins Docker installation documentation](https://www.jenkins.io/doc/book/installing/docker/).

The environment includes:

- `jenkins-blueocean` – Runs the Jenkins server.
- `jenkins-docker` – Provides the Docker engine used by Jenkins to build images and run containers.
- `graduation-port-forward` – Allows the application running inside Docker-in-Docker to be accessed through a browser.

The Jenkins environment uses Linux-based containers and a Docker network named `jenkins`.

## Continuous Integration

Jenkins is used as the Continuous Integration (CI) platform for this project. The pipeline configuration is defined in the `Jenkinsfile` included with the project.

The pipeline retrieves the application from GitHub and uses Maven to compile the application, execute the unit tests, and package the application into a JAR file.

For the midterm project, I updated the Jenkinsfile to include an additional stage that builds a Docker image after the Maven build and tests complete successfully.

The Docker image is created using the project's `Dockerfile` and tagged:

`graduation-web-app:latest`

The Jenkins pipeline also archives the generated JAR file.

## Running the Application with Docker

After Jenkins successfully builds the Docker image, the application can be started in a container using PowerShell:

```powershell
docker exec jenkins-blueocean docker run -d `
  --name graduation-web-app `
  --restart unless-stopped `
  -p 8081:8080 `
  graduation-web-app:latest
```

Because the application runs inside Docker-in-Docker, a separate port-forwarding container is used to access it from the Windows host.

The port-forwarding container uses the `alpine/socat` image and forwards port `8082` to port `8081` on `jenkins-docker`.

To create the port-forwarding container:

```powershell
docker run -d `
  --name graduation-port-forward `
  --network jenkins `
  -p 8082:8082 `
  alpine/socat `
  TCP-LISTEN:8082,fork,reuseaddr TCP:jenkins-docker:8081
```

Once the application and port-forwarding containers are running, the application can be accessed at:

**http://localhost:8082/greeting**

The application displays the submitted greeting information and the calculated years remaining until graduation.


## Application Logging

The application uses SLF4J and Logback, which are included with Spring Boot, to record application activity and help identify potential issues.

Logging is implemented in `GreetingController.java` and includes the following levels:

- **INFO** – Records when the greeting form is accessed, submitted, or processed successfully.
- **DEBUG** – Records additional details about form processing, including creating a new Greeting object and calculating the years remaining until graduation.
- **WARN** – Records when a user submits the form with missing required information.
- **ERROR** – Records an exception when the expected graduation year is entered as 2099.

### Logging Configuration

The logging configuration is located in `src/main/resources/application.properties`.

The application is currently configured to use DEBUG logging for the application package, allowing DEBUG, INFO, WARN, and ERROR messages to be recorded.

The logging level can be changed to control how much information is recorded. For example, changing the level from DEBUG to INFO filters out DEBUG messages while continuing to record INFO, WARN, and ERROR messages.

### Viewing Application Logs

Application logs can be viewed in the IntelliJ IDEA Run console while the application is running.

The console output can also be saved to a text file for reviewing and analyzing recorded events. PowerShell can be used to count log entries and filter logs for specific messages or logging levels.

### Form Validation

The greeting form uses server-side validation in `GreetingController.java` to check for missing required fields.

The HTML form includes the `novalidate` attribute, allowing incomplete submissions to reach the Java controller. When required information is missing, the application displays an error message and records a WARN log.
