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

- We've implemented design patterns where applicable, making our code adhere more to SOLID design and Clean Architecture (see [`phase1/design_document.md](https://github.com/CSC207-UofT/course-project-team-scouts/blob/main/phase1/design_docmument.md)).
  - Includes adapter, factory, and builder design patterns.
- The refactoring we've done has made things easier to work with
  - Classes were moved/refactored according to our packaging technique
  - The `Player` creation process is now the responsibility of a factory class, `PlayerFactory`
    - Allowed us to refactor and simplify `CSVAdapter`
  - Some duplicate code was removed (resolved a code smell)

## Questions and Concerns

- There's no such thing as a static abstract method (in Java, at least)
  - We would like to have an abstract `Database` class that our `PlayerDatabase` and `TeamDatabase` can implement.
  - We want to make sure that all database classes have methods which return a list of the entities in the database, but with our current design, these methods are static.
  - Because Java doesn't allow static abstract methods, it's impossible to enforce a method like this in a parent class.
  - **Possible solution:** refactor our code so that these methods, along with the lists of entities, are no longer static. Instead, we will create a new instance of `PlayerDatabase` and `TeamDatabase` when the program runs, and then pass these instances as arguments to whatever methods depend on the databases (*e.g.* our search methods).
- Text formatting in the console
  - Some of the player names in our database contain non-Latin characters. These are not monospaced, but our current presenter functions assume that all output is monospaced.
  - The result is that column formatting is ruined, and attribute data becomes misaligned.
  - From our research, it seems like there is no real solution to this problem in Java, other than replacing non-Latin characters with a placeholder (*e.g.* `?`), or removing these players from the output entirely (this is our current solution).
- Our `Database` classes have static variables and methods, causing multiple issues
  - Testing is a hassle, since we have to reset the static variables after every single test that modifies them and make sure that we aren't missing any hidden mutation.
  - Serialization is not possible for the `Database` classes if they have static variables.
    - Java does not allow static variables to be serialized.
    - Even if we could, it wouldn't be possible read in a previously created `PlayerDatabase` or `TeamDatabase` object and replace the static variable's value from when it was first initialized.
  - The static methods prevent us from having an abstract `Database` class or interface, and the static variables create issues with inheritance (we have to shadow the parent's variables).
- Our `InputData` classes are difficult to test
  - Testing how console inputs are handled is significantly harder than testing if the right objects are returned by a method.
    - *e.g.* for many of our methods we can do:
      ```java
      expected = Object(...)
      actual = methodCall(...)
      assertEquals(expected, actual)
      ``` 
    - For the `InputData.run` method, the return type is `void`, so we cannot test it as easily.
  - Additionally, the `run` method's output depends on other classes (the `PresentData` classes), which we already have some tests for.
    - Any test of `run` would just indirectly be a test of a `PresentData` method.

## Group Member Roles

### Aditya

Implemented a simple Builder Design Pattern in the command line. I further worked with Kaartik on implementing the Factory Design Pattern. I also extended the functionality of the `PlayerFactory` class by making it incorporate different sub 'position' attributes for each position from the database (For example RW/RF/LF/LW for Forward, CM/LM/RM for midfielder, etc.). Apart from this, I also worked with Michael on thoroughly debugging the implementation of Factory Design Pattern, the `SearchForPlayer` class and the `InputForPlayer` class. Lastly, I also helped Kaartik in writing the tests for the `Player` class.  

### Daniel

Worked on written materials, including the updated specification and walkthrough, the design document, and the progress report. 
Decided on a packaging strategy and refactored all classes to adhere to the strategy.
Performed other miscellaneous refactoring and reformatting of code (eliminating duplicated code, renaming for styling, etc.), and added documenation where it was missing.
Wrote tests for `TeamStatsCalculator`, fixed broken tests, and increased overall line coverage.

### Kaartik

Implemented the Factory Design Pattern along with Aditya while working on the `Player`, `Defender`, `Midfielder`, `Defender`, `Goalkeeper` and `PlayerFactory` classes (where we also overcame the issue of coupling different subpositions of a position into one). Also helped in `Builder` class, and wrote documentation for it. Wrote documentation for design principles, Factory Design Pattern and Builder Design pattern. Wrote a couple of tests for `Player` Class.

### Matthew

Continued working on the Presenter classes, made lots of modifications to `PlayersPresenter` and implemented
`TeamsPresenter`. Also worked on the aggregated Statistics functionality for the program by implementing both the `TeamStatsCalculator` and `PlayerStatsCalculator` classes. 
Wrote tests for some of these classes and helped write about the packaging strategy
decision on the design document.

### Michael 

Implemented `TeamDatabase` and Team classes. Reimplemented `CSVAdapter`, `Player` and `SearchForPlayer` to enable `Team` data loading, `Player` factory design method initialization and fuzzy name searching. Wrote some tests. 

### Tobey

Implemented `User`, `UserList`, `ReadWriter`, `UserReadWriter`, `LoginController`, `LoginUseCase`, and `LoginInputBoundary` classes. Edited the `CommandLine` class to run through a simple username and password prompt sequence according to a pre-defined User object added to the list of users. Within the relevant login classes, implemented serialization within the storage and retrieval of data from `users.ser`. Additionally, added relevant tests for the implemented Login feature.
