#include <stdio.h>
#include <stdlib.h>

int main(){
    double a, b, c, avg;
    double winavg = 0;
    int name;
    int winner = 0;

    while(4 == scanf("%d%lg%lg%lg", &name, &a, &b, &c)){
        if(a<b && a<c){
            avg = (b+c)/2;
        } else if(b<a && b<c){
            avg = (a+c)/2;
        } else{
            avg = (a+b)/2;
        }
        if(avg > winavg){
            winavg = avg;
            winner = name;
        }
    }
    printf("%d\n", winner);
    return EXIT_SUCCESS;
       
}
