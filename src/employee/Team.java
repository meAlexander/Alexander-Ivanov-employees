package employee;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Team implements Comparable<Team> {
    private final int employeeID1;
    private final int employeeID2;
    private final int projectID;
    private long days;

    public Team(int employeeID1, int employeeID2, int projectID) {
        this.employeeID1 = employeeID1;
        this.employeeID2 = employeeID2;
        this.projectID = projectID;
    }

    public void calculateWorkingPeriod(Period days1, Period days2) {
        LocalDate dateFrom1 = days1.getDateFrom();
        LocalDate dateTo1 = days1.getDateTo();
        if (dateTo1 == null) {
            dateTo1 = LocalDate.now();
        }

        LocalDate dateFrom2 = days2.getDateFrom();
        LocalDate dateTo2 = days2.getDateTo();
        if (dateTo2 == null) {
            dateTo2 = LocalDate.now();
        }

        if (dateFrom1.isEqual(dateFrom2) && dateTo1.isEqual(dateTo2)) {
            this.days = calculateWorkingDays(dateFrom1, dateTo1);
        } else if (dateFrom1.isEqual(dateFrom2) && dateTo1.isAfter(dateTo2)) {
            this.days = calculateWorkingDays(dateFrom1, dateTo2);
        } else if (dateFrom1.isEqual(dateFrom2) && dateTo1.isBefore(dateTo2)) {
            this.days = calculateWorkingDays(dateFrom1, dateTo1);               // dateTo2.isAfter(dateFrom1)
        } else if (dateFrom1.isAfter(dateFrom2) && dateTo1.isAfter(dateTo2) && dateFrom1.isBefore(dateTo2)) {
            this.days = calculateWorkingDays(dateFrom1, dateTo2);
        } else if (dateFrom1.isBefore(dateFrom2) && dateTo1.isBefore(dateTo2) && dateFrom2.isBefore(dateTo1)) {
            this.days = calculateWorkingDays(dateFrom2, dateTo1);               // dateTo1.isAfter(dateFrom1)
        } else if (dateFrom1.isAfter(dateFrom2) && dateTo1.isBefore(dateTo2)) {
            this.days = calculateWorkingDays(dateFrom1, dateTo1);
        } else if (dateFrom1.isBefore(dateFrom2) && dateTo1.isAfter(dateTo2)) {
            this.days = calculateWorkingDays(dateFrom2, dateTo2);
        }
    }

    private long calculateWorkingDays(LocalDate dateFrom, LocalDate dateTo) {
        return ChronoUnit.DAYS.between(dateFrom, dateTo);
    }

    public long getDays() {
        return this.days;
    }

    @Override
    public int compareTo(Team o) {
        if (this.days > o.getDays()) {
            return -1;
        }
        return 1;
    }

    @Override
    public String toString() {
        return "employee.Team{" +
                "employeeID1=" + this.employeeID1 +
                ", employeeID2=" + this.employeeID2 +
                ", projectID=" + this.projectID +
                ", days=" + this.days +
                '}';
    }
}