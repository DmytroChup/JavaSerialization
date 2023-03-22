package way1;

import java.io.Serializable;
public class Author extends Person implements Serializable {
    public Author(String name, String surname) {
        this.setName(name);
        this.setSurname(surname);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
