package com.jobsearch.job_app.reviews;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/companies/{companyId}")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable Long companyId){
        return new ResponseEntity<>(reviewService.getAllReviews(companyId), HttpStatus.OK);
    }

    @PostMapping("/reviews")
    public ResponseEntity<String> addReview(@RequestBody Review review, @PathVariable Long companyId){
        boolean added = reviewService.addReview(review, companyId);
        if (added) return new ResponseEntity<>("Review added successfully", HttpStatus.CREATED);
        return new ResponseEntity<>("Failed to add review", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long companyId, @PathVariable Long reviewId){
        Review review = reviewService.getReviewById(companyId, reviewId);
        if (review != null) return new ResponseEntity<>(review, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long companyId, @PathVariable Long reviewId, @RequestBody Review review)
    {
        boolean updated = reviewService.updateReview(companyId, reviewId, review);
        if (updated) return new ResponseEntity<>("Review updated successfully", HttpStatus.OK);
        return new ResponseEntity<>("Error updating review", HttpStatus.BAD_REQUEST);
    }
}
