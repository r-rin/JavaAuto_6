package com.github.rin.javaauto;

import java.util.Objects;
import java.util.UUID;

/**
 * Represents a book with a title, publish year, author, and description.
 */
public final class Book {

    /** Unique identifier for the book. */
    private UUID id;

    /** Title of the book. */
    @NotEmpty
    private String title;

    /** Publish year of the book. */
    @MinValue(0)
    private int publishYear;

    /** Author of the book. */
    @NotEmpty
    private String author;

    /** Description of the book. */
    @NotEmpty
    private String description;

    /**
     * Default constructor. Generates a new UUID for the book.
     */
    public Book() {
        this.id = UUID.randomUUID();
    }

    /**
     * Sets the title of the book.
     * @param bookTitle The title to set.
     */
    public void setTitle(final String bookTitle) {
        this.title = bookTitle;
    }

    /**
     * Sets the publish year of the book.
     * @param bookPublishYear The publish year to set.
     */
    public void setPublishYear(final int bookPublishYear) {
        this.publishYear = bookPublishYear;
    }

    /**
     * Sets the author of the book.
     * @param bookAuthor The author to set.
     */
    public void setAuthor(final String bookAuthor) {
        this.author = bookAuthor;
    }

    /**
     * Sets the description of the book.
     * @param bookDescription The description to set.
     */
    public void setDescription(final String bookDescription) {
        this.description = bookDescription;
    }

    /**
     * Gets the title of the book.
     * @return The title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets the publish year of the book.
     *
     * @return The publish year.
     */
    public int getPublishYear() {
        return publishYear;
    }

    /**
     * Gets the author of the book.
     * @return The author.
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Gets the description of the book.
     * @return The description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the unique identifier of the book.
     * @param bookId The UUID to set.
     */
    public void setId(final UUID bookId) {
        this.id = bookId;
    }

    /**
     * Gets the unique identifier of the book.
     * @return The UUID.
     */
    public UUID getId() {
        return id;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * @param o The reference object with which to compare.
     * @return {@code true} if this object is the same as the obj argument;
     * {@code false} otherwise.
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Book book = (Book) o;
        return Objects.equals(id, book.id);
    }

    /**
     * Returns a hash code value for the object.
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    /**
     * Returns a string representation of the book.
     * @return A string representation of the book.
     */
    @Override
    public String toString() {
        return "Book{"
                + "id="
                + id
                + ", title='"
                + title + '\''
                + ", publishYear='"
                + publishYear
                + '\''
                + ", author='"
                + author
                + '\''
                + ", description='"
                + description
                + '\''
                + '}';
    }
}
