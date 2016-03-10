#include <stdio.h>
#include <stdlib.h>

void display_repeats(int *a, int size){
    int *freq;
    int freq_size = size-1;
    int i;
    freq = malloc(freq_size * sizeof freq[0]);
    for (i= 0; i < freq_size; i++){
        freq[i]=0;
    }
    for (i = 0; i < size; i++){
        freq[a[i]]++;
    }
    for (i = 0; i <= freq_size; i++){
        printf("number: %d appears %d times\n", i, freq[i]);
    }
}

int main(void) {
    int array_size = 0;
    int *my_dynamic_array;
    int i = 0;
    printf("Enter the size of the array: ");
    scanf("%d", &array_size);
    /* initialise the array to the appropriate size */
    my_dynamic_array = malloc(array_size * sizeof my_dynamic_array[0]);
    if (NULL == my_dynamic_array) {
        fprintf(stderr, "memory allocation failed!\n");
        return EXIT_FAILURE;
    }
    for (i = 0; i < array_size; i++) {
        my_dynamic_array[i] = rand() % array_size;
    }
    printf("What’s in the array:\n");
    for (i = 0; i < array_size; i++) {
        printf("%d ", my_dynamic_array[i]);
    }
    printf("\n");
    display_repeats(my_dynamic_array, array_size);
    /* release the memory associated with the array */
    free(my_dynamic_array);
    return EXIT_SUCCESS;
}
