package soft.coursesfinal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import soft.coursesfinal.entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
}
