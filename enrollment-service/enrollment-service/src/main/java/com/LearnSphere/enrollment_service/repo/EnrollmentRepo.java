package com.LearnSphere.enrollment_service.repo;

import com.LearnSphere.enrollment_service.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnrollmentRepo extends JpaRepository<Enrollment , Integer> {
    void deleteByStudentEmail(String Email);
    List<Enrollment> findAllByStudentEmail(String email);

    @Query("SELECT e FROM Enrollment e WHERE e.studentEmail = :email AND e.courseId = :courseId")
    Enrollment findByStudentEmailAndCourseId(
            @Param("email") String email,
            @Param("courseId") Integer courseId
    );

//    // For checking if rating already exists
//    @Query("SELECT CASE WHEN COUNT(e) > 0 THEN true ELSE false END " +
//            "FROM Enrollment e WHERE e.studentEmail = :email AND e.courseId = :courseId AND e.ratingGiven IS NOT NULL")
//    boolean existsRatingForStudentAndCourse(
//            @Param("email") String email,
//            @Param("courseId") Integer courseId
//    );
}
