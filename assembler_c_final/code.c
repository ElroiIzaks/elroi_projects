#include "code.h"

/************************************************************************
* Function Name: new_code_table
* Output: machine_code *
* Function Operation: the function make a machine code (table of words
                      and ic) that we will use, for save values of
                      symbols. the function reset the values to be false
                      (in boolean values) and NULL (in the pointer value)  
************************************************************************/
machine_code *new_machine_code_table ()
{
    machine_code *machine_tbl;
    get_memory(machine_tbl,1,machine_code)

    machine_tbl->ic = 100;
    machine_tbl->dc = 0;
    get_memory(machine_tbl->code,1,code)
        machine_tbl->code[0] = new_code_word();

    get_memory(machine_tbl->data,1,data_word)
        machine_tbl->data[0] = new_data_word();
    
    return machine_tbl;
}

void release_machine_code_table(machine_code *machine_tbl)
{
    release_code_table(*machine_tbl);
    free(machine_tbl->data);
    free(machine_tbl);
}

/************************************************************************
* Function Name: new_code_word
* Output: code
* Function Operation: the function make a new code (code word) with 
                      initial values.
************************************************************************/
code new_code_word ()
{
    code code_word;
        code_word.first_word.ic_num           = -1;
        code_word.has_second               = false;
        code_word.has_another              = false;
            get_memory(code_word.another_words,1,another_word)
            code_word.another_words[0].ic_num = -1;
    return code_word;
}

/************************************************************************
* Function Name: new_data_word
* Output: data
* Function Operation: the function make a new data word with initial
                      values.
************************************************************************/
data_word new_data_word ()
{
    data_word data_word;
        data_word.dc_num         =    -1;
        data_word.is_num         = false;
        data_word.is_char        = false;
    return data_word;
}

/************************************************************************
* Function Name: size_of_code_table
* Input: machine_code * (pointer to machine code)
* Output: int
* Function Operation: the function return the number of "code"s in the
                      given table (not include the NULL closer)
************************************************************************/
int size_of_code_table (machine_code *machine_tbl)
{
    int i = 1;
    while (machine_tbl->code[i-1].first_word.ic_num != -1) i++;
    return i-1;
}

/************************************************************************
* Function Name: size_of_data_table
* Input: machine_code * (pointer to machine code)
* Output: int
* Function Operation: the function return the number of "data"s in the
                      given table (including the NULL closer)
************************************************************************/
int size_of_data_table (machine_code *machine_tbl)
{
    int i = 1;
    while (machine_tbl->data[i-1].dc_num != -1) 
    {
        i++;
    }
    return i;
}



/************************************************************************
* Function Name: add_another_word
* Input: code *code (containing a table of another word)
         char *txt (with a parameter (first or second) from a line)
         symbols_table **given_table 
         int table_length (not including the NULL (ic_num == -1))
         int ic (the first empty ic)
* Output: int (how much the ic move along)
* Function Operation: the function add an "another_word" to the table
                      (base on the txt and the given_table vars).
                      the function return how much the ic progress
************************************************************************/
int add_another_word(code *code , char *txt, symbols_table **symbols_table , int table_length, int ic)
{
    int i = 0;
    
    switch (find_addressing_method(txt, symbols_table))
    {
    case immediate:
        get_more_memory(code->another_words,table_length+2,another_word)
        set_another_word_values(&(code->another_words[table_length]),ic,a,make_int_from_str(txt+1),false,NULL);
        set_another_word_values(&(code->another_words[table_length+1]),-1,a,-1,false,NULL);
        return 1;
    
    case direct:
        get_more_memory(code->another_words,table_length+3,another_word)
        i = find_symbol(symbols_table,txt);
        if ((*symbols_table)[i].attributes_table.external == true)
        {
            set_another_word_values(&(code->another_words[table_length]),ic,e,0,true,txt);
            set_another_word_values(&(code->another_words[table_length+1]),ic+1,e,0,true,txt);
            set_another_word_values(&(code->another_words[table_length+2]),-1,a,-1,false,NULL);
        }
        else
        {
            set_another_word_values(&(code->another_words[table_length]),ic,r,((*symbols_table)[i].value / 16)*16,false,NULL);
            set_another_word_values(&(code->another_words[table_length+1]),ic+1,r,(*symbols_table)[i].value - code->another_words[table_length].value,false,NULL);
            set_another_word_values(&(code->another_words[table_length+2]),-1,a,-1,false,NULL);
        }
        return 2;

    case index:
        get_more_memory(code->another_words,table_length+3,another_word)
        i = find_symbol_by_index(symbols_table,txt);
        if((*symbols_table)[i].attributes_table.external == true)
        {
            set_another_word_values(&(code->another_words[table_length]),ic,e,0,true,txt);
            set_another_word_values(&(code->another_words[table_length+1]),ic+1,e,0,true,txt);
            set_another_word_values(&(code->another_words[table_length+2]),-1,a,-1,false,NULL);        
        }
        else
        {
            set_another_word_values(&(code->another_words[table_length]),ic,r,((*symbols_table)[i].value / 16)*16,false,NULL);
            set_another_word_values(&(code->another_words[table_length+1]),ic+1,r,(*symbols_table)[i].value - code->another_words[table_length].value,false,NULL);
            set_another_word_values(&(code->another_words[table_length+2]),-1,a,-1,false,NULL);
        }
        return 2;
    
    case register_direct:;
    case no_method:;
    }    
    return 0;
}

