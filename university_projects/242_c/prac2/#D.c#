/* Author: Jeremy Haakma */
/* Sorts an array of ints using an insertion sort algorithm */

#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#define ARRAY_MAX 30000

   /* Sorts an array of ints. */
   /* Iteratively takes each item from the unsorted */
   /* part of the array and inserts it into the correct position in the */
   /* sorted part of the array, shifting the others along. */
   /* int *a: an array of ints to be sorted */
   /* int length: the length of the array   */  
void insertion_sort(int *a, int length) {
    /*pos: below pos = sorted, above pos = unsorted*/
    int pos;
    /*i: scans through unsorted part of array*/
    int i;
    /*key: value of item to be inserted */
    int key;
    for (pos = 1; pos <length; pos++){
        if (pos == length/2){
            for (i = 0; i < length; i++) {
                fprintf(stderr, "%d\n", a[i]);
            }
        }
        key = a[pos];
        i = pos -1;
        while(i >= 0 && a[i] > key){
            a[i+1] = a[i];
            i--;
        }
        a[i+1]=key;
    }
}

/* Main method. Reads in an array of ints from stdin, */
/*  sorts them and prints out the sorted array  Also */
/* times the sorting algorithm to be compared to selection */
/* sort */
int main(void) {
    /* my_array: array to be sorted */
    int my_array[ARRAY_MAX];
    /* i: for loop counter, count: number of items scanned in */
    int i, count = 0;
    while (count < ARRAY_MAX && 1 == scanf("%d", &my_array[count])) {
        count++;
    }
    /* start sort */
    insertion_sort(my_array, count);
    for (i = 0; i < count; i++) {
        printf("%d\n", my_array[i]);
    }
        return EXIT_SUCCESS;
}
