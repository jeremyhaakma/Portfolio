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
    int *frequencies;
    char word[WORD_LEN];
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
    result->frequencies = emalloc
        (result->capacity * sizeof result->frequencies[0]);
    result->keys = emalloc(result->capacity * sizeof result->keys[0]);
    
    for (i = 0; i < capacity; i++){
        result->frequencies[i] = 0;
        result->keys[i] = NULL;
    }
    return result;
}

void htable_free(htable h){
    int i;
    free(h->frequencies);
    for (i = h->capacity-1; i>=0; i--){
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
            h->frequencies[index]++;
            return 1;
    
            /* string are the same: increase and return frequency */
        } else if (strcmp(str, h->keys[index]) == 0){
            h->frequencies[index]++;
            return h->frequencies[index];
        }
        /* increment index with wraparound */
        index = (index+1) % h->capacity;
        check_sum++;
    }
    /* table full */
    return 0;
}

int htable_search(htable h, char *key) {
    int collisions = 0;
    /* hash key to get starting index*/
    int index = htable_word_to_int(key) % h->capacity;
    while (h->keys[index] != NULL
           && strcmp(h->keys[index], key) != 0
           && collisions < h->capacity){
        index =  (index+1) % h->capacity;
        collisions++;
    }
    if (collisions == h->capacity){
        return 0;
    } else {
        return h->frequencies[index];
    }
}

void htable_print(htable h, FILE *stream){
    int i;
    for (i=0; i < h->capacity; i++){
        if (h->keys[i] != '\0'){
            fprintf(stream, "%d %s\n", h->frequencies[i], h->keys[i]);
        }
    }
}
