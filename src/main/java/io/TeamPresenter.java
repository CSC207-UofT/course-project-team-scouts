package io;

import data.TeamStatsCalculator;
import entities.Team;

import java.util.List;

class TeamPresenter implements PresentData<Team> {
    TeamStatsCalculator calculator = new TeamStatsCalculator();

    /**
     * Prints a well-formatted description of the key features of a list of
     * Team entities.
     *
     * @param teams the teams returned from a search.
     */
    @Override
    public void outputResults(List<Team> teams) {
        for (Team t : teams) {
            System.out.println("Team name: " + t.getName());
            System.out.println("Overall Rating: " + calculator.generateOverallRating(t));
            System.out.println("Net worth: " + calculator.generateNetWorth(t));
            System.out.println("Number of players: " + t.getPlayers().size());
        }
    }
}