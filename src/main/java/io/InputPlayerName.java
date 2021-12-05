package io;

import data.Database;
import entities.Player;
import search.SearchByName;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class InputPlayerName implements InputData<Player> {
    // Instance variable storing the search results after a search by attributes is completed.
    List<Player> searchResults;

    @Override
    public String getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        return reader.readLine();
    }

    @Override
    public void run(Database<Player> database) {
        String name = "";
        System.out.print("Enter a player's name: ");
        try {
            name = this.getInput();
        } catch (IOException e) {
            System.out.println("Invalid player name.");
        }

        try {
            if (name != null) {
                SearchByName<Player> searchByName = new SearchByName<>();
                this.searchResults = searchByName.search(database, name);
                PlayersPresenter pPresenter = new PlayersPresenter();
                pPresenter.outputResults(searchResults);
            }
        } catch (Exception e) {
            System.out.println("Empty name.");
        }
    }
}