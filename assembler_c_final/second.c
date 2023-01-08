#include "second.h"

/************************************************************************
* Function Name: second_pass
* Input: char *file_name
         machine_code *machine_tbl
         symbols_table **symbol_tbl
* Function Operation: the function enter to machine_tbl all the left
                      data - the codes words (instructions).
                      no error (beside memory's problem of personal
                      computer) can be happend (else, the program will 
                      stop in the "assembler" function).
************************************************************************/
void second_pass (char *file_name, machine_code *machine_tbl, symbols_table **symbol_tbl)
{
    char str[81];
    int line_num = 0;
    
    line *corrent_line = new_line();
    FILE *file;

    file = open_file_for_reading(file_name,"am");

    machine_tbl->ic = 100;

    while (!feof(file))
    {
        fgets(str,81,file);
        line_num++;

        release_line(corrent_line);
        corrent_line = process_line (str ,file_name, line_num, false);
        
        if (str_not_equals(corrent_line->command ,"empty","comment",NULL))
        {
            enter_code(machine_tbl,*corrent_line,symbol_tbl);
        }
    }
    release_line(corrent_line);
    free(corrent_line);
}

/************************************************************************
* Function Name: enter_code
* Input: machine_code *machine_tbl
         line line
         symbols_table **symbol_tbl
* Output: boolean (for different exit points) 
* Function Operation: the function enter to "machine_tbl" a code 
                      (instructions) from line.
************************************************************************/
boolean enter_code(machine_code *machine_code, line line, symbols_table **symbols_table)
{
    int size = size_of_code_table(machine_code);
    instruction command = instruction_by_text(line.command);


    /*if guidance*/
    if (guidance_by_text(line.command) != no_guidance)
    {
        free(command.name);
        return true;
    }

    /*instruction*/
    expand_code_table(machine_code);

    /*first word*/
    machine_code->code[size].first_word.ic_num = machine_code->ic;
    machine_code->ic++;
    machine_code->code[size].first_word.opcode = command.opcode;
    
    if (!str_not_equals(line.command ,"rts","stop",NULL)) 
    {
        free(command.name);
        return true;
    }

    /*second*/
    machine_code->code[size].has_second = true;
    machine_code->code[size].second_word.ic_num = machine_code->ic;
    machine_code->ic++;
    machine_code->code[size].second_word.funct = command.funct;
    machine_code->code[size].second_word.source_reg = source_reg(line,symbols_table);
    machine_code->code[size].second_word.address_source = source_address(line,symbols_table);
    machine_code->code[size].second_word.target_reg = target_reg(line,symbols_table);
    machine_code->code[size].second_word.address_target = target_address(line,symbols_table);
    free(command.name);

    /*another words*/
    if(find_addressing_method(line.parameters[0],symbols_table) != register_direct ||
       (find_addressing_method(line.parameters[1],symbols_table) != register_direct &&
       find_addressing_method(line.parameters[1],symbols_table) != no_method))
    {
       machine_code->code[size].has_another = true;
    }
    
    machine_code->ic += add_another_word(&(machine_code->code[size]) , line.parameters[0],symbols_table ,0, machine_code->ic);
    
    machine_code->ic += add_another_word(&(machine_code->code[size]) , line.parameters[1],symbols_table ,another_words_length(machine_code->code[size]), machine_code->ic);
    
    return true;
}