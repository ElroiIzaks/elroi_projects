#include "valid.h"


/************************************************************************
* Function Name: is_legal_label_name
* Input: char *str (string)
* Output: label_error
* Function Operation: the function return why the str can not be label
                      name, and if the name is valid, the function
                      return "no_error".
************************************************************************/
label_error is_legal_label_name(char * str)
{
    int i = 0;

    if (isalpha(str[0]) == 0) return no_alphabet_start;

    if (strlen(str) > 31) return too_long;
    
    while (str[i] != '\0')
    {
        if (!isalpha(str[i]) && !isdigit(str[i])) return include_unvalid_symbol;
        i++;
    }
    
    if (!str_not_equals(str,"r0","r1","r2","r3","r4","r5","r6","r7","r8",
                            "r9","r10","r11","r12","r13","r14","r15",NULL))
        return register_name;

    if (!str_not_equals(str,"mov","cmp","add","sub","lea","clr","not","inc","dec",
                            "jmp","bne","jsr","red","prn","rts","stop",NULL))
        return instruct_name;
    
    if (!str_not_equals(str,"data","string","entry","extern",NULL))
        return guidance_name;

    return no_error;
}


/************************************************************************
* Function Name: register_by_text
* Input: char *txt (should be 'r'+ (0 - 15))
* Output: int
* Function Operation: the function return a num of register from a str 
                      present a register name. if the txt isn't legal
                      register type, the function return -1;
************************************************************************/
int register_by_text(char *txt)
{
    if(txt == NULL)           return -1;
    if(str_equals(txt,"r0"))   return 0;
    if(str_equals(txt,"r1"))   return 1;
    if(str_equals(txt,"r2"))   return 2;
    if(str_equals(txt,"r3"))   return 3;
    if(str_equals(txt,"r4"))   return 4;
    if(str_equals(txt,"r5"))   return 5;
    if(str_equals(txt,"r6"))   return 6;
    if(str_equals(txt,"r7"))   return 7;
    if(str_equals(txt,"r8"))   return 8;
    if(str_equals(txt,"r9"))   return 9;
    if(str_equals(txt,"r10")) return 10;
    if(str_equals(txt,"r11")) return 11;
    if(str_equals(txt,"r12")) return 12;
    if(str_equals(txt,"r13")) return 13;
    if(str_equals(txt,"r14")) return 14;
    if(str_equals(txt,"r15")) return 15;
    return -1;
}

/************************************************************************
* Function Name: legal_immediate
* Input: char *txt
* Output: boolean
* Function Operation: the function return true if the txt is a legal
                      immediate type parameter
************************************************************************/
boolean legal_immediate(char *txt)
{
    int i = 0;
    if (txt == NULL) return false;
    if (txt[i] != '#')  return false;
    i++;
    if (txt[i] == '\0') return false;
    if (txt[i] != '+' &&
        txt[i] != '-' &&
        !isdigit(txt[i]))  return false;
    if(txt[i] == '+' ||
       txt[i] == '-') i++;
    if (!isdigit(txt[i])) return false;
    while (txt[i] != '\0')
    {
        if (!isdigit(txt[i])) return false;
        i++;
    }
    return true;    
}

/************************************************************************
* Function Name: legal_direct
* Input: char *txt
         symbols_table **given_table (a pointer to a symbols' table)
* Output: boolean
* Function Operation: the function return true if the txt is a legal
                      direct type parameter
************************************************************************/
boolean legal_direct(char *txt, symbols_table **given_table)
{
    if (txt == NULL) return false;
    if (find_symbol(given_table,txt) != -1)
        return true;
    return false;
}

/************************************************************************
* Function Name: legal_index
* Input: char *txt
         symbols_table **given_table (a pointer to a symbols' table)
* Output: int
* Function Operation: the function return the number of the inner
                      register. if the txt is not legal index, the 
                      function return -1;
************************************************************************/
int legal_index(char *txt, symbols_table **given_table)
{
    int i = 0;
    int name_length;
    symbols_table *table = *given_table;
    if (txt == NULL) return -1;
    while (table[i].name != NULL)
    {
        name_length = strlen(table[i].name);
        if (strncmp(txt,table[i].name,name_length) == 0)
        {
            if (txt[name_length]   == '['   &&
                txt[name_length+1] == 'r'   &&
                txt[name_length+2] == '1'   &&
                txt[name_length+4] == ']'   &&
                txt[name_length+5] == '\0')
            {
                if (txt[name_length+3] == '0') return 10;
                if (txt[name_length+3] == '1') return 11;
                if (txt[name_length+3] == '2') return 12;
                if (txt[name_length+3] == '3') return 13;
                if (txt[name_length+3] == '4') return 14;
                if (txt[name_length+3] == '5') return 15;
            }
        }
        i++;
    }
    return -1;
}


/************************************************************************
* Function Name: find_addressing_method
* Input: char *txt (the parameter)
        symbols_table **symbols_table (pointer to symbols' table)
* Output: addressing_method
* Function Operation: the function return the method that the txt is.
************************************************************************/
addressing_method find_addressing_method (char *txt, symbols_table **symbols_table)
{
    if (legal_immediate(txt) == true)             return immediate;
    if (legal_direct(txt, symbols_table) == true) return direct;
    if (legal_index(txt, symbols_table) != -1)  return index;
    if (register_by_text(txt) != -1)              return register_direct;
    
    return no_method;
}


