package com.jobsearch.job_app.reviews;

import com.jobsearch.job_app.company.Company;
import com.jobsearch.job_app.company.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private CompanyService companyService;

    @Override
    public List<Review> getAllReviews(Long companyId) {
        return reviewRepository.findByCompanyId(companyId);
    }

    @Override
    public boolean addReview(Review review, Long companyId) {
        Company company = companyService.findById(companyId);
        if (company != null){
            review.setCompany(company);
            reviewRepository.save(review);
            return true;
        }
        return false;
    }

    @Override
    public Review getReviewById(Long companyId, Long reviewId) {
        Company company = companyService.findById(companyId);
        if (company != null){
            Optional<Review> review = reviewRepository.findById(reviewId);
            if (review.isPresent()){
                return review.get();
            }
        }
        return null;
    }

    @Override
    public boolean updateReview(Long companyId, Long reviewId, Review review) {
        Company company = companyService.findById(companyId);
        if (company != null){
            Optional<Review> existingReviewOptional = reviewRepository.findById(reviewId);
            if (existingReviewOptional.isPresent()){
                Review rev = existingReviewOptional.get();
                rev.setCompany(review.getCompany());
                rev.setTitle(review.getTitle());
                rev.setDescription(review.getDescription());
                reviewRepository.save(rev);
                return true;
            }
        }
        return false;
    }
}
