# Accessibility Report

- [Principles of Universal Design](#principles-of-universal-design)
    - [Principle 1: Equitable Use](#principle-1-equitable-use)
    - [Principle 2: Flexibility in Use](#principle-2-flexibility-in-use)
    - [Principle 3: Simple and Intuitive Use](#principle-3-simple-and-intuitive-use)
    - [Principle 4: Perceptible Information](#principle-4-perceptible-information)
    - [Principle 5: Tolerance for Error](#principle-5-tolerance-for-error)
    - [Principle 6: Low Physical Effort](#principle-6-low-physical-effort)
    - [Principle 7: Size and Space for Approach and Use](#principle-7-size-and-space-for-approach-and-use)
- [Target Demographics](#target-demographics)

## Principles of Universal Design

### Principle 1: Equitable Use

- After the completion of this semester, some members of the group will continue to develop the project further and release it as a freely available, open-source program on GitHub.
    - This ensures that anyone, regardless of economic status, is able to use our program and have access to all the same features.
- Some users may not prefer the command line interface, but having this kind of interface allows the user to easily customize the appearance of the program with colour schemes, for example. This makes the design appealing to many users.

### Principle 2: Flexibility in Use

- Since our program uses a command line interface, it adapts to whatever settings the user has for their terminal. 
    - If the user struggles to read small text, they can easily increase the text size, or if they prefer a certain colour scheme, they can make that change as well.
- Using only Java to implement our program means that the program could run on almost any platform, as long as they support JVM. This includes Windows, MacOS, Android, Linux, and many other operating systems.
- The program also adapts to the user's pace. If the user becomes tired or wants to take a break for any reason, they can easily exit the program and save all the changes they have made to their user profile.

### Principle 3: Simple and Intuitive Use

- We put a lot of effort into making sure that the instructions given to the user while they use our program are clear and easy to understand.
    - Even those with less technical skills will be able to use our program without issue (provided that they know how to run it).
- Users are also provided with clear, descriptive feedback when they perform certain actions.
    - For example, when logging in, the program tells the user if they have successfully logged in to an existing account, created a new account, or if they have entered the incorrect password for an existing account.

### Principle 4: Perceptible Information

<!-- TODO: Make sure to implement the stuff in this section! -->

- Some features of our program, like searching for players by their attributes, can result in large amounts of information being returned. We have taken steps to ensure that the output is still legible and easy to comprehend.
    - For example, when outputting a list of players, the players' names, ratings, skills, etc. get displayed in a table rather than in lines of comma-separated values.
    - If the user is interested in a particular attribute, they only need to look at the corresponding column for that attribute.
- We also ensure legibility of essential information by adding small amounts of spacing between related prompts, and much more spacing between unrelated prompts.
    - For example, the prompts for inputting a username and password are separated by a single line.
    - In contrast, the prompts for logging in and for searching are separated by several lines. This ensures that the different functionalities of our program are easy to distinguish from each other.

### Principle 5: Tolerance for Error

- If an error occurs in our program, we let the user know through the command line in a simple and easy to understand way. Also, there is a failsafe wherever an error can occur (*e.g* where exceptions are raised).
- If we know the cause of an error, then we communicate it and allow the user to repeat the action. 
    - For example, entering a wrong password returns the message, "Failed to log in, incorrect password! Please try again."
- If we don't know what has caused an error, instead of our program halting, we tell the user that an unknown error has occurred and allow the user to continue using the program.
    - This avoids the loss of data (if the user has made unsaved changes).
    - This also avoids long, complicated error messages from being displayed.
- We also tolerate some error in user inputs.
    - For example, when searching by player attributes, the user should specify a range by separating two integer values with a single space, *e.g.* `21 45`.
    - They may also input `  21           45` and get the exact same results.

### Principle 6: Low Physical Effort

- This is an area of our program that could use some improvement.
- To minimize repetitive actions, we could allow each user to save or favourite searches that they will be using multiple times.
    - This means that they will not have to go through all the program's prompts every single time, which would be especially useful for an attributes query.
- To minimize physical effort, we could allow users to choose options by specifying a number, rather than typing out the full action that they want to perform.
    - For example, when we ask the user to pick between searching for players and searching for teams, we can present the options like so:
        ```Would you like to search for players or for teams? Please enter the number corresponding to your choice:
        1 - Players
        2 - Teams
        ```
    - Relating this to [Principle 2](#principle-2-flexibility-in-use), we could allow the user to input using whatever method they prefer (by typing options or using numbers).

### Principle 7: Size and Space for Approach and Use

- This principle does not really apply to our project, since there is not much we can do to improve ergonomics with a command line program.
    - For example, the visibility of the UI from different angles and distances depends highly on the user's terminal configuration (font size, colour scheme, etc.).

## Target Demographics

There are two main groups of people that we could market our program to. 
The first is **soccer fans** who like learning about player stats and ratings, purely for their entertainment.
This is the same demographic that collects soccer trading cards and plays games like Football Manager (which was a major source of inspiration for our product). 
The second group of people that we could market to is **professional soccer scouts**.
These people would use our program to search for players that would be a good fit.
For example, maybe the team they are scouting for has a weak defense.
They can search for players that possess high ratings in defensive categories, like tackling, dribbling, and passing,
in order to improve the defensive capabilities of their team.
They can also see the net worth of each player, allowing them to determine if it is economically feasible to acquire them.

Soccer fans who are less "nerdy," for lack of a better word, would probably not be interested in using our program. There are other programs - or more specifically, games - that provide more interactivity and engagement, including FIFA and PES. These would be more suitable for a casual soccer fan. Anyone who has no interest in soccer whatsoever would probably not want to use our program for a substantial amount of time. Additionally, people who have an aversion to command line interface (CLI) programs would likely prefer to use a graphical application, even it has less functionality. For this reason, it would not make sense to market our product to any demographic that is less tech-literate (*e.g.* older people).