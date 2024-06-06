package pt.ipp.isep.dei.esoft.project.repository.data;

import java.io.*;

public abstract class SerializableRepository<T> {
    private String filename;

    public SerializableRepository(String filename) {
        this.filename = filename;
    }

    public void save(T data) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public T load() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            return (T) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}