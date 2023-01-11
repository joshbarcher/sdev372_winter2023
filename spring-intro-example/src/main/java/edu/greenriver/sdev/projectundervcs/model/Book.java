package edu.greenriver.sdev.projectundervcs.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book
{
    private String author;
    private String title;
    private int pages;
}
