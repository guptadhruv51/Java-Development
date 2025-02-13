package com.project.review.DTO;


import com.project.review.models.Genre;
import com.project.review.models.Movie;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovieRequest
{
    String title;
    Genre genre;

   public Movie toMovie()
   {
       return Movie.builder().title(this.title).genre(this.genre).rating(0.0).build();
   }
}
