package way3;

import java.util.Collections;

public class Test {
    public static void main(String[] args) {
        Library library = new Library("Central library of KhNU V.N.Karazina");

        Bookshelf bookshelf1 = new Bookshelf("Science");
        Bookshelf bookshelf2 = new Bookshelf("History");
        Bookshelf bookshelf3 = new Bookshelf("Chemistry");

        Book b1 = new Book("T1", new Author("Name1", "Surname1"), 1996);
        Book b2 = new Book("T2", new Author("Name2", "Surname2"), 2011);
        Book b3 = new Book("T3", new Author("Name3", "Surname3"), 2014);
        Book b4 = new Book("T4", new Author("Name4", "Surname4"), 1995);
        Book b5 = new Book("T5", new Author("Name5", "Surname5"), 1965);
        Book b6 = new Book("T6", new Author("Name6", "Surname6"), 2007);
        Book b7 = new Book("T7", new Author("Name7", "Surname7"), 1969);
        Book b8 = new Book("T8", new Author("Name8", "Surname8"), 2018);
        Book b9 = new Book("T10", new Author("Name9", "Surname9"), 1999);

        bookshelf1.addBook(b1);
        bookshelf1.addBook(b2);

        bookshelf2.addBook(b3);
        bookshelf2.addBook(b4);
        bookshelf2.addBook(b5);

        bookshelf3.addBook(b6);

        bookshelf1.addBook(b7);
        bookshelf2.addBook(b8);
        bookshelf3.addBook(b9);


        library.addBookshelf(bookshelf1);
        library.addBookshelf(bookshelf2);
        library.addBookshelf(bookshelf3);

        Reader bookReader1 = new Reader("NameBR1", "SurnameBR1", 33);
        Reader bookReader2 = new Reader("NameBR2", "SurnameBR2", 99);


        library.registerReader(bookReader1);
        library.registerReader(bookReader2);


        bookReader2.setBooks(Collections.singletonList(library.giveBook(b1)));
        bookReader2.setBooks(Collections.singletonList(library.giveBook(b2)));
        bookReader2.setBooks(Collections.singletonList(library.giveBook(b3)));
        bookReader2.setBooks(Collections.singletonList(library.giveBook(b4)));

        bookReader1.setBooks(Collections.singletonList(library.giveBook(b5)));
        bookReader1.setBooks(Collections.singletonList(library.giveBook(b6)));

        System.out.println("Before serialization");
        System.out.println(library);

        System.out.println("\nAfter deserialization");
        LibraryDriver.serializeObj(library);
        System.out.println(LibraryDriver.deserializeObj());
    }
}
