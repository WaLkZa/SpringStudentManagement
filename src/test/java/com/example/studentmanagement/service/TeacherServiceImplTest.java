package com.example.studentmanagement.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.studentmanagement.exception.TeacherNotFoundException;
import com.example.studentmanagement.model.entity.Teacher;
import com.example.studentmanagement.repository.TeacherRepository;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;


import com.example.studentmanagement.service.impl.TeacherServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class TeacherServiceImplTest {
    @MockBean
    private TeacherRepository teacherRepository;

    @Autowired
    private TeacherServiceImpl teacherServiceImpl;

    @Test
    void testGetAll() {
        ArrayList<Teacher> teacherList = new ArrayList<>();
        when(teacherRepository.findAll()).thenReturn(teacherList);
        List<Teacher> actualAll = teacherServiceImpl.getAll();
        assertSame(teacherList, actualAll);
        assertTrue(actualAll.isEmpty());
        verify(teacherRepository).findAll();
    }

    @Test
    void testGetAll2() {
        when(teacherRepository.findAll()).thenThrow(new TeacherNotFoundException());
        assertThrows(TeacherNotFoundException.class, () -> teacherServiceImpl.getAll());
        verify(teacherRepository).findAll();
    }

    @Test
    void testGetById() {
        Teacher teacher = new Teacher();
        teacher.setDegree("Degree");
        teacher.setId(123L);
        teacher.setName("Name");
        Optional<Teacher> ofResult = Optional.of(teacher);
        when(teacherRepository.findById((Long) any())).thenReturn(ofResult);
        Optional<Teacher> actualById = teacherServiceImpl.getById(123L);
        assertSame(ofResult, actualById);
        assertTrue(actualById.isPresent());
        verify(teacherRepository).findById((Long) any());
    }

    @Test
    void testGetById2() {
        when(teacherRepository.findById((Long) any())).thenThrow(new TeacherNotFoundException());
        assertThrows(TeacherNotFoundException.class, () -> teacherServiceImpl.getById(123L));
        verify(teacherRepository).findById((Long) any());
    }

    @Test
    void testCreate() {
        Teacher teacher = new Teacher();
        teacher.setDegree("Degree");
        teacher.setId(123L);
        teacher.setName("Name");
        when(teacherRepository.save((Teacher) any())).thenReturn(teacher);

        Teacher teacher1 = new Teacher();
        teacher1.setDegree("Degree");
        teacher1.setId(123L);
        teacher1.setName("Name");
        assertSame(teacher, teacherServiceImpl.create(teacher1));
        verify(teacherRepository).save((Teacher) any());
    }

    @Test
    void testCreate2() {
        when(teacherRepository.save((Teacher) any())).thenThrow(new TeacherNotFoundException());

        Teacher teacher = new Teacher();
        teacher.setDegree("Degree");
        teacher.setId(123L);
        teacher.setName("Name");
        assertThrows(TeacherNotFoundException.class, () -> teacherServiceImpl.create(teacher));
        verify(teacherRepository).save((Teacher) any());
    }

    @Test
    void testDelete() {
        Teacher teacher = new Teacher();
        teacher.setDegree("Degree");
        teacher.setId(123L);
        teacher.setName("Name");
        Optional<Teacher> ofResult = Optional.of(teacher);
        doNothing().when(teacherRepository).deleteById((Long) any());
        when(teacherRepository.findById((Long) any())).thenReturn(ofResult);
        teacherServiceImpl.delete(123L);
        verify(teacherRepository).findById((Long) any());
        verify(teacherRepository).deleteById((Long) any());
    }

    @Test
    void testDelete2() {
        Teacher teacher = new Teacher();
        teacher.setDegree("Degree");
        teacher.setId(123L);
        teacher.setName("Name");
        Optional<Teacher> ofResult = Optional.of(teacher);
        doThrow(new TeacherNotFoundException()).when(teacherRepository).deleteById((Long) any());
        when(teacherRepository.findById((Long) any())).thenReturn(ofResult);
        assertThrows(TeacherNotFoundException.class, () -> teacherServiceImpl.delete(123L));
        verify(teacherRepository).findById((Long) any());
        verify(teacherRepository).deleteById((Long) any());
    }
}

