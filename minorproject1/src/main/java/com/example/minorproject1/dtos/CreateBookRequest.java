package com.example.minorproject1.dtos;

import com.example.minorproject1.models.Author;
import com.example.minorproject1.models.Book;
import com.example.minorproject1.models.Genre;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateBookRequest
{

    private Genre genre;
    @NotBlank
    private String bookName;
    private String authorName;
    @NotBlank
    private String authorEmail;
    private String authorCountry;
   public Book maptoBook()
   {
    return Book.builder()
            .name(bookName)
            .genre(genre)
            .author(
                    Author.builder().
                            email(this.authorEmail)
                            .name(this.authorName)
                            .build()
            )
            .build();
   }

}
