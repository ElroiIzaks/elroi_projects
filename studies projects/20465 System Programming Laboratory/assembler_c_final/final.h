#include "code.h"
#include "file.h"

void export_files(machine_code *,symbols_table *, char *);
void wrote_ob_file(machine_code,symbols_table *,char *);
void wrote_binary_code_line(code,FILE *);
void wrote_binary_data_line(data_word,FILE *,int);
void wrote_ext_file(code *,symbols_table *,char *);
void wrote_ent_file(symbols_table *,char *);