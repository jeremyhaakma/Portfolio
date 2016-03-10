#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "htable.h"
#include "mylib.h"

struct htablerec {
    int capacity;
    int num_keys;
    char **keys;
};

static unsigned int htable_word_to_int(char *word) {
    unsigned int result = 0;

    while (*word != '\0') {
        result = (*word++ + 31 * result);
    }
    return result;
}

static unsigned int htable_hash(htable h, unsigned int i_key) {
    return i_key % h->capacity;
}

htable htable_new(int capacity) {
    int i;
    htable result = emalloc(sizeof *result);
    result->capacity = capacity;
    result->num_keys = 0;
    result->keys = emalloc(result->capacity * sizeof result->keys[0]);
    for(i=0;i<capacity;i++){
        result->keys[i] = NULL;
    }    
    return result;

}

void htable_free(htable h) {
    int i;
    for(i=0;i<h->capacity;i++){
        free(h->keys[i]);
    }
    free(h->keys);
    free(h);
}

int htable_insert(htable h, char *key) {
    int hash_index = htable_word_to_int(key)%h->capacity;
    int s_count = 0;

    while(NULL != h->keys[hash_index]){
        if(s_count >= h->capacity){
            return 0;
        }
        if(strcmp(h->keys[hash_index], key) == 0){
            return 1;
        }else{
            hash_index = htable_hash(h, hash_index+1);;
            s_count++;
        }         
    }
    
    h->keys[hash_index] = emalloc((strlen(key)+1) * sizeof key[0]);
    strcpy(h->keys[hash_index], key);
    return 1;  
}

void htable_print(htable h, FILE *stream) {
    int i;
    for (i = 0; i < h->capacity; i++) {
        fprintf(stream, "%2d %s\n", i, h->keys[i] == NULL ? "" : h->keys[i]);
    }
}
