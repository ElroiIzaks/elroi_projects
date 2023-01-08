#include "symbols.h"
#include "line.h"
#include "import.h"
#include "valid.h"


#ifndef CODE_STRUCTS
#define CODE_STRUCTS

typedef enum {a,r,e} are;

typedef enum {immediate , direct , index , register_direct , no_method} addressing_method;

typedef enum {data , string , entry , externy , no_guidance} guidance;

typedef struct
{
    char *name;
    int opcode;
    int funct;
}instruction;

typedef struct 
{
    int ic_num;
    int opcode;
}first_word;

typedef struct 
{
    int ic_num;

    int funct;
    int source_reg;
    addressing_method address_source;
    int target_reg;
    addressing_method address_target;
}second_word;

typedef struct 
{
    int ic_num;

    are are;
    int value;

    boolean is_external;
    char *external;
}another_word;

typedef struct
{
    int dc_num;

    boolean is_num;
        int num;
    
    boolean is_char;
        char character;
}data_word;


typedef struct 
{
    first_word first_word;

    boolean has_second;
    second_word second_word;
    
    boolean has_another;
    another_word *another_words;
}code;

typedef struct 
{
    int ic;
        code *code;
    
    int dc;
        data_word *data;
}machine_code;

#endif

/*machine code*/
machine_code *new_machine_code_table();
void release_machine_code_table(machine_code *);

/*code*/
code new_code_word ();
int size_of_code_table (machine_code *);/**/
int add_another_word(code *code, char *, symbols_table **, int, int);
void expand_code_table(machine_code *);
int another_words_length(code);
void set_another_word_values(another_word *, int, are, int, boolean, char *);
void release_another_word_table(code);
void release_code_table(machine_code);

/*data*/
data_word new_data_word ();
boolean enter_data(line line , machine_code *machine_code , symbols_table **symbols_table);
void expand_data_table(machine_code *);

void print_machine_code(machine_code *);/*dalete!!*/