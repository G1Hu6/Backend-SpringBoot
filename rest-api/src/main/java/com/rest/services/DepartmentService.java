package com.rest.services;

import com.rest.dto.DepartmentDto;
import com.rest.entities.DepartmentEntity;
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

    public boolean isDepartmentExistsById(Long id){
        return departmentRepository.existsById(id);
    }

    public DepartmentDto insertNewDepartment(DepartmentDto departmentDto, Long id){
        DepartmentEntity departmentEntity = modelMapper.map(departmentDto, DepartmentEntity.class);
        departmentEntity.setId(id);
        return modelMapper.map(departmentRepository.save(departmentEntity), DepartmentDto.class);
    }

    public boolean deleteDepartmentById(Long id){
        boolean isExists = isDepartmentExistsById(id);
        if(!isExists) return false;
        departmentRepository.deleteById(id);
        return true;
    }

    public DepartmentDto updateDepartment(Map<String, Object> updates, Long id){
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
