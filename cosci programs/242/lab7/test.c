#include <stdio.h>
#include <stdlib.h>
#define MAX_ARRAY 100
#define WORD_LENGTH 79

void *emalloc (size_t s){
    void *result = malloc(s);
    if (NULL = result) {
        fprintf(stderr, "Memory allocation failed\n");
        exit(EXIT_FAILURE);
    }
    return result;
}

int main(){
    
    return EXIT_SUCCESS;
}
