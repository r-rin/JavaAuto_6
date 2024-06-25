Feature: Get book by given ID
  If given ID is actually a UUID and book with such ID exists, then it must return something. Otherwise – null.

  Background: A book storage where you can store books
    Given a book storage I just created

  Scenario: ID is not in UUID format
    Given an invalid UUID ID: "abcd123av4567"
    When the system attempts to retrieve a book with this ID
    Then it should return null

  Scenario: ID is a valid UUID but book does not exist
    Given a valid UUID ID of a non-existing book: "8524e317-7274-4763-9cb3-af22085eab7d"
    When the system attempts to retrieve a book with this ID
    Then it should return null

  Scenario: ID is a valid UUID and book exists
    Given a valid UUID ID of an existing book: "ae857ccc-27b5-4c77-b270-c7231fdfa940"
    When the system attempts to retrieve a book with this ID
    Then it should return the book