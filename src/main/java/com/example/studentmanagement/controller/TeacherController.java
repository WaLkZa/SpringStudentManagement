package com.example.studentmanagement.controller;

import com.example.studentmanagement.model.entity.Teacher;
import com.example.studentmanagement.service.TeacherService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/teachers")
public class TeacherController {

    private final TeacherService teacherService;


    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping(params = {"page", "size", "order", "sortField"})
    //@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_TEACHER','ROLE_STUDENT')")
    public ResponseEntity<Page<Teacher>> getAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size,
                                                @RequestParam(defaultValue = "ASC") Sort.Direction order, @RequestParam(defaultValue = "name") String sortField) {
        return new ResponseEntity<>(this.teacherService.getAll(page, size, order, sortField), HttpStatus.OK);
    }

    @GetMapping(params = {"page", "size"})
    //@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_TEACHER','ROLE_STUDENT')")
    public ResponseEntity<Page<Teacher>> getAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
        return new ResponseEntity<>(this.teacherService.getAll(page, size), HttpStatus.OK);
    }

    @GetMapping()
    //@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_TEACHER','ROLE_STUDENT')")
    public ResponseEntity<List<Teacher>> getAll() {
        return new ResponseEntity<>(this.teacherService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    //@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_TEACHER','ROLE_STUDENT')")
    public ResponseEntity<Teacher> getById(@PathVariable("id") long id) {
        Optional<Teacher> teacherData = this.teacherService.getById(id);

        return new ResponseEntity<>(teacherData.get(), HttpStatus.OK);
    }

    @PostMapping
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Teacher> create(@RequestBody Teacher newTeacher) {
        return new ResponseEntity<>(this.teacherService.create(newTeacher), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") long id) {
        this.teacherService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
