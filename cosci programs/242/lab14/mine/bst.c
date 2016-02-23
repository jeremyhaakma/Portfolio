#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "mylib.h"
#include "bst.h"

#define WORD_LEN 80

struct bst_node {
    char *key;
    bst left;
    bst right;
};

bst bst_delete(bst b, char *str){
    str = str;
    return b;
}

bst bst_free(bst b){
    if (b->left != NULL){
        b->left = bst_free(b->left);
    }
    if (b->right!= NULL){
        b->right = bst_free(b->right);
    }
    if (b != NULL){
        free(b->key);
        free(b);
        b = bst_new();
    }
    return b;
}

void bst_inorder(bst b, void f(char *str)) {
    /* stopping case: tree is empty */
    if (b == NULL){
        return;
    }
    /* otherwise perform inorder (left, this, right) traversal */
    else {
        bst_inorder(b->left, f);
        f(b->key);
        bst_inorder(b->right, f);
    }
}

void bst_preorder(bst b, void f(char *str)){
    /* stopping case: tree is empty */
    if (b == NULL){
        return;
    }
    /* otherwise preorder traverse and apply function */
    else {
        f(b->key);
        bst_preorder(b->left, f);
        bst_preorder(b->right, f);
    }
}

bst bst_insert(bst b, char *str){
    /* node is empty: insert here */
    if (b == NULL){
        b = emalloc(sizeof *b);
        b->key = emalloc( (strlen(str)+1) * sizeof b->key[0]);
        strcpy(b->key, str);
        b->left = NULL;
          b->right = NULL;
    }
    /* key to insert is greater: insert to right */
    else if (strcmp(b->key, str) < 0) {
        b->right = bst_insert(b->right, str);
    }
    /* key to insert is smaller: insert to left */
    else if (strcmp(b->key, str) > 0) {
        b->left = bst_insert(b->left, str);
    }
    /* inserting key = node key: do nothing */

    /* return b */
    return b;  
}

bst bst_new(){
    return NULL;
}

int bst_search(bst b, char *str){
    /* Empty bst */
    if (b == NULL){
        return 0;
    }
    /* Found the key */
    else if (strcmp(b->key, str) == 0){
        return 1;
    }
    /* target key is greater than current node: search right */
    else if (strcmp(b->key, str) < 0){
        return bst_search(b->right, str);
    }
    /* target key is smaller than current node: search left */
    else{
        return bst_search(b->left, str);
    }
}