/************************************************************************
* Function Name: release_another_word_table
* Input: code code
* Function Operation: the function free (from memory )the "another_words" 
                      table of "code". 
************************************************************************/
void release_another_word_table(code code)
{
    int i = 0;
    while (code.another_words[i].ic_num != -1)
    {
        free(code.another_words[i].external);
        i++;
    }
    free(code.another_words);
}

/************************************************************************
* Function Name: release_code_table
* Input: machine_code machine_code
* Function Operation: the function free (from memory )the "code" 
                      table of "machine_code". 
************************************************************************/
void release_code_table(machine_code machine_code)
{
    int i = 0;
    while (machine_code.code[i].first_word.ic_num != -1)
    {
        release_another_word_table(machine_code.code[i]);
        i++;
    }
    free(machine_code.code);
}

/************************************************************************
* Function Name: set_another_word_values
* Input: another_word *another_word (the income address)
         int ic
         are are
         int value
         boolean is_external
         char *external
* Function Operation: the function set the properties of "another_word"
                      (by address) with those values. 
************************************************************************/
void set_another_word_values(another_word *another_word, int ic, are are, int value, boolean is_external, char *external)
{
    another_word->ic_num = ic;
    another_word->are = are;
    another_word->value = value;
    another_word->is_external = is_external;
    if (is_external == true)
    {
        get_memory(another_word->external,strlen(external)+1,char)
        strcpy(another_word->external,external);
    }
    else
        another_word->external = NULL;
}

/************************************************************************
* Function Name: enter_data
* Input: line line
         machine_code **machine_code
         symbol_table **symbols_table
* Output: boolean (false if error occurred)
* Function Operation: the function update machine_code and symbol_table
                      by values from line. if error occurred, function
                      return false. else - true.
************************************************************************/
boolean enter_data(line line , machine_code *machine_code , symbols_table **symbols_table)
{
    int i = 0;
    int j = 0;
    int temp = -1;

    temp = find_symbol(symbols_table,line.parameters[0]);

    switch (guidance_by_text(line.command))
    {
    case externy:
        if (exactly_parammeters(line,1) == false)/*check the number of parameters*/
            return false;

        if ((*symbols_table)[temp].attributes_table.entry == true) /*can not be both entry and external*/
        {
            printf("Error in %s:%d: label %s can not be both external and entry.\n",line.file_name,line.line_num,line.parameters[0]);
            return false;
        }
        add_symbol(symbols_table , line.parameters[0] , 0 , "external");
        break;
    
    case entry:
        if (exactly_parammeters(line,1) == false)/*check the number of parameters*/
            return false;
        
        if (temp == -1) /*un-known label*/
        {
            printf("Error in %s:%d: un-known label- \"%s\".\n",line.file_name,line.line_num,line.parameters[0]);
            return false;
        }
  
        if ((*symbols_table)[temp].attributes_table.external == true) /*can not be both entry and external*/ 
        {
            printf("Error in %s:%d: label %s can not be both external and entry.\n",line.file_name,line.line_num,line.parameters[0]);
            return false;
        }
        add_symbol(symbols_table , line.parameters[0] , 0 , "entry");
        break;

    case data:
        if (line.parameters[0] == NULL) /*un-valid number of parameters*/
        {
            printf("Error in %s:%d: no data was entered.\n",line.file_name,line.line_num);
            return false;
        }
        while (line.parameters[i] != NULL)
        {
            if (legal_int_from_str(line.parameters[i]) == false)
            {
                printf("Error in %s:%d: only digits allowed with \".data\".\n",line.file_name,line.line_num);
                return false;
            }
            expand_data_table(machine_code);
            machine_code->data[machine_code->dc+i].dc_num = machine_code->dc+i;
            machine_code->data[machine_code->dc+i].is_num = true;
            machine_code->data[machine_code->dc+i].num = make_int_from_str(line.parameters[i]);
            i++;
        }
        if(line.has_label == true)
        {
            add_symbol(symbols_table , line.label , machine_code->dc , "data");
        }
        machine_code->dc += i;
        break;

    case string:
        if (exactly_parammeters(line,1) == false)/*check the number of parameters*/
            return false;
        if (line.parameters[0][0] != '\"' || line.parameters[0][strlen(line.parameters[0])-1] != '\"')
        { 
            printf("Error in %s:%d: only string with quotes allowed with \".string\".\n",line.file_name,line.line_num);
            return false;  
        }
        while (line.parameters[0][i] != '\0')
        {
            if (line.parameters[0][i] == '\"')
            {
                i++;
                continue;
            } 
            expand_data_table(machine_code);
            machine_code->data[machine_code->dc+j].dc_num = machine_code->dc+j;
            machine_code->data[machine_code->dc+j].is_char = true;
            machine_code->data[machine_code->dc+j].character = line.parameters[0][i];
            i++;
            j++;
        }
        expand_data_table(machine_code);
        machine_code->data[machine_code->dc+j].dc_num = machine_code->dc+j;
        machine_code->data[machine_code->dc+j].is_char = true;
        machine_code->data[machine_code->dc+j].character = '\0';
        j++;
        add_symbol(symbols_table , line.label , machine_code->dc , "data");
        machine_code->dc += j;
    
    case no_guidance:;
    }
    return true;
}

