# Build Your Own World Design Document

### **Partner 1:**
Adithya Sagar
### **Partner 2:**
JiHo Bang
## Classes and Data Structures
### Main:
This is the entry point to our program. It takes in arguments from the command line and based on the command calls the corresponding command in Engine which will actually execute the logic of the command. Main will take in a string, in the form of "N#######S", as an argument. Every time a new, unique string is put in, a new, random world will be created.   

### Engine:
This is where the main logic of our program will live. The engine contains the two methods (string, keyboard) that will allow the user to interact with the world.

### hallwayGenerator
This class will contain methods relevant to creating hallways for our world. Three important methods this class will contain are: drawHorizontalHallway, drawVerticalHallway, and drawCorner. These methods will take in a width integer as an argument and the method will respectively draw the desired hallway shape in the specified width with a random length that will be determined through a random number generator. We will also implement a validateSize method that will return a boolean that will let us know if the new hallway is added to a valid square on the board (not out of bounds, or not overlapping with another object). 

### roomGenerator
This class will have similar methods and functionality to the hallwayGenerator only for rooms and doors.

## Algorithms
One way to randomly generate our world is through the following process. First, we randomly generate a random amount of rooms in the world using our roomGenerator. Then, we connect these rooms with our hallwayGenerator. We can connect these rooms using any of the graph algorithms we've learned (paths, Dijksra, A*, etc.). After we've connected these rooms, we randomly assign a certain amount of walls to be doors. If there is a hallway in that wall we can randomly determine if it'll be an accessible or locked door. If there is no hallway beyond that wall we have to make it a locked door.

## Persistence
The directory structure will look like this:
#### CWD: Whatever the current working directory is.
#### .world: All persistant data is stored within here.               
#### N####S: The folder will hold data in the form of a hashmap. The hashmap stores the specific string for the world as the key (e.g. "N3412S") and the actual data for that random world as the value.

The Engine class will set up all persistence. It will create the .world folder if it doesn’t already exist. The world folder will contain different hashmaps with strings and the world data as its key and value. Later, users will be able to load their worlds by checking if that world already exists in the data. 
