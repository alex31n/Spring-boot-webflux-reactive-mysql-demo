package com.example.reactivemysqldemo.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("Authors")
@Data
public class Author {

    @Id
    private long id;

    private String name;
}
