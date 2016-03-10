#include <stdio.h>
#include <stdlib.h>

void display_repeats(int *a, int size){

 int freq_size = size-1;
 int *freq;
 int i;
 freq = malloc(freq_size * sizeof freq);
 for( i = 0; i < freq_size; i++){
     freq[i] = 0;
 }
 for (i = 0; i < size; i++){
     freq[a[i]]++;
 }
 for (i = 0; i < freq_size; i++){
     if (freq[i] > 1){
         printf("%d occurs %d times\n", i, freq[i]);
     }
 }
 free(freq);
}

int main(void) {
    int array_size = 0;
    int *my_array;
    int i = 0;
    printf("Enter the size of the array:\n");
    scanf("%d", &array_size);
    my_array = malloc(array_size * sizeof my_array[0]);
    if (NULL == my_array) {
        fprintf(stderr, "memory allocation failed!\n");
        return EXIT_FAILURE;
    }
    for (i = 0; i < array_size; i++) {
        my_array[i] = rand() % array_size;
    }
    printf("What's in the array:\n");
    for (i = 0; i < array_size; i++) {
        printf("%d ", my_array[i]);
    }
    
    printf("\n");
    display_repeats(my_array, array_size);
   
    free(my_array);
    return EXIT_SUCCESS;
}
