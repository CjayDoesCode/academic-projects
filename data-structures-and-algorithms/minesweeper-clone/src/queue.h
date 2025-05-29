#ifndef QUEUE_H
#define QUEUE_H

typedef struct {
  int x, y;
} QueueNode;

typedef struct {
  QueueNode *data;
  int front, rear, size, capacity;
} Queue;

Queue *createQueue(int capacity);
void enqueue(Queue *q, int x, int y);
QueueNode dequeue(Queue *q);
int isEmpty(Queue *q);
void freeQueue(Queue *q);

#endif
