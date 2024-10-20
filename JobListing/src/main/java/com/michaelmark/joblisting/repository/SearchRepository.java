package com.michaelmark.joblisting.repository;

import com.michaelmark.joblisting.model.Post;
import org.springframework.stereotype.Component;

import java.util.List;

public interface SearchRepository  {
    List<Post> findByText(String text);
}
