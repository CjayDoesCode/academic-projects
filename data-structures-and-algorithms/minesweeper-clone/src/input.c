#include "input.h"
#include "constants.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void getInput(char *str) {
  char buffer[MAX_BUFFER_SIZE];
  bool hasNewline = false;

  printf("> ");
  fgets(buffer, sizeof(buffer), stdin);

  for (int i = 0; buffer[i] != '\0'; i++) {
    if (buffer[i] == '\n') {
      hasNewline = true;
      buffer[i] = '\0';
      break;
    }
  }

  if (!hasNewline) {
    while (getchar() != '\n')
      ;
  }

  strncpy(str, buffer, MAX_BUFFER_SIZE);
}

int getIntInput() {
  char buffer[MAX_BUFFER_SIZE];

  getInput(buffer);
  for (int i = 0; buffer[i] != '\0'; i++) {
    if (buffer[i] < '0' || buffer[i] > '9') {
      return ERROR;
    }
  }

  return atoi(buffer);
}