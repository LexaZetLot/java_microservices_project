package org.example.jobservice.clients;

import org.example.jobservice.external.ReviewsExternal;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@FeignClient(name = "REVIEW-SERVICE")
public interface ReviewsClient {
    @GetMapping("/reviews")
    ReviewsExternal[] getReviews(@RequestParam("companyId") Long id);
}
