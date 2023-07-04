Hello!
Here you can find my app that is emulatiing a restaruant buissness.
This is one of my first projects, so there is a list of things i would like to change in a new version of this app:
Architecture - the whole app could really use MVVM or MVM kinds of architecture, the only thing that looks good enough
is database and still it could be better by perhaps creating only one database class.
Design - it could be redesigned in some places and also arhcitecture of it could be changed.
This is what i need to do in next repository:
Databases can be rearranged into one big database, because daos are deviding itself there is no need for a further division
Need to implement both mvvm and mve or mvi pattern because I need the first one for representing db and other one for correctly providing user with intents that he has. 
Further redesign is not needed with few exceptions like news on sales screen
Need to add 2 big apis for project and purhaps fragment for the second: payment (yookassa /stripe) and map (google /mapbox) 
Need to understand how dagger hilt works because I don't really now
