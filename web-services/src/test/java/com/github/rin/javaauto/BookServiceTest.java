package com.github.rin.javaauto;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BookServiceTest {

    private static BookService bookService;

    @BeforeAll
    static void init() {
        bookService = BookService.getInstance();

        //If there's testData.txt in test/resources
        // assumption will become false and won't call addTestData();
        Assumptions.assumeTrue(bookService.getBooks().isEmpty());
        addTestData();
    }

    private static void addTestData() {
        BookService bookService = BookService.getInstance();

        Book book1 = new Book();
        book1.setTitle("Sample Book 1");
        book1.setPublishYear(2020);
        book1.setAuthor("John Doe");
        book1.setDescription("A sample book description");

        Book book2 = new Book();
        book2.setTitle("Sample Book 2");
        book2.setPublishYear(2021);
        book2.setAuthor("Jane Smith");
        book2.setDescription("Another sample book description");

        Book book3 = new Book();
        book3.setTitle("Sample Book 3");
        book3.setPublishYear(2019);
        book3.setAuthor("Michael Brown");
        book3.setDescription("Yet another sample book description");

        bookService.addBook(book1);
        bookService.addBook(book2);
        bookService.addBook(book3);

        List<Book> books = BookService.getInstance().getBooks();

        assertThat(books).contains(book1, book2, book3);
    }

    @Test
    @DisplayName("Deleting a book")
    void deleteBook() {
        Book book = new Book();
        book.setTitle("Little Prince");
        book.setDescription("A classic novella");
        book.setAuthor("Antoine de Saint-Exupéry");
        book.setPublishYear(1943);

        bookService.addBook(book);

        assertThat(bookService.getBooks())
                .contains(book);

        bookService.removeBook(book.getId());

        assertThat(bookService.getBooks())
                .doesNotContain(book);
    }

    @Test
    @DisplayName("Adding a book")
    void addingBookTest() {
        Book book = new Book();
        book.setTitle("Test title");
        book.setDescription("Test description");
        book.setAuthor("Test author");
        book.setPublishYear(2024);
        bookService.addBook(book);

        assertThat(bookService.getBooks()).contains(book);
    }

    @ParameterizedTest
    @DisplayName("Adding books with empty titles")
    @ValueSource(strings = {
            "", // empty string
            " ", // whitespace
            "       ", // multiple whitespaces
            "\t", // tab
            "\t\t\t\t", // multiple tabs
            "\t \t   \t   \t", // tabs with whitespaces
            "\n", // new line
            "\n \n \n", // multiple new lines
            "\r", //carriage return
            "\r \r \r \r", // multiple carriage returns
            "\t \n \r \t  \r    \n   "  // everything at once in random sequence
    })
    void emptyTitlesTest(String title) {
        BookService bookService = BookService.getInstance();

        Book book = new Book();
        book.setTitle(title);
        book.setDescription("Description placeholder");
        book.setAuthor("Author placeholder");
        book.setPublishYear(2024);

        bookService.addBook(book);

        Assertions.assertNull(bookService.getBookById(book.getId().toString()));
    }

    @ParameterizedTest
    @DisplayName("Adding books with empty descriptions")
    @ValueSource(strings = {
            "", // empty string
            " ", // whitespace
            "       ", // multiple whitespaces
            "\t", // tab
            "\t\t\t\t", // multiple tabs
            "\t \t   \t   \t", // tabs with whitespaces
            "\n", // new line
            "\n \n \n", // multiple new lines
            "\r", //carriage return
            "\r \r \r \r", // multiple carriage returns
            "\t \n \r \t  \r    \n   "  // everything at once in random sequence
    })
    void emptyDescriptionsTest(String description) {
        BookService bookService = BookService.getInstance();

        Book book = new Book();
        book.setTitle("Title placeholder");
        book.setDescription(description);
        book.setAuthor("Author placeholder");
        book.setPublishYear(2024);

        bookService.addBook(book);

        Assertions.assertNull(bookService.getBookById(book.getId().toString()));
    }

    @ParameterizedTest
    @DisplayName("Adding books with empty authors")
    @ValueSource(strings = {
            "", // empty string
            " ", // whitespace
            "       ", // multiple whitespaces
            "\t", // tab
            "\t\t\t\t", // multiple tabs
            "\t \t   \t   \t", // tabs with whitespaces
            "\n", // new line
            "\n \n \n", // multiple new lines
            "\r", //carriage return
            "\r \r \r \r", // multiple carriage returns
            "\t \n \r \t  \r    \n   "  // everything at once in random sequence
    })
    void emptyAuthorsTest(String author) {
        BookService bookService = BookService.getInstance();

        Book book = new Book();
        book.setTitle("Title placeholder");
        book.setDescription("Description placeholder");
        book.setAuthor(author);
        book.setPublishYear(2024);

        bookService.addBook(book);

        Assertions.assertNull(bookService.getBookById(book.getId().toString()));
    }

    @ParameterizedTest
    @DisplayName("Adding books with negative publish year")
    @ValueSource(ints = {
            -1,
            -2,
            -100,
            -234324,
            Integer.MIN_VALUE
    })
    void negativePublishYearTest(int year) {
        BookService bookService = BookService.getInstance();

        Book book = new Book();
        book.setTitle("Title placeholder");
        book.setDescription("Description placeholder");
        book.setAuthor("Author placeholder");
        book.setPublishYear(year);

        bookService.addBook(book);

        Assertions.assertNull(bookService.getBookById(book.getId().toString()));
    }

    @AfterAll
    static void cleanAll() {
        bookService.cleanAll();
    }
}
