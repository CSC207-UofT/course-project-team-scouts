# Progress Report

- [The Good Parts](#the-good-parts)
- [Questions and Concerns](#questions-and-concerns)
- [Group Member Roles](#group-member-roles)
  - [Aditya](#aditya)
  - [Daniel](#daniel)
  - [Kaartik](#kaartik)
  - [Matthew](#matthew)
  - [Michael](#michael)
  - [Tobey](#tobey)

## The Good Parts

*What has worked well with our design?*

## Questions and Concerns

*What are we struggling with?*

- There's no such thing as a static abstract method (in Java, at least)
  - We would like to have an abstract `Database` class that our `PlayerDatabase` and `TeamDatabase` can implement.
  - We want to make sure that all database classes have methods which return a list of the entities in the database, but with our current design, these methods are static.
  - Because Java doesn't allow static abstract methods, it's impossible to enforce a method like this in a parent class.
  - **Possible solution:** refactor our code so that these methods, along with the lists of entities, are no longer static. Instead, we will create a new instance of `PlayerDatabase` and `TeamDatabase` when the program runs, and then pass these instances as arguments to whatever methods depend on the databases (*e.g.* our search methods).
- Text formatting in the console
  - Some of the player names in our database contain non-Latin characters. These are not monospaced, but our current presenter functions assume that all output is monospaced.
  - The result is that column formatting is ruined, and attribute data becomes misaligned.
  - From our research, it seems like there is no real solution to this problem in Java, other than replacing non-Latin characters with a placeholder (*e.g.* `?`), or removing these players from the output entirely (this is our current solution).

## Group Member Roles

### Aditya

### Daniel

### Kaartik

### Matthew

Continued working on the Presenter classes, made lots of modifications to `PlayersPresenter` and implemented
`TeamsPresenter`. Also worked on the aggregated Statistics functionality for the program by implementing both the `TeamStatsCalculator` and `PlayerStatsCalculator` classes. 
Wrote tests for some of these classes and helped write about the packaging strategy
decision on the design document.

### Michael - Implemented TeamDatabase and Team classes. Reimplemnted CsvAdapter, Player and SearchForPlayer to enamble Team data loading, Player factory design method initialization and fuzzy name searching. Wrote some tests. 

### Tobey
