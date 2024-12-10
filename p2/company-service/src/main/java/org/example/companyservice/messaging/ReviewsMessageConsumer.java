package org.example.companyservice.messaging;

import org.example.companyservice.dto.ReviewsMessage;
import org.example.companyservice.service.CompanyService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewsMessageConsumer {
    private final CompanyService companyService;

    @Autowired
    public ReviewsMessageConsumer(CompanyService companyService) {
        this.companyService = companyService;
    }

    @RabbitListener(queues = "companyRatingQueue")
    public void consumeMessage(ReviewsMessage reviewsMessage) {
        companyService.updateCompanyRating(reviewsMessage);
    }
}
