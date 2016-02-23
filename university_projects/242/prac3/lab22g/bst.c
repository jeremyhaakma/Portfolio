#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include "mylib.h"
#include "bst.h"

struct bstnode{
    char *key;
    bst left;
    bst right;
};

bst bst_free(bst b){
    if(b!=NULL){
        b->left = bst_free(b->left);
        b->right = bst_free(b->right);
        free(b->key);
        free(b);
    }
    return b;
}

void bst_inorder(bst b, void f(char *str)){
    if(b!= NULL){
        bst_inorder(b->left, f);
        f(b->key);
        bst_inorder(b->right, f);
    }
}

bst bst_insert(bst b, char *str){
    if(b==NULL){
        b = emalloc(sizeof *b);
        b->key = emalloc ( (strlen(str)+1) * sizeof str[0]);
        b->left = b->right = NULL;
        strcpy(b->key, str);
    }
    else if (strcmp(str, b->key) < 0){
        b->left = bst_insert(b->left, str);
    } else if(strcmp(str, b->key) > 0){
        b->right = bst_insert(b->right, str);
    }
    return b;
}

bst bst_new(){
    return NULL;
}

void bst_preorder(bst b, void f(char *str)){
    if(b!=NULL){
        f(b->key);
        bst_preorder(b->left, f);
        bst_preorder(b->right, f);
    }
}

int bst_search(bst b, char *str){
    if(b== NULL) { return 0; }

    if(strcmp(str, b->key) < 0){
        return bst_search(b->left, str);
    } else if(strcmp(str, b->key) > 0){
        return bst_search(b->right, str);
    }
    return 1;
}

