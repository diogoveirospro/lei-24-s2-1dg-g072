package pt.ipp.isep.dei.esoft.project.repository.data;

import java.io.*;

public abstract class SerializableRepository<T> {
    private String filename;

    public SerializableRepository(String filename) {
        this.filename = filename;
    }

    public void save(T data) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(
                "src/main/java/pt/ipp/isep/dei/esoft/project/repository/data/" + filename))) {
            out.writeObject(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    protected T load() {
        T data = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/main/java/pt/ipp/isep/dei/esoft/project/repository/data/" + filename))) {
            data = (T) ois.readObject();
        } catch (EOFException e) {
            return null;
        } catch (FileNotFoundException e) {
            System.err.println("File not found. Returning null: " + filename);
            return null;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return data;
    }
}