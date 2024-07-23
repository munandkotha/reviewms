package com.myspringboot.reviewms;

import java.util.List;

public interface ReviewService {
    public List<Review> getAllReviewsByCompany(long compId);

    public boolean createReviewsForCompany(long compId, Review newReview);

    public Review getAReviewForCompany(long compId, long reviewId);

    public boolean updateReviewForCompany(Review review, long reviewId);

    public boolean deleteReviewForCompany(long compId);
}
