package edu.greenriver.sdev.projectundervcs;

import edu.greenriver.sdev.projectundervcs.model.Book;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjectUnderVcsApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(ProjectUnderVcsApplication.class, args);

        Book book = new Book("The Sword of Destiny",
                "Andrzej Sapkowski", 384);
        Book defaultBook = new Book();

        Book builtBook = Book.builder()
            .author("Randal Garrett")
            .title("Lord d'Arcy Investigates")
            .pages(229)
            .build();
    }
}
