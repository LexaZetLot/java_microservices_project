package org.example.reviewservice.service;

import org.example.reviewservice.model.Reviews;

import java.util.List;

public interface ReviewsService {
    List<Reviews> getAllReviews(Long id);
    boolean updateReviews(Long reviewId, Reviews reviews);
    boolean createReviews(Long companyId, Reviews reviews);
    boolean deleteReviews(Long reviewId);
    Reviews getReviewsById(Long reviewId);
}
