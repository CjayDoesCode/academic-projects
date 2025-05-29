#include "grid.h"
#include "constants.h"
#include "leaderboard.h"
#include "queue.h"
#include "ui.h"
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

clock_t gameStartTime;

void initializeGrid(Grid *grid, int difficulty) {
  int height, width, mines;

  switch (difficulty) {
  case EASY:
    height = EASY_HEIGHT;
    width = EASY_WIDTH;
    mines = EASY_MINES;
    break;
  case MEDIUM:
    height = MEDIUM_HEIGHT;
    width = MEDIUM_WIDTH;
    mines = MEDIUM_MINES;
    break;
  case HARD:
    height = HARD_HEIGHT;
    width = HARD_WIDTH;
    mines = HARD_MINES;
    break;
  }

  grid->remainingCells = height * width - mines;
  grid->height = height;
  grid->width = width;
  grid->mines = mines;
  grid->firstMove = true;

  grid->cells = (Cell **)malloc(height * sizeof(Cell *));
  for (int i = 0; i < height; i++) {
    grid->cells[i] = (Cell *)malloc(width * sizeof(Cell));
  }

  for (int i = 0; i < height; i++) {
    for (int j = 0; j < width; j++) {
      grid->cells[i][j].adjacentMines = 0;
      grid->cells[i][j].isRevealed = false;
      grid->cells[i][j].isFlagged = false;
      grid->cells[i][j].hasMine = false;
    }
  }
}

void displayGrid(const Grid *grid, bool isRevealed) {
  int height = grid->height;
  int width = grid->width;

  printf("\n");

  // Top border
  printf("     ┌───");
  for (int i = 1; i < width; i++) {
    printf("┬───");
  }
  printf("┐\n");

  // Column labels
  printf("     ");
  for (int j = 0; j < width; j++) {
    printf("│ %c ", 'A' + j);
  }
  printf("│\n");

  // Top separator
  printf("┌────");
  for (int i = 0; i < width; i++) {
    printf("┼───");
  }
  printf("┤\n");

  // Grid rows
  for (int i = 0; i < height; i++) {
    printf("│ %2d ", i + 1);
    for (int j = 0; j < width; j++) {
      const Cell *cell = &grid->cells[i][j];
      if (isRevealed|| cell->isRevealed) {
        if (cell->hasMine) {
          printf("│ %s*%s ", RED, RESET);
        } else {
          int adjacentMines = cell->adjacentMines;
          if (adjacentMines == 0) {
            printf("│ - ");
          } else if (adjacentMines == 1) {
            printf("│ %s1%s ", GREEN, RESET);
          } else if (adjacentMines == 2) {
            printf("│ %s2%s ", YELLOW, RESET);
          } else {
            printf("│ %s%d%s ", RED, adjacentMines, RESET);
          }
        }
      } else if (cell->isFlagged) {
        printf("│ %s!%s ", RED, RESET);
      } else {
        printf("│   ");
      }
    }
    printf("│\n");

    // Row separator
    if (i < height - 1) {
      printf("├────");
      for (int j = 0; j < width; j++) {
        printf("┼───");
      }
      printf("┤\n");
    }
  }

  // Bottom border
  printf("└────");
  for (int i = 0; i < width; i++) {
    printf("┴───");
  }
  printf("┘\n");
}

void placeMines(Grid *grid, int initialX, int initialY) {
  int placedMines = 0;
  int height = grid->height;
  int width = grid->width;
  int mines = grid->mines;

  while (placedMines < mines) {
    int x = rand() % width;
    int y = rand() % height;

    if ((abs(x - initialX) <= 1 && abs(y - initialY) <= 1) ||
        grid->cells[y][x].hasMine) {
      continue;
    }

    grid->cells[y][x].hasMine = true;
    placedMines++;

    for (int dy = -1; dy <= 1; dy++) {
      for (int dx = -1; dx <= 1; dx++) {
        int nx = x + dx;
        int ny = y + dy;

        if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
          grid->cells[ny][nx].adjacentMines++;
        }
      }
    }
  }
}

void revealCell(Grid *grid, int x, int y) {
  if (grid->firstMove) {
    placeMines(grid, x, y);
    gameStartTime = clock();
    grid->firstMove = false;
  }

  if (x < 0 || x >= grid->width || y < 0 || y >= grid->height)
    return;

  Queue *q = createQueue(grid->width * grid->height);
  enqueue(q, x, y);

  while (!isEmpty(q)) {
    QueueNode current = dequeue(q);
    int cx = current.x;
    int cy = current.y;

    if (cx < 0 || cx >= grid->width || cy < 0 || cy >= grid->height)
      continue;

    Cell *cell = &grid->cells[cy][cx];

    if (cell->isRevealed || cell->isFlagged)
      continue;

    cell->isRevealed = true;
    grid->remainingCells--;

    if (cell->hasMine) {
      displayGrid(grid, true);
      printf("\n%s[ Mission Failed! You died. ]%s\n", RED, RESET);

      LeaderboardNode *leaderboardRoot = loadLeaderboard();
      int rank = 1;

      printf("\n[ Leaderboard ]\n\n");
      printLeaderboard(leaderboardRoot, &rank);

      exit(EXIT_SUCCESS);
    }

    if (grid->remainingCells == 0) {
      displayGrid(grid, true);
      printf("\n%s[ Mission Passed! Respect + ]%s\n", GREEN, RESET);
      updateLeaderboard();
      exit(EXIT_SUCCESS);
    }

    if (cell->adjacentMines == 0) {
      for (int dy = -1; dy <= 1; dy++) {
        for (int dx = -1; dx <= 1; dx++) {
          if (dx != 0 || dy != 0) {
            enqueue(q, cx + dx, cy + dy);
          }
        }
      }
    }
  }

  freeQueue(q);
}

void flagCell(Grid *grid, int x, int y) {
  grid->cells[y][x].isFlagged = !grid->cells[y][x].isFlagged;
}

void freeGridCells(Grid *grid) {
  for (int i = 0; i < grid->height; i++) {
    free(grid->cells[i]);
  }
  free(grid->cells);
}

Grid *cloneGrid(const Grid *src) {
  Grid *copy = malloc(sizeof(Grid));
  copy->height = src->height;
  copy->width = src->width;
  copy->mines = src->mines;
  copy->remainingCells = src->remainingCells;
  copy->firstMove = src->firstMove;

  copy->cells = malloc(copy->height * sizeof(Cell *));
  for (int i = 0; i < copy->height; i++) {
    copy->cells[i] = malloc(copy->width * sizeof(Cell));
    for (int j = 0; j < copy->width; j++) {
      copy->cells[i][j] = src->cells[i][j];
    }
  }

  return copy;
}