package employee;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

public class TeamsStats {
    private final TreeSet<Team> workingTime;

    public TeamsStats() {
        this.workingTime = new TreeSet<>();
    }

    public void createTeam(Set<Employee> allEmployees) {
        ArrayList<Employee> employees = new ArrayList<>(allEmployees);
        //employees.forEach(Employee::printInfo);

        for (int i = 0; i < employees.size(); i++) {
            for (int j = i + 1; j < employees.size(); j++) {
                checkForMainProjects(employees.get(i), employees.get(j));
            }
        }
    }

    private void checkForMainProjects(Employee employee1, Employee employee2) {
        Set<Integer> employee1ProjectIDs = employee1.getProjectWorkingPeriod().keySet();
        Set<Integer> employee2ProjectIDs = employee2.getProjectWorkingPeriod().keySet();
        for (Integer employee1ProjectID : employee1ProjectIDs) {
            for (Integer employee2ProjectID : employee2ProjectIDs) {
                if (employee1ProjectID.equals(employee2ProjectID)) {
                    Team team = new Team(employee1.getEmployeeID(), employee2.getEmployeeID(), employee1ProjectID);
                    team.calculateWorkingPeriod(
                            employee1.getProjectWorkingPeriod().get(employee1ProjectID),
                            employee2.getProjectWorkingPeriod().get(employee2ProjectID)
                    );
                    this.workingTime.add(team);
                }
            }
        }
    }

    public void getAllWorkingTeams() {
        this.workingTime.forEach((team) -> System.out.println(team.toString()));
    }

    public void getTeamWithTheMostWorkedTime() {
        ArrayList<Team> teams = new ArrayList<>(this.workingTime);
        //get first team with most working days(they are already order from the most to the least)
        //and check if next team have same working days and so on
        //max days
        long max = teams.get(0).getDays();
        for (int i = 0; i < teams.size() - 1; i++) {
            System.out.println(teams.get(i).toString());
            if (max == teams.get(i + 1).getDays()) {
                continue;
            }
            break;
        }
    }
}