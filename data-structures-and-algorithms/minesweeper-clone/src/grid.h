#ifndef GRID_H
#define GRID_H

#include <stdbool.h>

typedef struct Cell {
  int adjacentMines;
  bool isRevealed;
  bool isFlagged;
  bool hasMine;
} Cell;

typedef struct Grid {
  Cell **cells;
  int remainingCells;
  int height;
  int width;
  int mines;
  bool firstMove;
} Grid;

void initializeGrid(Grid *grid, int difficulty);
void displayGrid(const Grid *grid, bool isRevealed);
void placeMines(Grid *grid, int initialX, int initialY);
void revealCell(Grid *grid, int x, int y);
void flagCell(Grid *grid, int x, int y);
void freeGridCells(Grid *grid);
Grid *cloneGrid(const Grid *src);

#endif
