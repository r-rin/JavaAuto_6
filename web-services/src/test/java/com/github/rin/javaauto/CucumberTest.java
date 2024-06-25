package com.github.rin.javaauto;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CucumberTest {
    private String id;
    private BookService bookService;
    private Book bookSearchResult;


    @Given("a book storage I just created")
    public void a_book_storage_i_just_created() {
        this.bookService = BookService.getInstance();
    }

    @Given("an invalid UUID ID: {string}")
    public void an_invalid_uuid_id(String invalidId) {
        this.id = invalidId;
    }

    @Given("a valid UUID ID of a non-existing book: {string}")
    public void a_valid_uuid_id_of_a_non_existing_book(String validId) {
        this.id = validId;
    }

    @Given("a valid UUID ID of an existing book: {string}")
    public void a_valid_uuid_id_of_an_existing_book(String validId) {
        this.id = validId;

        Book book = new Book();
        book.setTitle("Title");
        book.setDescription("Description");
        book.setPublishYear(2024);
        book.setAuthor("Author");
        book.setId(UUID.fromString(validId));

        bookService.addBook(book);
    }

    @When("the system attempts to retrieve a book with this ID")
    public void the_system_attempts_to_retrieve_a_book_with_this_id() {
        this.bookSearchResult = bookService.getBookById(id);
    }

    @Then("it should return null")
    public void it_should_return_null() {
        assertNull(bookSearchResult);
    }

    @Then("it should return the book")
    public void it_should_return_the_book() {
        assertNotNull(bookSearchResult);
    }
}
