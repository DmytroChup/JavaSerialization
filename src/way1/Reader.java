package way1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Reader extends Person implements Serializable {
    private int readerNumber;
    private List<Book> books;

    public Reader(String name, String surname, int readerNumber) {
        this.setName(name);
        this.setSurname(surname);
        this.readerNumber = readerNumber;
        this.books = new ArrayList<>();
    }

    public void setBook(Book book) {
        books.add(book);
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public int getReaderNumber() {
        return readerNumber;
    }

    public void setReaderNumber(int readerNumber) {
        this.readerNumber = readerNumber;
    }

    public List<Book> getBooks() {
        return books;
    }

    @Override
    public String toString() {
        return super.toString() + "\nReader number: " + this.readerNumber
                + "\nBooks:" + books.stream().map(Book::getTitle).collect(Collectors.joining(", "));
    }
}
