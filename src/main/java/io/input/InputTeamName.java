package io.input;

import data.Database;
import entities.Team;
import io.output.TeamsPresenter;
import search.SearchByName;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class InputTeamName implements InputData<Team> {
    // Storing a list of the results returned by a search.
    List<Team> searchResults;

    /**
     * Takes in a single line of user input from the console.
     *
     * @return a string of the input entered by user in the console.
     * @throws IOException for when reader takes input from console that cannot be handled.
     */
    @Override
    public String getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        return reader.readLine();
    }

    /**
     * Prompts a user to input the name of a team for a search by name,
     * passing it along to the search use case. Passes results of search as
     * input to a presenter after search completes.
     *
     * @param database a database of Teams passed along to the search use case.
     */
    @Override
    public void run(Database<Team> database) {
        String teamName = "";
        System.out.print("Enter the name of the team: ");
        try {
            teamName = this.getInput();
        } catch (IOException e) {
            System.out.println("Invalid team name.");
        }

        try {
            if (teamName != null) {
                SearchByName<Team> searchByName = new SearchByName<>();
                this.searchResults = searchByName.search(database, teamName);
                TeamsPresenter tPresenter = new TeamsPresenter();
                tPresenter.outputResults(searchResults);
            }
        } catch (Exception e) {
            System.out.println("Empty name.");
        }
    }
}
