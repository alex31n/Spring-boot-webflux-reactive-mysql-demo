package com.example.reactivemysqldemo.dto;

import com.example.reactivemysqldemo.model.Book;
import lombok.Data;

import java.util.List;

@Data
public class AuthorDTO {
    private long id;
    private String name;
    private List<Book> books;
}
