#ifndef LEADERBOARD_H
#define LEADERBOARD_H

#include "constants.h"

typedef struct LeaderboardNode {
  char name[MAX_NAME_SIZE];
  int time;
  struct LeaderboardNode *left;
  struct LeaderboardNode *right;
} LeaderboardNode;

LeaderboardNode *createNode(const char *name, int time);
LeaderboardNode *insertNode(LeaderboardNode *root, const char *name, int time);
void printLeaderboard(LeaderboardNode *root, int *rank);
void freeLeaderboard(LeaderboardNode *root);
void saveLeaderboard(LeaderboardNode *root);
LeaderboardNode *loadLeaderboard();

#endif
