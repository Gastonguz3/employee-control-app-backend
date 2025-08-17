package com.gaston.project.employee.services;

import com.gaston.project.employee.dto.EmployeeDTO;
import com.gaston.project.employee.entities.EmployeeEntity;
import com.gaston.project.employee.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class EmployeeServiceImpl implements IEmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public EmployeeDTO findById(Long id) {

        EmployeeEntity employeeEntity = employeeRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "El empleado de ID " + id + " no existe"));

        //Convierto el employeeEntity en un employeeDTO
        EmployeeDTO employeeDTO = modelMapper.map(employeeEntity, EmployeeDTO.class);

        return employeeDTO;
    }

    @Override
    public List<EmployeeDTO> findAll() {
        return employeeRepository.findAll().stream()
                .map(employeeEntity -> modelMapper.map(employeeEntity, EmployeeDTO.class))
                .toList();
    }

    @Override
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {

        //Convierto el EmployeeDTO en un employeeEntity
        EmployeeEntity employeeEntitySaved = modelMapper.map(employeeDTO, EmployeeEntity.class);
        //Guardo el employee pasado por parametro a la base de datos
        employeeRepository.save(employeeEntitySaved);

        return modelMapper.map(employeeEntitySaved, EmployeeDTO.class);
    }

    @Override
    public EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO) {

        //Obtengo el empleado que se quiere actualizar
        EmployeeEntity employeeEntity = employeeRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "El empleado de ID " + id + " no existe"));

        //Actualizo los datos del empleado
        employeeEntity.setName(employeeDTO.getName());
        employeeEntity.setEmail(employeeDTO.getEmail());
        employeeEntity.setPhone(employeeDTO.getPhone());
        employeeEntity.setDepartment(employeeDTO.getDepartment());

        //Guardo al empleado actualizado en la base de datos
        employeeRepository.save(employeeEntity);

        return modelMapper.map(employeeEntity, EmployeeDTO.class);
    }

    @Override
    public void deleteById(Long id) {

        EmployeeEntity employeeEntity = employeeRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "El empleado de ID " + id + " no existe") );

        employeeRepository.delete(employeeEntity);
    }
}
