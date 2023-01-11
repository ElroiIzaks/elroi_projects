#include "assembler.h"

void assembler (char *file_name)
{ 
    symbols_table **symbol_tbl = new_symbols_table();
    machine_code *machine_tbl = new_machine_code_table();
    boolean first_successed;

    first_successed = first_pass(file_name, machine_tbl, symbol_tbl);
    
    if (first_successed == true)
    {
        second_pass(file_name, machine_tbl, symbol_tbl);
        export_files(machine_tbl,*symbol_tbl,file_name);
    }

    release_symbol_table(symbol_tbl);
    release_machine_code_table(machine_tbl);
}