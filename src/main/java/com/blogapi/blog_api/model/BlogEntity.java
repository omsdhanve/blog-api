package com.blogapi.blog_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

    @Entity
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Table(name = "BLOG_INFO")
    public class BlogEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        @NotBlank(message = "Title is mandatory")
        @Size(min = 3, max = 100, message = "Title must be 3 to 100 characters")
        private String title;

        @NotBlank(message = "Content is required")
        @Lob
        @Column(columnDefinition = "TEXT")
        private String content;

        @NotBlank(message = "Author name is required")
        private String author;

        @CreationTimestamp
        private Timestamp createdAt;
    }


