# CEN 4802 Java Web Application

## Brianna Allen

## Description

This project is a Java web application originally created for a previous course, COP3330. The application provides a greeting form that allows a user to enter a student ID, date information, expected graduation year, and a message. The application processes the submitted information and displays the results to the user.

The application also includes validation for required fields and a live character counter that limits the Message field to 100 characters.

## Build and Testing

The project uses Maven for the automated build process. The command:

```bash
mvn package
```

compiles the application, executes the configured unit tests, and packages the application into a JAR file.

Unit tests are located under the `src/test/java` directory and are executed automatically as part of the Maven build lifecycle.

## Docker Environment

Docker Desktop is used to provide the containerized environment for the Jenkins CI server. The project uses Linux-based Docker containers and a dedicated Docker bridge network named `jenkins`.

The Jenkins environment consists of two containers:

- `jenkins-blueocean` – Runs the Jenkins server and provides access to the Jenkins web interface.
- `jenkins-docker` – Runs Docker-in-Docker (DinD) to provide Docker functionality within the Jenkins environment.

A custom Jenkins Docker image, `myjenkins-blueocean:2.568.3-1`, was created using a Dockerfile based on the Jenkins JDK 21 image. The custom image includes the Docker CLI and the Jenkins plugins required for the CI environment.

The Jenkins web interface is exposed on port `8080`, and port `50000` is used for Jenkins agent communication.

## Continuous Integration

Jenkins is used as the Continuous Integration (CI) platform for this project. The Jenkins Pipeline configuration is defined in the `Jenkinsfile` included with the project.

The CI Pipeline retrieves the project from the GitHub repository and executes the existing Maven build process. Jenkins runs the Maven build, executes the unit tests, packages the application into a JAR file, and archives the generated artifact.

The pipeline is automatically defined by the project's `Jenkinsfile`, which uses the configured Maven installation and executes:

```bash
mvn package
```

A successful pipeline confirms that the application compiles correctly, all configured unit tests pass, and the deployable JAR artifact is successfully generated.