#include <stdio.h>
#include <stdlib.h>
#include "flexarray.h"
    
struct flexarrayrec {
int capacity;
int itemcount;
    int *items;
};

void *emalloc(size_t s){
    void *result = malloc(s);
    if (NULL == result){
        fprintf(stderr, "Memory allocation failed!\n");
        exit(EXIT_FAILURE);
    }
    return result;
}

void *erealloc(void *p, size_t s){
    void *result = realloc(p, s);
    if (NULL == result){
        fprintf(stderr, "Memory allocation failed!\n");
        exit(EXIT_FAILURE);
    }
    return result;
}

flexarray flexarray_new() {
    flexarray a = emalloc(sizeof *a);
    a->capacity = 2;
    a->itemcount = 0;
    a->items = emalloc(a->capacity * sizeof a->items[0]);
    return a;
}
    
void flexarray_append(flexarray f, int num) {
    if (f->capacity == f->itemcount){
        f->capacity *= 2;
        f->items = erealloc(f->items, f->capacity * sizeof f->items[0]);
    }
    f->items[f->itemcount++] = num;
}
    
void flexarray_print(flexarray f) {
    int i;
    for (i=0; i < f->itemcount; i++){
        printf("%d\n", f->items[i]);
    }
}
    
void flexarray_sort(flexarray f) {
    /* sort into ascending order */
    int pos, key, i;
    for(pos=1; pos < f->itemcount; pos++){
        if (pos == f->itemcount/2){
            for (i = 0; i < f->itemcount; i++){
                fprintf(stderr, "%d\n", f->items[i]);
            }
        }
        key = f->items[pos];
        i = pos - 1;
        while(i >= 0 && f->items[i] > key){
            f->items[i+1] = f->items[i];
            i--;
        }
        f->items[i+1] = key;
    }
}
    
void flexarray_free(flexarray f) {
    free(f->items);
}
