package com.advpro.profiling.tutorial.repository;

import com.advpro.profiling.tutorial.model.StudentCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author muhammad.khadafi
 */
@Repository
public interface StudentCourseRepository extends JpaRepository<StudentCourse, Long> {

    interface StudentCourseSummary {
        String getStudentName();
        String getCourseName();
    }

    @Query("""
            select s.name as studentName, c.name as courseName
            from StudentCourse sc
            join sc.student s
            join sc.course c
            """)
    List<StudentCourseSummary> findAllStudentCourseSummaries();
}