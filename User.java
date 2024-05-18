import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class User {
    String name;
    String role;
    String department;

    public User(String employee, String position, String expertise) {
        this.name = employee;
        this.role = position;
        this.department = expertise;
    }

    public String getName() {
        return name;
    }

    public String getJob() {
        return role;
    }

    public String getDept() {
        return department;
    }

    public String toString() {
        return name + " : " + role + " : " + department;
    }

    public static ArrayList<User> loadUserData(String fileName) {
        ArrayList<User> answer = new ArrayList<>();
        try (FileReader fr = new FileReader(fileName);
                @SuppressWarnings("deprecation")
                CSVParser csvParser = CSVFormat.DEFAULT
                        .withFirstRecordAsHeader()
                        .parse(fr)) {

            for (CSVRecord record : csvParser) {
                String Name = record.get("Name");
                String Role = record.get("Role");
                String Department = record.get("Department");
                User user = new User(Name, Role, Department);
                answer.add(user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return answer;
    }

    public static boolean employeeVerify(String inputName, String inputJob, String inputDept,
            ArrayList<User> employees) {
        inputName = inputName.toLowerCase();
        inputJob = inputJob.toLowerCase();
        inputDept = inputDept.toLowerCase();

        for (User employee : employees) {
            String name = employee.getName().toLowerCase();
            String dept = employee.getDept().toLowerCase();
            String job = employee.getJob().toLowerCase();
            if (inputName.equals(name) && inputDept.equals(dept) && inputJob.equals(job)) {
                return true;
            }
        }

        return false;

    }

    public static String showMessage(boolean result) {
        if (result == false) {
            return "Failure to login. Please contact administrator";
        }
        return "Success";

    }

    public static boolean canAccessEmployeeData(String deptName) {
        deptName = deptName.toLowerCase();
        ArrayList<String> departments = new ArrayList<>();
        departments.add("human resources");
        departments.add("legal");
        departments.add("executive");
        for (String department : departments) {
            if (department.equals(deptName)) {
                return true;
            }
        }
        return false;
    }

    public static boolean canAccessSalesData(String name) {
        name = name.toLowerCase();
        ArrayList<String> departments = new ArrayList<>();
        departments.add("finance");
        departments.add("business development");
        departments.add("sales and marketing");
        departments.add("executive");
        for (String department : departments) {
            if (name.equals(department)) {
                return true;
            }
        }
        return false;
    }

    public static boolean canAccessLegalData(String name) {
        name = name.toLowerCase();
        ArrayList<String> departments = new ArrayList<>();
        departments.add("legal");
        departments.add("business development");
        departments.add("medical affairs");
        departments.add("executive");
        departments.add("regulatory affairs");
        for (String department : departments) {
            if (name.equals(department)) {
                return true;
            }
        }
        return false;
    }

    public static boolean canAccessVaccineData(String name) {
        name = name.toLowerCase();
        ArrayList<String> departments = new ArrayList<>();
        departments.add("manufacturing");
        departments.add("research and development");
        departments.add("medical affairs");
        departments.add("executive");
        for (String department : departments) {
            if (name.equals(department)) {
                return true;
            }
        }
        return false;
    }

    public static boolean canAccessProductData(String name) {
        name = name.toLowerCase();
        ArrayList<String> departments = new ArrayList<>();
        departments.add("manufacturing");
        departments.add("research and development");
        departments.add("business development");
        departments.add("medical affairs");
        departments.add("sales and manufacturing");
        departments.add("executive");
        departments.add("finance");
        for (String department : departments) {
            if (name.equals(department)) {
                return true;
            }
        }

        return false;
    }

    public static ArrayList<String> whatAccess(String deptName) {
        ArrayList<String> dataAccessList = new ArrayList<>();
        deptName = deptName.toLowerCase();
        if (canAccessProductData(deptName) == true) {
            dataAccessList.add("Product");
        }
        if (canAccessEmployeeData(deptName) == true) {
            dataAccessList.add("Employee");
        }
        if (canAccessLegalData(deptName) == true) {
            dataAccessList.add("Legal");
        }
        if (canAccessSalesData(deptName) == true) {
            dataAccessList.add("Sales");
        }
        if (canAccessVaccineData(deptName) == true) {
            dataAccessList.add("Vaccine");
        }
        return dataAccessList;

    }

    public static void main(String[] args) {
        String fileName = "/Users/god/Desktop/Access Control System/Pharmaceutical_Company_Employees.csv";
        ArrayList<User> answer = loadUserData(fileName);
        boolean employeeVerify = employeeVerify("Susan Lee", "Chief Research Officer", "Research and Development",
                answer);
        System.out.println(employeeVerify);

    }

}
