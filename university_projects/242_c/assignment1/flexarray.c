#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "flexarray.h"
#include "mylib.h" /*for emalloc and erealloc */
#define WORD_LEN 80/* Max size of word */

/**
 * flexarrayrec: struct for a String array that automatically
 * doubles in size when needed.
 * 
 **/
struct flexarrayrec {
    /* capacity: current size of flexarray */
    int capacity;
    /* wordcount: number of words in flexarray. */
    int wordcount;
    /* word[]: array of chars, representing a word. */
    char word[WORD_LEN];
    /* words: pointer to an array of word[] */
    char **words;
};
/**
 * flexarray_new creates a new flexarray.
 * Allocates memory for the struct,
 * sets the initial capacity to 2 and wordcount to 0,
 * allocates memory for the array of words.
 * @return returns newly creates flexarray.
 **/
flexarray flexarray_new(){
    flexarray result = emalloc (sizeof *result);
    result->capacity = 2;
    result->wordcount = 0;
    result->words = emalloc(result->capacity * sizeof result->words[0]);
    return result;
}

/**
 * flexarray_append inserts a new word at the end of the flexarray.
 * If the flexarray is full, it doubles the size and reallocates the
 * extra memory for the words array and the word being added. 
 * @param f flexarray to append.
 * @param *word word to be appended.
 **/
void flexarray_append(flexarray f, char *word){
    if (f->wordcount == f->capacity){
        f->capacity *= 2;
        f->words = erealloc(f->words, f->capacity * sizeof f->words[0]);
    }
    f->words[f->wordcount] = emalloc(strlen(word) * sizeof f->words[0]);
    strcpy(f->words[f->wordcount++], word);
}

/**
 * flexarray_word_present searches the flexarray for the target word.
 * @param f flexarray to be searched.
 * @param *word word to be searched for.
 * @return 1 if word is present, otherwise 0.
 **/
int flexarray_word_present(flexarray f, char *word) {
    int i;
    for (i = 0; i < f->wordcount; i++){
        if (strcmp(word, f->words[i]) == 0){
            return 1;
        }
    }
    return 0;
}
/**
 * flexarray_visit calls the parameter function on each item in the
 * flexarray f.
 * @param f flexarray to be visited.
 * @param function function to be called on each item.
 **/
void flexarray_visit(flexarray f, void function(char *word)){
    int i;
    for (i = 0; i < f->wordcount; i++){
        function(f->words[i]);
    }
}

/**
 * flexarray_free frees all the memory used by the flexarray.
 * Frees each word, then the array of words, then the struct itself.
 * @param f flexarray to be freed. 
 **/
void flexarray_free(flexarray f){
    int i;
    for (i = 0; i < f->wordcount; i++){
        free( f->words[i] );
    }
    free(f->words);
    free(f);
}

/**
 * flexarray_is_empty checks whether a flexarray has no words.
 * @param f flexarray to be checked.
 * @result returns 1 if the flexarray has no words, otherwise 0.
 **/
int flexarray_is_empty(flexarray f){
    if(f->wordcount == 0){
        return 1;
    }
    return 0;
}
