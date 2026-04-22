package com.advpro.profiling.tutorial.repository;

import com.advpro.profiling.tutorial.model.StudentCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author muhammad.khadafi
 */
@Repository
public interface StudentCourseRepository extends JpaRepository<StudentCourse, Long> {

    @Query(value = """
            select concat(
                '[',
                coalesce(
                    string_agg(
                        'StudentCourse{, student=' || s.name || ', course=' || c.name || '}' || E'\n',
                        ', ' order by sc.id
                    ),
                    ''
                ),
                ']'
            )
            from student_courses sc
            join students s on s.id = sc.student_id
            join courses c on c.id = sc.course_id
            """, nativeQuery = true)
    String findAllStudentsWithCoursesAsString();
}