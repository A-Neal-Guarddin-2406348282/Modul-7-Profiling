package com.advpro.profiling.tutorial.service;

import com.advpro.profiling.tutorial.model.Student;
import com.advpro.profiling.tutorial.repository.StudentCourseRepository;
import com.advpro.profiling.tutorial.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author muhammad.khadafi
 */
@Service
@Transactional(readOnly = true)
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentCourseRepository studentCourseRepository;

    public StudentService(StudentRepository studentRepository,
                          StudentCourseRepository studentCourseRepository) {
        this.studentRepository = studentRepository;
        this.studentCourseRepository = studentCourseRepository;
    }

    // Hasil refactor all-student sehingga meningkatkan kecepatan respons dan aplikasi jadi lebih ringan
    // Menggunakan JMeter dan IntelliJ Profiler
    public String getAllStudentsWithCourses() {
        List<StudentCourseRepository.StudentCourseSummary> rows =
                studentCourseRepository.findAllStudentCourseSummaries();

        StringBuilder result = new StringBuilder(Math.max(16, rows.size() * 48));
        result.append("[");

        for (int i = 0; i < rows.size(); i++) {
            StudentCourseRepository.StudentCourseSummary row = rows.get(i);

            result.append("StudentCourse{")
                    .append(", student=")
                    .append(row.getStudentName())
                    .append(", course=")
                    .append(row.getCourseName())
                    .append("}")
                    .append("\n");

            if (i < rows.size() - 1) {
                result.append(", ");
            }
        }

        result.append("]");
        return result.toString();
    }

    // Hasil fungsi highest GPA refactor sehingga meningkatkan kecepatan respons dan aplikasi jadi lebih ringan
    public Optional<Student> findStudentWithHighestGpa() {
        return studentRepository.findTopByOrderByGpaDesc();
    }

    // Hasil refactor all-student-name sehingga meningkatkan kecepatan respons dan aplikasi jadi lebih ringan
    public String joinStudentNames() {
        return studentRepository.findAllStudentNamesJoined();
    }
}