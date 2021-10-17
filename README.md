# Team Scouts

A CSC207 course project by Tobey Brizuela, Aditya Peri, Matthew Parvaneh, Michael Umeh Kaartik Issar, and Daniel Lazaro. We're making a football player scouting app.

## To-Do

### `Player` class

- [ ] add value attribute to players
- [ ] use `Skill` class instead of `HashMap` for storing player skills (?)
- [ ] use `Team` class instead of `String` for storing player's team

### `Search____` classes

- [ ] combine `SearchForPlayer` and `SearchByPlayerAttributes` (?)
  - instead of using separate classes, we can have one `Player` search class with an overloaded method
  - one version of the method takes in a single argument (a player's name)
  - the other version takes in a mapping of skills with whatever values the user specified
- [ ] also combine `SearchForTeam` and `SearchByTeamAttributes` (?)
