package com.localconnect.repository;

import com.localconnect.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
    List<Review> findByProviderId(Integer providerId);
    List<Review> findByCustomerId(Integer customerId);
}