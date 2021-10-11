# Specification

This application is designed to provide soccer scouts with a system that allows them to find players that are a good 
fit for a given team based on certain characteristics of their choosing. It contains a database of all players in the 
Premier League, with each player having a set of attributes that describe their playing style and physical dimensions. 
Players, Scouts, and Teams make up the core entities that this application handles. Each player holds all of their 
relevant physical attributes, their position, and a mapping of all their skill attributes to the numeric value 
corresponding to their proficiency in this skill. Teams are groupings of individual players, and Scouts are the 
entities who will be using this application, keeping track of the Team they are scouting for, their scouting history, 
and the type of scout that they are (player scout or tactical scout).

Within the application, the first crucial task is to download and organize the data into the form of an SQL database, 
so that players can be easily accessed when searched for. Some of the use cases corresponding to this functionality 
include adding players to the database and assigning/grouping them into their specific Team. Additionally, users will 
be able to make their own profiles as a Scout entity so that they may begin using the program. To accomplish this, the 
application will be able to create and add new Scout profiles to the system, which can be retrieved from local storage, 
as well to continue using an existing profile. Finally, the most crucial element of the application is the functionality 
that allows a user to search for desired players. The application will provide use cases that allow users to search for 
players in the database either by name, or by querying it based on a given set of player skill attributes with desired 
values/value-ranges. The returned player(s) will be output neatly by presenter methods.
