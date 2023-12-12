# Portfolio project IDATT1003

STUDENT NAME = "Nikolai Tandberg"  
STUDENT ID = "10107"

## Project description
This project is a simplified train dispatch system for a single station, used to view, add, and modify trains and their properties.

## Project structure
The project uses maven package structure, with three packages: models, utils and view. </br>
Source files are stored in src/main/java, test files are stored in src/test/java.

## Link to repository
Link to gitlab repository: https://gitlab.stud.idi.ntnu.no/nikoltan/portfolio-project-idatt1003

## How to run the project
1. make sure you have apache-maven and java SDK 17.0 installed
2. open a terminal
3. Navigate to the project folder
4. to package and compile the project run the following command:
```bash
mvn compile
```
5. then execute the jar file:
```bash
mvn java -jar target/TrainDispatchSystem-1.0-SNAPSHOT.jar
```
## How to run the tests
follow steps 1-3 from "How to run the project" </br>
then run the following command:
```bash
mvn test
```
