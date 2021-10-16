# Behind-the-Scenes Scenario Walkthrough

This file contains more technical details than the other walkthrough files. If you'd like to make changes, please submit a pull request or let the others know what you're changing.

## general notes

- this plan deviates from the CRC cards in many ways; I am working on updating the cards to match
- all the `Database` classes store entities in a private, static list
  - the classes include `addEntity` methods (add to the list) and `getEntities` methods (return the list)
- all the `____Input` classes display prompts, accept inputs, and pass that input to a search class

## program startup

- **BTS:** a `CommandLine` class will display all of the main prompts, but it call methods from relevant classes to do everything else (handling user input)
    - `CommandLine` is sorta like the main class
- **BTS:** `CommandLine` calls on the `CSVAdapter` class, which will fetch all the players and teams from the CSV file
    - it will add players to `PlayerDatabase` and teams to `TeamDatabase` by calling their `addEntity` method
    - alternatively, we could have an actual `Main` class that calls on `CSVAdapter` before `CommandLine` does anything

1. select an existing scout profile (corresponds to a `Scout` object), or create a new one (**THIS WILL NOT BE A PART OF PHASE 0**)
    <!-- - **BTS:** the `ScoutDatabase` class holds `Scouts`
    - **BTS:** `CommandLine` calls on `ScoutsPresenter`, which presents a list of scouts from `ScoutDatabase`
	- **BTS:** `ScoutInput` will ask the user to input a scouts name
    1. if creating a new one, specify the name, team, and type (player scout or tactical scout)
	    - **BTS:** `ScoutInput` will call an adder method from `ScoutDatabase`, then set the new scout to the current scout -->
2. present options:
	1. search for a player
	2. search for a team (**THIS WILL NOT BE A PART OF PHASE 0**)

## searching entities

1. **search by attributes**
    - **BTS:** call on `InputPlayerAttributes` to handle user input (it will display the prompts described below)
        - `InputPlayerAttributes` implements the `InputData` interface
    1. allow user to specify which attributes are relevan
        - present the user with a list (or table), and they can enter the attributes they care about (separated by commas)
        - alternatively, present a prompt for every attribute, and the user can skip ones they don't care about (by pressing `Enter`)
    2. then, allow user to specify acceptable ranges of values for those attributes
        - prompt user for input, one attribute at a time
    - **BTS:** `InputPlayerAttributes` will call on `SearchByPlayerAttributes` after recieving all input
        - then `SearchByPlayerAttributes` will return a list of search results
        - then `InputPlayerAttributes` will pass this list to a presenter (specifically `PlayersPresenter`, see [presenting results](#presenting-results))
2. **search by name**
    - **BTS:** call on `InputPlayer` to handle user input (it will display a prompt)
    - allow user to enter a string, and search for any player whose name contains that string
    - **BTS:** `InputPlayer` will call on `SearchForPlayer` after name has been input
        - then `SearchForPlayer` will return a list of search results
        - then `InputPlayer` will pass this list to a presenter (specifically `PlayersPresenter`, see [presenting results](#presenting-results))

<!-- 4. if searching for a team:
	1. search by attributes?
	    - **BTS:** call on `TeamInput` to handle user input (it will display the prompts)
		1. allow user to specify which attributes are relevant: present the user with a list (or table), and they can enter the attributes they care about (separated by commas)
		2. then, allow user to specify acceptable ranges of values for those attributes (maybe enter integer values one by one?)
			- *e.g.* `Minimum acceptable value for 'Defense Pressure': `
	    - **BTS:** call on `SearchByTeamAttributes` after attributes have been input
	2. search by name?
	    - **BTS:** again, call on `TeamInput` to handle user input (it will display the prompts)
		1. allow user to enter a string, and search for any team whose name contains that string
			- *e.g.* `Manchester` will yield `Manchester United` and `Manchester City`
	    - **BTS:** call on `SearchForTeam` after name has been input -->

## presenting results

- searching by team or player, name or attributes will always yield a (possibly empty) list of results
	- **BTS:** `PlayersPresenter` or `TeamsPresenter` will do exactly this
- each entity's name should be printed, along with a summary of some important details
	- teams should be printed with their size (how many players in the roster) and possibly a list of current players
	- players should be printed with their age, weight, height, country

### extra presenting feature

- each entity should be numbered
	- allow the user to input a team/player's corresponding number so they can see them in more detail (see ALL of the attributes)
	    - **BTS:** this should call on `PlayerPresenter` or `TeamPresenter`
	- pressing `Enter` will let them go back to the list, and pressing `Enter` again will let them make a new query

## future features

- during the [searching](#searching-entities) phase, we can allow the user to choose from a list of previous queries
	- maybe each time they want to enter a new query, allow them to name it
	- the user can choose to discard a query if they don't want to use it again later
- when searching by attribute, we can allow the user to weight the importance of each attribute
	- *e.g.* the user can specify that they care about height the most; or stamina and volleys equally, but agility less
- adding scouting functionality
    - the user can scout and unscout players in the database
    - the `Scout` object will keep track of scouting history, and this history can be viewed using `ScoutPresenter`
