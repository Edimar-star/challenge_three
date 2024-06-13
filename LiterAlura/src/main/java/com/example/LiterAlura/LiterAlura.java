package com.example.LiterAlura;

import java.util.List;
import java.util.Optional;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.stream.Collectors;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.CloseableHttpClient;

public class LiterAlura {
    private List<Book> books;
    private List<Author> authors;

    public LiterAlura() {
        this.books = new ArrayList<>();
        this.authors = new ArrayList<>();
        this.getInformation();
    }

    public Optional<Book> getBookByTitle(String title) {
        return this.books.stream()
                    .filter(book -> book.getTitle().compareTo(title) == 0)
                    .findFirst();
    }

    public List<Book> allBooks() {
        return this.books;
    }

    public List<Author> allAuthors() {
        return this.authors;
    }

    public List<Author> allAuthorsAliveByYear(int year) {
        return this.authors.stream()
                    .filter(author -> author.getBirthYear() < year && year < author.getDeathYear())
                    .collect(Collectors.toList());
    }

    public List<Book> allBooksByLanguage(String language) {
        return this.books.stream()
                    .filter(book -> book.getLanguages().contains(language))
                    .collect(Collectors.toList());
    }

    private void getInformation() {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {  
            String url = "https://gutendex.com/books";
            HttpGet request = new HttpGet(url);
            HttpResponse response = httpClient.execute(request);
            String jsonResponse = EntityUtils.toString(response.getEntity());

            JSONObject jsonObject = new JSONObject(jsonResponse);
            JSONArray results = jsonObject.getJSONArray("results");

            // Books
            for (int i = 0; i < results.length(); i++) {
                JSONObject bookObject = results.getJSONObject(i);
                int id = bookObject.getInt("id");
                String title = bookObject.getString("title");
                int download_count = bookObject.getInt("download_count");
                Book book = new Book(id, title, download_count);

                // Languages
                JSONArray languagesArray = bookObject.getJSONArray("languages");
                for (int j = 0; j < languagesArray.length(); j++) {
                    book.addLanguage(languagesArray.getString(j));
                }

                // Authors
                JSONArray authorsArray = bookObject.getJSONArray("authors");
                for (int j = 0; j < authorsArray.length(); j++) {
                    JSONObject authorObject = authorsArray.getJSONObject(j);
                    String name = authorObject.getString("name");
                    int birth_year = authorObject.getInt("birth_year");
                    int death_year = authorObject.getInt("death_year");
                    Author author = new Author(name, birth_year, death_year);

                    if (authors.contains(author)) {
                        int index = this.authors.indexOf(author);
                        this.authors.get(index).addBook(book);
                    } else {
                        author.addBook(book);
                        this.authors.add(author);
                    }
                }

                this.books.add(book);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}