#include <stdlib.h>
#include <stdio.h>
#include "mylib.h"
#include "queue.h"


struct queue {
    int *items;
    int head;
    int capacity;
    int num_items;
};

queue queue_new(int size) {
    queue q = emalloc(sizeof *q);
    q->capacity = size;
    q->head = 0;
    q->num_items = 0;
    q->items = emalloc(q->capacity * sizeof q->items[0]);
    return q;
}

void enqueue(queue q, int item) {
    if (q->num_items < q->capacity) {
        q->items[(q->head + q->num_items++) % q->capacity] = item;
    }
}

int dequeue(queue q) {
    int result = q->items[q->head % q->capacity];
    q->num_items--;
    q->head++;
    return result;
}

void queue_print(queue q) {
    /* print queue contents one per line to 2 decimal places */
    int i;
    for(i=q->head; i < (q->num_items+q->head); i++){
        printf("%d\n", q->items[i%q->capacity]);
    }
}

void queue_print_info(queue q) {
    int i;
    printf("capacity %d, num_items %d, head %d\n[", q->capacity,
           q->num_items, q->head);
    for (i = 0; i < q->capacity; i++) {
        printf("%s%d", i == 0 ? "" : ", ", q->items[i]);
    }
    printf("]\n");
}

int queue_size(queue q) {
    return q->num_items;
}

queue queue_free(queue q) {
    free(q->items);
    free(q);
    return q;
}
