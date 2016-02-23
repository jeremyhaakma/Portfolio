#include <stdio.h>
#include <stdlib.h>
#include "container.h"
#include "mylib.h"
#include "flexarray.h"
#include "rbt.h"

struct containerrec {
    container_t type;
    void *contents;
};
void print_func(char *str){
    fprintf(stderr, "%s ", str);
}
container container_new(container_t type){
    container result = emalloc(sizeof *result);
    result->type = type;
    if(type == RED_BLACK_TREE){
        result->contents = rbt_new();
    }else{
        result->contents = flexarray_new();
    }
    return result;
}
void container_add(container c, char *word){
    if(c->type == RED_BLACK_TREE){
        c->contents = rbt_insert(c->contents, word);
    }else{
        flexarray_append(c->contents, word);
    }
}
void container_free(container c){
    if(c->type == RED_BLACK_TREE){
        c->contents = rbt_free(c->contents);
    }else{
        flexarray_free(c->contents);
    }
    free(c);
}
int container_search(container c, char *word){
    if(c->type == RED_BLACK_TREE){
        return rbt_search(c->contents, word);
    }
    return flexarray_word_present(c->contents, word);
}
void container_print(container c){
    if(c->type == RED_BLACK_TREE){
        if(rbt_is_null(c->contents) == 0){
            /*may have to change the traversal order*/
            rbt_inorder(c->contents, print_func);
            fprintf(stderr, "\n");
        }
    }else{
        if(flexarray_is_empty(c->contents) == 0){
            flexarray_visit(c->contents, print_func);
            fprintf(stderr, "\n");
        }
    }
}




