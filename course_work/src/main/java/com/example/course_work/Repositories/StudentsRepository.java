package com.example.course_work.Repositories;

import com.example.course_work.Models.Students;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StudentsRepository extends JpaRepository<Students, Long> {
    @Query("SELECT p FROM Students p LEFT JOIN FETCH  p.grades WHERE CONCAT(p.surname, '', p.name, '', p.patronymic, '', p.email, '', p.date_of_birth, '', p.date_of_enrollment, '', p.studentGroup) LIKE %?1%")
    List<Students> search(String keyword);

    @Query("SELECT p FROM Students p ORDER BY p.date_of_enrollment")
    List<Students> sort();

//    @Query("SELECT p FROM Students p LEFT JOIN FETCH  p.grades where ")
   // @Override
    @Query("SELECT p FROM Students p LEFT JOIN FETCH  p.grades WHERE p.studentGroup LIKE %?1%")
    List<Students> findByStudentGroupContaining(String student_group);

    @Query("SELECT p.studentGroup, AVG(g.grade) as double FROM Students p LEFT JOIN p.grades g group by p.studentGroup")
    List<Object[]> forTable();

    @Query("select p from Students p left join p.user u where u.username like %?1%")
    Optional<Students> findByUser(String username);

}
