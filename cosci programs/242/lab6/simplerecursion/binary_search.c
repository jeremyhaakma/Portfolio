#include <stdlib.h>
#include <stdio.h>


int binary_search(int target, int *i, int size){
    int mid = size/2;
    if ( i[mid] == target ){   /* found target: return true */
        return 1;          
    } else if (size <1 ){ /* end of array, target not found: return false */
        return 0; 
    } else if ( i[mid] > target ){
        return binary_search(target, i, mid-1); /* call on lower half */
    } else if ( mid < target ){
        return binary_search(target, &i[mid+1], mid-1);
    }
    return 0;
}

int main() {
    int int_array[5] = {1, 3, 5, 7, 8};
    printf("%d\n", binary_search(5, int_array, sizeof int_array / sizeof *int_array) );

    return EXIT_SUCCESS;
}
