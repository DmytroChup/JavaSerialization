package way2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Library implements Serializable {
    private final List<Bookshelf> bookshelves;
    private transient List<Reader> readers;
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

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeInt(readers.size());
        for(Reader reader: readers) {
            out.writeObject(reader.getName());
            out.writeObject(reader.getSurname());
            out.writeInt(reader.getReaderNumber());

            out.writeInt(reader.getBooks().size());
            for(Book book: reader.getBooks()) {
                out.writeObject(book.getTitle());

                out.writeInt(book.getAuthors().size());
                for(Author author: book.getAuthors()) {
                    out.writeObject(author.getName());
                    out.writeObject(author.getSurname());
                }
                out.writeInt(book.getYearOfWriting());
            }
        }
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        readers = new ArrayList<>();

        int readersCount = in.readInt();
        for(int i = 0; i < readersCount; i++) {
            String readerName = (String) in.readObject();
            String readerSurname = (String) in.readObject();
            int readerNumber = in.readInt();
            Reader reader = new Reader(readerName, readerSurname, readerNumber);

            int booksCount = in.readInt();
            for(int j = 0; j < booksCount; j++) {
                String bookTitle = (String) in.readObject();

                int authorsCount = in.readInt();
                List<Author> authors = new ArrayList<>();
                for(int k = 0; k < authorsCount; k++) {
                    String authorName = (String) in.readObject();
                    String authorSurname = (String) in.readObject();
                    Author author = new Author(authorName, authorSurname);
                    authors.add(author);
                }
                int bookYearOfWriting = in.readInt();
                Book book = new Book(bookTitle, authors, bookYearOfWriting);
                reader.setBooks(Collections.singletonList(book));
            }

            readers.add(reader);
        }
    }
}
