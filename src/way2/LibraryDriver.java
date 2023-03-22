package way2;

import java.io.*;

public class LibraryDriver {
    private static final File file = new File("src/data/way2.dat");

    public static void serializeObj(Library library) {
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
            out.writeObject(library);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Library deserializeObj() {
        Library library;
        try(ObjectInputStream ins = new ObjectInputStream(new FileInputStream(file))) {
            library = (Library) ins.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return library;
    }
}
