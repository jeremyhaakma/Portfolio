#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "mylib.h"
#include "rbt.h"

#define WORD_LEN 80
#define IS_BLACK(x) ((NULL == (x)) || (BLACK == (x)->color))
#define IS_RED(x) ((NULL != (x)) && (RED == (x)->color))

/**
 * Defines possible types for the rbt color.
 *
 */
typedef enum { RED, BLACK } rbt_color;

/**
 * Struct for a node that has rbt restrictions.
 */
struct rbt_node {
    char *key; 
    rbt_color color;
    rbt left;
    rbt right;
};

/**
 * Creates a new RBT node.
 *
 * All new RBT nodes are NULL
 * @return rbt Empty RBT with value NULL.
 */
rbt rbt_new() {
    return NULL;
}

/**
 * Rotate an RBT to the right.
 *
 * @param r RBT to be rotated to the right.
 * @return rbt RBT that has been rotated right.
 */
static rbt right_rotate(rbt r) {
    rbt temp = r;
    r = temp->left;
    temp->left = r->right;
    r->right = temp;
    return r;
}

/**
 * Rotate an RBT to the left.
 *
 * @param r RBT to be rotated to the left.
 * @return rbt RBT that has been rotated left.
 */
static rbt left_rotate(rbt r) {
    rbt temp = r;
    r = temp->right;
    temp->right = r->left;
    r->left = temp;
    return r;
}

/**
 * Corrects rbt to ensure the RBT adheres to RBT structure rules.
 *
 * @param r RBT to be fixed.
 * @return rbt RBT that has been fixed.
 */
static rbt rbt_fix(rbt r) {
    if (IS_RED(r->left) && IS_RED(r->left->left)) {
        if (IS_RED(r->right)) {
            r->color = RED;
            r->left->color = BLACK;
            r->right->color = BLACK;
        } else{
            r = right_rotate(r);
            r->color = BLACK;
            r->right->color = RED;
        }
    }
    if (IS_RED(r->left) && IS_RED(r->left->right)) {
        if (IS_RED(r->right)) {
            r->color = RED;
            r->left->color = BLACK;
            r->right->color = BLACK;
        } else{
            r->left = left_rotate(r->left);
            r = right_rotate(r);
            r->color = BLACK;
            r->right->color = RED;
        }
    }
    if (IS_RED(r->right) && IS_RED(r->right->left)) {
        if (IS_RED(r->left)) {
            r->color = RED;
            r->left->color = BLACK;
            r->right->color = BLACK;
        } else{
            r->right = right_rotate(r->right);
            r = left_rotate(r);
            r->color = BLACK;
            r->left->color = RED;
        }
    }
    if (IS_RED(r->right) && IS_RED(r->right->right)) {
        if (IS_RED(r->left)) {
            r->color = RED;
            r->left->color = BLACK;
            r->right->color = BLACK;
        } else{
            r = left_rotate(r);
            r->color = BLACK;
            r->left->color = RED;
        }
    }
    return r;
}

/**
 * Sets the root to BLACK.
 * Should always and only be called on the root node of an rbt after it
 * has been created or changed.
 *
 * @param r RBT to blacken.
 * @return an RBT with a black root.
 */
rbt rbt_blacken_root(rbt r){
    r->color = BLACK;
    return r;
}

/**
 * Insert string into RBT.
 *
 * @param r RBT to insert the string into.
 * @param str String to insert.
 * @return rbt RBT with new string inserted.
 */
rbt rbt_insert(rbt r, char *str) {

    /* Inserting into an empty RBT */
    if (r == NULL) {
        r = emalloc(sizeof *r);
        r->color = RED;
        r->key = emalloc((strlen(str)+1) * sizeof r->key[0]);
        strcpy(r->key, str);
        r->left = r->right = NULL;
    }
    /* If str is less-than or identical to the key: insert to the left child */
    else if (strcmp(str, r->key) < 0){ 
        r->left = rbt_insert(r->left, str);
    }
    else if(strcmp(str, r->key) == 0){
        r->left = rbt_insert(r->left, str);
    }
    /* If str is greater-than the key: insert to the right child */
    else if (strcmp(str, r->key) > 0) {
        r->right = rbt_insert(r->right, str);
    }
    r = rbt_fix(r);
    return r;
}

/**
 * Pre-Order traversal of RBT with input function applied.
 * The function passed as a parameter must take a single
 * string value as a parameter.
 * 
 * @param r RBT to traverse.
 * @param f Function to be applied to each node.
 */
void rbt_preorder(rbt r, void f(char *str)) {
    if (r == NULL) {
        return;
    }
    f(r->key);
    rbt_preorder(r->left, f);
    rbt_preorder(r->right, f);
}

/**
 * Search an RBT for a string
 *
 * @param r RBT to search for the string.
 * @param str String to be searched for.
 * @return 1 if string is found, 0 if otherwise.
 */
int rbt_search(rbt r, char *str) {
    if (r == NULL) {
        return 0;
    }
    else if (strcmp(str, r->key) == 0) {
        return 1;
    }
    else if (strcmp(str, r->key) < 0) {
        return rbt_search(r->left, str);
    }
    else if (strcmp(str, r->key) > 0) {
        return rbt_search(r->right, str);
    }

    return 0;
}

/**
 * Delete a string from RBT if present.
 *
 * @param r RBT to delete string from.
 * @param str String to be deleted.
 * @return rbt RBT with string deleted.
 */
rbt rbt_delete(rbt r, char *str) {
    if (r == NULL) {
        return r;
    }
    /* If we find the key in r, Splice out the node */
    else if (strcmp(str, r->key) == 0) {
        /* If r is a leaf free the key and node, set the nodes pointer to NULL */
        if ((r->left == NULL) && (r->right == NULL)) {
            free(r->key);
            free(r);
            r = rbt_new();
        }
        /* If r has only one child make the pointer point to the child instead */
        else if (r->left == NULL) {
            strcpy(r->key, r->right->key);
            r->right = rbt_delete(r->right, r->key);
        } else if (r->right == NULL) {
            strcpy(r->key, r->left->key);
            r->left = rbt_delete(r->left, r->key);
        }
        /* If r has two children: */
        else {
            rbt successor = r->right;
            while (successor->left != NULL) {
                successor = successor->left;
            }	
            strcpy(r->key, successor->key);
            r->right = rbt_delete(r->right, r->key);
        }
    }
    /* If the key is not found search the left or right sub-tree */
    else if (strcmp(str, r->key) < 0) {
        r->left = rbt_delete(r->left, str);
    }
    else if (strcmp(str, r->key) > 0) {
        r->right = rbt_delete(r->right, str);
    }

    return r;
}

/**
 * Free all memory associated with an RBT.
 *
 * @param r RBT to be freed.
 * @return rbt Empty RBT with NULL value.
 */
rbt rbt_free(rbt r) {
    if(r == NULL){
        return NULL;
    }
    rbt_free(r->left);
    rbt_free(r->right);

    free(r->key);
    free(r);

    return NULL;
}

/**
 * Checks if the rbt is null.  
 * 
 * @param r RBT to check.
 * @return an int 1 for true, 0 for false.
 */
int rbt_is_null(rbt r){
    if(r == NULL){
        return 1;
    }
    return 0;
}
