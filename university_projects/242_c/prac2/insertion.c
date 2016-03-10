#include <stdio.h>
#include <stdlib.h>
#include "flexarray.h"

int main(void) {
    flexarray my_flex = flexarray_new();
    int i, num;
    while (1 == scanf("%d", &num)) {
        flexarray_append(my_flex, num);
    }
    flexarray_sort(my_flex);
    flexarray_print(my_flex);
    return EXIT_SUCCESS;
}
