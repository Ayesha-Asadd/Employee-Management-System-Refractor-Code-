package service;

import repository.RatingRepository;
import java.util.Map;
import java.util.Optional;

public class EmployeeRatingService {
    private final RatingRepository repo;

    public EmployeeRatingService(RatingRepository repo) {
        this.repo = repo;
    }

    public void setRating(String employeeId, int rating) {
        if (rating < 1 || rating > 5) throw new IllegalArgumentException("Rating must be between 1 and 5.");
        repo.save(employeeId, rating);
    }

    public Optional<Integer> getRating(String employeeId) {
        return repo.findById(employeeId);
    }

    public Map<String, Integer> listAllRatings() {
        return repo.findAll();
    }
}
