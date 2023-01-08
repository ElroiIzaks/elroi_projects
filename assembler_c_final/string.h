#include <stdarg.h>
#include "basic.h"
#include "import.h"

int make_int_from_str(char *);
boolean str_equals(char *a , char *b);
boolean str_not_equals(char *a , ...);
boolean string_length_is_ok (char *, int);
char *find_start_of_exactly(char *,char *);
char *move_to_not_white(char *);
char *move_to_white (char *);
char *get_first_word (char *);
int distance_between_chars(char *, char *);
boolean legal_int_from_str(char *);