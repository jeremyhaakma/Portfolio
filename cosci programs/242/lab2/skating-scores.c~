#include <stdio.h>
#include <stdlib.h>

/* get_avg removes the lowest of three scores and returns
 the average of the remaining two*/
double get_avg(double a, double b, double c){
    double s1 = a;
    double s2 = b;
    double s3 = c;
    double result;
    /* If s1 is smallest */
    if (s1 < s2 && s1 < s3){
        result = (s2 + s3)/2;
    }
    /* If s2 is smallest */
    else if (s2 < s1 && s2 < s3){
        result = (s1 + s3)/2;
    }
    
    /* If s3 is smallest */
    else{
        result = (s1 + s2)/2;
    }
    return result;
}


int main(void){
    int return_code = 4;
    int name;
    double s1, s2, s3, avg;
    double win_avg = 0;
    int winner = 0;
    while(return_code == 4){
        return_code = scanf("%d%lg%lg%lg", &name, &s1, &s2, &s3);
        avg = get_avg(s1, s2, s3);
        if (avg > win_avg){
            win_avg = avg;
            winner = name;
        }
    }
    printf("The winner is competitor %d with a score of %.1f!\n"
           , winner, win_avg);
    return EXIT_SUCCESS;
}
