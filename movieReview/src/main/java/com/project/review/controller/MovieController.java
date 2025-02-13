package com.project.review.controller;

import com.project.review.DTO.MovieRequest;
import com.project.review.DTO.MovieResponse;
import com.project.review.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("movie/")
public class MovieController
{

    @Autowired
    MovieService movieService;
    @GetMapping("title")
    public MovieResponse findMovie(@RequestParam String title)
    {

        return movieService.findByTitle(title);
    }

    @GetMapping("genre")
    public List<MovieResponse> findByGenre(@RequestParam String genre)
    {
        return movieService.findByGenre(genre);
    }

}
