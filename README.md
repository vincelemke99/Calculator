# Fresenius Formulator

Fresenius Formulator is a web-based calculator that provides an easy-to-use interface for performing various mathematical calculations. This project leverages a JavaScript-based frontend and a Java/Spring Boot backend with a MySQL database to store calculation results.

## Features

- **User-friendly Interface:** A clean and straightforward design for easy operation and result viewing.
- **Backend Calculation Processing:** Server-side processing ensures accurate results and lightens the client-side load.
- **History Tracking:** Enables users to view their most recent calculations.

## Technologies Used

- **Frontend:** HTML, CSS, JavaScript
- **Backend:** Java with Spring Boot
- **Database:** MySQL
- **Libraries:** [exp4j](https://www.objecthunter.net/exp4j/) for expression evaluation

## Installation Guide

### Prerequisites

- IDE capable of handling Java and JavaScript (Recommended: IntelliJ IDEA, Visual Studio Code).
- XAMPP for local server and database management.

### Setup Instructions

1. **Start XAMPP Control Panel:**
   - Launch XAMPP and start the Apache and MySQL modules.

2. **Clone GitHub Repository:**
   - Clone the repository to your local machine. Open the backend project in your IDE and the frontend in a web development editor if necessary.

3. **Manual Library Installation:**
   - Download the `exp4j` library from [ObjectHunter](https://www.objecthunter.net/exp4j/).
   - In your IDE, navigate to the project settings or library configurations.
   - Add the downloaded `exp4j` JAR file to your project's library path.

4. **Configure the Project:**
   - **Backend:** If not using Maven, ensure the `exp4j` library is added manually as described above.
   - **Frontend:** Check and install all necessary dependencies.

## Running the Application

1. **Start the Backend Application:**
   - Run `CalculatorApplication` class to start the Spring Boot server.

2. **Launch the Frontend Web Application:**
   - If configured within the same IDE, start directly. Otherwise, set up a separate server.

3. **Access the Application:**
   - Enter your local host address in your browser.

4. **Functionality Check:**
   - Perform various calculations to ensure proper processing and display by the backend.

## Documentation

### Backend Classes

- **CalculatorApplication:** Initializes and runs the Spring Boot application.
- **CalculatorController:** Manages HTTP requests and communicates with the calculation service.
- **CalculationService:** Service layer interface for calculations.
- **CalculationServiceImplementation:** Implements service logic.
- **CalculationProcessor:** Evaluates mathematical expressions.
- **CalculationResultRepository:** Manages database interactions for storing results.
- **Calculator:** Calculation model with properties for input, output, and timestamp.

### Frontend

- Utilizes HTML and JavaScript for an interactive web interface.
- Main functions include input handling, result display, and history management.
