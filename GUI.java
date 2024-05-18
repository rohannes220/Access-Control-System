import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class GUI {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(GUI::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Access Control System");
        frame.setSize(1000, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.CYAN);
        frame.add(panel);

        JTextField fullNameField = new JTextField(10);
        fullNameField.setFont(new Font("Arial", Font.PLAIN, 14));
        JLabel nameLabel = new JLabel("Full Name:");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        nameLabel.setLabelFor(fullNameField);

        JTextField fullRoleField = new JTextField(15);
        fullRoleField.setFont(new Font("Arial", Font.PLAIN, 14));
        JLabel roleLabel = new JLabel("Role:");
        roleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        roleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        roleLabel.setLabelFor(fullRoleField);

        JComboBox<String> deptComboBox = new JComboBox<>(new String[] {
                "Human Resources", "Sales and Marketing", "Medical Affairs", "Manufacturing",
                "Quality Assurance", "Regulatory Affairs", "Legal", "Finance", "Executive",
                "Business Development", "Research and Development"
        });
        deptComboBox.setFont(new Font("Arial", Font.PLAIN, 14));
        JLabel deptLabel = new JLabel("Dept:");
        deptLabel.setFont(new Font("Arial", Font.BOLD, 14));
        deptLabel.setHorizontalAlignment(SwingConstants.CENTER);
        deptLabel.setLabelFor(deptComboBox);

        JTextField cityTextField = new JTextField(15);
        cityTextField.setFont(new Font("Arial", Font.PLAIN, 14));
        JLabel cityLabel = new JLabel("Birth City:");
        cityLabel.setFont(new Font("Arial", Font.BOLD, 14));
        cityLabel.setHorizontalAlignment(SwingConstants.CENTER);
        cityLabel.setLabelFor(cityTextField);

        JTextField countryTextField = new JTextField(15);
        countryTextField.setFont(new Font("Arial", Font.PLAIN, 14));
        JLabel countryLabel = new JLabel("Birth Country:");
        countryLabel.setFont(new Font("Arial", Font.BOLD, 14));
        countryLabel.setHorizontalAlignment(SwingConstants.CENTER);
        countryLabel.setLabelFor(countryTextField);

        // Changed to JPasswordField and made visible by default
        JPasswordField salaryTextField = new JPasswordField(15);
        salaryTextField.setFont(new Font("Arial", Font.PLAIN, 14));
        salaryTextField.setEchoChar('*'); // Display asterisks instead of actual characters
        JLabel salaryLabel = new JLabel("Current Salary (no commas):");
        salaryLabel.setFont(new Font("Arial", Font.BOLD, 14));
        salaryLabel.setHorizontalAlignment(SwingConstants.CENTER);
        salaryLabel.setLabelFor(salaryTextField);

        JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        namePanel.setBackground(Color.CYAN);
        namePanel.add(nameLabel);
        namePanel.add(fullNameField);
        namePanel.add(roleLabel);
        namePanel.add(fullRoleField);
        namePanel.add(deptLabel);
        namePanel.add(deptComboBox);
        namePanel.add(cityLabel);
        namePanel.add(cityTextField);
        namePanel.add(countryLabel);
        namePanel.add(countryTextField);
        namePanel.add(salaryLabel);
        namePanel.add(salaryTextField);

        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.addActionListener(e -> {
            String userFile = "/Users/god/Desktop/Access Control System/Pharmaceutical_Company_Employees.csv";
            String employeeFile = "/Users/god/Desktop/Access Control System/Confidential /Employee_Data.csv";
            String selectedDepartment = (String) deptComboBox.getSelectedItem();
            String fullName = fullNameField.getText();
            String city = cityTextField.getText();
            String country = countryTextField.getText();
            int salary = 0;
            try {
                salary = Integer.parseInt(new String(salaryTextField.getPassword()));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter a valid numeric salary.");
                return; // Exit the method if salary isn't a valid integer
            }
            String role = fullRoleField.getText();
            // User u1 = new User(fullName,role, selectedDepartment);
            ArrayList<User> users = User.loadUserData(userFile);
            ArrayList<Employee> answer = Employee.loadEmployeeData(employeeFile);
            boolean isEmployee = User.employeeVerify(fullName, role, selectedDepartment, users);
            boolean areSecurityQuestionsCorrect = Employee.areSecurityQuestionsCorrect(fullName, city, country, salary,
                    answer);
            if (isEmployee && areSecurityQuestionsCorrect) {
                JOptionPane.showMessageDialog(null, "Login Successful!");
                ArrayList<String> departmentStrings = User.whatAccess(selectedDepartment);
                openNewWindow(departmentStrings);
            } else {
                JOptionPane.showMessageDialog(null, "Login Failed! Please check your inputs.");
            }

        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(Color.CYAN);
        buttonPanel.add(loginButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        panel.add(namePanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    public static void openNewWindow(ArrayList<String> whatData) {
        JFrame newFrame = new JFrame("New Window");
        newFrame.setSize(800, 200);
        newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.CYAN);

        // Setup GridBagConstraints
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 10, 0); // top, left, bottom, right padding

        JLabel label = new JLabel("Welcome to the new window!", SwingConstants.CENTER);
        panel.add(label, gbc);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.CYAN);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        for (String data : whatData) {
            JButton button = new JButton(data + " Data");
            button.setFont(new Font("Arial", Font.BOLD, 14));
            buttonPanel.add(button);
            if (data.equals("Vaccine")) {
                button.addActionListener(e -> openVaccineData());
            } else if (data.equals("Product")) {
                button.addActionListener(e -> openProductData());
            } else if (data.equals("Legal")) {
                button.addActionListener(e -> openLegalData());
            } else if (data.equals("Sales")) {
                button.addActionListener(e -> openSalesData());
            } else if (data.equals("Employee")) {
                button.addActionListener(e -> openEmployeeData());
            }
        }

        panel.add(buttonPanel, gbc);

        newFrame.add(panel);
        newFrame.setVisible(true);
    }

    private static void openVaccineData() {
        String fileName = "/System/Volumes/Data/Users/god/Desktop/Access Control System/Confidential /Vaccine_Trials_Data.csv";
        File file = new File(fileName);
        if (!file.exists()) {
            JOptionPane.showMessageDialog(null, "File does not exist: " + file.getAbsolutePath(), "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            Desktop.getDesktop().open(file);
            JOptionPane.showMessageDialog(null, "Data Opened Successfully!", "Vaccine Data",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "An error occurred while opening the file: " + e.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void openProductData() {
        String fileName = "/System/Volumes/Data/Users/god/Desktop/Access Control System/Confidential /Product_Data.csv";
        try {
            File file = new File(fileName);
            if (file.exists()) {
                Desktop.getDesktop().open(file);
            } else {
                JOptionPane.showMessageDialog(null, "File does not exist.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "An error occurred while opening the file.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void openSalesData() {
        String fileName = "/System/Volumes/Data/Users/god/Desktop/Access Control System/Confidential /Sales_Data.csv";
        try {
            File file = new File(fileName);
            if (file.exists()) {
                Desktop.getDesktop().open(file);
            } else {
                JOptionPane.showMessageDialog(null, "File does not exist.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "An error occurred while opening the file.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void openEmployeeData() {
        String fileName = "/System/Volumes/Data/Users/god/Desktop/Access Control System/Confidential /Employee_Data.csv";
        try {
            File file = new File(fileName);
            if (file.exists()) {
                Desktop.getDesktop().open(file);
            } else {
                JOptionPane.showMessageDialog(null, "File does not exist.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "An error occurred while opening the file.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void openLegalData() {
        String fileName = "/System/Volumes/Data/Users/god/Desktop/Access Control System/Confidential /Legal_Data.csv";
        try {
            File file = new File(fileName);
            if (file.exists()) {
                Desktop.getDesktop().open(file);
            } else {
                JOptionPane.showMessageDialog(null, "File does not exist.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "An error occurred while opening the file.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
