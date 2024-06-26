package com.github.rin.javaauto;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class CucumberValidatorTest {
    private BookService bookService;
    private List<Map<String, String>> givenBooks;

    @Given("the following books")
    public void givenTheFollowingBooks(DataTable dataTable) {
        this.givenBooks = dataTable.asMaps(String.class, String.class);
    }

    @When("I add the books to the book storage")
    public void whenIAddTheBooksToTheBookService() {
        for (Map<String, String> bookData : givenBooks) {
            Book book = new Book();
            book.setTitle(bookData.get("title"));
            book.setPublishYear(Integer.parseInt(bookData.get("publishYear")));
            book.setAuthor(bookData.get("author"));
            book.setDescription(bookData.get("description"));
            book.setId(UUID.fromString(bookData.get("uuid")));
            bookService.addBook(book);
        }
    }

    @Then("the book storage should have the following books")
    public void thenTheBookServiceShouldHaveTheFollowingBooks(DataTable dataTable) {
        List<Map<String, String>> expectedBooks = dataTable.asMaps(String.class, String.class);
        List<Book> actualBooks = bookService.getBooks();
        assertEquals(expectedBooks.size(), actualBooks.size());

        for (Map<String, String> expectedBook : expectedBooks) {
            Book actualBook = bookService.getBookById(expectedBook.get("uuid"));

            assertNotNull(actualBook);
            assertEquals(expectedBook.get("title"), actualBook.getTitle());
            assertEquals(Integer.parseInt(expectedBook.get("publishYear")), actualBook.getPublishYear());
            assertEquals(expectedBook.get("author"), actualBook.getAuthor());
            assertEquals(expectedBook.get("description"), actualBook.getDescription());
        }
    }

    @Given("an empty book storage")
    public void anEmptyBookStorage() {
        this.bookService = BookService.getInstance();
        bookService.cleanAll();
    }

    @DataTableType(replaceWithEmptyString = "[blank]")
    public String stringType(String cell) {
        return cell;
    }
}
