# online-todo-list
## features considered
1. New user signup
2. JWT authentication
3. Create/Delete TODOs
4. View todo's list
7. Login/Logout feature

# To Run Jar file
Backend : java -jar todo-app-0.0.1-SNAPSHOT.jar

Frontend :
1. Goto frontend/todo-ui
2. RUN: 'npm install' followed by 'ng serve' command

# Frontend
Access todo-app UI : http://localhost:4200

# Swagger UI
Access the swagger UI: http://localhost:8080/swagger-ui.html

#H2 DB
H2 console : http://localhost:8080/h2

# To build jar package
1. Goto backend/todo-app code directory
2. RUN: 'mvn clean install' in terminal/cmd.

## Back End Code Explaination
Backend is developed using Java8 and Spring boot framework. Backend is organised into UserController and TodoController process and is secured by Token(JWT) authentication. Appropriately Model beans, service and repository classes/interfaces have been created. JPARepository interface is used to provide JPA features around the model classes.

H2 with hibernate is configured to store in-memory data.
Similarly, exception handling, utility, web security classes have been configured.

## Front End Code Explaination
Front end code is developed using Angular 7. It includes components like login, logout, signup, view ToDos and Add Todo.
HttpInterceptor has been used to set Authrization header for all requests.

## Things to consider in future
1. Handling exception occur at Spring security layer and sending proper error response.
2. JUnit test cases for each method(Added only for service classes for now).
3. Spring integration testing
4. UI enhancements and angular test cases for each method.
5. Improve UI error handling.
