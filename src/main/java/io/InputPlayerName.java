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
    public void run(Database<Player> database) {
        String name;
        System.out.print("Enter a player's name: ");
        while (true) {
            try {
                name = getInput();
                break;
            } catch (IOException e) {
                System.out.println("An error occurred, please try again.\n");
            }
        }
        
        SearchByName<Player> searchByName = new SearchByName<>();
        searchResults = searchByName.search(database, name.strip());

        PlayersPresenter pPresenter = new PlayersPresenter();
        pPresenter.outputResults(searchResults);
    }
}