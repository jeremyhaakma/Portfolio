

#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define ARRAY_MAX 30000


/* Author: Jeremy Haakma */
/* Sorts an array of ints using an insertion sort algorithm */
void insertion_sort(int *a, int n) {
    int p;
    int l;
    int key;
    for (p = 1; p<n; p++){
        key = a[p];
        l = p-1;
        while( a[l] > key ){
            a[l+1]=a[l];
            l--;
        }
        a[l+1]=key;
    }
}

int main(void) {
    int my_array[ARRAY_MAX];
    int i, count = 0;
    clock_t start, stop;
    while (count < ARRAY_MAX && 1 == scanf("%d", &my_array[count])) {
        count++;
    }
    start = clock();
    insertion_sort(my_array, count);
    stop = clock();
    for (i = 0; i < count; i++) {
        printf("%d\n", my_array[i]);
    }
    fprintf(stderr, "%d %f\n", count, (stop - start) / (double)CLOCKS_PER_SEC);
    return EXIT_SUCCESS;
}
