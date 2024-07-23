package com.myspringboot.reviewms.repos;

import com.myspringboot.reviewms.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByCompanyId(long compId);
}
