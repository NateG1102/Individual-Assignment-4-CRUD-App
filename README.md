1.
# Task Manager Application

This Task Manager application allows users to track their goals and the individual tasks associated with those goals. It is built using Java, Spring Boot, JPA/Hibernate, MySQL, and Thymeleaf.

## Dependencies

- Java 11 or higher
- Maven
- MySQL
- Spring Boot Starter Web
- Spring Boot Starter Data JPA
- Spring Boot Starter Thymeleaf
- Spring Boot Starter Validation

  2. Set Up the Database
Create a MySQL database named 340-test:

sql
Copy code
CREATE DATABASE 340-test;
You MUST have the database up and running before running the project!

Open your XAMPP Control Panel:

Start the Apache server.
Start MySQL.
Click on MySQL "Admin" to open up the DBMS.
Ensure the database that you need is available.

3. Configure the Application
Update the src/main/resources/application.properties file with your MySQL database credentials:

spring.datasource.url=jdbc:mysql://localhost:3306/340-test
spring.datasource.username=<your-username>
spring.datasource.password=<your-password>
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.thymeleaf.cache=false

4. Build and Run the Application
Use Maven to build and run the application:

bash:
mvn clean install
mvn spring-boot:run


5. Access the Application
Open your web browser and navigate to http://localhost:8080.
