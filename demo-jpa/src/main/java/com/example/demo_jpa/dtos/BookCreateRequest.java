package com.example.demo_jpa.dtos;

import com.example.demo_jpa.models.Book;
import com.example.demo_jpa.models.Genre;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookCreateRequest
{

    @NotBlank
    @Length(max=50,min=5)
    private String name;
    @NotNull
    private Genre genre;
    private String authorName;
    private String authorCountry;
    private String authorEmail;
    public Book to()
    {
        return Book.builder()
                .name(this.name)
                .authorName(this.authorName)
                .authorCountry(this.authorCountry)
                .authorEmail(this.authorEmail)
                .genre(this.genre)
                .createdOn(new Date())
                .updatedOn(new Date())
                .build();
    }
}