/************************************************************************
* Function Name: instruction_by_text
* Input: char *txt (to be checked)
* Output: instruction
* Function Operation: the function make an "instruction" (struct) with 
                      the properties matching the given name.
                      if the txt is not instruction, function return 
                      instruction with the name: "no_instruction"
************************************************************************/
instruction instruction_by_text (char *txt)
{
    instruction instruct;
        instruct.opcode = -1;
    /*name*/
    get_memory(instruct.name,strlen(txt)+1,char)
    strcpy(instruct.name,txt);
    instruct.name[strlen(instruct.name)] = '\0';

    /*funct*/
    if (!str_not_equals(txt,"mov","cmp","lea","red","prn","rts","stop",NULL)) instruct.funct  = -1;
    if (!str_not_equals(txt,"add","clr","jmp",NULL))                          instruct.funct =  10;
    if (!str_not_equals(txt,"sub","not","bne",NULL))                          instruct.funct =  11;
    if (!str_not_equals(txt,"inc","jsr",NULL))                                instruct.funct =  12;
    if (str_equals(txt,"dec"))                                                instruct.funct =  13;

    /*opcode*/
    if(str_equals(txt, "mov"))                                                instruct.opcode =  0;
    if(str_equals(txt, "cmp"))                                                instruct.opcode =  1;
    if(!str_not_equals(txt, "add","sub",NULL))                                instruct.opcode =  2;
    if(str_equals(txt, "lea"))                                                instruct.opcode =  4;
    if(!str_not_equals(txt, "clr","not","inc","dec",NULL))                    instruct.opcode =  5;
    if(!str_not_equals(txt, "jmp","bne","jsr",NULL))                          instruct.opcode =  9;
    if(str_equals(txt, "red"))                                                instruct.opcode = 12;
    if(str_equals(txt, "prn"))                                                instruct.opcode = 13;
    if(str_equals(txt, "rts"))                                                instruct.opcode = 14;
    if(str_equals(txt,"stop"))                                                instruct.opcode = 15;
    
    if (instruct.opcode == -1) /*not an instruction*/
    {
        free(instruct.name);
        get_memory(instruct.name,14,char)
        strcpy(instruct.name , "no_instruction"); 
    }
    return instruct;
}



/************************************************************************
* Function Name: guidance_by_text
* Input: char *txt
* Output: guidance
* Function Operation: the function return which guidance as the same name
                      as txt's value. if non - return "no_guidance". 
************************************************************************/
guidance guidance_by_text (char *txt)
{
    if(str_equals(txt,".data")) return data;
    if(str_equals(txt,".string")) return string;
    if(str_equals(txt,".extern")) return externy;
    if(str_equals(txt,".entry")) return entry;
    return no_guidance;
}

/************************************************************************
* Function Name: source_reg
* Input: line line
         symbol_table ** symbol_table
* Output: int (return the num of source register)
* Function Operation: the function return the num of source register.
                      if there is no source reg, the function return 0.
************************************************************************/
int source_reg(line line, symbols_table ** symbols_table)
{
    if (!str_not_equals(line.command , "mov","cmp","add","sub","lea",NULL))
    {
        if (register_by_text(line.parameters[0]) != -1)
            return register_by_text(line.parameters[0]);
        if (legal_index(line.parameters[0] , symbols_table) != -1)
            return legal_index(line.parameters[0] , symbols_table);
    }   
    return 0;
}



/************************************************************************
* Function Name: source_address
* Input: line line
         symbols_table ** symbols_table
* Output: addressing_method (return respectively to the source param)
* Function Operation: the function return the method of source param.
************************************************************************/
addressing_method source_address (line line, symbols_table **symbols_table)
{
    if (!str_not_equals(line.command , "mov","cmp","add","sub","lea",NULL))
    {
        return find_addressing_method(line.parameters[0],symbols_table);
    }   
    return no_method;       
}

/************************************************************************
* Function Name: target_reg
* Input: line line
         symbols_table ** symbol_table
* Output: int (return the num of target register)
* Function Operation: the function return the num of target register.
                      if there is no target reg, the function return 0.
************************************************************************/
int target_reg(line line, symbols_table **symbols_table)
{
    if (!str_not_equals(line.command , "mov","cmp","add","sub","lea",NULL))
    {
        if (register_by_text(line.parameters[1]) != -1)
            return register_by_text(line.parameters[1]);
        if (legal_index(line.parameters[1] , symbols_table) != -1)
            return legal_index(line.parameters[1] , symbols_table);
    }
    
    if (!str_not_equals(line.command , "clr","not","inc","dec","jmp","bne","jsr","red","prn",NULL))
    {
        if (register_by_text(line.parameters[0]) != -1)
            return register_by_text(line.parameters[0]);
        if (legal_index(line.parameters[0] , symbols_table) != -1)
            return legal_index(line.parameters[0] , symbols_table);
    }
    return 0;
}

/************************************************************************
* Function Name: target_address
* Input: line line
         symbols_table ** symbols_table
* Output: addressing_method (return respectively to the target param)
* Function Operation: the function return the method of target param.
************************************************************************/
addressing_method target_address (line line, symbols_table **symbols_table)
{
    if (!str_not_equals(line.command , "mov","cmp","add","sub","lea",NULL))
    {
        return find_addressing_method(line.parameters[1],symbols_table);
    }   
    return find_addressing_method(line.parameters[0],symbols_table);       
}
