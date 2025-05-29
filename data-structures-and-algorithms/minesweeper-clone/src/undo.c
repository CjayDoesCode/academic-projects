#include "undo.h"
#include "constants.h"
#include "grid.h"
#include <stdio.h>

#define MAX_UNDO 100

static Grid *undoStack[MAX_UNDO];
static int undoTop = 0;

void pushUndo(Grid *grid) {
  if (undoTop < MAX_UNDO) {
    Grid *clone = cloneGrid(grid);
    undoStack[undoTop++] = clone;
  } else {
    printf("[ Error ] Undo stack full.\n");
  }
}

Grid *popUndo() {
  if (undoTop > 0)
    return undoStack[--undoTop];
  return NULL;
}
