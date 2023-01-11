#include "first.h"

/************************************************************************
* Function Name: first_pass
* Input: char *file_name
         machine_code *machine_tbl
         symbols_table **symbol_tbl
* Output: boolean (true if no error occurred)
* Function Operation: the function make a table of all the labels in the 
                      above file (and put it in "symbol_tbl)", enter all
                      the data words (from guidances) to the
                      "machine_tbl", and count how many memory the 
                      program require (as well as counting and calculating  
                      the ic and dc for second pass).
                      any error (beside memory's problem of personal
                      computer) will be detected in this pass.
                      if any error occurred, the function return false
                      (and give suitable message), else- true.
************************************************************************/
boolean first_pass (char *file_name, machine_code *machine_tbl, symbols_table **symbol_tbl)
{
    /*file's variables*/
    boolean error_occurred = false;

    get_all_symbols(file_name,symbol_tbl);
    
    error_occurred = find_problems_and_insert_data(file_name,machine_tbl,symbol_tbl);
    
    if (error_occurred == true) return false;
    change_to_new_dc(symbol_tbl, machine_tbl->ic);
    return memory_is_enough(machine_tbl,file_name);
}

/************************************************************************
* Function Name: find_problems_and_insert_data
* Input: FILE *file
         machine_code *machine_tbl
         symbols_table **symbol_tbl
* Output: boolean (true if any error occurred)
* Function Operation: the function enter all the data (from guidances) 
                      to the machine_tbl, and search for errors.
                      if any error occurred, the function return true
                      (and give suitable message), else- false.
************************************************************************/
boolean find_problems_and_insert_data(char *file_name,machine_code *machine_tbl,symbols_table **symbol_tbl)
{
    int line_num = 0;
    char str[MAX_LINE_SIZE+1];
    boolean error_occurred = false;
    FILE *file = open_file_for_reading(file_name,"am");
    line *corrent_line = new_line();

    while (!feof(file)) /*while file not ended*/
    {
        line_num++;

        fgets(str,81,file);
        if (line_is_too_long(line_num,file_name,str) == true)
            error_occurred = true;

        release_line(corrent_line);
        corrent_line = process_line (str ,file_name, line_num , true);
        
        if (str_equals(corrent_line->command,"error")) /*syntax error*/
        {
            error_occurred = true;       
        }
        else if (str_not_equals(corrent_line->command ,"empty","comment",NULL))
        {
            if (each_line_data_and_propriety(machine_tbl,*corrent_line,symbol_tbl) == false)
                error_occurred = true;
        }
    }
    fclose(file);
    release_line(corrent_line);
    free(corrent_line);
    return error_occurred;
}

/************************************************************************
* Function Name: each_line_data_and_propriety
* Input: machine_code *machine_code
         line line
         symbols_table **symbol_tbl
* Output: boolean (true if no error occurred)
* Function Operation: the function check the line's propriety. if the 
                      line has label, the function enter the label to the
                      symbols_table. if the line is guidance, the 
                      function will enter the data to the machine_code.
                      if no error occurred, the function return true.
                      else, proper message will be send, and the function
                      return false.
************************************************************************/
boolean each_line_data_and_propriety (machine_code *machine_code, line line, symbols_table **symbols_table)
{
    boolean line_is_valid = true;

    /*label*/
    line_is_valid = is_valid_label(machine_code, line, symbols_table);
    
    /*command*/
    if (line_is_valid)
        line_is_valid = is_valid_guidance (machine_code, line, symbols_table);
    
    if (line_is_valid)
        line_is_valid = is_valid_instruction (machine_code, line, symbols_table);
    
    return line_is_valid;
}

