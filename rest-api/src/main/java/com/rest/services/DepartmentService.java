package com.rest.services;

import com.rest.dto.DepartmentDto;
import com.rest.entities.DepartmentEntity;
import com.rest.exceptions.ResourceNotFoundException;
import com.rest.repository.DepartmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class DepartmentService{

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Optional<DepartmentDto> getDepartmentById(Long id){
        Optional<DepartmentEntity> departmentEntity = departmentRepository.findById(id);
        return departmentEntity.map(departmentEntity1 -> modelMapper.map(departmentEntity1, DepartmentDto.class));
    }

    public List<DepartmentDto> getAllDepartments(){
        List<DepartmentEntity> allDepartment = departmentRepository.findAll();
        return allDepartment.stream()
                .map(departmentEntity -> modelMapper.map(departmentEntity, DepartmentDto.class))
                .toList();
    }

    public void isDepartmentExistsById(Long id){
        boolean isExists =  departmentRepository.existsById(id);
        if(!isExists) throw new ResourceNotFoundException("Department not found with id : " + id);
    }

    public boolean deleteDepartmentById(Long id){
        isDepartmentExistsById(id);
        departmentRepository.deleteById(id);
        return true;
    }

    public DepartmentDto insertNewDepartment(DepartmentDto departmentDto){
        DepartmentEntity departmentEntity = departmentRepository.save(modelMapper.map(departmentDto, DepartmentEntity.class));
        return modelMapper.map(departmentEntity, DepartmentDto.class);
    }

    public DepartmentDto updateDepartment(DepartmentDto departmentDto, Long id){
        isDepartmentExistsById(id);
        DepartmentEntity departmentEntity = modelMapper.map(departmentDto, DepartmentEntity.class);
        departmentEntity.setId(id);
        return modelMapper.map(departmentRepository.save(departmentEntity), DepartmentDto.class);
    }

    public DepartmentDto partiallyUpdateDepartment(Map<String, Object> updates, Long id){
        isDepartmentExistsById(id);
        DepartmentEntity departmentEntity = departmentRepository.findById(id).get();

        updates.forEach(
                (field, value) -> {
                    Field toBeUpdatedField = ReflectionUtils.findField(DepartmentEntity.class, field);
                    if(toBeUpdatedField != null){
                        toBeUpdatedField.setAccessible(true);
                        ReflectionUtils.setField(toBeUpdatedField, departmentEntity ,value);
                    }
                }
        );
        return modelMapper.map(departmentRepository.save(departmentEntity), DepartmentDto.class);
    }
}
