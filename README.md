Simulation
====

This project implements a cellular automata simulator.

Names: Umika Paul, Yasser Elmzoudi, Fernanda Corona

### Timeline

Start Date: 10/03/20

Finish Date: 10/19/20

Hours Spent: 180

### Primary Roles

All: Each member helped and worked with other members to implement their portions of the project. Each part of the project involved contributions from all members.

Umika Paul: Responsible for the backend of the project. She made the abstract classes, enum, and concrete classes for the Cell and Grid to allow for proper functioning of the six simulations. She was also responsible for implementing the different edge and neighborhood policies. She wrote all the tests for these classes. 

Fernanda Corona: Responsible for the frontend of the project. She was responsible for the classes including the GamePane, ScreenVisuals, and ButtonPanel, as well as the different shapes. She worked to make the simulation more user interactive by implementing colors, styles, and languages. She wrote the tests for these classes.

Yasser Elmzoudi: Responsible for Controller, configuration, and exceptions. He wrote the classes encapsulating the exceptions and ErrorPanel, and was responsible for proper functioning of the graph. He also separated the controller from the view and helped structure the code. 

### Resources Used

Professor Duvall's lectures and notes from class
- Examples of reflection
- Examples of abstraction
- Principles such as Open Closed, Single Responsibility, Liskov's Substitution

StackOverFlow

### Running the Program

Main class:

**Start**: Contains the method to <code>launch args</code> and run the program.

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

### Notes/Assumptions

Interesting data files:

In particular, the Predator Prey simulation is interesting when the sharks (originally dark blue) are set to "Shark Image" and the fish (originally turquoise) are set to "Fish Image" while the water is set to "Water Image."

The Spreading of Fire simulation is also interesting. One tree starts off burning and you can see the fire growing outwards.


Extra credit:

Different styles
- Different colors for the cells
- Different languages can be chosen upon startup
- Different images
- Grids are outlined for the simulations with a large number of cells for easier identification.

User Interactive Mode
- Ability to dynamically change the speed of the simulation through an interactive slider.

Different Shapes
- Triangle Grid
- Hexagon Grid
- Rectangle Grid

### Impressions

Umika Paul: Working on the simulations was definitely a lot of work, but it was enjoyable to see the end product each of the simulations functioned properly. Helping out with each portion of the project gave me exposure to both frontend and backend work for this project and I learned a lot from applying the principles taught in class such as abstraction and reflection. 

Yasser Elmzoudi: This project greatly opened my eyes to the benefits and applications of creating a SOLID design. With so many different moving parts for this project, such as the various types of simulations, grids, and cells that were at play, ensuring that a sound design was established proved vital to moving from week to week for this project. Overall, the completion of this project was extremely satisfying as it truly was a manifestation of the material and concepts that we have learned thus far in the course. 

Fernanda Corona: Although it was really difficult to learn how to make visually appealling front end styles and what objects to use, it was my first time getting exposed to front end coding so I learned a lot about making functional user interfaces. It was also really great seeing it all come together as the project relied heavily on different parts working cohesively so once we finally got everything to work together, it was just a matter of fixing small bugs and refactoring for better design. Overall, I really enjoyed making a project that gave users different options to choose from as it really gave us a chance to use various course concepts, especially reflection, to be able to provide these options while still implementing good code design.
