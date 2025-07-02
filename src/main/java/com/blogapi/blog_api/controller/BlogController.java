package com.blogapi.blog_api.controller;

import com.blogapi.blog_api.model.BlogEntity;
import com.blogapi.blog_api.service.IBlogService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blogs")
public class BlogController {

    @Autowired
    private IBlogService blogService;

    @PostMapping
    public ResponseEntity<BlogEntity> createBlog(@RequestBody @Valid BlogEntity blog) {
        return new ResponseEntity<>(blogService.createBlog(blog), HttpStatus.CREATED);
    }

    @GetMapping
    public List<BlogEntity> getAllBlogs() {
        return blogService.getAllBlogs();
    }

    @GetMapping("/{id}")
    public BlogEntity getBlogById(@PathVariable Integer id) {
        return blogService.getBlogById(id);
    }

    @PutMapping("/{id}")
    public BlogEntity updateBlog(@PathVariable Integer id, @RequestBody BlogEntity blog) {
        return blogService.updateBlog(id, blog);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBlog(@PathVariable Integer id) {
        blogService.deleteBlog(id);
        return ResponseEntity.noContent().build();
    }
}
