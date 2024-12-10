package org.example.reviewservice.service.impl;

import org.example.reviewservice.model.Reviews;
import org.example.reviewservice.repository.ReviewsRepository;
import org.example.reviewservice.service.ReviewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewsServiceImpl implements ReviewsService {
    private final ReviewsRepository reviewsRepository;

    @Autowired
    public ReviewsServiceImpl(ReviewsRepository reviewsRepository) {
        this.reviewsRepository = reviewsRepository;
    }

    @Override
    public List<Reviews> getAllReviews(Long id) {
        return reviewsRepository.findByCompanyId(id);
    }

    @Override
    public boolean updateReviews(Long reviewId, Reviews reviews) {
        Reviews reviews1 = reviewsRepository.findById(reviewId).orElse(null);
        if (reviews1 != null) {
            reviews1.setTitle(reviews.getTitle());
            reviews1.setDescription(reviews.getDescription());
            reviews1.setRating(reviews.getRating());
            reviews1.setCompanyId(reviews.getCompanyId());
            reviewsRepository.save(reviews);
            return true;
        }
        return false;
    }

    @Override
    public boolean createReviews(Long companyId, Reviews reviews) {
        if (companyId != null && reviews != null) {
            reviews.setCompanyId(companyId);
            reviewsRepository.save(reviews);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteReviews(Long reviewId) {
        Reviews reviews = reviewsRepository.findById(reviewId).orElse(null);
        if(reviews != null) {
            reviewsRepository.delete(reviews);
            return true;
        }
        return false;
    }

    @Override
    public Reviews getReviewsById(Long reviewId) {
        return reviewsRepository.findById(reviewId).orElse(null);
    }
}