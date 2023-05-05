package hw_2023_04_24__2_10.service;

import hw_2023_04_24__2_10.domain.Employee;
import hw_2023_04_24__2_10.exception.EmployeeAlreadyAddedException;
import hw_2023_04_24__2_10.exception.EmployeeNotFoundException;
import hw_2023_04_24__2_10.exception.EmployeeStorageIsFullException;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.*;

@Service
public class EmployeeService {

    private static final int SIZE = 5;
    private final List<Employee> employees = new ArrayList<>(SIZE);

    private final ValidatorService validatorService; // подключаем ValidatorService

    public EmployeeService(ValidatorService validatorService) {
        this.validatorService = validatorService;
    }


    public Employee add(String name, String surname, int department, int salary) {
//        if(!validateName(name)){
//            throw new IncorrectNameException();
//        }
//        if(!validateSurname(surname)){
//            throw new IncorrectSurnameException();
//        }

        Employee employee = new Employee(
                validatorService.validateName(name),
                validatorService.validateSurname(surname),
                department,
                salary);
        if (employees.size() < SIZE) {
            for (Employee emp : employees) {
                if (emp.equals(employee)) {
                    throw new EmployeeAlreadyAddedException(employee);
                }
            }
            employees.add(employee);
            return employee;
        }
        throw new EmployeeStorageIsFullException(employee);
    }

    public Employee find(String name, String surname, int department, int salary) {
        Employee employee = new Employee(name, surname, department, salary);
        if (employees.contains(employee)) {
            return employee;
        }
        throw new EmployeeNotFoundException(employee);
    }

    public Employee remove(String name, String surname, int department, int salary) {
        Employee employee = new Employee(name, surname, department, salary);
        if (employees.remove(employee)) {
            return employee;
        }
        throw new EmployeeNotFoundException(employee);
    }

    public List<Employee> list(){
        return Collections.unmodifiableList(employees);
    }
    public List<Employee> getAll(){
        return new ArrayList<>(employees);
    }

//    private boolean validateName(String name){
//        return isAlpha(name);
//    }
//
//    private boolean validateSurname(String surname){
//        return isAlpha(surname);
//    }
}


