#include <stdio.h>
#include <stdlib.h>

double get_avg(double a, double b, double c){
    /* a smallest. return avg of b and c */
    if (a < b && a < c){
        return (b+c)/2;
    }
    /* b smallest */
    else if (b < a && b < c){
        return (a+c)/2;
    }
    /* c smallest */
    else return (a+b)/2;
}

int main(void){
    int contestant;
    double a;
    double b;
    double c;
    double avg;
    int winner;
    double win_avg = 0;
    while (scanf("%d%lg%lg%lg", &contestant, &a, &b, &c) == 4){
        avg = get_avg(a,b,c);
        if (avg > win_avg){
            win_avg = avg;
            winner = contestant;
        }
    }
    printf("%d\n", winner);
    return EXIT_SUCCESS;
}
