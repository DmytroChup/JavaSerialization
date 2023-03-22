package way1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Book implements Serializable {
    private String title;
    private List<Author> authors;
    private int yearOfWriting;

    public Book(String title, Author author, int yearOfWriting) {
        this.authors = new ArrayList<>();
        this.authors.add(author);

        this.title = title;
        this.yearOfWriting = yearOfWriting;
    }

    public Book(String title, List<Author> authors, int yearOfWriting) {
        this.title = title;
        this.authors = authors;
        this.yearOfWriting = yearOfWriting;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public int getYearOfWriting() {
        return yearOfWriting;
    }

    public void setYearOfWriting(int yearOfWriting) {
        this.yearOfWriting = yearOfWriting;
    }

    @Override
    public String toString() {
        return "Title: " + title + "\nYear of writing: " + yearOfWriting
                + "\nAuthor(s): " + authors.stream().map(Author::toString).collect(Collectors.joining(", "));
    }
}
