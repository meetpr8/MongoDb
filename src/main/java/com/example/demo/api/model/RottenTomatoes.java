package com.example.demo.api.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document @Getter @Setter
public class RottenTomatoes {
    private Date lastUpdated;

    private ViewerRating viewer;


}
