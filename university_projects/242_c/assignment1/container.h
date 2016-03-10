#ifndef CONTAINER_H_
#define CONTAINER_H_

typedef struct containerrec *container;
typedef enum container_e{FLEX_ARRAY, RED_BLACK_TREE} container_t;
extern container container_new();
extern void container_add(container c, char *word);
extern void container_free(container c);
extern int container_search(container c, char *word);
extern void container_print(container c);

#endif
