package io;

import data.Database;
import entities.Player;
import search.SearchByName;

import java.io.IOException;
import java.util.List;

public class InputPlayerName implements InputData<Player> {
    // Instance variable storing the search results after a search by attributes is completed.
    List<Player> searchResults;

    /**
     * Prompts a user to input the name of a player for a search by name,
     * passing it along to the search use case. Passes results of search as
     * input to a presenter after search completes.
     *
     * @param database a database of Players passed along to the search use case.
     */
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