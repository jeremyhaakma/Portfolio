#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define ARRAY_LEN 100
#define BUFF_SIZE 80

void *emalloc(size_t s){
    void *result = malloc(s);
    if (NULL == result) {
        fprintf(stderr, "Can't allocate memory\n");
        exit(EXIT_FAILURE);
    }
    return result;
}

void print_big_words(char **words, int length, double average){
    if (length > 0){
        if(strlen(words[0]) > average){
            printf("%s\n", words[0]);
        }
        print_big_words(words +1, length-1, average);
    }
}

int main(void) {
    char *words[ARRAY_LEN];
    char word[BUFF_SIZE];
    int count = 0;
    int i;
    double average = 0.0;

    while(count < ARRAY_LEN && 1 == scanf("%79s", word)){
        words[count] = emalloc((strlen(word) +1) * sizeof word[0] );
        strcpy(words[count++], word);
        average += strlen(word);
    }

    if (count > 0) {
        average /= count;
        fprintf(stderr, "%.2f\n", average);
        print_big_words(words, count, average);
    }
    for (i=0; i< count; i++){
        free(words[i]);
    }
    return EXIT_SUCCESS;
}
