# local_database

is responsible for handling all data related requests. Mainly, these services include, persistence and retrieval related tasks. The data services layer sits on top of databases in order to provide data to all other modules in the application.


[WSDL](https://nameless-forest-62807.herokuapp.com/ws/localdb?wsdl)

## SOAP Services
 
 
  1- ** saveData(MeasureData md) ** Saves Measure Data
 
  2- ** getLatestData(int telegramId, int limit) ** return the latest data in the database
 
  3- ** userExists(int id) ** tells you if a user is already registered
 
  4- ** getUser(int id) ** return the user data given it's id
 
  5- ** createUser(UserData user) ** create a new user