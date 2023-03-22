package way3;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Bookshelf implements Externalizable {
    private List<Book> books;
    private String theme;

    public Bookshelf(String theme) {
        this.theme = theme;
        this.books = new ArrayList<>();
    }

    public Bookshelf() {
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

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeUTF(theme);

        out.writeInt(books.size());
        for(Externalizable book: books) {
            book.writeExternal(out);
        }
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        theme = in.readUTF();

        int booksCount = in.readInt();
        books = new ArrayList<>();
        for(int i = 0; i < booksCount; i++) {
            Book book = new Book();
            book.readExternal(in);
            books.add(book);
        }
    }
}
