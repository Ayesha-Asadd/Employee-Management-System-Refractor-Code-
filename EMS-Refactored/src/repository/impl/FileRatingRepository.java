package repository.impl;

import repository.RatingRepository;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class FileRatingRepository implements RatingRepository {
    private final Path file = Paths.get("ratings.txt");

    public FileRatingRepository() throws IOException {
        if (!Files.exists(file)) Files.createFile(file);
    }

    @Override
    public void save(String employeeId, int rating) {
        Map<String, Integer> all = findAll();
        all.put(employeeId, rating);
        try (BufferedWriter writer = Files.newBufferedWriter(file)) {
            for (var entry : all.entrySet()) {
                writer.write(entry.getKey() + ":" + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Integer> findById(String employeeId) {
        return Optional.ofNullable(findAll().get(employeeId));
    }

    @Override
    public Map<String, Integer> findAll() {
        Map<String, Integer> map = new HashMap<>();
        try {
            Files.lines(file).forEach(line -> {
                String[] parts = line.split(":");
                map.put(parts[0], Integer.parseInt(parts[1]));
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return map;
    }
}
