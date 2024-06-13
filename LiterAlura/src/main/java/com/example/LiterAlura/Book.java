package com.example.LiterAlura;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Book {
    private int id;
    private String title;
    private List<Author> authors;
    private List<String> languages;
    private int downloadCount;

    public Book(int id, String title, int downloadCount) {
        this.id = id;
        this.title = title;
        this.downloadCount = downloadCount;
        this.authors = new ArrayList<>();
        this.languages = new ArrayList<>();
    }

    public void addAuthor(Author author) {
        this.authors.add(author);
    }

    public void addLanguage(String language) {
        this.languages.add(language);
    }

    public String getTitle() {
        return this.title;
    }

    public String getAuthors() {
        return this.authors.stream().map(Author::getName).collect(Collectors.joining(","));
    }

    public String getLanguages() {
        return this.languages.stream().collect(Collectors.joining(","));
    }

    public int getDownloadCount() {
        return this.downloadCount;
    }

    @Override
    public String toString() {
        return "\n----- LIBRO -----\n" + 
               "titulo: " + this.getTitle() + "\n" + 
               "Author: " + this.getAuthors() + "\n" + 
               "Idioma: " + this.getLanguages() + "\n" + 
               "Numero de descargas: " + this.getDownloadCount();
    }
}