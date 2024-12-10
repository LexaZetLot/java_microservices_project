package org.example.companyservice.dto;


import org.example.companyservice.model.Reviews;

public class ReviewsMessage {
    private Long id;
    private String title;
    private String description;
    private String rating;
    private Long companyId;

    public ReviewsMessage() {
    }

    public ReviewsMessage(Long id, String title, String description, String rating, Long companyId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.rating = rating;
        this.companyId = companyId;
    }

    public ReviewsMessage(Reviews reviews){
        this.id = reviews.getId();
        this.title = reviews.getTitle();
        this.description = reviews.getDescription();
        this.rating = reviews.getRating();
        this.companyId = reviews.getCompanyId();
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
        return "ReviewsMessage{ id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", rating=" + rating +
                ", companyId=" + companyId +
                '}';
    }
}
