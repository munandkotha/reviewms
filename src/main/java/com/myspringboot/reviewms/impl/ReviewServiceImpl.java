package com.myspringboot.reviewms.impl;

import com.myspringboot.reviewms.Review;
import com.myspringboot.reviewms.ReviewService;
import com.myspringboot.reviewms.repos.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    private ReviewRepository reviewRepo;

    public ReviewServiceImpl(ReviewRepository reviewRepo) {
        this.reviewRepo = reviewRepo;
    }

    @Override
    public List<Review> getAllReviewsByCompany(long compId) {
        return reviewRepo.findByCompanyId(compId);
    }

    @Override
    public boolean createReviewsForCompany(long compId, Review newReview) {
        newReview.setCompanyId(compId);
        reviewRepo.save(newReview);
        return true;
    }

    @Override
    public Review getAReviewForCompany(long compId, long reviewId) {
        List<Review> reviews = reviewRepo.findByCompanyId(compId);
        return reviews.stream().filter(review -> review.getId() == reviewId).findFirst().orElse(null);
    }

    @Override
    public boolean updateReviewForCompany(Review newReview, long reviewId) {
        Review oldReview = reviewRepo.findById(reviewId).orElse(null);
        if(oldReview != null) {
            oldReview.setTitle(newReview.getTitle());
            oldReview.setDescription(newReview.getDescription());
            oldReview.setRating(newReview.getRating());
            reviewRepo.save(oldReview);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteReviewForCompany(long reviewId) {
        reviewRepo.deleteById(reviewId);
        return true;
    }
}
