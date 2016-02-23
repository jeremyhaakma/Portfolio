#include <stdio.h>
#include <stdlib.h>
#include "mylib.h"
#include "bst.h"

void print_key(char *s) {
    printf("%s\n", s);
}

int main() {
    bst b;
    char word[256];

    b = bst_new();

    while (getword(word, sizeof word, stdin) != EOF) {
        b = bst_insert(b, word);
    }
    fprintf(stderr, "inorder: \n");
    bst_inorder(b, print_key);
    b = bst_free(b);
    return EXIT_SUCCESS;
}
