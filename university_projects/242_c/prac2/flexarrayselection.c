#include <stdio.h>
#include <stdlib.h>
/*#include "mylib.h"*/
#include "flexarray.h"

struct flexarrayrec {
    int capacity;
    int itemcount;
    int *items;

};



/* implementation of emalloc goes here */
void *emalloc(size_t s) {

    void *result = malloc(s);
    if (NULL == result) {
        fprintf(stderr, "memory allocation failed.\n");
        exit(EXIT_FAILURE);
    }
    return result;
}


/* implementation of erealloc goes here */
void *erealloc(void *p, size_t s) {

    void *result = realloc(p, s);
    if (NULL == result) {
        fprintf(stderr, "memory reallocation failed.\n");
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


void flexarray_append(flexarray f, int num) {
    if (f->itemcount == f->capacity) {

        f->capacity += f->capacity;
        f->items = erealloc(f->items, f->capacity * sizeof f->items[0]);
    }
    f->items[f->itemcount++] = num;
}


void selection_sort(int *a, int n) {
    int key;                      /* temporary holding variable*/
    int i;
    int j;                        /* index to compare*/
    int sortedIndex;
    int g;                        /*beginning of imaginary sorted array*/
    int count = 0;



    
    for (i = 0; i < (n-1); i++) {
        sortedIndex = i;             /*start at first index*/

        for(j=(i+1); j < n; j++){    /* comparing initial index with all index's*/
            if(a[j] < a[sortedIndex]) {  /*finding smallest value in array*/
                sortedIndex = j;        /*setting smallest value as sortedIndex*/
            }                                                               
        }


        /* sortedIndex is smallest value*/

        key = a[i];                /*assign original index value in temp*/
        a[i] = a[sortedIndex];    /* swap sortedIndex value in original index*/
        a[sortedIndex] = key;     /* with index that held smallest value*/


        if(count == (n/2-1)) {
            for (g = 0; g < n; g++) {
                fprintf(stderr, "%d\n",  a[g]);
            }
        }
        
        count++;
        
    }
}



void flexarray_sort(flexarray f){
    /*   int *work_array = emalloc(f->itemcount * sizeof work_array[0]);*/
    selection_sort(f->items, f->itemcount);
    /* free(work_array);*/

}


void flexarray_print(flexarray f) {
    int i;

    for (i = 0; i < f->itemcount; i++) {
        printf("%d\n",  f->items[i]);
    }
}

void flexarray_free(flexarray f) {

    free(f->items);
    free(f);
}
