#include "code.h"
#include "file.h"

#define START_OF_MACRO_WORD "macro"
#define END_OF_MACRO_WORD "endm"

boolean pre_assembler(char []);
void find_macros(char *);
void delete_macros(char **);
void add_macros(char **);