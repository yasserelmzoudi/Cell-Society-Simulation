package model.cell;

/**
 * Enum that represents the different states of cells for each simulation.
 *
 * Game of Life
 * <code>DEAD</code> Cell is not alive.
 * <code>ALIVE</code> Cell is alive.
 *
 * Percolation
 * <code>EMPTY_OPEN</code> If surrounding cells are full, this cell can become full.
 * <code>FULL_OPEN</code> Cell does not change state once it is full.
 * <code>BLOCKED</code> Cell cannot change state and is blocked.
 *
 * Rock Paper Scissors
 * <code>ROCK</code> If enough surrounding cells are paper, rock changes.
 * <code>PAPER</code> If enough surrounding cells are scissors, paper changes.
 * <code>SCISSORS</code> If enough surrounding cells are paper, scissors changes.
 *
 * Segregation
 * <code>X</code> Type of agent X.
 * <code>O</code> Type of agent O.
 * <code>NO_RACE</code> Empty cell with no agent.
 *
 * Spreading of Fire
 * <code>TREE</code> Represents a tree that may catch fire from surrounding fires.
 * <code>BURNING</code> Represents a burning tree.
 * <code>EMPTY</code> No tree at this location.
 *
 *
 * Predator Prey
 * <code>FISH</code> Represents a fish that can be eaten.
 * <code>SHARK</code> Represents a shark.
 * <code>WATER</code> Represents empty space that fish or shark can move to.
 *
 */


public enum CellType {
  DEAD,
  ALIVE,
  EMPTY_OPEN,
  FULL_OPEN,
  BLOCKED,
  FISH,
  SHARK,
  WATER,
  ROCK,
  PAPER,
  SCISSORS,
  EMPTY,
  TREE,
  BURNING,
  X,
  O,
  NO_RACE;
}
