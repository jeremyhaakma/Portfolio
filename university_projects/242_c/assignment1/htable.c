#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "mylib.h"
#include "htable.h"
#include "container.h"

/**
 * htablerec: struct for a hash table made of containers which can hold
 * multiple words using chaining. Containers can be implemented as either
 * flexarrays (type: FLEX_ARRAY) or red-black trees (type: RED_BLACK_TREE).
 **/
struct htablerec {
    /* capacity: set by user in htable_new */
    int capacity;
    /* containers: pointer to array of containers */
    container *containers;
    /* type: set by user in htable_new. Sets the type of container to be used*/
    container_t type;
};

/**
 * htable_word_to_int: Uses a hashing algorithm to convert a
 * string into a int, which is then used as the index in
 * the hash table (with wraparound - see htable_insert). 
 * @param word string to be converted.
 * @return int of the word hashed.
 **/
static unsigned int htable_word_to_int(char *word) {
    unsigned int result = 0;
    while (*word != '\0') {
        result = (*word++ + 31 * result);
    }
    return result;
}

/**
 * htable_new: creates a new htable.
 * Takes capacity and container types as parameters to create
 * a new htable, allocating memory for the containers array and
 * for each container in the containers array. 
 * @param capacity: sets the size of the htable.
 * @param type: sets the type of container being used. 
 * @return returns the newly creates htable. 
 **/
htable htable_new(int capacity, container_t type){
    int i;
    htable result = emalloc(sizeof *result);
    result->capacity = capacity;
    result->containers =
        emalloc(result->capacity * sizeof result->containers[0]);
    for(i=0;i<result->capacity;i++){
        if(type == RED_BLACK_TREE){
            result->containers[i] = container_new(RED_BLACK_TREE);
        }else{
            result->containers[i] = container_new(FLEX_ARRAY);
        }
    }
    return result;
}

/**
 * htable_free: frees all the memory used by the htable/
 * @param h htable to be freed. 
 **/
void htable_free(htable h){
    int i;
    for(i=0;i<h->capacity;i++){
        container_free(h->containers[i]);
    }
    free(h->containers);
}

/**
 * htable_insert: inserts a new char string into the htable.
 * Uses htable_word_to_int() method to hash the word, and wraps
 * around the size of the htable to find the index at which the word
 * will be inserted.
 * @param h htable inserted into.
 * @param *word word to be inserted.
 **/
void htable_insert(htable h, char *word){
    int hash_index = htable_word_to_int(word)%h->capacity;
    container_add(h->containers[hash_index], word);
}

/**
 * htable_print: calls the print method determined by container.h to
 * print each item in the htable.
 * @param h htable to be printed.
 **/
void htable_print(htable h){
    int i;
    for(i=0;i<h->capacity;i++){
        container_print(h->containers[i]);
    }
}

/**
 * htable_search: Uses the search method determined by container.h
 * (depending on the container type set) to search through each
 * container in the htable for a specific key word.
 * @param h htable to be searched.
 * @param *key word to be searched for.
 * @return returns 1 if word is found, otherwise 0.
 **/
int htable_search(htable h, char *key){
    int hash_index = htable_word_to_int(key)%h->capacity;
    if(h->containers[hash_index] == NULL){
        return 0;
    }else{
        return container_search(h->containers[hash_index], key);
    }
}
