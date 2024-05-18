import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class Employee {
    String name;
    Integer salary;
    String city;
    String country;
    Integer year;

    public Employee(String fullName, int pay, String birthCity, String birthCountry, int hiredYear) {
        this.name = fullName;
        this.salary = pay;
        this.city = birthCity;
        this.country = birthCountry;
        this.year = hiredYear;
    }

    public String getName() {
        return name;
    }

    public Integer getPay() {
        return salary;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public Integer getYear() {
        return year;
    }

    public String toString() {
        return name + " : " + salary + " : " + city + " : " + country + " : " + year;
    }

    public static ArrayList<Employee> loadEmployeeData(String fileName) {
        ArrayList<Employee> answer = new ArrayList<>();
        try (FileReader fr = new FileReader(fileName);
                @SuppressWarnings("deprecation")
                CSVParser csvParser = CSVFormat.DEFAULT
                        .withFirstRecordAsHeader()
                        .parse(fr)) {

            for (CSVRecord record : csvParser) {
                String name = record.get("Name");
                Integer salary = Integer.parseInt(record.get("Salary"));
                String city = record.get("Birth City");
                String country = record.get("Birth Country");
                Integer year = Integer.parseInt(record.get("Year Employed"));
                Employee e = new Employee(name, salary, city, country, year);
                answer.add(e);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return answer;
    }

    public static HashMap<String, Integer> howManyForeignAndNativeBorn(ArrayList<Employee> input) {
        HashMap<String, Integer> answer = new HashMap<>();
        String key = "American Born";
        String key2 = "Foreign Born";
        int foreignBorn = 0;
        int americanBorn = 0;
        for (Employee employee : input) {
            String country = employee.getCountry();
            if (!country.equals("USA")) {
                foreignBorn += 1;
            } else {
                americanBorn += 1;
            }
        }
        answer.put(key, americanBorn);
        answer.put(key2, foreignBorn);
        return answer;
    }

    public static int averageSalary(ArrayList<Employee> input) {
        int totalSalary = 0;
        int totalCount = 0;
        for (Employee employee : input) {
            Integer salary = employee.getPay();
            totalCount += 1;
            totalSalary += salary;
        }

        return totalSalary / totalCount;

    }

    private static int howManyYears(ArrayList<Employee> input, String name) {
        name = name.toLowerCase();
        for (Employee worker : input) {
            String currentName = worker.getName().toLowerCase();
            if (currentName.equals(name)) {
                int year = worker.getYear();
                int totalYears = 2024 - year;
                return totalYears;
            }
        }
        return 0;
    }

    public static int averageEmployedYears(ArrayList<Employee> employees) {
        int totalEmployees = 0;
        int totalYears = 0;
        for (Employee employee : employees) {
            String currentEmployee = employee.getName();
            int years = howManyYears(employees, currentEmployee);
            totalEmployees += 1;
            totalYears += years;
        }
        return totalYears / totalEmployees;
    }

    public static int howManyEarnAboveAverage(ArrayList<Employee> employees) {
        int average = averageSalary(employees);
        int total = 0;
        for (Employee employee : employees) {
            int salary = employee.getPay();
            if (salary < average) {
                total += 1;
            }
        }
        return total;
    }

    public static int howManyEarnBelowAverage(ArrayList<Employee> employees) {
        int average = averageSalary(employees);
        int total = 0;
        for (Employee employee : employees) {
            int salary = employee.getPay();
            if (salary > average) {
                total += 1;
            }
        }
        return total;
    }

    public static int howManyWereHiredAfterAYear(int year, ArrayList<Employee> employees) {
        int count = 0;
        for (Employee employee : employees) {
            int currentYear = employee.getYear();
            if (currentYear > year) {
                count += 1;
            }
        }
        return count;
    }

    public static int howManyFromACity(String city, ArrayList<Employee> employees) {
        int count = 0;
        for (Employee employee : employees) {
            String currentCity = employee.getCity();
            if (currentCity.equals(city)) {
                count += 1;
            }
        }
        return count;
    }

    public static int howManyFromACountry(String country, ArrayList<Employee> employees) {
        int count = 0;
        for (Employee employee : employees) {
            String countryofBirth = employee.getCountry();
            if (country.equals(countryofBirth)) {
                count += 1;
            }
        }
        return count;
    }

    public static boolean areSecurityQuestionsCorrect(String inputName, String inputCity, String inputCountry,
            int inputSalary,
            ArrayList<Employee> answer) {
        inputCity = inputCity.toLowerCase();
        inputCountry = inputCountry.toLowerCase();
        inputName = inputName.toLowerCase();
        for (Employee employee : answer) {
            String name = employee.getName().toLowerCase();
            String city = employee.getCity().toLowerCase();
            String country = employee.getCountry().toLowerCase();
            int salary = employee.getPay();
            if (name.equals(inputName) && city.equals(inputCity) && country.equals(inputCountry)
                    && salary == inputSalary) {
                return true;
            }
        }
        return false;

    }

    public static void main(String[] args) {
        String fileName = "/Users/god/Desktop/Access Control System/Confidential /Employee_Data.csv";
        ArrayList<Employee> answer = loadEmployeeData(fileName);
        String name = "Susan Lee";
        String birthCountry = "USA";
        String birthCity = "Los Angeles";
        int salary = 151265;
        boolean penis = areSecurityQuestionsCorrect(name, birthCity, birthCountry, salary, answer);
        System.out.println(penis);
    }

}
