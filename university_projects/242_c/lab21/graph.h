#ifndef GRAPH_H_
#define GRAPH_H_

typedef struct vertex vertex;
typedef struct graphrec *graph;

extern graph graph_new(int num_vertices);
extern void graph_add_edge(graph g, int a, int b, int directed);
extern void graph_print(graph g);
extern void graph_bfs(graph g, int source);
extern void graph_dfs(graph g);
extern graph graph_free(graph g);

#endif
