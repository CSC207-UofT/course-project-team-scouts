# Scenario Walk-Through

The user is presented with a welcome/startup message, and then is prompted to choose how they would like to search (handled by `CommandLine`). 
They may choose to search for a player or a team. For example, let's say that the user chooses to search for a player.
Then, the user will be asked if they would like to search for players by name or by specifying attributes.
Let's say that the user chooses to search based on attributes.
They will be given a series of prompts asking them to specify a value for each attribute (handled by `InputPlayerAttributes`).
For example, the console will display `Age:`, and the user could enter `22`. 
Or, as another example, the console will display `Strength (from 1 to 100):`, and the user could enter `72`.
This process will repeat until the user has entered a desired value for all attributes.
Once all attribute values have been entered, the application will search for all players (in `PlayerDatabase`) which match the user's specification (handled by `SearchByPlayerAttributes`).
The results of the search will be passed to a presenter class (`PlayersPresenter`) which displays the names and attributes of the matching players.

If instead the user chooses to search by name, they will be given a prompt asking them to specify a name (handled by `InputPlayerName`).
For example, the user may input `Danny`. 
The application will search for all players (in `PlayerDatabase`) which contain `Danny` somewhere in their name (handled by `SearchForPlayer`).
The results of the search will be passed to the same presenter class (`PlayersPresenter`), and the user will see a similar output. 
Similar processes would occur if a user chose to search for teams at the beginning.
But of course, different classes are involved in those processes (*e.g.* `InputTeamAttributes`, `TeamsPresenter`, etc.).