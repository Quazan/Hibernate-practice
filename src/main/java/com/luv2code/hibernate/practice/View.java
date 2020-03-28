package com.luv2code.hibernate.practice;

import com.luv2code.hibernate.practice.entity.Employee;

import java.util.List;
import java.util.Scanner;

public class View {

    private Controller controller;

    private Scanner scanner;

    public View(Controller controller) {
        this.controller = controller;
        this.scanner = new Scanner(System.in);
    }

    public void run() {

        while(true){
            System.out.println("Main menu");
            System.out.println("1\tCreate new Employee");
            System.out.println("2\tGet Employee by ID");
            System.out.println("3\tFind Employees by Company");
            System.out.println("4\tDelete Employee by ID");
            System.out.println("\n\n0\t Exit");

            int s = scanner.nextInt();

            switch (s) {
                case 1: {
                    createEmployeeMenu();
                    break;
                }
                case 2: {
                    getEmployeeByIdMenu();
                    break;
                }
                case 3: {
                    findEmployeeByCompanyMenu();
                    break;
                }
                case 4: {
                    deleteEmployeeByIdMenu();
                    break;
                }
                case 0: {
                    System.exit(0);
                }
                default:{
                    System.out.println("Wrong option");
                }
            }
        }
    }

    private void deleteEmployeeByIdMenu() {
        System.out.println("Enter Employee id");
        int s = scanner.nextInt();
        try {
            controller.deleteEmployeeById(s);
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private void findEmployeeByCompanyMenu() {
        scanner.nextLine();
        System.out.println("Enter company name");
        String s = scanner.nextLine();
        try {
            List<Employee> employees = controller.getEmployeeByCompany(s);
            for (Employee emp : employees) {
                System.out.println(emp);
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private void getEmployeeByIdMenu() {
        System.out.println("Enter Employee id");
        int s = scanner.nextInt();
        try {
            Employee employee = controller.getEmployeeById(s);
            System.out.println(employee);
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private void createEmployeeMenu() {
        scanner.nextLine();
        Employee employee = new Employee();
        System.out.println("Enter first name");
        employee.setFirstName(scanner.nextLine());
        System.out.println("Enter last name");
        employee.setLastName(scanner.nextLine());
        System.out.println("Enter Company");
        employee.setCompany(scanner.nextLine());

        System.out.println("\n\nDo you want to add employee to db " + employee);
        System.out.println("yes/no");
        String s = scanner.nextLine();

        if(s.equals("yes")){
            controller.addEmployee(employee);
        }
    }
}
