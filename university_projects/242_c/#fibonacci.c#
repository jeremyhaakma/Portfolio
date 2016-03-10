#include <stdio.h>
#include <stdlib.h>
#include <math.h>
int main(void) {
    /* variable declarations */
    int f = 0;
    int g = 1;
    int old_g = 0;
    int i = 0;
    for (i = 0; i < 40; i++){
        printf("%13d", g);
        old_g = g;
        g += f;
        f = old_g;
        if( (i+1)%5 == 0){
            printf("\n");
        }
    }

    return EXIT_SUCCESS; /* defined in stdlib.h */
}
