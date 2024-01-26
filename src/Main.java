import entities.Employee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ClientInfoStatus;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter full file path: ");
        String path = sc.nextLine();

        try(BufferedReader br = new BufferedReader(new FileReader(path))){

            List<Employee> employees = new ArrayList<>();

            String line = br.readLine();
            while (line != null){
                String[] fields = line.split(",");

                employees.add(new Employee(fields[0], fields[1], Double.parseDouble(fields[2])));

                line = br.readLine();
            }

            System.out.print("Enter salary: ");
            Double salary = sc.nextDouble();

            System.out.printf("Email of people whose salary is more than $%.2f:\n", salary);

            // comparator para ordenar por ordem alfabética
            Comparator<String> comp = (s1, s2) -> s1.toUpperCase().compareTo(s2.toUpperCase());

            List<String> emails = employees.stream()
                // filtra os elementos que o salário é maior do que a variável salary
                .filter(e -> e.getSalary() > salary)
                // pega o email desses elementos filtrados
                .map(Employee::getEmail)
                // ordena por ordem alfabética
                .sorted(comp)
                // adiciona à lista
                .toList();

            emails.forEach(System.out::println);

            Double salarySum = employees.stream()
                // filtra os elementos que começam com a letra 'M'
                .filter(e -> e.getName().charAt(0) == 'M')
                // pega o salário destes elementos
                .map(Employee::getSalary)
                // soma o salário desses elementos
                .reduce(0.0, Double::sum);

            System.out.printf("Sum of salary of people whose name starts with 'M': $%.2f", salarySum);


        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
}