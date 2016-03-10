#include <stdio.h>
#include <stdlib.h>

int is_prime(int cand){
    int i;
    for(i=2; i < cand; i++){
        if(cand%i == 0){
            return 0;
        }
    }
    return 1;
}

int main(void){
    int cand = 2;
    int num_printed = 0;
    while(num_printed < 200){
        if(is_prime(cand) == 1){
            printf("%5d", cand);
            num_printed++;
            if(num_printed%10 == 0){
                printf("\n");
            }
        }
        cand++;
    }
    
    return EXIT_SUCCESS; 
}
