#include <stdio.h>
#include <stdlib.h>
#include "graph.h"

int main(){
    int edgeX;
    int edgeY;
    int size;
    graph g;
    
    if (scanf("%d", &size) ==1){
        g = graph_new(size);        
    } else{
        fprintf(stderr, "Must enter a size\n");
        exit(EXIT_FAILURE);
    }

    while(scanf("%d%d", &edgeX, &edgeY) == 2){
        if (edgeX < size && edgeY < size){
            graph_add_edge(g, edgeX, edgeY, 1);
        }
        else {
            fprintf(stderr, "Edge values must be smaller than size!\n");
            exit(EXIT_FAILURE);
        }        
    }
    /*graph_bfs(g, 1);*/
    graph_dfs(g);
    graph_print(g);
    graph_free(g);
    
    return EXIT_SUCCESS;
}
