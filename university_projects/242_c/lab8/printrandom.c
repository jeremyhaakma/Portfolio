#include <stdlib.h>
#include <stdio.h>

int main(int argc, char **argv){
    int num; /*how many numbers to print out */
    int i;
    if (argc<2){
        for (i=0; i < 100; i++){
            printf("%d\n", rand()%100 );
        }   
    } else{
        num = atoi(argv[1]);
        for (i=0; i < num; i++){
            printf("%d\n", rand()%100 );
        } 
    }
    return EXIT_SUCCESS;
}
