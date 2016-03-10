#include <stdio.h>
#include <stdlib.h>
#include <assert.h>
#include <ctype.h>
#include "mylib.h"

/**
 * Allocates memory of requested size.
 *
 * @param s size of the memory to be allocated.
 * @return result pointer to a block of memory as big as the requested size.
 */
void *emalloc(size_t s) {
    void *result = malloc(s);
    if(NULL == result){
        fprintf(stderr, "Can't allocate memory.\n");
        exit(EXIT_FAILURE);
    }
    return result;
}

/**
 * Reallocates memory.
 *
 * @param p pointer to exising block of memory previously allocated.
 * @param s size of the new block of memory to be allocated
 * @return result pointer to a block of memory as big as the requested size.
 */
void *erealloc(void *p, size_t s) {
    void *result = realloc(p,s);
    if(NULL == result){
        fprintf(stderr, "Can't allocate memory.\n");
        exit(EXIT_FAILURE);
    }
    return result;
}

/**
 * Reading a file word by word converting to lower case.
 *
 * @param s character array to store word.
 * @param limit integer representing the memory size of the word.
 * @param stream input stream where words are retrieved from.
 * @return int determines whether end of file has been reached or not.
 */
int getword(char *s, int limit, FILE *stream) {
    int c;
    char *w = s;
    assert(limit > 0 && s != NULL && stream != NULL);
    /* skip to the start of the word */
    while (!isalnum(c = getc(stream)) && EOF != c)
        ;
    if (EOF == c) {
        return EOF;
    } else if (--limit > 0) { /* allows for the \0 */
        *w++ = tolower(c);
    }
    while (--limit > 0) {
        if (isalnum(c = getc(stream))) {
            *w++ = tolower(c);
        } else if ('\'' == c) {
            limit++;
        } else {
            break;
        }
    }
    *w = '\0';
    return w - s;
}
