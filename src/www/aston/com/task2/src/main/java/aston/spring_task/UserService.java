package aston.spring_task;

import io.micrometer.observation.ObservationFilter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsById(Long id);
    void deleteById(Long id);
    User save(User user);
    ObservationFilter findById(Long id);
}