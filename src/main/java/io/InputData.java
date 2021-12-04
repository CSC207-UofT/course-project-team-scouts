package io;

import data.Database;

import java.io.IOException;

public interface InputData<T> {
    String getInput() throws IOException;

    void run(Database<T> database);
}