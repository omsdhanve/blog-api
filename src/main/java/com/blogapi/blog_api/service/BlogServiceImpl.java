package com.blogapi.blog_api.service;

import com.blogapi.blog_api.model.BlogEntity;
import com.blogapi.blog_api.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BlogServiceImpl implements IBlogService{
    @Autowired
    private BlogRepository blogRepo;

    @Override
    public BlogEntity createBlog(BlogEntity blog) {
        return blogRepo.save(blog);
    }

    @Override
    public List<BlogEntity> getAllBlogs() {
        return blogRepo.findAll();
    }

    @Override
    public BlogEntity getBlogById(Integer id) {
        return blogRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Blog not found"));
    }

    @Override
    public BlogEntity updateBlog(Integer id, BlogEntity newBlog) {
        BlogEntity old = getBlogById(id);
        old.setTitle(newBlog.getTitle());
        old.setContent(newBlog.getContent());
        old.setAuthor(newBlog.getAuthor());
        return blogRepo.save(old);
    }

    @Override
    public void deleteBlog(Integer id) {
        blogRepo.deleteById(id);
    }
}
