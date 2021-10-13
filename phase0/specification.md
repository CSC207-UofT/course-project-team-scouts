# Specification

This application is designed to provide soccer scouts with a system that allows them to find players that are a good 
fit for a given team based on specific characteristics of their choosing. It contains a database of all players in the 
Premier League, with each player having a set of attributes that describe their playing style and physical dimensions. 
Players, Scouts, and Teams make up the core entities that this application handles. Each Player holds all of their 
relevant physical attributes, their position, and a mapping of their skill attributes to the numeric value 
corresponding to their proficiency in this skill. Teams are groupings of individual players, and Scouts are the 
entities who will be using this application. Scouts will keep track of the Team they are scouting for, their scouting 
history, and the type of scout that they are (player scout or tactical scout).

Within the application, the first crucial task is to download and organize the data into the form of an SQL database, 
so that players can be easily accessed when searched for. Some of the use cases corresponding to this functionality 
include adding players to the database and assigning/grouping them into their specific Team. Additionally, users will 
be able to make their own profiles as a Scout entity, allowing them to interact with Player entities. To accomplish 
this, the application will be able to add new Scout profiles to the system or retrieve an existing profile from storage. 
Finally, the most crucial element of the application is the functionality that allows a user to search for desired 
players. The application should allow users to search for players in the database either by name, or by querying the
database based on a given set of player skill attributes with desired values/ranges of values. The returned player(s) 
will be output neatly by presenter methods. The presenter methods should display the player's name and a summary of 
their attributes and skills.
