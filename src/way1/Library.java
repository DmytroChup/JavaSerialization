package way1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Library implements Serializable {
    private final List<Bookshelf> bookshelves;
    private final List<Reader> readers;
    private String name;

    public Library(String name) {
        this.name = name;
        this.bookshelves = new ArrayList<>();
        this.readers = new ArrayList<>();
    }

    public Book giveBook(Book book) {
        for(Bookshelf bookshelf: bookshelves) {
            if(bookshelf.getBook(book) != null) {
                return book;
            }
        }
        return null;
    }

    public List<Bookshelf> getBookshelves() {
        return bookshelves;
    }

    public void addBookshelf(Bookshelf bookshelf) {
        this.bookshelves.add(bookshelf);
    }

    public List<Reader> getReaders() {
        return readers;
    }

    public void registerReader(Reader reader) {
        this.readers.add(reader);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\nReaders: " + readers.stream().map(Reader::toString).collect(Collectors.joining("\n"))
                + "\nBookshelves: " + bookshelves.stream().map(Bookshelf::toString).collect(Collectors.joining("\n"));
    }
}
