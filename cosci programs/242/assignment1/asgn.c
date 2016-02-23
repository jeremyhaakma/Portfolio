#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#include "mylib.h"
#include "htable.h"
#include "getopt.h"
#include "container.h"

#define DEFAULT_CAPACITY 3877

int main(int argc, char **argv) {

    /*htable variables*/
    htable h;
    container_t htable_type = FLEX_ARRAY;
    int htable_size = DEFAULT_CAPACITY;
    /*input reading variables*/
    char *filename;
    FILE *file;
    char word[256];
    /*getopt variables*/
    const char *optstring = "rs:pih";
    char option;
    /*additional printing option variables*/
    int to_print_htable = 0;
    int to_print_info = 0;
    /*info variables*/
    int num_unknown = 0;
    clock_t f_time_start, f_time_end, s_time_start, s_time_end;
    /*so dirty*/
    f_time_start = f_time_end = s_time_start = s_time_end = clock();

    /*Check to see if filename given, i.e. correct commandline input*/
    if(argc < 2){
        printf("usage: %s <filename>", argv[0]);
        exit(EXIT_FAILURE);
    }else{
        filename = argv[1];
    }

    /*check for switch options*/
    while ((option = getopt(argc, argv, optstring)) != EOF){
        switch (option) {
            case 'r':
                /*Option -r*/
                htable_type = RED_BLACK_TREE;
                break;
            case 's':
                /*Option -s*/
                /*'optarg' is the variable associated with '-s'*/
                htable_size = atoi(optarg);
                break;
            case 'p':
                /*Option -p*/
                to_print_htable = 1;
                break;
            case 'i':
                /*Option -i*/
                to_print_info = 1;
                break;
            case 'h':
                /*Option -h*/
                fprintf(stderr, "*insert helpful message here*");
                exit(EXIT_SUCCESS);
                break;
            default:
                fprintf(stderr, "Option unknown");
                break;
        }
    }

    /*initialise the htable based off the arguments given*/
    h = htable_new(htable_size, htable_type); 
 
    /*try to open the file given by the user*/
    file = fopen(filename, "r");
    if(file == 0){
        printf("Could not open file: %s\n", argv[1]);
        exit(EXIT_FAILURE);
    }else{
        /*read words from file into our containers. Time this.*/
        f_time_start = clock();
        while(getword(word, sizeof word, file) != EOF){    
            htable_insert(h, word);                
        }
        f_time_end = clock();
    }
    fclose(file);
    
    /*if htable print option given on commandline*/
    if(to_print_htable == 1){
        htable_print(h);   
    }else{
        s_time_start = clock();
        while(getword(word, sizeof word, stdin) != EOF){
            if(htable_search(h, word) == 0){
                fprintf(stderr, "%s\n", word);
                num_unknown++;
            }
        }
        s_time_end = clock();

        /*if info print option given on the commandline*/
        if(to_print_info == 1){
            fprintf(stderr, "Fill time :  %f\n",
                    (f_time_end - f_time_start)/(double)CLOCKS_PER_SEC);
            fprintf(stderr, "Search time :  %f\n",
                    (s_time_end - s_time_start)/(double)CLOCKS_PER_SEC);
            fprintf(stderr, "Unknown words :  %d\n", num_unknown);
        }
    }

    htable_free(h);        
    free(h);

    return EXIT_SUCCESS;
}
