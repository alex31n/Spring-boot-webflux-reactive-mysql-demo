package com.example.reactivemysqldemo.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@Table("books")
@Data
public class Book {

    @Id
    private long id;

    @Column
    private String name;

    @Column
    private String isbn;

    @Column
    private long authorId;

    @Column("publication_id")
    private long publicationId;


}
