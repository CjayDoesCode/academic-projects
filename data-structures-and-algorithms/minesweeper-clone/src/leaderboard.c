#include "leaderboard.h"
#include "constants.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

LeaderboardNode *createNode(const char *name, int time) {
  LeaderboardNode *node = (LeaderboardNode *)malloc(sizeof(LeaderboardNode));

  if (!node) {
    printf("%s[ Error ] Failed to allocate memory for leaderboard node.%s", RED,
           RESET);
    exit(EXIT_FAILURE);
  }

  strcpy(node->name, name);
  node->time = time;
  node->left = node->right = NULL;
  return node;
}

LeaderboardNode *insertNode(LeaderboardNode *root, const char *name, int time) {
  if (root == NULL) {
    return createNode(name, time);
  }

  if (time < root->time) {
    root->left = insertNode(root->left, name, time);
  } else if (time > root->time) {
    root->right = insertNode(root->right, name, time);
  } else {
    if (strcmp(name, root->name) < 0)
      root->left = insertNode(root->left, name, time);
    else
      root->right = insertNode(root->right, name, time);
  }
  return root;
}

void printLeaderboard(LeaderboardNode *root, int *rank) {
  if (root == NULL)
    return;

  if (*rank >= 10)
    return;

  printLeaderboard(root->left, rank);
  printf("%d. %s - %d seconds\n", (*rank)++, root->name, root->time);
  printLeaderboard(root->right, rank);
}

void freeLeaderboard(LeaderboardNode *root) {
  if (root == NULL)
    return;

  freeLeaderboard(root->left);
  freeLeaderboard(root->right);
  free(root);
}

static void saveLeaderboardHelper(LeaderboardNode *root, FILE *fp) {
  if (root == NULL)
    return;

  fprintf(fp, "%d\t%s\n", root->time, root->name);

  saveLeaderboardHelper(root->left, fp);
  saveLeaderboardHelper(root->right, fp);
}

void saveLeaderboard(LeaderboardNode *root) {
  FILE *fp = fopen(FILE_NAME, "w");

  if (!fp) {
    printf("%s[ Error ] Failed to open leaderboard file for writing.%s", RED,
           RESET);
    return;
  }

  saveLeaderboardHelper(root, fp);
  fclose(fp);
}

LeaderboardNode *loadLeaderboard() {
  FILE *fp = fopen(FILE_NAME, "r");

  if (!fp) {
    return NULL;
  }

  LeaderboardNode *root = NULL;

  char line[256];
  char name[200];
  int time;

  while (fgets(line, sizeof(line), fp)) {
    if (sscanf(line, "%d\t%[^\n]", &time, name) == 2) {
      root = insertNode(root, name, time);
    }
  }

  fclose(fp);
  return root;
}
