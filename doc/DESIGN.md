# Simulation Design Final
### Names

Umika Paul, Fernanda Corona, Yasser Elmzoudi

## Team Roles and Responsibilities

 All: Each member helped and worked with other members to implement their portions of the project. Each part of the project involved contributions from all members.

* Team Member #1 (Umika Paul): Responsible for the backend of the project. She made the abstract classes, enum, and concrete classes for the Cell and Grid to allow for proper functioning of the six simulations. She was also responsible for implementing the different edge and neighborhood policies. She wrote all the tests for these classes.

* Team Member #2 (Fernanda Corona): Responsible for the frontend of the project. She was responsible for the classes including the GamePane, ScreenVisuals, and ButtonPanel, as well as the different shapes. She worked to make the simulation more user interactive by implementing colors, styles, and languages. She wrote the tests for these classes.

* Team Member #3 (Yasser Elmzoudi): Responsible for Controller, configuration, and exceptions. He wrote the classes encapsulating the exceptions and ErrorPanel, and was responsible for proper functioning of the graph. He also separated the controller from the view and helped structure the code.

## Design goals

#### What Features are Easy to Add

New simulations

- Adding a new simulation simply entails adding another subclass of <code>Cell</code> and <code>Grid</code> 
- Cell would contain an overridden method <code>update</code> that contains the rules for updating cells in each cycle

New shapes

- Our <code>GamePanel</code> allows for the addition of new shapes, simply by adding another subclass with the new shape that needs to be added
- Unless a shape already exists within the <code>javafx.scene.shape.Shape</code> library, a new class should be made in the Shapes package. The points corresponding to the shape should then be chosen within the class that was created. The constructor for this class should, at the very minimum, take in row and column parameters to have the shape’s points be dependent on these values. 

New Buttons / Displays on the grid

- Adding a new button can be done easily by adding the button to the <code>ButtonPanel</code> class. To have control over the button’s styles, the button name should be added as a key to the <code>ObjectId.properties</code> file found in the StyleResources package. The string value of this key should then be added to the <code>UserButtonStyles.css</code> file by adding a hashtag before the string value and then placing curly brackets containing the button’s styles after the value. An example of this can be seen below. 



## High-level Design

#### Core Classes

<img src="doc/DesignDiagram.JPG" width="1000" height="400"/>

Data files needed: 

All the data csv files are located in the data folder. The properties files are located in the resources directory in src, and the properties files for the languages are located in the languageresources directory. The css files are located in the styleresources directory.

Note: For the tests, some of the files needed to run the tests are in the <code>Test Sources</code> folder. This folder needs to be set as the Test Resources root.

Features implemented:

- There are six different simulations included - Game of Life, Percolation, Segregation, Rock Paper Scissors, Predatory Prey, and Spreading of Fire.

#### Game of Life

<img src="doc/GameOfLife.gif" width="500" height="500"/>

#### Spreading of Fire

<img src="doc/SpreadingOfFire.gif" width="500" height="500"/>

#### Percolation

<img src="doc/Percolation.gif" width="500" height="500"/>

#### Predator Prey

<img src="doc/PredatorPrey.gif" width="500" height="500"/>

#### Rock, Paper, Scissors

<img src="doc/RockPaperScissors.gif" width="500" height="500"/>

#### Segregation

<img src="doc/Segregation.gif" width="500" height="500"/>

- Different edge policies: These include Finite, Klein Bottle, and Torodial.

#### Klein Bottle

Notice how the enclosed block on the bottom right and top left side of the screen are full because the grid connects on both sides after twisting one of the sides. Site: https://en.wikipedia.org/wiki/Klein_bottle.

<img src="doc/KleinBottle.gif" width="500" height="500"/>

#### Torodial

Notice how the enclosed block on the right and left side of the screen are full because the grid connects on both sides.

<img src="doc/Torodial.gif" width="500" height="500"/>

- Different neighborhood policies: These include Complete, Diagonal, and Cardinal.

#### Diagonal

Only the northeast, southeast, northwest, and southwest neighbors count. The cells blocked above and below remain blocked.

