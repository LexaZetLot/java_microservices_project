package org.example.reviewservice.controller;

import org.example.reviewservice.messaging.ReviewsMessageProducer;
import org.example.reviewservice.model.Reviews;
import org.example.reviewservice.repository.ReviewsRepository;
import org.example.reviewservice.service.ReviewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewsController {
    private final ReviewsService reviewsService;
    private final ReviewsRepository reviewsRepository;
    private final ReviewsMessageProducer reviewsMessageProducer;

    @Autowired
    public ReviewsController(ReviewsService reviewsService, ReviewsRepository reviewsRepository, ReviewsMessageProducer reviewsMessageProducer) {
        this.reviewsService = reviewsService;
        this.reviewsRepository = reviewsRepository;
        this.reviewsMessageProducer = reviewsMessageProducer;
    }

    @GetMapping
    public ResponseEntity<List<Reviews>> getAllReviews(@RequestParam("companyId") Long companyId) {
        return new ResponseEntity<>(reviewsService.getAllReviews(companyId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addReview(@RequestParam Long companyId, @RequestBody Reviews reviews) {
        boolean status = reviewsService.createReviews(companyId, reviews);
        if (status) {
            reviewsMessageProducer.sendMessage(reviews);
            return new ResponseEntity<>("Successful", HttpStatus.OK);
        }
        return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<Reviews> getReview(@PathVariable Long reviewId) {
        return new ResponseEntity<>(reviewsService.getReviewsById(reviewId), HttpStatus.OK);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long reviewId, @RequestBody Reviews reviews){
        boolean status = reviewsService.updateReviews(reviewId,  reviews);
        if (status)
            return new ResponseEntity<>("Review updated successfully", HttpStatus.OK);
        return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long reviewId) {
        boolean status = reviewsService.deleteReviews(reviewId);
        if(status)
            return new ResponseEntity<>("Review deleted successfully", HttpStatus.OK);
        return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/averageRating")
    public Double getAverageRating(@RequestParam("companyId") Long companyId) {
        List<Reviews> reviews = reviewsService.getAllReviews(companyId);
        double averageRating = 0.0;
        for(Reviews review : reviews)
            averageRating += Double.parseDouble(review.getRating());
        averageRating /= reviews.size();
        return averageRating;
    }
}
