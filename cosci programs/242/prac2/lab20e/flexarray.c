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
    flexarray result = emalloc(sizeof *result);
    result->capacity = 2;
    result->itemcount = 0;
    result->items = emalloc(result->capacity * sizeof result->items[0]);
    return result;
}
    
void flexarray_append(flexarray f, int item){
    if (f->capacity == f->itemcount){
        f->capacity *= 2;
        f->items = erealloc(f->items, f->capacity * sizeof f->items[0]);
    }
    f->items[f->itemcount++] = item;
}

void flexarray_print(flexarray f) {
    int i;
    for (i=0; i < f->itemcount; i++){
        printf("%d\n", f->items[i]);
    }
}
    
void flexarray_sort(flexarray f) {
    /* selection sort */
    int i, pos, smallest, temp;
    for(pos = 0; pos < f->itemcount; pos++){
        if (pos == f->itemcount/2){
            for (i = 0; i < f->itemcount; i++) {
                fprintf(stderr, "%d\n",  f->items[i]);
            }
            }
        smallest = pos;
        for (i = pos+1; i < f->itemcount; i++){
            if (f->items[i] < f->items[smallest]){
                smallest = i;
            }
        }
        temp = f->items[pos];
        f->items[pos] = f->items[smallest];
        f->items[smallest] = temp;
    }
    
}
    
void flexarray_free(flexarray f) {
    free(f->items);
    free(f);
}
