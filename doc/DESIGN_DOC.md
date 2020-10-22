### Design Document

### Names of all people who worked on the project

Umika Paul, Fernanda Corona, Yasser Elmzoudi

### Each person's role in developing the project

All: Each member helped and worked with other members to implement their portions of the project. Each part of the project involved contributions from all members.

Umika Paul: Responsible for the backend of the project. She made the abstract classes, enum, and concrete classes for the Cell and Grid to allow for proper functioning of the six simulations. She was also responsible for implementing the different edge and neighborhood policies. She wrote all the tests for these classes.

Fernanda Corona: Responsible for the frontend of the project. She was responsible for the classes including the GamePane, ScreenVisuals, and ButtonPanel, as well as the different shapes. She worked to make the simulation more user interactive by implementing colors, styles, and languages. She wrote the tests for these classes.

Yasser Elmzoudi: Responsible for Controller, configuration, and exceptions. He wrote the classes encapsulating the exceptions and ErrorPanel, and was responsible for proper functioning of the graph. He also separated the controller from the view and helped structure the code.

### What are the project's design goals, specifically what kinds of new features did you want to make easy to add

New simulations
-Adding a new simulation simply entails adding another subclass of Cell and Grid 
-Cell would contain an overridden method “update” that contains the rules for updating cells in each cycle

### Describe, in detail, how to add new features to your project, especially ones you were not able to complete by the deadline

#### Edge and neighborhood policies

To add more edge policies or neighborhood policies it is possible to create a new “getNeighbors” interface that can have classes with information about how to get the neighbors. We implemented different edge policies and neighborhood policies for the rectangle shape but to do so in the same capacity for the hexagon and the triangle it would be easier to make use of an interface, if we were given more time. 

To add new Grid Shapes, the abstract class GamePane must be extended and an instance variable to hold a new 2D Shape Array should be created. In the example below,  this instance variable is called allShapes. The abstract method makeArray should be copied and altered according to the new shape that will be displayed in the grid. Within the makeArray method, every shape should be added to the instance variable described above. The method getInitialArray should also be overridden and should return the instance variable of the 2D Shape Array to which the shapes were added. 

To add an additional Grid Shape that the user can choose from, a GamePane with that shape should be added to the directory Game Pane Shapes found in the View package. This class should be named “<English Translation Of Shape>GamePane '' to allow our use of reflection to work correctly. Once the file is made, the shape should be added to each language file by having the key be the english translation of the shape and having the value be the corresponding language’s translation of the shape. The translated version of the shape should also be added to the existing key “Shape” to have the option available as a drop down item.

In order to add an additional language, a corresponding “.properties” file must be made in the Language Resources directory. Once the file is made, all the keys found in the english.properties must be copied and have their values changed accordingly. An example of how a language properties file should be created can be seen below. 

//Insert gif or image 

To add additional Styles, a new “.css” file must be created and placed in the Style Resources directory. The appropriate translation for each language found in the Language Resources directory should also be added by adding a key with the english translation of the style and having this key’s value be the corresponding language’s translation. The translated version of the new style should be added to the key “Style” to have the new option displayed as an item in the drop down menu. The “ColorStyles.properties” file should then reflect the new additions by having the English translation of the word as the key and having the corresponding “.css” file name be the value. 



