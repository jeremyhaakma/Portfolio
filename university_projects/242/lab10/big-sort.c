#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include "flexarray.h"

int main(void) {
    int item;
    int count = 0;
    clock_t start, stop;
    flexarray my_flexarray = flexarray_new();
    flexarray my_flexarray2 = flexarray_new();

    while (1 == scanf("%d", &item)) {
        flexarray_append(my_flexarray, item);
        flexarray_append(my_flexarray2, item);
    }
    start = clock();
    flexarray_sort(my_flexarray);
    stop = clock();
    fprintf(stderr, "%d %f\n", count, (stop - start) / (double)CLOCKS_PER_SEC);
    start = clock();
    flexarray_quicksort(my_flexarray2);
    stop = clock();
    fprintf(stderr, "%d %f\n", count, (stop - start) / (double)CLOCKS_PER_SEC);
    /* flexarray_print(my_flexarray);*/
    flexarray_free(my_flexarray);
    flexarray_free(my_flexarray2);    
    return EXIT_SUCCESS;
}
