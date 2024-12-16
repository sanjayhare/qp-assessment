Key Features and Components

1. Main Application

File: GroceryAppApplication.java

Description:

Serves as the entry point of the application.

Configures and initializes the Spring Boot framework.

2. Controllers

These classes handle incoming HTTP requests and route them to the appropriate services.

Files:

AuthController.java: Manages user authentication and token generation.

CartController.java: Handles cart-related operations, such as adding/removing items.

OrderController.java: Manages user orders and order history.

ProductController.java: Provides APIs to fetch and manage product listings.

UserController.java: Handles user account operations.

3. Entities

Entities represent the database tables and include attributes for each table column.

Files:

BaseEntity.java: A base class for common attributes like createdAt and updatedAt.

Cart.java: Represents a shopping cart.

CartItem.java: Represents individual items in a cart.

Order.java: Represents a customer order.

OrderItem.java: Represents items within an order.

Product.java: Represents the product catalog.

Users.java: Represents registered users of the application.

4. Services

Services implement the core business logic of the application.

Files:

CartService.java: Manages cart-related operations.

OrderService.java: Handles order placement and retrieval.

ProductService.java: Provides product-related functionality.

UserServiceImpl.java: Implements user-related operations.

ExcelReaderService.java: Handles Excel-based data imports (possibly for bulk product uploads).

5. Repositories

Repositories are interfaces used to access the database using Spring Data JPA.

Files:

CartRepository.java

CartItemRepository.java

OrderRepository.java

OrderItemRepository.java

ProductRepository.java

UserRepository.java

6. Security

The project includes configurations and utilities to ensure secure user authentication and authorization.

Files:

ProjectSecuirtyConfig.java: Configures security policies.

UserAuthFilter.java: Implements custom authentication logic.

JwtUtil.java: Provides methods to generate and validate JSON Web Tokens (JWTs).

7. Custom Exceptions

Custom exceptions improve error handling.

Files:

CustomerAlreadyExistsException.java

ResourceNotFoundException.java

GroceryMessegeException.java

GlobalExceptionHandler.java: A global handler for API errors.

8. Utilities and Mappers

Files:

JwtUtil.java: Manages JWT tokens.

UsersMapper.java and UsersListMapper.java: Map entity objects to DTOs for data transfer.

9. Testing

File: GroceryAppApplicationTests.java

Description: Contains basic test cases to validate core functionality of the application.

10. Documentation

File: HELP.md

Description: Provides basic instructions or guidance on setting up or running the project.

Project Architecture

The Grocery App follows a layered architecture:

Controller Layer: Handles HTTP requests and responses.

Service Layer: Implements business logic.

Repository Layer: Interfaces with the database.

Entity Layer: Maps database tables to Java objects.

Security Integration

The app uses Spring Security with JWT for secure authentication.

Features:

User credentials are verified via UserAuthentication and JwtUtil.

APIs are protected by authentication filters (UserAuthFilter.java).

