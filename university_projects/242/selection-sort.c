#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define ARRAY_MAX 30000


void selection_sort(int *a, int n) {
    int p;
    int i;
    int smallest_index;
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

int main(void) {
    int my_array[ARRAY_MAX];
    int i, count = 0;
    clock_t start, stop;
    while (count < ARRAY_MAX && 1 == scanf("%d", &my_array[count])) {
        count++;
    }
    start = clock();
    selection_sort(my_array, count);
    stop = clock();
    for (i = 0; i < count; i++) {
        printf("%d\n", my_array[i]);
    }
    fprintf(stderr, "%d %f\n", count, (stop - start) / (double)CLOCKS_PER_SEC);
    return EXIT_SUCCESS;
}
