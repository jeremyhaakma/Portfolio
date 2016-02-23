#include <stdio.h>
#include <stdlib.h>
#include "mylib.h"
#include "bst.h"

void print_key(char *s) {
    printf("%s\n", s);
}

int main(void) {

    bst b;
    char word[256];

    b = bst_new();
    
    while (getword(word, sizeof word, stdin) != EOF) {
        b = bst_insert(b, word);
    }

    if (bst_search(b, "cat") > 0) {
        printf("Found the word 'cat'\n");
    } else {
        printf("Did not find the word 'cat'\n");
    }
   
    printf("Inorder Traversal:\n");
    bst_inorder(b, print_key);
    if (bst_search(b, "the") > 0) {
   	printf("Deleting 'the':\n");
   	b = bst_delete(b, "the");
    }
    printf("Inorder Traversal:\n");
    bst_inorder(b, print_key);

    printf("Freeing Tree:\n");
    b = bst_free(b);

    printf("Inorder Traversal:\n");
    bst_inorder(b, print_key);

    return EXIT_SUCCESS;
}