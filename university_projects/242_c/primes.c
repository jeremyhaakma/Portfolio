#include <stdio.h>
#include <stdlib.h>
#include <math.h>
int is_prime(int x) {
    int i = 2;
        for (i = 2; i < x; i++){
        if(x%i==0){
            return 0;
        }
    }
    return 1;
}

int main(void){
    int num = 2;
    int num_printed = 0;;
    while(num_printed < 100){
        if(is_prime(num) ){
            printf("%d ", num);
            num_printed++;
        }
        num++;
    }
    return EXIT_SUCCESS;
}