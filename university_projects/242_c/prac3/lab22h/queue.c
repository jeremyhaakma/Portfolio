#include <stdio.h>
#include <stdlib.h>
#include "mylib.h"
#include "queue.h"

struct queue {
    int capacity;
    int head;
    int num_items;
    double *items;
};

queue queue_new() {
    int default_size = 7;
    int i;
    queue q = emalloc(sizeof *q);
    q->capacity = default_size;
    q->num_items = 0;
    q->items = emalloc(q->capacity * sizeof q->items[0]);
    for(i=0; i< q->capacity; i++){
        q->items[i] = 0.00;
    }
    return q;
}

void enqueue(queue q, double item) {
    if (q->num_items < q->capacity) {
        q->items[(q->head + q->num_items++) % q->capacity] = item;
    }
}

double dequeue(queue q) {
    double result = q->items[q->head];
    q->num_items--;
    q->head = (q->head+1)%q->capacity;
    return result;
}

void queue_print(queue q) {
    /* print queue contents one per line to 2 decimal places */
    int i;
    for(i= 0; i < q->num_items; i++){
        printf("%.2f\n", q->items[(i+q->head)%q->capacity]);
    }
}

void queue_print_info(queue q) {
    int i;
    printf("capacity %d, num_items %d, head %d\n[", q->capacity,
           q->num_items, q->head);
    for (i = 0; i < q->capacity; i++) {
        printf("%s%.2f", i == 0 ? "" : ", ", q->items[i]);
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
