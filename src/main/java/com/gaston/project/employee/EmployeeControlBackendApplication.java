package com.gaston.project.employee;

import com.gaston.project.employee.entities.EmployeeEntity;
import com.gaston.project.employee.repository.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class EmployeeControlBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeControlBackendApplication.class, args);
	}

	//Inicializo la base de datos con 2 empleados
	@Bean
	CommandLineRunner init(EmployeeRepository employeeRepository){

        return args -> {
            if(employeeRepository.count() == 0){  //Para que se ejecute solo una vez
                EmployeeEntity employeeGaston = EmployeeEntity.builder()
                        .name("Gaston Guzman")
                        .email("gastonezguzman@gmail.com")
                        .phone("1234-5678")
                        .department("Web Developer")
                        .build();

                EmployeeEntity employeeFenix = EmployeeEntity.builder()
                        .name("Fenix")
                        .email("fenixn@gmail.com")
                        .phone("9876-54321")
                        .department("Human Resources")
                        .build();

                employeeRepository.saveAll(List.of(employeeGaston, employeeFenix));
            }
        };
	}

}
