package com.example.demo.api.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document @Getter @Setter
public class IMDB {
    private int id;
    private int votes;
    private double rating;

}
