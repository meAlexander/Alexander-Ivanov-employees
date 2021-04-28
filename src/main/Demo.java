package main;

import employee.Employee;
import employee.TeamsStats;
import file.FileParser;

import java.util.Set;

public class Demo {
    public static void main(String[] args) {
        FileParser fileParser = new FileParser();
        Set<Employee> employees = fileParser.retrieveDataFromFile("employees.txt");

        TeamsStats stats = new TeamsStats();
        stats.createTeam(employees);
        //stats.getAllWorkingTeams();

        System.out.println("----------Team worked together for most time-----------");
        stats.getTeamWithTheMostWorkedTime();
    }
}