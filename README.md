<h1 align="center" id="title">Spring Boot Application with MySQL Database</h1>

<p id="description">This is a Spring Boot application that uses MySQL as the database. This README file provides instructions on how to configure and run the application.</p>

<h2>🛠️ Installation Steps:</h2>

<p>1. Update MySQL Database Properties :To configure the MySQL database properties update the application.properties file located in the src/main/resources directory. Update the following properties according to your MySQL database setup:</p>

```
spring.datasource.url=jdbc:mysql://localhost:3306/{your_database_name} spring.datasource.username={your_database_username} spring.datasource.password={your_database_password}
```

<p>2. Run Query After Application Start After the application is started run the following query to insert a default user role into the database:</p>

```
INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO USERS(id,email,password,username) VALUES (100,'test2@yopmail.com','$2a$10$4pi0fcq53BftE3CWSavhmulIuxzVy4yGJvaZTA1gdZS3mxJp5ewFu','admin');
INSERT INTO user_roles (`user_id`, `role_id`) VALUES ('100', '1');
```

<p>3. Running the Application To run the application execute the following command in the project directory:</p>

```
mvn spring-boot:run
```

<p>4. Testing the Endpoints To test the endpoints open a web browser and enter the following URL: bash</p>
<p>Default user to login is</p>

```
{
  "password": "Test@1234",
  "username": "admin"
}
```



```
http://localhost:8081/swagger-ui.html
```

<h2> Test:</h2>


To sign up create a user using the /api/auth/signup endpoint. 
Login and Obtain JWT Token To login and obtain a JWT token use the /api/auth/login endpoint with the user credentials. 

The response will contain the JWT token. Access Secure API To access the secure API pass the JWT token in the Authorization header of the HTTP request to the secure endpoint. 
The header should be in the following format:
```
Authorization: Bearer {your_JWT_token}

```
