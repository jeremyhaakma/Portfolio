/* Author: Jeremy Haakma */
/* Sorts an array of ints using a selection sort algorithm */

#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#define ARRAY_MAX 30000

/* Sorts an array of ints. */
/* Iteratively takes each item in the array, scans the rest of */
/* the array and swaps it with the smallest valued item. */
/* int *a: an array of ints to be sorted */
/* int n: the length of the array   */
void selection_sort(int *a, int n) {
    /* pos: above pos = unsorted, below pos = sorted */
    int p;
    /* i: scans through array for smallest value */
    int i;
    /*smallest_index: index of the smallest value of the unsorted array */
    int smallest_index;
    /* temp: used for swapping */
    int temp;
    for (p = 0; p < n-1; p++){
        smallest_index = p;
        for(i=p; i < n; i++){
            if(a[i] < a[smallest_index]){
                smallest_index = i;
            }
        }
        temp = a[smallest_index];
        a[smallest_index] = a[p];
        a[p] = temp; 
    }
}
/* Main method. Reads in an array of ints from stdin, */
/*  sorts them and prints out the sorted array. Also */
/* times the sorting algorithm to be compared to insertion */
/* sort */
int main(void) {
    /* my_array: array to be sorted */
    int my_array[ARRAY_MAX];
    /* i: for loop counter, count: number of items scanned in */
    int i, count = 0;
    /* start, stop: for timing sort algorithm*/
    clock_t start, stop;
    while (count < ARRAY_MAX && 1 == scanf("%d", &my_array[count])) {
        count++;
    }
    /* start sort */
    start = clock();
    selection_sort(my_array, count);
    stop = clock();
    /* end sort */
    
    for (i = 0; i < count; i++) {
        printf("%d\n", my_array[i]);
    }
    fprintf(stderr, "%d %f\n", count, (stop - start) / (double)CLOCKS_PER_SEC);
    return EXIT_SUCCESS;
}