/************************************************************************
* Function Name: expand_code_table
* Input: machine_code *machine_tbl
* Function Operation: the function expand the code table in the given 
                      machine_code by one, and insert to the new cell
                      the initial values
************************************************************************/
void expand_code_table(machine_code *machine_tbl)
{
    int size = size_of_code_table(machine_tbl);
    get_more_memory(machine_tbl->code,size+2,code)
    machine_tbl->code[size+1] = new_code_word();
}

/************************************************************************
* Function Name: expand_data_table
* Input: machine_code *machine_tbl
* Function Operation: the function expand the data table in the given 
                      machine_code by one, and insert to the new cell
                      the initial values
************************************************************************/
void expand_data_table(machine_code *machine_tbl)
{
    int size = size_of_data_table(machine_tbl);
    get_more_memory(machine_tbl->data,size+1,data_word)
    machine_tbl->data[size] = new_data_word();
}

/************************************************************************
* Function Name: another_words_length
* Input: code code
* Output: int (how many another_word there are in some code word)
* Function Operation: the function return the length of "another_word"
                      table in specific code word (not including the 
                      ending NULL)
************************************************************************/
int another_words_length(code code)
{
    int i = 0;
    while (code.another_words[i].ic_num != -1) i++;
    return i;
}

/*dalete!!*/
void print_machine_code(machine_code *machines_code)
{
    int i = 0;
    int j = 100;
    int k = 0;
    machine_code machine = *machines_code;

    printf("machine code:\n\tic-\t%d\n\tdc-\t%d\n\n",machine.ic,machine.dc);

    printf("\tCODE-");
    while (machine.code[i].first_word.ic_num != -1)
    {
        printf("\n\t*****\t");
        printf("\n\tword %d:\t",j);
        j++;
        printf("are - a. opcode - %d.\n",machine.code[i].first_word.opcode);
        if (machine.code[i].has_second == true)
        {
            printf("\tword %d:\t",j);
            j++;
            printf("are - a. funct - %d. ",machine.code[i].second_word.funct);
            printf("source: reg - %d, method - ",machine.code[i].second_word.source_reg);
            if (machine.code[i].second_word.address_source == immediate) printf("immediate. ");
            if (machine.code[i].second_word.address_source == direct)    printf("direct. ");
            if (machine.code[i].second_word.address_source == index) printf("index. ");
            if (machine.code[i].second_word.address_source == register_direct) printf("register_direct. ");
            printf("target: reg - %d, method - ",machine.code[i].second_word.target_reg);
            if (machine.code[i].second_word.address_target == immediate) printf("immediate. ");
            if (machine.code[i].second_word.address_target == direct)    printf("direct. ");
            if (machine.code[i].second_word.address_target == index) printf("index. ");
            if (machine.code[i].second_word.address_target == register_direct) printf("register_direct. ");
        }
        if (machine.code[i].has_another == true)
        {
            while (machine.code[i].another_words[k].ic_num != -1)
            {
                printf("\n\tword %d:\t",j);
                j++;
                printf("ic - %d. are - ",machine.code[i].another_words[k].ic_num);
                if (machine.code[i].another_words[k].are == a) printf("a. ");
                if (machine.code[i].another_words[k].are == r) printf("r. ");
                if (machine.code[i].another_words[k].are == e) printf("e. ");
                printf("value - %d",machine.code[i].another_words[k].value);
                k++;
            }
            k = 0;   
        }
        i++;
    }
    i = 0;
    printf("\tDATA-\n");
    while (machine.data[i].dc_num != -1)
    {
        printf("\tword %d:\t",j);
        j++;
        printf("dc num - %d. is num - %s. ",machine.data[i].dc_num, machine.data[i].is_num? "yes":"no");
        printf("is char - %s. ", machine.data[i].is_char? "yes":"no");
        printf("value - ");
        if (machine.data[i].is_char == true) printf("%c\n",machine.data[i].character);
        if (machine.data[i].is_num == true) printf("%d\n",machine.data[i].num);
        i++;
    }
}