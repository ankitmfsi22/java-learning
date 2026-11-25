package com.mindfire.banking.storage;

import com.mindfire.banking.exception.BankException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.function.Supplier;

public class FileStorage<T extends Serializable> {
    private final File file;

    public FileStorage(String fileName) {
        this.file = new File(fileName);
    }

    @SuppressWarnings("unchecked")
    public T load(Supplier<T> defaultValue) {
        if (!file.exists()) {
            return defaultValue.get();
        }

        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(file))) {
            return (T) input.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new BankException("Could not load saved data: " + e.getMessage());
        }
    }

    public synchronized void save(T data) {
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(file))) {
            output.writeObject(data);
        } catch (IOException e) {
            throw new BankException("Could not save data: " + e.getMessage());
        }
    }
}