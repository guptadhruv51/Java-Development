package com.example.minorproject1.dtos;

import com.example.minorproject1.models.Genre;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetBookDetailsResponse
{
    private Genre genre;
    private String name;
    private String AuthorName;
}
