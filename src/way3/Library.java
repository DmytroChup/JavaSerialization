package way3;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Library implements Externalizable {
    private List<Bookshelf> bookshelves;
    private List<Reader> readers;
    private String name;

    public Library() {
    }

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

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeUTF(name);

        out.writeInt(bookshelves.size());
        for(Externalizable bookshelf: bookshelves) {
            bookshelf.writeExternal(out);
        }

        out.writeInt(readers.size());
        for(Externalizable reader: readers) {
            reader.writeExternal(out);
        }
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        name = in.readUTF();

        int bookshelvesCount = in.readInt();
        bookshelves = new ArrayList<>();
        for(int i = 0; i < bookshelvesCount; i++) {
            Bookshelf bookshelf = new Bookshelf();
            bookshelf.readExternal(in);
            bookshelves.add(bookshelf);
        }

        int readersCount = in.readInt();
        readers = new ArrayList<>();
        for(int i = 0; i < readersCount; i++) {
            Reader reader = new Reader();
            reader.readExternal(in);
            readers.add(reader);
        }
    }
}
