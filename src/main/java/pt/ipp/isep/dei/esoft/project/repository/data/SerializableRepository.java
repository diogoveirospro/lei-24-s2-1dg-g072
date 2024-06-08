package pt.ipp.isep.dei.esoft.project.repository.data;

import java.io.*;

public abstract class SerializableRepository<T> {
    private String filename;

    public SerializableRepository() {
    }



    public SerializableRepository(String filename) {
        this.filename = filename;
    }

    public void save(T data) {
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream("src/main/java/pt/ipp/isep/dei/esoft/project/repository/data/" + filename));
            out.writeObject(data);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null){
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    protected T load() {
        T data = null;
        ObjectInputStream ois = null;
        try  {
            ois = new ObjectInputStream(new FileInputStream("src/main/java/pt/ipp/isep/dei/esoft/project/repository/data/" + filename));
            data = (T) ois.readObject();
            return data;
        } catch (EOFException e) {
            System.err.println("EOFException caught while loading " + filename);
            e.printStackTrace();
            return null;
        } catch (FileNotFoundException e) {
            System.err.println("FileNotFoundException caught while loading " + filename);
            e.printStackTrace();
            return null;
        } catch (StreamCorruptedException e) {
            System.err.println("StreamCorruptedException caught while loading " + filename);
            e.printStackTrace();
            clear();
            return null;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("IOException or ClassNotFoundException caught while loading " + filename);
            e.printStackTrace();
        } finally {
            if (ois != null){
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return data;
    }
    public void clear() {
        try (PrintWriter writer = new PrintWriter("src/main/java/pt/ipp/isep/dei/esoft/project/repository/data/" + filename)) {
            writer.print("");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}