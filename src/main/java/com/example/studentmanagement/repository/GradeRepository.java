package com.example.studentmanagement.repository;

import com.example.studentmanagement.model.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {

    @Query(nativeQuery = true, value = "SELECT AVG(g.grade) FROM grades g\n" +
            "WHERE g.course_id = ?1\n" +
            "GROUP BY g.course_id")
    Double findAverageGradeInCourse(long courseId);

    @Query(nativeQuery = true, value = "SELECT AVG(g.grade) FROM grades g\n" +
            "WHERE g.student_id = ?1\n" +
            "GROUP BY g.student_id")
    Double findAverageGradeForStudent(Long studentId);
}
