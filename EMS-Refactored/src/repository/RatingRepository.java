package repository;

import java.util.Map;
import java.util.Optional;

public interface RatingRepository {
    void save(String employeeId, int rating);
    Optional<Integer> findById(String employeeId);
    Map<String, Integer> findAll();
}
