package org.example.reviewservice.messaging;

import org.example.reviewservice.dto.ReviewsMessage;
import org.example.reviewservice.model.Reviews;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewsMessageProducer {
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public ReviewsMessageProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(Reviews reviews) {
        ReviewsMessage reviewsMessage = new ReviewsMessage(reviews);
        rabbitTemplate.convertAndSend("companyRatingQueue", reviewsMessage);
    }
}
