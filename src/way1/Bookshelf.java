package way1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Bookshelf implements Serializable {
    private List<Book> books;
    private String theme;

    public Bookshelf(String theme) {
        this.theme = theme;
        this.books = new ArrayList<>();
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public void addBook(Book book) {
        this.books.add(book);
    }

    public Book getBook(Book book) {
        return removeBook(book);
    }

    private Book removeBook(Book book) {
        if(books.size() != 0) {
            if(books.remove(book)) {
                return book;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Theme: " + theme + "\nBooks: " + books.stream().map(Book::toString).collect(Collectors.joining("\n"));
    }
}
