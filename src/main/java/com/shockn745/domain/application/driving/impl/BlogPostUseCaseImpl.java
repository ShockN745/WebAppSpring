package com.shockn745.domain.application.driving.impl;

import com.shockn745.domain.application.driven.BlogPostRepository;
import com.shockn745.domain.application.driving.BlogPostUseCase;
import com.shockn745.domain.application.driving.dto.BlogPostDTO;
import com.shockn745.domain.application.mapper.BlogPostMapper;
import com.shockn745.domain.core.BlogPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kempenich Florian
 */
@Component
public class BlogPostUseCaseImpl implements BlogPostUseCase {

    private final BlogPostRepository repository;
    private final BlogPostMapper mapper;

    @Autowired
    public BlogPostUseCaseImpl(BlogPostRepository repository, BlogPostMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public BlogPostDTO save(BlogPostDTO post) {
        BlogPost blogPost = mapper.transform(post);
        BlogPostDTO blogPostDTO = mapper.transform(blogPost);
        // if needed do stuff with blogPost here
        blogPostDTO = repository.save(blogPostDTO);
        return blogPostDTO;
    }

    @Override
    public List<Integer> getAllIds() {
        List<BlogPostDTO> posts = repository.getAll();
        List<Integer> result = new ArrayList<>(posts.size());
        for (BlogPostDTO post : posts) {
            result.add(post.getId());
        }

        return result;
    }

}
