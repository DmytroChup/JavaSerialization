package way2;

import java.util.Collections;

public class Test {
    public static void main(String[] args) {
        Library library = new Library("Library");

        Bookshelf bookshelf1 = new Bookshelf("Programming");
        Bookshelf bookshelf2 = new Bookshelf("History");
        Bookshelf bookshelf3 = new Bookshelf("Science");

        Book b1 = new Book("book1", new Author("Name1", "Surname1"), 2000);
        Book b2 = new Book("book2", new Author("Name2", "Surname2"), 2023);
        Book b3 = new Book("book3", new Author("Name3", "Surname3"), 1999);
        Book b4 = new Book("book4", new Author("Name4", "Surname4"), 1923);
        Book b5 = new Book("book5", new Author("Name5", "Surname5"), 1900);
        Book b6 = new Book("book6", new Author("Name6", "Surname6"), 1999);
        Book b7 = new Book("book7", new Author("Name7", "Surname7"), 1990);
        Book b8 = new Book("book8", new Author("Name8", "Surname8"), 2011);
        Book b9 = new Book("book9", new Author("Name9", "Surname9"), 2014);

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

        Reader bookReader1 = new Reader("NameReader1", "SurnameReader1", 1);
        Reader bookReader2 = new Reader("NameReader2", "SurnameReader2", 2);


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
