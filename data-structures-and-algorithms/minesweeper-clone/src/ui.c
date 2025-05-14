#include "constants.h"
#include "grid.h"
#include "input.h"
#include "leaderboard.h"
#include "undo.h"
#include <ctype.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

extern clock_t gameStartTime;

int selectDifficulty() {
  while (true) {
    printf("Select a difficulty:\n"
           "                    \n"
           "[ 1 ] Easy          \n"
           "[ 2 ] Medium        \n"
           "[ 3 ] Hard          \n"
           "                    \n");

    switch (getIntInput()) {
    case EASY:
      return EASY;
      break;
    case MEDIUM:
      return MEDIUM;
      break;
    case HARD:
      return HARD;
      break;
    default:
      printf("\n[ Error ] Invalid input. Try again.\n\n");
      continue;
    }
  }
}

void selectCell(Grid *grid) {
  static int mode = REVEAL_MODE;
  char buffer[MAX_BUFFER_SIZE];
  int x, y;

  printf("\n"
         "Enter 'u' to undo your last move.\n"
         "Enter 'f' to switch to flag mode.\n"
         "Enter 'r' to switch to reveal mode.\n"
         "Enter the letter and number to select a cell (e.g.: e4).\n");

  while (true) {
    switch (mode) {
    case REVEAL_MODE:
      printf("\nSelect a cell to %s%s%s:\n\n", RED, "reveal", RESET);
      break;
    case FLAG_MODE:
      printf("\nSelect a cell to %s%s%s:\n\n", GREEN, "flag", RESET);
      break;
    }

    getInput(buffer);
    size_t length = strlen(buffer);

    if (length == 1 && (buffer[0] == 'u' || buffer[0] == 'U')) {
      Grid *prev = popUndo();
      if (prev == NULL) {
        printf("\n[ Error ] No moves to undo.\n");
        continue;
      } else {
        freeGridCells(grid);

        grid->height = prev->height;
        grid->width = prev->width;
        grid->mines = prev->mines;
        grid->remainingCells = prev->remainingCells;
        grid->firstMove = prev->firstMove;

        grid->cells = prev->cells;
        free(prev);

        printf("\n[ Info ] Last move undone.\n");

        return;
      }
    }

    if (length == 1 && (buffer[0] == 'f' || buffer[0] == 'F')) {
      mode = FLAG_MODE;
      break;
    }

    if (length == 1 && (buffer[0] == 'r' || buffer[0] == 'R')) {
      mode = REVEAL_MODE;
      break;
    }

    if (!(length == 2 || length == 3)) {
      printf("\n[ Error ] Invalid input. Try again.\n");
      continue;
    }

    if (!isalpha(buffer[0]) || !isdigit(buffer[1])) {
      printf("\n[ Error ] Invalid input. Try again.\n");
      continue;
    }

    if (length == 3 && !isdigit(buffer[2])) {
      printf("\n[ Error ] Invalid input. Try again.\n");
      continue;
    }

    x = toupper(buffer[0]) - 'A';
    y = atoi(&buffer[1]) - 1;

    if (x < 0 || x >= grid->width || y < 0 || y >= grid->height) {
      printf("\n[ Error ] Coordinates out of range. Try again.\n");
      continue;
    }

    pushUndo(grid);

    switch (mode) {
    case REVEAL_MODE:
      revealCell(grid, x, y);
      break;
    case FLAG_MODE:
      flagCell(grid, x, y);
      break;
    }
    return;
  }
}

void updateLeaderboard() {
  LeaderboardNode *leaderboardRoot = loadLeaderboard();
  clock_t gameEndTime = clock();

  double elapsedSeconds =
      (double)(gameEndTime - gameStartTime) / CLOCKS_PER_SEC;
  int time = (int)elapsedSeconds;

  printf("\n[ Info ] Your time is %d seconds.\n", time);

  char playerName[MAX_NAME_SIZE];

  printf("\nEnter your name: \n\n");
  getInput(playerName);

  leaderboardRoot = insertNode(leaderboardRoot, playerName, time);
  saveLeaderboard(leaderboardRoot);

  printf("\n[ Leaderboard ]\n\n");

  int rank = 1;
  printLeaderboard(leaderboardRoot, &rank);

  freeLeaderboard(leaderboardRoot);
}