package com.myspringboot.reviewms;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews(@RequestParam long companyId) {
        return new ResponseEntity<>(reviewService.getAllReviewsByCompany(companyId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createReviewForCompany(@RequestParam long companyId,
                                                         @RequestBody Review newReview) {
        if(reviewService.createReviewsForCompany(companyId, newReview)) {
            return new ResponseEntity<>("Review created Successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Couldn't crate Review!", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReviewForCompany(@RequestParam long companyId,
                                                      @PathVariable long reviewId) {
        Review review = reviewService.getAReviewForCompany(companyId, reviewId);
        if(review != null) {
            return new ResponseEntity<>(review, HttpStatus.OK);
        }
        return new ResponseEntity<>(new Review(), HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<String> updateReviewOfCompany(@RequestBody Review newReview,
                                                        @PathVariable long reviewId) {
        if(reviewService.updateReviewForCompany(newReview, reviewId)) {
            return new ResponseEntity<>("Review Updated Successfully!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Couldn't find and update the Reivew!", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReviewForCompany(@PathVariable long reviewId) {
        if (reviewService.deleteReviewForCompany(reviewId)) {
            return new ResponseEntity<>("Deleted Review Successfully!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Couldn't find the review!", HttpStatus.NOT_FOUND);
    }
}
