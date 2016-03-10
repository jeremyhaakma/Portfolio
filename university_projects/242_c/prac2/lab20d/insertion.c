#include <stdio.h>
#include <stdlib.h>
#include "flexarray.h"

int main(){
    flexarray a = flexarray_new();
    int item;
    while(1 == scanf("%d", &item)){
        flexarray_append(a, item);
    }
    flexarray_sort(a);
    flexarray_print(a);
    flexarray_free(a);
    return EXIT_SUCCESS;
}