/************************************************************************
* Function Name: is_valid_label
* Input: machine_code *machine_code
         line line
         symbols_table **symbol_table
* Output: boolean (true if no error occurred)
* Function Operation: the function check the label's propriety. if the 
                      line has label, the function run and enter the
                      label to the symbols_table.
                      if no error occurred, the function return true.
                      else, proper message will be send, and the function
                      return false.
************************************************************************/
boolean is_valid_label(machine_code *machine_code, line line, symbols_table **symbols_table)
{
    char *command = (instruction_by_text(line.command)).name;

    /*if label existe*/
    if (line.has_label == true)
    {
        /*if illegal label*/
        if (is_legal_label_name(line.label) != no_error) /*some error found*/
        {
            printf("Error in %s:%d: label \"%s\" has illegal name - ",line.file_name,line.line_num,line.label);
            switch(is_legal_label_name(line.label))
            {
                case no_alphabet_start:
                    printf("label must start with alphabet character.\n");
                    break;
                case too_long:
                    printf("label can not be longer than 31 characters.\n");
                    break;
                case include_unvalid_symbol:
                    printf("label can contain only alphabetic letters or numbers.\n");
                    break;
                case register_name:
                    printf("label name can not be equal to register name.\n");
                    break;
                case instruct_name:
                    printf("label name can not be equal to instruction name.\n");
                    break;
                case guidance_name:
                    printf("label name can not be equal to guidance name.\n");
                    break;
                case no_error:;
            } 
            return false;
        }


        if (guidance_by_text(line.command) != no_guidance || /*command is guidance*/
            !str_equals(command , "no_instruction")) /*command is instruction*/
        {
            /*label appears twice*/
            if ((*symbols_table)[find_symbol(symbols_table , line.label)].attributes_table.code == true ||
                (*symbols_table)[find_symbol(symbols_table , line.label)].attributes_table.data == true)
            {
                printf("Error in %s:%d: label \"%s\" as allready defined.\n",line.file_name,line.line_num,line.label);
                return false;
            }

            /*enter code's label*/
            if (!str_equals(command , "no_instruction"))
            {
                add_symbol(symbols_table , line.label , machine_code->ic , "code");
            }
        }
    }
    free(command);
    return true;
}

/************************************************************************
* Function Name: is_valid_guidance
* Input: machine_code *machine_code
         line line
         symbols_table **symbol_table
* Output: boolean (true if no error occurred)
* Function Operation: the function check the command, if the 
                      command is guidance.
                      if no error occurred, the function enter the data
                      into the machine_tbl and return true.
                      else, proper message will be send, and the function
                      return false.
************************************************************************/
boolean is_valid_guidance (machine_code *machine_code, line line, symbols_table **symbols_table)
{
    char *command = (instruction_by_text(line.command)).name;

    /*if illegal command*/
    if (guidance_by_text(line.command) == no_guidance && 
        str_equals(command , "no_instruction"))
    {
        printf("Error in %s:%d: command \"%s\" is unidentified.\n",line.file_name,line.line_num,line.command);
        free(command);
        return false; 
    }

    free(command);
    
    /*if guidance*/
    if (guidance_by_text(line.command) != no_guidance)
    {
        if (enter_data(line , machine_code , symbols_table) == false)
            return false;
    }
    return true;
}

/************************************************************************
* Function Name: is_valid_instruction
* Input: machine_code *machine_code
         line line
         symbols_table **symbol_table
* Output: boolean (true if no error occurred)
* Function Operation: the function check the command, if the 
                      command is guidance.
                      if no error occurred, the function enter the data
                      into the machine_tbl and return true.
                      else, proper message will be send, and the function
                      return false.
************************************************************************/
boolean is_valid_instruction (machine_code *machine_code, line line, symbols_table **symbols_table)
{
    char *command = (instruction_by_text(line.command)).name;

    if (str_equals(command , "no_instruction")) 
    {
        free(command);
        return true;
    }
    free(command);

    /*instruction*/
    
    machine_code->ic++;

    if (!str_not_equals(line.command ,"rts","stop",NULL)) /*no parameter's commands*/
    {
        return exactly_parammeters(line, 0);
    }

    machine_code->ic++;

    /*at list one parameter's commands*/
        /*first parameter*/
    if (line.parameters[0] == NULL)
    {
        printf("Error in %s:%d: command %s expecting parameters.\n",line.file_name,line.line_num,line.command);
        return false;
    }

    switch(find_addressing_method(line.parameters[0] , symbols_table))
    {
        case immediate:
            if (str_not_equals(line.command , "prn","cmp","mov","add","sub",NULL))
            {
                printf("Error in %s:%d: command %s can't use immediate addressing.\n",line.file_name,line.line_num,line.command);
                return false;
            }
            machine_code->ic++;
            break;
        
        case direct:
        case index:
            machine_code->ic += 2;
            break;

        case register_direct:
            if (!str_not_equals(line.command ,"lea","jmp","bne","jsr",NULL))
            {
                printf("Error in %s:%d: command %s can't use register direct addressing.\n",line.file_name,line.line_num,line.command);
                return false;
            }
            break;

        case no_method:
            printf("Error in %s:%d: parameter %s is illegal.\n",line.file_name,line.line_num,line.parameters[0]);
            return false;
    }

    if (str_not_equals(line.command, "mov","cmp","add","sub","lea",NULL)) /*one parameter's commands*/
    {
        return exactly_parammeters(line, 1); 
    }

    /*two parameters' commands*/
        /*second parameter*/
    if (line.parameters[1] == NULL)
    {
        printf("Error in %s:%d: command %s expecting two parameters.\n",line.file_name,line.line_num,line.command);
        return false;
    }

    switch (find_addressing_method(line.parameters[1] , symbols_table))
    {
        case immediate:
            if (!str_equals(line.command ,"cmp"))
            {
                printf("Error in %s:%d: command %s can't use immediate addressing.\n",line.file_name,line.line_num,line.command);
                return false;
            }
            machine_code->ic++;
            break;

        case direct:
        case index:
            machine_code->ic += 2;
            break;

        case no_method:
            printf("Error in %s:%d: parameter %s is illegal.\n",line.file_name,line.line_num,line.parameters[1]);
            return false;

        case register_direct:;     
    }

    /*there is more than 2 parameters*/
    return exactly_parammeters(line, 2);
}

