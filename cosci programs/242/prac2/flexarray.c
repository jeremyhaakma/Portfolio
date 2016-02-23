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
    if (NULL == result) {
        fprintf(stderr, "Memory allocation failed!\n");
        exit(EXIT_FAILURE);
    }
    return result;
}

void *erealloc(void *p, size_t s ){
    void *result = realloc(p, s);
    if (NULL == result) {
        fprintf(stderr, "memory reallocation failed.\n");
        exit(EXIT_FAILURE);
    }
    return result;
}

flexarray flexarray_new(){
    flexarray result = emalloc (sizeof *result);
    result->capacity = 2;
    result->itemcount = 0;
    result->items = emalloc(result->capacity * sizeof result->items[0]);
    return result;
}

void flexarray_append(flexarray f, int num){
    if (f->itemcount == f->capacity){
        f->capacity *= 2;
        f->items = erealloc(f->items, f->capacity * sizeof f->items[0]);
    }
    f-> items[f->itemcount++] = num;
}

void flexarray_print(flexarray f) {
    int i;
    for (i = 0; i < f->itemcount; i++){
        printf("%d\n", f->items[i]);
    }
}


static void insertion_sort(int *a, int length) {
    int i;
    int pos;
    int key;
    for(pos = 1; pos < length; pos++){
        if (pos == length/2){
            for(i=0; i<length; i++){
                fprintf(stderr, "%d\n", a[i]);
            }
        }
        i = pos -1;
        key = a[pos];
        while(i >= 0 && a[i] > key){
            a[i+1] = a[i];
            i--;
        }
        a[i+1] = key;
    }
}



void flexarray_sort(flexarray f){
    insertion_sort(f->items, f->itemcount);
}


void flexarray_free(flexarray f){
    free(f->items);
}
