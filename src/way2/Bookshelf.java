package way2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Bookshelf implements Serializable {
    private transient List<Book> books;
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

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeInt(books.size());
        for(Book b: books) {
            out.writeObject(b.getTitle());
            out.writeInt(b.getAuthors().size());
            for(Author a: b.getAuthors()) {
                out.writeObject(a.getName());
                out.writeObject(a.getSurname());
            }
            out.writeInt(b.getYearOfWriting());
        }
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        books = new ArrayList<>();

        int bookCount = in.readInt();
        for(int i = 0; i < bookCount; i++) {
            String title = (String) in.readObject();

            int authorCount = in.readInt();
            List<Author> authors = new ArrayList<>();
            for(int j = 0; j < authorCount; j++) {
                String authorName = (String) in.readObject();
                String authorSurname = (String) in.readObject();

                Author author = new Author(authorName, authorSurname);
                authors.add(author);
            }

            int yearOfWriting = in.readInt();
            Book book = new Book(title, authors, yearOfWriting);
            books.add(book);
        }
    }
}
