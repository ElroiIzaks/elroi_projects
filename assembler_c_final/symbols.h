#include "string.h"
#include "import.h"

#ifndef SYMMB_STRUCTS
#define SYMMB_STRUCTS

typedef struct
{
    boolean code;
    boolean data;
    boolean entry;
    boolean external;
} attributes_table;

typedef struct symbol
{
    char *name;
    int value;
    attributes_table attributes_table;
} symbols_table;

#endif

symbols_table **new_symbols_table();
void add_symbol(symbols_table **, char *, int, char *);
int find_symbol(symbols_table **, char *);
int find_symbol_by_index(symbols_table **, char *);
void add_attribute(attributes_table *, char *);
void new_attributes_table(attributes_table *);
void print_symbol_table(symbols_table **);
int size_of_symbols_table(symbols_table **);
void release_symbol_table(symbols_table **);