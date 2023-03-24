package way3;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Reader extends Person {
    private int readerNumber;
    private List<Book> books;

    public Reader() {
    }

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

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        super.writeExternal(out);
        out.writeInt(this.readerNumber);

        out.writeInt(books.size());
        for(Externalizable book: books) {
            book.writeExternal(out);
        }
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        super.readExternal(in);
        readerNumber = in.readInt();

        books = new ArrayList<>();
        int booksCount = in.readInt();
        for(int i = 0; i < booksCount; i++) {
            Book book = new Book();
            book.readExternal(in);
            books.add(book);
        }
    }
}
