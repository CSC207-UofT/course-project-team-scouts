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

- `Database` superclass w/ generics
- Making databases non-static
- `Identifiable` interface
- Simplifying `CSVAdapter` (extracting logic)

## Questions and Concerns

- Too many classes? Packaging strategy not working?
- Databases being passed through multiple layers?
- Too much responsibility given to `CommandLine`?
- Trying to avoid dependency in wrong direction w/ entities and `StatsCalculator` -> means we have to calculate stats every single time we search
- A lot of duplicated code in input functions
  - Tried to resolve with the default `getInput` method, but there is still some duplication (esp. `InputPlayerAttributes`)
- Fuzziness of search functions can lead to bad results with shorter names

## Group Member Roles

<!-- 
TODO: What have you worked on since Phase 1? What are some additional features/improvements that you could implement in the future?
TODO: Make sure to add a link to a significant pull request that you had a part in! See https://q.utoronto.ca/courses/233945/pages/project-phase-2 for more details on this. 
-->

### Aditya

### Daniel

Implemented new user functionality and `Database` superclass with Tobey, and implemented serialization of databases, players, teams, and users (PR [#38](https://github.com/CSC207-UofT/course-project-team-scouts/pull/38) and [#39](https://github.com/CSC207-UofT/course-project-team-scouts/pull/39)). These contributions were essential for **satisfying our specification** (*e.g.* logging in and saving state) and for **improving our testing** (no longer using static, global variables). Re-implemented `InputPlayerAttributes` according to updated specification from last phase. Refactored a lot of our code to support new features and simplify code. Contributed to user interface design decisions. Wrote and finalized much of the documentation (esp. the Accessibility Report). In the future, I would like to refactor the program and add more documentation so others can expand on our code if they desire.

### Kaartik

### Matthew

### Michael 

Made minor changes to Player, CsvAdapter classes to enable search by attributes funcitonality. Reimplemented the SearchForPlayer and SearchByAttributes classes to allow them to interact appropriately with new implementation of databases that can now be serialized. Implemented SearchByAttributes to enable Player searching using attributes as outlined by our specificication. Contributed to user interface and searching use case design decisions [#44](https://github.com/CSC207-UofT/course-project-team-scouts/pull/44). In the future I would like to add database editing funtionality like the ability to add, remove or edit players and teams so the progrma is still useful even after the current databse becomes outdated.

### Tobey

Implemented the `Database` superclass along with it's subclasses `PlayerDatabase`, `TeamDatabase`, and `UserDatabase` with Daniel. In light of this, we had revamped how we work with our data. Additionally, assisted with the creation and implementation of our `User` entities and how they would play a part in our program. Currently working to finish up implementing the ability for User's to create teams with our current layout of data and flow of our program.