<img src="doc/Diagonal.gif" width="500" height="500"/>

#### Cardinal

Only the north, east, south, and west count as neighbors. The cells that are blocked diagonally remain blocked.

<img src="doc/Cardinal.gif" width="500" height="500"/>

- **Three languages: English, Spanish, and French.**

Below is the panel you can choose a language and shape from.

<img src="doc/LanguageShape.gif" width="400" height="300"/>

- **Simulation graph: Represents the number of each type of state in the simulation across time.**

<img src="doc/Graph.gif" width="500" height="500"/>

- **Ability to load new files, pause, reset, change speed, and step through simulation.**

As shown in the above gifs, the user can press the buttons in order to change the simulation. To increase the speed, drag the slider to the right.

- **Different shapes in grid: Triangle, Hexagon, and Rectangle.**

#### Triangle

<img src="doc/Spanish.gif" width="500" height="500"/>

#### Hexagon

<img src="doc/Hexagon.gif" width="500" height="500"/>

**- Style of Cells: Both images and different colors can be loaded onto the cells.**

<img src="doc/TreeImage.gif" width="500" height="500"/><img src="doc/FishImage.gif" width="500" height="500"/>

**- Different Themes: A few different colors / themes can be loaded upon startup into the simulation.**

Different themes can be chosen. Look at the Duke and UNC below!

<img src="doc/Duke.gif" width="500" height="500"/><img src="doc/UNC.gif" width="500" height="500"/>

**- Exception handling.**

An error box opens up upon a user entering an invalid simulation or any other error.


## Assumptions that Affect the Design

#### Features Affected by Assumptions


## Significant differences from Original Plan


## New Features HowTo

#### Easy to Add Features

Unless a shape already exists within the <code>javafx.scene.shape.Shape</code> library, a new class should be made in the Shapes package. The points corresponding to the shape should then be chosen within the class that was created. The constructor for this class should, at the very minimum, take in row and column parameters to have the shape’s points be dependent on these values. 

To add new Grid Shapes, the abstract class <code>GamePane</code> must be extended and an instance variable to hold a new 2D Shape Array should be created. In the example below,  this instance variable is called allShapes. The abstract method <code>makeArray</code> should be copied and altered according to the new shape that will be displayed in the grid. Within the <code>makeArray</code> method, every shape should be added to the instance variable described above. The method <code>getInitialArray</code> should also be overridden and should return the instance variable of the 2D Shape Array to which the shapes were added. 

To add an additional <code>Grid</code> Shape that the user can choose from, a <code>GamePane</code> with that shape should be added to the package <code>GamePane</code> Shapes found in the View package. This class should be named “<English Translation Of Shape>GamePane '' to allow our use of reflection to work correctly. Once the file is made, the shape should be added to each language file by having the key be the english translation of the shape and having the value be the corresponding language’s translation of the shape. The translated version of the shape should also be added to the existing key <code>Shape</code> to have the option available as a drop down item.

In order to add an additional language, a corresponding “.properties” file must be made in the Language Resources package. Once the file is made, all the keys found in the english.properties must be copied and have their values changed accordingly. An example of how a language properties file should be created can be seen below. 

//Insert gif or image 

To add additional Styles, a new <code>.css</code> file must be created and placed in the <code>StyleResources</code>package. The appropriate translation for each language found in the Language Resources package should also be added by adding a key with the english translation of the style and having this key’s value be the corresponding language’s translation. The translated version of the new style should be added to the key <code>Style</code> to have the new option displayed as an item in the drop down menu. The <code>ColorStyles.properties</code> file should then reflect the new additions by having the English translation of the word as the key and having the corresponding <code>.css</code> file name be the value. 

#### Other Features not yet Done

To add more edge policies or neighborhood policies it is possible to create a new <code>getNeighbors</code> interface that can have classes with information about how to get the neighbors. We implemented different edge policies and neighborhood policies for the rectangle shape but to do so in the same capacity for the hexagon and the triangle it would be easier to make use of an interface, if we were given more time. 



