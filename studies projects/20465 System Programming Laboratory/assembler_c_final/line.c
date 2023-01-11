#include "line.h"

/************************************************************************
* Function Name: process_line
* Input: char *str (a line of assembly language)
         int line_num 
         boolean notify_errors (if false - no error message will send)
* Output: line (struct of line's properties)
* Function Operation: the function return a line with the str properties
                      if the line is blank, the "command" will be "empty"
                      if the line is comment, "command" will be "comment"
                      if there is any error, the "command" will be "error"                      
************************************************************************/
line *process_line (char *str ,char *file_name, int line_num , boolean notify_errors)
{
    boolean *error_occurred;
    boolean is_comment = false;

    line *corrent_line;
    get_memory(corrent_line,1,line)
    get_memory(corrent_line->parameters,1,char *)
        corrent_line->parameters[0] = NULL;
        corrent_line->line_num = line_num;
    get_memory(corrent_line->file_name,strlen(file_name)+1,char)
        strcpy(corrent_line->file_name,file_name);
        
    get_memory(error_occurred,1,boolean)
    *error_occurred  = false;

    str = move_to_not_white(str);
    if (str[0] == ';') is_comment = true;

    if (is_comment == false)
    {
        str = get_label(str,corrent_line);
        str = get_command(str,corrent_line,error_occurred,notify_errors);
        get_parameters(str,corrent_line,error_occurred,notify_errors);
    }
    
    /*check edge cases*/
    if (is_comment == true)
    {
        get_more_memory(corrent_line->command,8,char)
        strcpy(corrent_line->command,"comment\0");
    }
    if (*error_occurred == true)
    {
        get_more_memory(corrent_line->command,6,char)
        strcpy(corrent_line->command,"error\0");
    }

    return corrent_line;
}


line *new_line ()
{
    line *corrent_line;
    get_memory(corrent_line,1,line)
    
    corrent_line->line_num = 0;
    
    get_memory(corrent_line->file_name,1,char)
        corrent_line->file_name = NULL;

    corrent_line->has_label = false;
    
    get_memory(corrent_line->label,1,char)
        corrent_line->label = NULL;

    get_memory(corrent_line->command,1,char)
    corrent_line->command = NULL;

    get_memory(corrent_line->parameters,1,char *)
    corrent_line->parameters[0] = NULL;

    return corrent_line;
}

/************************************************************************
* Function Name: release_line
* Input: line *line
* Function Operation: the function free (from memory) the "line" var.
************************************************************************/
void release_line(line *line)
{
    int i = 0;    
    while (line->parameters[i] != NULL)
    { 
        free(line->parameters[i]);
        i++;
    }    
    if (line->parameters != NULL)
        free(line->parameters);
    if (line->command != NULL)
        free(line->command);
    if (line->label != NULL)
        free(line->label);
    if (line->file_name != NULL)
        free(line->file_name);
}


/************************************************************************
* Function Name: get_label
* Input: char *str (a line of assembly language)
         line *corrent_line 
* Output: char *(pointer to the location of str, after the label, if any)
* Function Operation: the function fill the label's property, if there is
                      and return the new place of the str (after the label)                       
************************************************************************/
char *get_label(char *str,line *corrent_line)
{
    char *label;
    label = get_first_word(str);
    
    if (label[strlen(label)-1] == ':' &&
        str[strlen(label)] == ' ')
    {
        label[strlen(label)-1] = '\0'; /*delete the ':'*/
        
        /*copy to corrent_line*/
        corrent_line->has_label = true;
        get_memory(corrent_line->label,strlen(label),char)
        strcpy(corrent_line->label,label);

        /*adjust the pointer*/
        str += strlen(label)+1;
        str = move_to_not_white(str);
    }
    free(label);   

    return str;
}

