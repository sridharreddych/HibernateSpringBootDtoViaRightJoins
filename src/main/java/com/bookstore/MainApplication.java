package com.bookstore;

import com.bookstore.service.BookstoreService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MainApplication {

    private final BookstoreService bookstoreService;

    public MainApplication(BookstoreService bookstoreService) {
        this.bookstoreService = bookstoreService;
    }

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Bean
    public ApplicationRunner init() {
        return args -> {

            System.out.println("\nfetchBooksAndAuthorsJpql: ");
            bookstoreService.fetchBooksAndAuthorsJpql()
                    .forEach((e) -> System.out.println(e.getName() + " | " + e.getTitle()));

            System.out.println("\nfetchBooksAndAuthorsSql: ");
            bookstoreService.fetchBooksAndAuthorsSql()
                    .forEach((e) -> System.out.println(e.getName() + " | " + e.getTitle()));

            System.out.println("\nfetchAuthorsAndBooksJpql: ");
            bookstoreService.fetchAuthorsAndBooksJpql()
                    .forEach((e) -> System.out.println(e.getName() + " | " + e.getTitle()));

            System.out.println("\nfetchAuthorsAndBooksSql: ");
            bookstoreService.fetchAuthorsAndBooksSql()
                    .forEach((e) -> System.out.println(e.getName() + " | " + e.getTitle()));
        };
    }
}

/*
 * How To Use Spring Projections(DTO) And Right Joins



Description: This application is a proof of concept for using Spring Projections(DTO) and right joins written via JPQL and native SQL (for MySQL).

Key points:

define two entities (e.g., Author and Book in a (lazy) bidirectional @OneToMany association)
populate the database with some test data (e.g., check the file resources/data-mysql.sql)
write interfaces (Spring projections) that contains getters for the columns that should be fetched from the database (e.g., check AuthorNameBookTitle.java)
write right joins queries using JPQL/SQL

 */
