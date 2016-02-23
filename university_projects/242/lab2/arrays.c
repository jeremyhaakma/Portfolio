#include <stdio.h>
#include <stdlib.h>
#include <math.h>
int main(void){
    int code;
    int i = 0;
    double judge1[10], judge2[10], judge3[10];
    int line = 0; /*based on no. lines read from file */
    int return_code = 4; /*used to find end of file. Each line has 4 tokens*/
    double mean1 = 0;
    double mean2 = 0;
    double mean3 = 0;
    double stdev1= 0;
    double stdev2= 0;
    double stdev3= 0;
    while(return_code == 4){
        return_code = scanf("%d%lg%lg%lg", &code,
                            &judge1[line], &judge2[line], &judge3[line]);
        line++;
    }
    line--;
    /*calculate means*/
    for (i = 0; i < line; i++){
        mean1 += judge1[i];
        mean2 += judge2[i];
        mean3 += judge3[i];
    }
    mean1 = mean1/line;
    mean2 = mean2/line;
    mean3 = mean3/line;
    /*calculate stdevs*/
    for (i = 0; i < line; i++){
        stdev1 += pow((judge1[i] - mean1), 2);
        stdev2 += pow((judge2[i] - mean2), 2);
        stdev3 += pow((judge3[i] - mean3), 2);
    }
    stdev1 = sqrt( stdev1 / (line-1) );
    stdev2 = sqrt( stdev2 / (line-1) );
    stdev3 = sqrt( stdev3 / (line-1) );
    /* Print results */
    printf("\t Average \t Stdev\n");
    printf("Judge 1: %1.2f %15.2f\n", mean1, stdev1);
    printf("Judge 2: %1.2f %15.2f\n", mean2, stdev2);
    printf("Judge 3: %1.2f %15.2f\n", mean3, stdev3);

    
    return EXIT_SUCCESS;
}
