#include <stdio.h>
#include <stdlib.h>
#include "flexarray.h"
#include "mylib.h" /*for emalloc and erealloc */

struct flexarrayrec {
    int capacity;
    int itemcount;
    int *items;
};

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


static void insertion_sort(int *a, int n) {
    int p;
    int l;
    int key;
    for (p = 1; p<n; p++){
        key = a[p];
        l = p-1;
        while( a[l] > key && l >= 0 ){
            a[l+1]=a[l];
            l--;
        }
        a[l+1]=key;
    }
}

static void merge(int *array, int *workspace, int len){
    int i = 0; /* index starting left side of array */
    int j = len/2; /* index starting right side of array */
    int w = 0;
    while (i < len/2 && j < len ){
        if (array[i] < array[j]){
            workspace[w] = array[i];
            i++;
        } else{
            workspace[w] = array[j];
            j++;
        }
        w++;
    }
    while (i < len/2){
        workspace[w] = array[i];
        i++;
        w++;
    }
    while (j < len){
        workspace[w] = array[j];
        j++;
        w++;
    }   
}

static void merge_sort(int *array, int *workspace, int n) {
    int limit = 40; /* smallest size before insertion sort is called */
    int i; /* for loop counter */
    if (n < limit){
        
        insertion_sort(array, n);

        return;
    }
    
    if (n > 1){
        merge_sort(array, workspace, n/2);
        merge_sort( (array + (n/2)), workspace, (n-(n/2)) );
        
        merge(array, workspace, n);
        for (i = 0; i < n; i++){
            array[i] = workspace[i];  
        }
    }
}

static void quicksort (int *array, int n){
    int pivot= array[0];
    int i = -1;
    int j = n;
    int temp;
    if (n < 2){
        return;
    }
    for (;;){
        do {
            i++;
        } while (array[i] < pivot);
        do {
            j--;
        } while (array[j] > pivot);
        if (i < j){
            temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        } else break; 
    }
    quicksort(array, j+1);
    quicksort(array+j+1, n-j-1);
    
}


void flexarray_sort(flexarray f){
    int *workspace = emalloc(f->itemcount * sizeof workspace[0]);
    merge_sort(f->items, workspace, f->itemcount);
    free(workspace);
}

void flexarray_quicksort(flexarray f){
    quicksort(f->items, f->itemcount);
}

void flexarray_free(flexarray f){
    free(f->items);
}
