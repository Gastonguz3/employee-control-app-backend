package com.gaston.project.employee.controllers;

import com.gaston.project.employee.dto.EmployeeDTO;
import com.gaston.project.employee.services.IEmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/employee")
@Tag(name = "Empleados", description = "Controlador para la gestión de empleados con operaciones CRUD")
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;

    @Operation(
            summary = "Buscar empleado por ID",
            description = "Obtiene la información de un empleado usando su ID.",
            responses = {@ApiResponse( responseCode = "200", description = "Empleado encontrado"),
                    @ApiResponse(responseCode = "404", description = "Empleado no encontrado")}
    )
    @GetMapping("/find/{id}")
    public ResponseEntity<EmployeeDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(employeeService.findById(id));
    }

    @Operation(
            summary = "Listar todos los empleados",
            description = "Retorna la lista completa de empleados registrados en el sistema.",
            responses = {@ApiResponse(responseCode = "200", description = "Lista de empleados creado")}
    )
    @GetMapping("/findall")
    public ResponseEntity<List<EmployeeDTO>> findAll(){
        return ResponseEntity.ok(employeeService.findAll());
    }

    @Operation(
            summary = "Crear nuevo empleado",
            description = "Agrega un nuevo empleado al sistema y retorna el registro creado.",
            responses = {@ApiResponse(responseCode = "201", description = "Empleado creado exitosamente"),
            @ApiResponse(responseCode = "201", description= "Datos del empleado invalido")}
    )
    @PostMapping("/create")
    public ResponseEntity<EmployeeDTO> createEmployee(@Valid @RequestBody EmployeeDTO employeeDTO){

        EmployeeDTO employeeDTOsaved = employeeService.createEmployee(employeeDTO);
        URI location = URI.create("/api/employee" + employeeDTOsaved.getId());

        return ResponseEntity.created(location).body(employeeDTOsaved);
    }

    @Operation(
            summary = "Actualizar empleado",
            description = "Modifica los datos de un empleado existente identificado por su ID.",
            responses = {@ApiResponse(responseCode = "200", description = "Empleado actualizado correctamente"),
                    @ApiResponse(responseCode = "404", description = "Empleado no encontrado")}
    )
    @PutMapping("/update/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable Long id, @Valid @RequestBody EmployeeDTO employeeDTO){
        return ResponseEntity.ok(employeeService.updateEmployee(id, employeeDTO));
    }

    @Operation(
            summary = "Eliminar empleado",
            description = "Borra el registro de un empleado según su ID.",
            responses = {@ApiResponse(responseCode = "200", description = "Empleado eliminado exitosamente"),
                    @ApiResponse(responseCode = "404", description = "Empleado no encontrado")}
    )
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id){

        employeeService.deleteById(id);
        return ResponseEntity.ok("El empleado con ID " + id + " fue eliminado con exito");
    }
}
