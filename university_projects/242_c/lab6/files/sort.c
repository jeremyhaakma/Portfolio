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

int main(int argc, char **argv) {
    FILE *infile;
    int my_array[ARRAY_MAX];
    int i, count = 0;
    int input;
    int in_file = 0;


    
    infile = fopen(argv[1], "r");
    argc=argc;
    if (infile == NULL){
        fprintf(stderr, "%s: can't find file %s\n", argv[0], argv[1]);
        return EXIT_FAILURE;
    }
    while (count < ARRAY_MAX && 1 == fscanf(infile, "%d", &my_array[count])) {
        count++;
    }
    fclose(infile);
    /*sort array*/
    selection_sort(my_array, count);



    while (1 == scanf("%d", &input)){
        for (i=0; i<count; i++){
            if (my_array[i] == input){
                in_file = 1;
            }
        }
        in_file == 1? printf("+\n"):printf("-\n");
        in_file = 0;
    }

    
    /*print sorted array
    for (i = 0; i < count; i++) {
        printf("%d\n", my_array[i]);
        } 
    fprintf(stderr, "%d %f\n", count, (stop - start) / (double)CLOCKS_PER_SEC);
    */

    return EXIT_SUCCESS;
}
