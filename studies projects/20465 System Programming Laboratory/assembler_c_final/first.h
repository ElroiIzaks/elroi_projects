#include "code.h"
#include "import.h"
#include "file.h"

boolean first_pass (char *, machine_code *, symbols_table **);
boolean each_line_data_and_propriety (machine_code *, line, symbols_table **);
void change_to_new_dc(symbols_table **, int);
void get_all_symbols(char *, symbols_table **);
boolean line_is_too_long(int,char *, char *);
boolean memory_is_enough(machine_code *, char *);
boolean find_problems_and_insert_data(char *,machine_code *,symbols_table **);
boolean is_valid_label (machine_code *, line, symbols_table **);
boolean is_valid_guidance (machine_code *, line, symbols_table **);
boolean is_valid_instruction (machine_code *, line, symbols_table **);