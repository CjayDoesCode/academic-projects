#ifndef UNDO_H
#define UNDO_H

#include "grid.h"

void pushUndo(Grid *grid);
Grid *popUndo();

#endif