#include "symbols.h"


#ifndef VALIDS_VARS
#define VALIDS_VARS

typedef enum {no_error              ,
              no_alphabet_start     ,
              too_long              ,
              include_unvalid_symbol,
              register_name         ,
              instruct_name         ,
              guidance_name
} label_error;

#endif

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

#ifndef SYMMB_STRUCTS
#define SYMMB_STRUCTS

typedef struct 
{
    boolean code;
    boolean data;
    boolean entry;
    boolean external;
}attributes_table;

typedef struct symbol
{
    char *name;
    int value;
    attributes_table attributes_table;
} symbols_table;

#endif

#ifndef LINE
#define LINE
typedef struct
{
    int line_num;
    char *file_name;

    boolean has_label;
    char * label;

    char *command;
    char **parameters;
}line;

#endif

int register_by_text(char *);
boolean legal_immediate(char *);
boolean legal_direct(char *, symbols_table **);
int legal_index(char *, symbols_table **);
addressing_method find_addressing_method (char *, symbols_table **);
instruction instruction_by_text (char *);
label_error is_legal_label_name(char * str);
guidance guidance_by_text (char *txt);
int source_reg(line, symbols_table **);
addressing_method source_address (line, symbols_table **);
int target_reg(line, symbols_table **);
addressing_method target_address (line, symbols_table **);