/************************************************************************
* Function Name: get_command
* Input: char *str (a line of assembly language)
         line *corrent_line 
         boolean *error_occurred (becomes true is any error happend)
         boolean notify_errors
* Output: char *(pointer to the location of str, after the command)
* Function Operation: the function fill the command's property, and 
                      return the new place of the str (after the command)  
                      if any error occurred - the matching var will change                     
************************************************************************/
char *get_command(char *str,line *corrent_line, boolean *error_occurred,boolean notify_errors)
{
    char *command;
    command = get_first_word(str);
    
    /*copy to corrent_line*/
    get_memory(corrent_line->command,strlen(command),char)
    strcpy(corrent_line->command,command);   
    
    /*adjust the pointer*/
    str += strlen(command);

    if (str[0] == ',')/*comma after command*/
    {
        if (notify_errors)
            printf("Error in %s:%d: unexpected comma after command.\n" ,corrent_line->file_name, corrent_line->line_num);
        *error_occurred = true;
    }
    if (!str_not_equals(command,"error","comment","empty",NULL))/*invalid command name*/
    {
        if (notify_errors)
            printf("Error in %s:%d: command \"%s\" is unidentified.\n",corrent_line->file_name,corrent_line->line_num,command);
        *error_occurred = true;
    }
    if (str_equals(corrent_line->command , ""))
    {
        if (corrent_line->has_label == false)
        {
            get_more_memory(corrent_line->command,6,char)
            strcpy(corrent_line->command,"empty"); 
        }
        else /*only label in line*/
        {
            if (notify_errors)
                printf("Error in %s:%d: only label line is not allowed.\n",corrent_line->file_name,corrent_line->line_num);
            *error_occurred = true;
        }
    }
    
    str = move_to_not_white(str);

    /*unexpected comma at the start of the parameters*/        
    if (str[0] == ',')
    {
        if (notify_errors)
            printf("Error in %s:%d: unexpected comma.\n" ,corrent_line->file_name, corrent_line->line_num);
        *error_occurred = true;
    }
    free(command);
    return str;
}

/************************************************************************
* Function Name: get_parameters
* Input: char *str (a line of assembly language)
         line *corrent_line 
         boolean *error_occurred (becomes true is any error happend)
         boolean notify_errors
* Function Operation: the function fill the parameters's property. if   
                      any error occurred - the matching var will change                     
************************************************************************/
void get_parameters(char *str,line *corrent_line,boolean *error_occurred,boolean notify_errors)
{
    int num_of_params = 0;
    while (str[0] != '\0') 
    {
        num_of_params++;
        get_more_memory(corrent_line->parameters,num_of_params+1,char *)
        corrent_line->parameters[num_of_params-1] = get_first_word(str);
        corrent_line->parameters[num_of_params] = NULL;

        str += strlen(corrent_line->parameters[num_of_params-1]);
        str = move_to_not_white(str);

        if (str[0] != ',' && str[0] != '\0')
        {
            if (notify_errors)
                printf("Error in %s:%d: missing comma.\n" ,corrent_line->file_name, corrent_line->line_num);
            *error_occurred = true;
            break;
        }
        
        /*check if unexpected comma at the end*/
        if (str[0] == ',')
        {
            str += 1;
            str = move_to_not_white(str);
         
            if(str[0] == '\0')
            {
                if (notify_errors)
                    printf("Error in %s:%d: unexpected comma at the end of the line.\n" ,corrent_line->file_name, corrent_line->line_num);
                *error_occurred = true;
            }
        }
    }
}

/************************************************************************
* Function Name: too_much_parameters
* Input: line line 
         int allowed_num
* Function Operation: the function return true if the parameters' number 
                      of line, are equal to allowed_num.
                      else - function inform and return false                     
************************************************************************/
boolean exactly_parammeters(line line , int allowed_num)
{
    int i = 0;
    while (line.parameters[i] != NULL) i++;
    if (i > allowed_num)
    {
        printf("Error in %s:%d: too much parameters.\n",line.file_name,line.line_num);
        return false;
    }
    if (i < allowed_num)
    {
        printf("Error in %s:%d: command %s expecting ",line.file_name,line.line_num,line.command);
        printf("%s parameter%s.\n",allowed_num  == 1? "a":"two",allowed_num  == 1? "":"s");
        return false;
    }
    return true;
}


/*delete!!*/
void print_line(line *line)
{
    int i = 0;
    printf("line number *%d*:\n", line->line_num);
    if (line->has_label == true) printf("\tlabel   = *%s*\n" , line->label);
    if (line->command != NULL)  printf("\tcommand = *%s*\n" , line->command);
    while(line->parameters[i] != NULL)
    {
        printf("\tparam %d = *%s*\n" , i , line->parameters[i]);
        i++;
    }   
}