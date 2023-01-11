package edu.greenriver.sdev.projectundervcs.services;

import edu.greenriver.sdev.projectundervcs.model.Book;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

/**
 * This class will have our business logic.
 */
@Service
public class BookService
{
    private List<Book> booksList = List.of(
            new Book("Andrzej Sapkowski", "The Sword of Destiny", 384),
            new Book("Randal Garrett", "Lord d'Arcy Investigates", 229),
            new Book("Joseph Murphy", "Powers of the Subconscious Mind", 387),
            new Book("Frank Herbert", "Dune", 412),
            new Book("JK Rowling", "Harry Potter and the Deathly Hallows", 607),
            new Book("Eric Carle", "The Very Hungry Caterpillar", 32)
    );

    public List<Book> getEveryBook()
    {
        return booksList;
    }

    public Book random()
    {
        Random random = new Random();
        int randomIndex = random.nextInt(booksList.size());
        return booksList.get(randomIndex);
    }

    public Book byName(String name)
    {
        Optional<Book> found = booksList.stream()
                .filter(book -> book.getTitle().equalsIgnoreCase(name))
                .findFirst();

        if (found.isPresent())
        {
            return found.get();
        }
        return null;
    }

    public List<Book> byPages(int minPages)
    {
        List<Book> found = booksList.stream()
                .filter(book -> book.getPages() >= minPages)
                .toList();

        return found;
    }
}









