package employee;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Objects;

public class Employee {
    private final int employeeID;
    //projectID - workingDays
    private final HashMap<Integer, Period> projectWorkingPeriod;

    public Employee(int employeeID, int projectID, LocalDate dateFrom, LocalDate dateTo) {
        this.employeeID = employeeID;
        this.projectWorkingPeriod = new HashMap<>();
        addProject(projectID, dateFrom, dateTo);
    }

    public void addProject(int projectID, LocalDate dateFrom, LocalDate dateTo) {
        Period period = new Period(dateFrom, dateTo);
        this.projectWorkingPeriod.put(projectID, period);
    }

    public void printInfo() {
        System.out.println("employeeID: " + this.employeeID);
        System.out.println("Working projects period: ");
        this.projectWorkingPeriod.forEach((projectID, period) ->
                System.out.println("projectID: " + projectID
                        + " From: " + period.getDateFrom()
                        + " To: " + period.getDateTo()));
        System.out.println("-----------------");
    }

    public int getEmployeeID() {
        return this.employeeID;
    }

    public HashMap<Integer, Period> getProjectWorkingPeriod() {
        return this.projectWorkingPeriod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return employeeID == employee.employeeID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeID);
    }
}