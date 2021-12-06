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

One of the major changes in this phase of the project was the **creation of a `Database` superclass**. Our TA, Ethan, asked us about having a superclass during our first presentation, but at the time, we didn't see much utility in it. However, after discussing it further, we decided that it would be a great idea for a few reasons:

1. We were in the process of designing our `User` and `UserDatabase` classes, and noticed that it would share a lot of functionality with our other database classes, `PlayerDatabase` and `TeamDatabase`.
  - Rather than duplicating code, it made more sense to inherit the identical functionality from a common superclass.
  - Also, if we were to ever extend our program and add other entities like coaches or managers, having the superclass would be really helpful. This helps our program adhere to the Open/Closed Principle.
2. We were also in the process of enabling serialization of our databases and entities. 
  - If we make our `Database` superclass serializable, then all subclasses will also automatically become serializable, and the methods that load and save data (in `ReadWriter`) will work with any instance of `Database`.

We also decided that using generics for this class would be a good idea, as it enables us to store any object and use the methods defined in the superclass. For example, `UserDatabase` extends `Database<User>`, so all of its methods work on `User` objects. This is one way in which the decision to make a `Database` superclass with generics has already paid off; It only took a few lines of code to have a fully working, serializable database for storing players.

While implementing the new `Database` superclass, we also decided to eliminate the "staticness" from all database classes. Previously, each database had a public static list of entities that could be accessed from anywhere in the program. This came with a few problems:

1. When you have "global data," such as a public static list, it is very easy to lose track of which methods are modifying that data. This makes bugs more likely and bug fixing more difficult. It also increases the chance that we will accidentally change data that we don't intend to change.
2. Static variables can make testing very tedious. To make sure that the operations in one test don't affect the next test, we have to make sure to reset the value of the static variable at the end of every single test.
3. Java simply does not allow static data to be written to a `.ser` file, which would make the serialization of our databases impossible.

In our current design, each database (`Player`/`Team`/`UserDatabase`) gets initialized when the program starts up. If there are existing `.ser` files for these databases, they will be loaded into the program. Otherwise, empty `Database` objects are created and added to by `CSVAdapter` (and `LoginUseCase`). Any classes or methods that used to access the static variable now require `Database` objects to be passed as arguments instead.

In addition to using generics in `Database`, we also applied them to one of our search classes, `SearchByName`. Our previous design would have required separate classes `SearchByPlayerName` and `SearchByTeamName` to enable searching of `Player`s and `Team`s, since the search methods would have different parameters. For example, `SearchByPlayerName.search()` would return a `List<Player>`, while `SearchByTeamName.search()` would return a `List<Team>`. (Alternatively, we could've used overloading, but this would still result in duplicated code.) With our new design, `SearchByName.search()` returns a generic `List<T>`, and the type of `T` will depend on how we initialize the `SearchByName` class.

- Simplifying `CSVAdapter` (extracting logic)

## Questions and Concerns

- Too many classes? Packaging strategy not working?
- Databases being passed through multiple layers?
- Too much responsibility given to `CommandLine`?
- In order to avoid the dependency in wrong direction with the entities and `StatsCalculator`, we end up making our code calculate the statistcics, everytime we search.
- A lot of duplicated code in input functions - We have a good number of input classes, that cover various input cases - inputting player name, team name, player attributes, or team ratings. These classes share a lot of common code. For instance the `InputPlayerAttributes` and `InputTeamRating`, share a lot of common input functionality, from the `run` methods to the `switch` cases (to get the correct input prompt) to the maps. Thus in our input classes we see sequence of codes, that occur more than once. 
  - Tried to resolve with the default `getInput` method, but there is still some duplication (esp. `InputPlayerAttributes`)
- Fuzziness of search functions can lead to bad results with shorter names - Althought implementing the fuzzy search has its benefits, but not having a high accuracy means that we can potentially get a lot of unrelated and undesired players with short names(depending on the column of names we used) when we search.  

## Group Member Roles

<!-- 
TODO: What have you worked on since Phase 1? What are some additional features/improvements that you could implement in the future?
TODO: Make sure to add a link to a significant pull request that you had a part in! See https://q.utoronto.ca/courses/233945/pages/project-phase-2 for more details on this. 
-->

### Aditya

### Daniel

Implemented new user functionality and `Database` superclass with Tobey, and implemented serialization of databases, players, teams, and users (PR [#38](https://github.com/CSC207-UofT/course-project-team-scouts/pull/38) and [#39](https://github.com/CSC207-UofT/course-project-team-scouts/pull/39)). These contributions were essential for **satisfying our specification** (*e.g.* logging in and saving state) and for **improving our testing** (no longer using static, global variables). Re-implemented `InputPlayerAttributes` according to updated specification from last phase. Refactored a lot of our code to support new features and simplify code. Contributed to user interface design decisions. Wrote and finalized much of the documentation (esp. the Accessibility Report). In the future, I would like to refactor the program and add more documentation so others can expand on our code if they desire.

### Kaartik
Refactored and added documentation to `Player` and its subclasses along with `Team`. Rewrote and added few tests, in accordance to the changes and additions in `processFile`. Helped Aditya enhance the functionality of the presenter classes. Introduced the Clean Arch Diagram. I Implemented and introduced the Factory Design Pattern in our project with `PlayerFactory`, `Player` and its subclasses. (PR [#6](https://github.com/CSC207-UofT/course-project-team-scouts/pull/6)) (PR [#40](https://github.com/CSC207-UofT/course-project-team-scouts/pull/40)). The following addition was a key feature of our project, as it helped instantiate appropriate objects (which are used in various parts of our project) without exposing the creation logic to client. In the future I would like to introduce a more enhanced GUI, and also possibly add a feature of calculating the potential chemistry of a scouted player in a team. 

### Matthew

Implemented the `PlayerPresenter` class to display more detailed output for a single player. Also helped with the implementation of the input and searching functionality of the program by extending it to work with `Team` entities as well (by implementing the classes `InputTeamName`, `InputTeamRating`, and `SearchByTeamRating` PR [#56](https://github.com/CSC207-UofT/course-project-team-scouts/pull/56])). These specific additions were crucial in fulfilling the basic functionality that was first defined in our specification, while also increasing the utility of the program to users. In the future, I would like to potentially create a GUI for the user interface, that allows users to enter in their search input more easily and see the results of their searches in a more visually pleasing fashion.

### Michael 

Made minor changes to Player, CsvAdapter classes to enable search by attributes funcitonality. Reimplemented the SearchForPlayer and SearchByAttributes classes to allow them to interact appropriately with new implementation of databases that can now be serialized. Implemented SearchByAttributes to enable Player searching using attributes as outlined by our specificication  (PR [#44](https://github.com/CSC207-UofT/course-project-team-scouts/pull/44)). Contributed to user interface and searching use case design decisions. In the future I would like to add database editing funtionality like the ability to add, remove or edit players and teams so the progrma is still useful even after the current databse becomes outdated.

### Tobey

Implemented the `Database` superclass along with it's subclasses `PlayerDatabase`, `TeamDatabase`, and `UserDatabase` with Daniel (PR [#38](https://github.com/CSC207-UofT/course-project-team-scouts/pull/38)). In light of this, we had revamped how we work with our data. Additionally, assisted with the creation and implementation of our `User` entities and how they would play a part in our program. Currently working to finish up implementing the ability for User's to create teams with our current layout of data and flow of our program.
