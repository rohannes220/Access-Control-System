# Pharma Company Access Control System

This repository contains a Java-based access control system designed to protect sensitive data for a pharmaceutical company. The system manages access to data related to vaccines, sales, legal matters, employee information, and more. The access control system ensures that only authorized personnel can view specific datasets based on their department and role within the company.

## Project Description

The access control system includes three main classes: `User`, `GUI`, and `Employee`. These classes work together to authenticate users and grant them access to appropriate datasets based on their department and security clearance.

### Classes

1. **User Class**
   - The `User` class verifies the user's credentials and determines their access level based on their department and role.
   - It checks the user's details against a list of current employees to ensure that the user is a legitimate member of the company.
   - The class ensures that all entered details (name, department, role, security questions) match the records.

2. **Login Class**
   - The `Login` class is the login page that users interact with.
   - Users enter their name, role, department, and answer security questions related to their salary, birth city, and country.
   - The page facilitates the authentication process and displays the appropriate datasets based on the user's access level.

3. **Employee Class**
   - The `Employee` class manages basic employee data and provides methods to retrieve details such as the number of foreign-born employees.
   - It supports the `User` class in verifying user credentials and determining access levels.
  

## Data Access

- **Medical Team:** Access to vaccine data.
- **Sales Team:** Access to sales data.
- **Legal Team:** Access to legal data.
- **Other Departments:** Access is granted based on specific departmental needs and security clearance.

## Usage

1. **Clone the repository:**
    ```bash
    git clone https://github.com/your-username/your-repository.git
    ```
2. **Open the project in your preferred Java development environment:**
    Open the cloned repository folder in an IDE like IntelliJ IDEA, Eclipse, or NetBeans.

3. **Compile and run the GUI class to start the application:**
    - The `GUI` class serves as the entry point for the application.
    - Users will be prompted to enter their credentials and security information through the interface.

## Example Code

Here's an example of how the `User` class might be used:

```java
public class Main {
    public static void main(String[] args) {
        // Initialize Page
        Login page = new Login();
        gui.displayLoginPage();
        
        // Sample user authentication
        User user = new User("John Doe", "Medical", "Researcher", "100000", "New York", "USA");
        if (user.authenticate()) {
            System.out.println("Access Granted");
            // Display appropriate data based on user's department and role
        } else {
            System.out.println("Access Denied");
        }
    }
}

Contributing
Feel free to fork this repository and contribute by submitting pull requests. Please ensure that your contributions are well-documented and tested.

License
This project is licensed under the MIT License.

Contact
If you have any questions or suggestions, feel free to reach out:

Email: rohankumar.id@gmail.com
GitHub: rohannes220