/************************************************************************
* Function Name: change_to_new_dc
* Input: symbols_table **symbol_tbl
         int new_dc
* Function Operation: the function change all the "value"'s properties of
                      all labels (in the symbols_table) with the 
                      attribute "data", to the updated-correct value 
************************************************************************/
void change_to_new_dc(symbols_table **symbol_tbl, int new_dc)
{
    int i = 0;
    while ((*symbol_tbl)[i].name != NULL)
    {    
        if ((*symbol_tbl)[i].attributes_table.data == true)
        {
            (*symbol_tbl)[i].value = (*symbol_tbl)[i].value + new_dc;
        }
        i++;
    }
}

/************************************************************************
* Function Name: get_all_symbols
* Input: FILE *file
         symbols_table **symbol_tbl
* Function Operation: the function gets all the possible labels from the
                      file, and enter them to the symbols_table with no
                      additional data (value= -1 and no attributes).
************************************************************************/
void get_all_symbols(char *file_name , symbols_table **symbol_tbl)
{
    char str[MAX_LINE_SIZE+1];
    FILE *file = open_file_for_reading(file_name,"am");
    int line_num = 0;
    line *corrent_line = new_line();


    while (!feof(file)) /*while file not ended*/
    {
        line_num++;

        fgets(str,MAX_LINE_SIZE+1,file);
        
        release_line(corrent_line);
        
        corrent_line = process_line (str , file_name, line_num, false);
     
        if (str_not_equals(corrent_line->command ,"empty","comment","error",NULL))
        {
            if (corrent_line->has_label == true) /*internal symbol*/
            {
                add_symbol(symbol_tbl , corrent_line->label , -1 , "");    
            }
            if (str_equals(corrent_line->command,".extern") && /*external symbol*/
                corrent_line->parameters[0] != NULL &&
                corrent_line->parameters[1] == NULL)
            {
                add_symbol(symbol_tbl , corrent_line->parameters[0] , -1 , "");        
            }
        }
    }

    release_line(corrent_line);
    free(corrent_line);
    fclose(file);
}

/************************************************************************
* Function Name: line_is_too_long
* Input: int line_num
         char *str
* Output: boolean (true if line is too long)
* Function Operation: the function notify if the line is too long (over
                      MAX_LINE_SIZE length) and return true if so.
************************************************************************/
boolean line_is_too_long(int line_num,char *file_name, char *str)
{
    if (string_length_is_ok(str,MAX_LINE_SIZE+1) == false)
    {
        printf("Error in %s:%d: line is too long. max valid line's length - %d\n",file_name,line_num,MAX_LINE_SIZE);
        printf("\tError may make additional secondary errors.\n");
        return true;
    }
    return false;
}

/************************************************************************
* Function Name: memory_is_enough
* Input: machine_code *machine_tbl
         char *file_name
* Output: boolean (true if memory is enough)
* Function Operation: the function notify if there is not enough memory
                      (over MAX_MEMORY_CELLS value).
                      if there is enough memory, function return true.
************************************************************************/
boolean memory_is_enough(machine_code *machine_tbl, char *file_name)
{
    if (machine_tbl->dc + machine_tbl->ic - 1 > MAX_MEMORY_CELLS)
    {
        printf("CRITICAL ERROR:  NOT ENOUGH MEMORY TO COMPILE %s." , file_name);
        printf( "MEMORY LIMIT: %d. NEEDED MEMORY: %d\n",MAX_MEMORY_CELLS,machine_tbl->dc);
        return false;
    }
    return true;
}