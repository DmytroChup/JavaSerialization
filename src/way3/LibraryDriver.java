package way3;

import java.io.*;

public class LibraryDriver {
    private static final File file = new File("src/data/way3.dat");

    public static void serializeObj(Externalizable library) {
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
            library.writeExternal(out);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Object deserializeObj() {
        Externalizable library = new Library();
        try(ObjectInputStream ins = new ObjectInputStream(new FileInputStream(file))) {
            library.readExternal(ins);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return library;
    }
}
