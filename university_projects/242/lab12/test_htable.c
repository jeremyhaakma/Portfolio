#include <stdio.h>
#include <stdlib.h>
#include "mylib.h"
#include "htable.h"
int main(int argc, char **argv) {
    int size;
    htable h;
    char word[256];
    if (argc <=1 ){
        size = 25013;
        fprintf(stderr, "No arguments given, set htable size to %d\n", size);
    } else {
        size = atoi(argv[1]);
    }
    h = htable_new(size);
    while (getword(word, sizeof word, stdin) != EOF) {
        htable_insert(h, word);
    }
    htable_print(h, stdout);
    htable_free(h);
    return EXIT_SUCCESS;
}
