#include "queue.h"
#include <stdlib.h>

Queue *createQueue(int capacity) {
  Queue *q = malloc(sizeof(Queue));
  q->data = malloc(sizeof(QueueNode) * capacity);
  q->front = q->rear = q->size = 0;
  q->capacity = capacity;
  return q;
}

void enqueue(Queue *q, int x, int y) {
  if (q->size == q->capacity)
    return;
  q->data[q->rear].x = x;
  q->data[q->rear].y = y;
  q->rear = (q->rear + 1) % q->capacity;
  q->size++;
}

QueueNode dequeue(Queue *q) {
  QueueNode node = q->data[q->front];
  q->front = (q->front + 1) % q->capacity;
  q->size--;
  return node;
}

int isEmpty(Queue *q) { return q->size == 0; }

void freeQueue(Queue *q) {
  free(q->data);
  free(q);
}
