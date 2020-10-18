## Lab Discussion
### Team 13
### Names Umika Paul, Fernanda Rizo, Yasser Elmzoudi


### Issues in Current Code

#### Method or Class
 * Design issues

 getNeighborsTorodial

 There is some repetition in the code with regards to this method. In order to get the neighbors to extend from one side of the grid to meet the other, we have similar lines of code for Klein Bottle and Torodial. This can be refactored out.

 * Design issue

 GamePane

 For hexagons, triangles, and rectangles we want to have separate game panes to show the different shapes on the grid.

#### Method or Class
 * Design issues

 Edge policies and Neighborhood policies were chosen using if statements rather than reflection.

 * Design issue

 The grid types were created using if statements rather than reflection.


### Refactoring Plan

 * What are the code's biggest issues?

 Handling of the neighborhood and edge policies.

 * Which issues are easy to fix and which are hard?

 Easy: Replacing the if statements with reflection.

 Hard: Creating new abstractions to handle different kinds of shapes and designing the code for the Klein Bottle rules.

 * What are good ways to implement the changes "in place"?

 Start replacing the parts of the code that can be refactored with reflection.


### Refactoring Work

 * Issue chosen: Fix and Alternatives

 Neighborhood and edge policy Refactoring
 -We refactored the code to get the neighbors based on three different kinds of neighborhoods and edge policies
 -Neighborhoods: Cardinal, Complete, Diagonal
 -Edge policies: Finite, Torodial, Klein Bottle
 -We used reflection to choose the neighborhood and edge policy and added these to the properties file

 * Issue chosen: Fix and Alternatives

 Different shapes
 -Created a GamePane abstract Class
 -Subclasses consisted of logic encapsulating building the grid from different shapes
 -Shapes: Hexagon, Rectangle, Triangle