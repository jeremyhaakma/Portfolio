#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define ARRAY_MAX 30000
#define STRING_LEN 80

/* n is the size of the array */
void print_array(char *a[], int n) {
    if (n > 0) {
        printf("%s\n", a[0]);
        print_array(a + 1, n - 1);
    }
}

void *emalloc(size_t s) {
    void *result = malloc(s);
    if (NULL == result) {
        fprintf(stderr, "Memory allocation failed!\n");
        exit(EXIT_FAILURE);
    }
    return result;
}

void selection_sort(char *wordlist[], int n) {
    int p;
    int i;
    int smallest_index;
    char *temp;
    for (p = 0; p < n-1; p++){
        smallest_index = p;
        for(i=p; i < n; i++){
            if(strcmp( wordlist[i],  wordlist[smallest_index]) < 0){
                smallest_index = i;
            }
        }
        temp = wordlist[smallest_index];
        wordlist[smallest_index] = wordlist[p];
        wordlist[p] = temp; 
    }
}

int main(void) {
    char word[STRING_LEN];
    char *wordlist[ARRAY_MAX];
    int count = 0;
    while (count < ARRAY_MAX && 1 == scanf("%79s", word)) {
        wordlist[count] = emalloc((strlen(word) + 1) * sizeof wordlist[0][0]);
        strcpy(wordlist[count], word);
        count++;
    }
    selection_sort(wordlist, count);

    print_array(wordlist, count);

    return EXIT_SUCCESS;
}
