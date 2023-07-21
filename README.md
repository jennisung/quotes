# Lab 08: Quotes

## Overview

In this lab, we have developed a Java application that displays random popular book quotes. The application uses the GSON library to parse a `.json` file and displays a random quote along with the author each time it is run.

## Setup

We started by creating a new repository for this lab and named it "quotes". In this repository, we added a `.gitignore` file from the course repository, and then initialized a new Gradle project with the command `gradle init --type java-application`.

## Dependencies

This project uses the following dependencies:

- GSON: A Java library that can be used to convert Java Objects into their JSON representation and vice versa. It can be added to your Gradle project by adding the following line to your `build.gradle` file's dependencies section:

```java
implementation 'com.google.code.gson:gson:2.10.1'
```
## Running the Application

To run the application, follow these steps:

1. Clone the repository to your local machine.
2. Navigate to the root directory of the project in your terminal.
3. Run the command gradle run to start the application.

## Feature Tasks

The main task of this lab was to use the recentquotes.json file to display random popular book quotes. Our application has the following features:

1. Parse JSON: Our program uses the GSON library to parse the recentquotes.json file. This file contains a collection of quotes from popular books.
2. Display Quote: The application displays a random quote along with the author each time it is run. The quote and the author are extracted from the parsed JSON data.

## Testing

We used JUnit to write tests for our application. We have tests that verify that our application correctly parses the JSON data and correctly displays a random quote.

To run the tests, navigate to the root directory of the project in your terminal and run the command ```./gradlew test```.


# Lab 09: Quotes API
## Testing
We used JUnit to write tests for our application. We have tests that verify that our application correctly parses the JSON data and correctly displays a random quote.

To run the tests, navigate to the root directory of the project in your terminal and run the command ```./gradlew test```.



### Collaborators
- Jennifer Sung
- Dylan Cooper
- Davey Oswald
