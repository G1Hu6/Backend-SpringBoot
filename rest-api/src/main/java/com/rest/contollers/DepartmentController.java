package com.rest.contollers;

import com.rest.dto.DepartmentDto;
import com.rest.exceptions.ResourceNotFoundException;
import com.rest.services.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping(path = "{id}")
    public ResponseEntity<DepartmentDto> getDepartmentById(@PathVariable Long id){
        Optional<DepartmentDto> departmentDto = departmentService.getDepartmentById(id);
        return departmentDto.map(ResponseEntity::ok).orElseThrow(() -> new ResourceNotFoundException("Department not found with id : " + id));
    }

    @GetMapping
    public ResponseEntity<List<DepartmentDto>> getAllDepartments(){
        return ResponseEntity.ok(departmentService.getAllDepartments());
    }

    @PostMapping
    public ResponseEntity<DepartmentDto> insertDepartmentById(@RequestBody @Valid DepartmentDto departmentDto){
        return new ResponseEntity<>(departmentService.insertNewDepartment(departmentDto), HttpStatus.CREATED);
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<DepartmentDto> updateDepartmentById(@RequestBody DepartmentDto departmentDto ,@PathVariable Long id){
        return ResponseEntity.ok(departmentService.updateDepartment(departmentDto, id));
    }

    @PatchMapping(path = "{id}")
    public ResponseEntity<DepartmentDto> patchDepartmentById(@RequestBody Map<String, Object> updetes , @PathVariable Long id){
        return ResponseEntity.ok(departmentService.partiallyUpdateDepartment(updetes, id));
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Boolean> deleteDepartment(@PathVariable Long id){
        return ResponseEntity.ok(departmentService.deleteDepartmentById(id));
    }
}
