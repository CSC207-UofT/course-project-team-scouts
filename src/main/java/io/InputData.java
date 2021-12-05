package io;

import data.Database;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public interface InputData<T> {
    default String getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        return reader.readLine();
    }

    void run(Database<T> database);
}