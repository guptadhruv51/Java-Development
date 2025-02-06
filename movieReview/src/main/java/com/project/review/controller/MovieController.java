package com.project.review.controller;

import com.project.review.DTO.MovieRequest;
import com.project.review.DTO.MovieResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("movie/")
public class MovieController
{

    @Autowired
    MovieResponse movieResponse;
    @GetMapping("title")
    public MovieResponse findMovie(@RequestParam String title)
    {
        return movieResponse.findByTitle(title);
    }

    @GetMapping("genre")
    public List<?> findByGenre(@RequestParam String genre)
    {
        return movieResponse.findByGenre(genre);
    }

}
