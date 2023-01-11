#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <string.h>
#include "string.h"
#include "import.h"

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


line *new_line ();
line *process_line (char *,char *, int, boolean);
void release_line(line *);
void print_line(line *);/*delete!!*/
char *get_label(char *,line *);
char *get_command(char *,line *,boolean *,boolean);
void get_parameters(char *,line *,boolean *,boolean);
boolean exactly_parammeters(line, int);