package com.example.LiterAlura;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Author {
    private String name;
    private int birthYear;
    private int deathYear;
    private List<Book> books;

    public Author(String name, int birthYear, int deathYear) {
        this.name = name;
        this.birthYear = birthYear;
        this.deathYear = deathYear;
        this.books = new ArrayList<>();
    }

    public void addBook(Book book) {
        this.books.add(book);
        book.addAuthor(this);
    }

    public String getName() {
        return this.name;
    }

    public int getBirthYear() {
        return this.birthYear;
    }

    public int getDeathYear() {
        return this.deathYear;
    }

    public String getBooks() {
        return this.books.stream().map(Book::getTitle).collect(Collectors.joining(","));
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Author) {
            return this.getName().compareTo(((Author) o).getName()) == 0 
                && this.getBirthYear() == ((Author) o).getBirthYear() 
                && this.getDeathYear() == ((Author) o).getDeathYear();
        }

        return false;
    }

    @Override
    public String toString() {
        return "\nAutor: " + this.getName() + "\n" + 
               "Fecha de nacimiento: " + this.getBirthYear() + "\n" + 
               "Fecha de fallecimiento: " + this.getDeathYear() + "\n" + 
               "Libros: " + this.getBooks();
    }
}