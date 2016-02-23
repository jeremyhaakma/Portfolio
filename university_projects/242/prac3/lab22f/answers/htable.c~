#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "htable.h"
#include "mylib.h"

#define WORD_LEN 80
#define MAX_CAPACITY 10000

struct htablerec {
    int capacity;
    int num_keys;
    char **keys;
};

static unsigned int htable_word_to_int(char *word){
    unsigned int result = 0;
    while (*word != '\0') {
        result = (*word++ + 31 * result);
    }
    return result;
}

htable htable_new(int capacity){
    htable result = emalloc (sizeof *result);
    int i;
    result->capacity = capacity;
    result->num_keys = 0;
    result->keys = emalloc(result->capacity * sizeof result->keys[0]);
    for (i = 0; i < capacity; i++){
        result->keys[i] = NULL;
    }
    return result;
}

void htable_free(htable h){
    int i;
    for (i=0; i < h->capacity; i++){
        free( h->keys[i] );
    }
    free(h->keys);
    free(h);
}


int htable_insert(htable h, char *str){
    int index = htable_word_to_int(str) % h->capacity;
    int check_sum = 0;
    while (check_sum < h->capacity){
        /* empty space: insert word */    
        if (h->keys[index] == NULL){
            h->keys[index] = emalloc( (strlen(str) +1) * sizeof str[0] );
            strcpy(h->keys[index], str);
            return 1;
            /* same word: return success */
        } else if (strcmp(h->keys[index], str) == 0){
            return 1;
        }
        /* increment index with wraparound */
        index = (index + 1) % h->capacity;
        check_sum++;
    }
    /* table full */
    return 0;
}


void htable_print(htable h, FILE *stream){
    int i;
    for (i = 0; i < h->capacity; i++) {
        fprintf(stream, "%2d %s\n", i, h->keys[i] == NULL ? "" : h->keys[i]);
    }
}
