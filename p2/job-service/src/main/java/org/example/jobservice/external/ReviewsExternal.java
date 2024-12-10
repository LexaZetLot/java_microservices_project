package org.example.jobservice.external;

import org.example.jobservice.model.Reviews;

import java.util.ArrayList;

public class ReviewsExternal {
    private Long id;
    private String title;
    private String description;
    private String rating;
    private Long companyId;

    public ReviewsExternal() {
    }

    public ReviewsExternal(Long id, String title, String description, String rating, Long companyId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.rating = rating;
        this.companyId = companyId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    @Override
    public String toString() {
        return "ReviewsExternal{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", rating='" + rating + '\'' +
                ", companyId=" + companyId +
                '}';
    }

    public static ArrayList<Reviews> convertReviewsToReviewsExternalArray(ReviewsExternal[] reviewsExternalArray) {
        ArrayList<Reviews> reviews = new ArrayList<>();
        for (ReviewsExternal reviewsExternal : reviewsExternalArray) {
            reviews.add(new Reviews(reviewsExternal.getId(), reviewsExternal.getTitle(), reviewsExternal.getDescription(), reviewsExternal.getRating()));
        }
        return reviews;
    }
}
