#include <stdio.h>
#include <stdlib.h>
#include <assert.h>
#include <ctype.h>
#include "mylib.h"

/**
 * ___
 *
 * @param ___
 * @return ___
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
 * ___
 *
 * @param ___
 * @param ___
 * @return ___
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
 * ___
 *
 * @param ___
 * @param ___
 * @param ___
 * @return ___
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
