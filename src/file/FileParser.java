package file;

import employee.Employee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.*;
import java.util.Set;

public class FileParser {
    private final Set<Employee> employees;

    public FileParser() {
        this.employees = new HashSet<>();
    }

    public Set<Employee> retrieveDataFromFile(String file) {
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = in.readLine()) != null && !line.isEmpty()) {
                getEmployee(line);
            }
        } catch (IOException e) {
            System.out.println("Retrieving data failed." + e.getMessage());
        }
        return this.employees;
    }

    private void getEmployee(String line) {
        String[] pair = line.split("(, |,| |;)");
        try {
            int employeeID = Integer.parseInt(pair[0]);
            int projectID = Integer.parseInt(pair[1]);
            LocalDate dateFrom = parseLocalDate(pair[2]);
            String date = pair[3];
            LocalDate dateTo;
            if (date.equalsIgnoreCase("null")) {
                dateTo = null;
            } else {
                dateTo = parseLocalDate(date);
            }
            for (Employee e : this.employees) {
                if (e.getEmployeeID() == employeeID) {
                    if (!e.getProjectWorkingPeriod().containsKey(projectID)) {
                        e.addProject(projectID, dateFrom, dateTo);
                    } else {
                        System.out.println("Already worked at this project!");
                    }
                    return;
                }
            }
            Employee employee = new Employee(employeeID, projectID, dateFrom, dateTo);
            this.employees.add(employee);
        } catch (DateTimeParseException e) {
            System.out.println("Date parsing was not successful. Reason - " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("ID parsing was not successful. Reason - " + e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Data for employee was not full. Reason - " + e.getMessage());
        }
    }

    public LocalDate parseLocalDate(String date) {
        LocalDate localDate;
        DateTimeFormatter parser = DateTimeFormatter.ofPattern(
                "[yyyy-MM-dd][yyyy/MM/dd][yyyy.MM.dd]"
                        + "[yyyy-dd-MM][yyyy/dd/MM][yyyy.dd.MM]"
                        + "[MM-dd-yyyy][MM/dd/yyyy][MM.dd.yyyy]"
        );
        localDate = LocalDate.parse(date, parser);
        return localDate;
    }
}