#include "constants.h"
#include "grid.h"
#include "input.h"
#include "ui.h"
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int main() {
  srand(time(NULL));

  Grid grid;
  initializeGrid(&grid, selectDifficulty());

  while (true) {
    displayGrid(&grid, false);
    selectCell(&grid);
  }

  return 0;
}
