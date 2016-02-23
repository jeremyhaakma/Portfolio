#ifndef FLEXARRAY_H_
#define FLEXARRAY_H_
typedef struct flexarrayrec *flexarray;
extern void flexarray_append(flexarray f, char *word);
extern void flexarray_free(flexarray f);
extern flexarray flexarray_new();
extern int flexarray_is_present(flexarray f, char *word);
extern void flexarray_visit(flexarray f, void function(char *word));
extern int flexarray_is_empty(flexarray f);
#endif

