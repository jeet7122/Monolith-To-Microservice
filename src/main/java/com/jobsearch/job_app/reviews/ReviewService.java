package com.jobsearch.job_app.reviews;

import com.jobsearch.job_app.company.Company;

import java.util.List;

public interface ReviewService {
    List<Review> getAllReviews(Long companyId);
    boolean addReview(Review review, Long companyId);
    Review getReviewById(Long companyId, Long reviewId);
    boolean updateReview(Long companyId, Long reviewId, Review review);
}
