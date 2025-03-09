# Job Recruitment Application
This is the job recruitment application built using spring boot for IV1201.
This web application allows users to register, login and create a job application. While admins can manage the job applications.

## How to run the application locally

### Prerequisites 
- Maven
- Java
- PostgreSQL

### Setup
1. Create a database in PostgreSQL and use this script to fill the database with initial data.
2. Update the `IV1201\Recruitment\src\main\resources\application.properties` file with your PostgreSQL credentials.
3. Open cmd and change directory to the Recruitment directory with ```cd IV1201\Recruitment```
4. Build the project with ```mvn clean install```
5. Run the project type ```mvn spring-boot:run```
6. Navigate to ```localhost:8080``` in a browser to access the application.

## Application details

### Software and plugins used
- PostgreSQL
- Maven
- Thymeleaf
- Spring Boot

### Code structure

#### Controller
- **PersonController**: Reponsible for handling HTTP requests and serving corresponding views as well as handling form submissions for login and registration. Also handles authorization and page redirections.

#### dto
- **LoginDTO**: Responsible for handling the data transfer objects regarding the users login details, i.e username and password.
- **RegisterDTO**: Handles the data transfer objects that deal with the data the user need to enter when creating an account. Besides username and password, the RegisterDTO contains 
the user's email, name, surname, personal number and role ID (applicant or employee).

#### error
- **ErrorHandling**: ErrorHandling contains methods to display error messages whenever a user enters a faulty detail such as an invalid email.

#### exceptions
- **IllegalRegistrationException**: This class will catch exceptions and re-direct the user to correct view with a relevant error message.

#### Model
- **Availability**: The Availability class is used to allow the applicant to state when they're available to work, from- & to date. It is linked to a person through their unique ID.
- **CompetenceProfile**: This class is for the job applicant to state what sort of work they'd be able to do. The "competence_id" is a number between 1-3, where 1 = ticket sales, 2 = lotteries, 3 = roller coster operation
- **Person**: The person class in the model contains important information about the user, admin or applicant. This holds their personal information such as username, email, competenceProfile etc. 

#### Service
- **PersonService**: Handles business logic.

#### Repository
- **PersonRepositry**: Handles calls to the database.

#### Security
- **SecurityConfig**: Uses Spring Security to configure the security settings for the application

