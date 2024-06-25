package com.github.rin.javaauto;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

/**
 * Controller class for managing operations related to books.
 */
@Controller
@RequestMapping("/books")
public class BookController {

    /**
     * Books service.
     */
    private final BookService bookService = BookService.getInstance();

    /**
     * Retrieves a list of all books and adds them to the model.
     *
     * @param model the model to add the books attribute to
     * @return the view name for displaying the list of books
     */
    @GetMapping()
    public String getBooks(final Model model) {
        List<Book> books = bookService.getBooks();
        model.addAttribute("books", books);
        return "books-list";
    }

    /**
     * Displays the form for creating a new book.
     *
     * @param model the model to add the book attribute to
     * @return the view name for the create book form
     */
    @GetMapping("/create")
    public String getCreateBookForm(final Model model) {
        model.addAttribute("book", new Book());
        return "create-book";
    }

    /**
     * Handles the submission of the create book form.
     *
     * @param book the book object to create
     * @return the redirect URL to view the list of books
     */
    @PostMapping("/create")
    public String createBook(@ModelAttribute("book") final Book book) {
        bookService.addBook(book);
        return "redirect:/books";
    }

    /**
     * Handles the deletion of a book.
     *
     * @param id the ID of the book to delete
     * @return the redirect URL to view the list of books
     */
    @PostMapping("/delete")
    public String deleteBook(@RequestParam("id") final UUID id) {
        bookService.removeBook(id);
        return "redirect:/books";
    }

}
