Feature: Book Validation

  Background: A book storage where you can store books
    Given an empty book storage

  Scenario:Adding only valid books
    Given the following books
      | uuid                                 | title                 | publishYear | author              | description                  |
      | 83987404-4112-4210-b9a5-b70ecc1a7393 | The Great Gatsby      | 1925        | F. Scott Fitzgerald | Life of a millionaire        |
      | ea3014c3-f932-4c47-8c58-bef6758f61fa | 1984                  | 1949        | George Orwell       | Dystopian novel              |
      | 0ef726cf-df70-4cd4-9905-dcfe2309f088 | To Kill a Mockingbird | 1960        | Harper Lee          | Novel about racial injustice |
    When I add the books to the book storage
    Then the book storage should have the following books
      | uuid                                 | title                 | publishYear | author              | description                  |
      | 83987404-4112-4210-b9a5-b70ecc1a7393 | The Great Gatsby      | 1925        | F. Scott Fitzgerald | Life of a millionaire        |
      | ea3014c3-f932-4c47-8c58-bef6758f61fa | 1984                  | 1949        | George Orwell       | Dystopian novel              |
      | 0ef726cf-df70-4cd4-9905-dcfe2309f088 | To Kill a Mockingbird | 1960        | Harper Lee          | Novel about racial injustice |

  Scenario:Adding valid and invalid books
    Given the following books
      | uuid                                 | title                 | publishYear | author              | description                  |
      | 83987404-4112-4210-b9a5-b70ecc1a7393 | [blank]      | 1925        | F. Scott Fitzgerald | Life of a millionaire        |
      | ea3014c3-f932-4c47-8c58-bef6758f61fa | 1984                  | -104        | George Orwell       | Dystopian novel              |
      | 0ef726cf-df70-4cd4-9905-dcfe2309f088 | To Kill a Mockingbird | 1960        | Harper Lee          | Novel about racial injustice |
      | c9c776a2-7392-4464-a237-a63e791ab679 | To Kill a Mockingbird | 1960        | [blank]          | Novel about racial injustice |
    When I add the books to the book storage
    Then the book storage should have the following books
      | uuid                                 | title                 | publishYear | author              | description                  |
      | 0ef726cf-df70-4cd4-9905-dcfe2309f088 | To Kill a Mockingbird | 1960        | Harper Lee          | Novel about racial injustice |