package com.blogapi.blog_api.service;

import com.blogapi.blog_api.model.BlogEntity;

import java.util.List;

public interface IBlogService {
    BlogEntity createBlog(BlogEntity blog);

    List<BlogEntity> getAllBlogs();

    BlogEntity getBlogById(Integer id);

    BlogEntity updateBlog(Integer id, BlogEntity newBlog);

    void deleteBlog(Integer id);
}
