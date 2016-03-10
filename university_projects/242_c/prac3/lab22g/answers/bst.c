#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "mylib.h"
#include "bst.h"

struct bst_node { 
    char *key;
    bst left;
    bst right;
};

bst bst_new(){
    return NULL;
}

bst bst_insert(bst b, char *str) {
    /* If we’re trying to insert into an empty tree:
       - allocate memory
       - copy in the key 
       - return the result. 
    */
    if (b == NULL) {
        /* Create new bst_node (????) */
        b = emalloc(sizeof *b);

        /* Allocate memory for the string and copy it across */
        b->key = emalloc((strlen(str)+1) * sizeof b->key[0]);
        strcpy(b->key, str);

        /* Set both left and right leave to NULL */
        b->left = b->right = NULL;
    }

    /* If the key to be inserted is smaller then 
       we should insert into the left subtree */
    else if (strcmp(str, b->key) < 0) {
        b->left = bst_insert(b->left, str);
    }

    /* If the key to be inserted is greater we 
       should insert into the right subtree */
    else if (strcmp(str, b->key) > 0) {
        b->right = bst_insert(b->right, str);
    }

/* Else if it’s the same, do nothing */

    /* Then return b */
    return b;
}

void bst_inorder(bst b, void f(char *str)) {
    /* Stopping case is when B is NULL */
    if (b == NULL) {
        return;
    }

    /* Inorder traversal of the left subtree */
    bst_inorder(b->left, f);

    /* Apply function F to b->key */
    f(b->key);

    /* Inorder traversal of the right subtree */
    bst_inorder(b->right, f);
}

void bst_preorder(bst b, void f(char *str)) {
    /* Stopping case is when B is NULL */
    if (b == NULL) {
        return;
    }

    /* Apply function F to b->key */
    f(b->key);

    /* Preorder traversal of the left subtree */
    bst_preorder(b->left, f);

    /* Preorder traversal of the right subtree */
    bst_preorder(b->right, f);
}


int bst_search(bst b, char *str) {
    /* Stopping case is when B is NULL */
    if (b == NULL) {
        return 0;
    }


    /* Search left tree if string is less-than b->key */
    else if (strcmp(str, b->key) < 0) {
        return bst_search(b->left, str);
    }

    /* Search right tree if string is greater-than b->key */
    else if (strcmp(str, b->key) > 0) {
        return bst_search(b->right, str);
    }   
    /* If B contains the string we are searching for: */
    else{
        return 1;
    }
}



bst bst_free(bst b) {
    
    /* Postorder traversal of the left subtree */
    if (b->left != NULL) {
        bst_free(b->left);
    }
	
    /* Postorder traversal of the right subtree */
    if (b->right != NULL) {
        bst_free(b->right);
    }

    free(b->key);
    free(b);
    b = bst_new();
        
    return b;
}
