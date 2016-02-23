#include <stdio.h>
#include <stdlib.h>
#include <math.h>

int main(void){
    double s1, s2, s3, result;
    int return_code;
    printf("please enter three doubles\n");
    fflush(stdout);
    return_code = scanf("%lg%lg%lg", &s1, &s2, &s3);
    if (return_code != 3){
        printf("scanf returned code %d\n", return_code);
        return EXIT_FAILURE;
    }
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
    printf("%.1f\n", result);
    
    return EXIT_SUCCESS;
}
