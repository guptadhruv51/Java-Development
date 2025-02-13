package com.project.review.service;

import com.project.review.DTO.MovieRequest;
import com.project.review.DTO.MovieResponse;
import com.project.review.repository.MovieRepo;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService
{
    @Autowired
    MovieRepo movieRepo;
    public MovieResponse findByTitle(String title)
    {

    }

    public List<MovieResponse> findByGenre(String genre)
    {

    }

    public void addMovie(MovieRequest movieRequest) {
    }
}
