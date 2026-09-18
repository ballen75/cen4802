# CEN 4802 Java Web Application

## Brianna Allen

## Description

This project is a Java web application originally created for a previous
course, COP3330. The application provides a greeting form that allows a user
to enter a student ID, date information, expected graduation year, and a
message. The application processes the submitted information and displays
the results to the user.

The application also includes validation for required fields and a live
character counter that limits the Message field to 100 characters.

## Build and Testing

The project uses Maven for the automated build process. The command:

mvn package

compiles the application, executes the configured unit tests, and packages
the application into a JAR file.

Unit tests are located under the src/test/java directory and are executed
automatically as part of the Maven build lifecycle.

## Continuous Integration

Jenkins is used as the Continuous Integration (CI) platform for this project.
The Jenkins Pipeline configuration is defined in the Jenkinsfile included
with the project.

The CI Pipeline retrieves the project from the GitHub repository and executes
the existing Maven build process. Jenkins runs the Maven build, executes the
unit tests, packages the application into a JAR file, and archives the
generated artifact.