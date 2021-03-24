package com.example.demo.api.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document @Getter @Setter
public class ViewerRating {
    private double rating;
    private int numReviews;

}
