#include "macro.h"

typedef struct {char *name;
                char *content;
} macro;

macro *macro_list;
int num_of_macros;

/************************************************************************
* Function Name: pre_assembler
* Input: char *file_name (a file's name or path)
* Output: boolean (true if the mission endded successfully)
* Function Operation: the function make a new file (ended with am),
                      with the data from the file represent by the given
                      name (or path), after spread all macros in it
************************************************************************/
boolean pre_assembler(char *file_name)
{   
    FILE *corrent_file;
    char **text;

    get_memory(text,1,char *)

    /*open file*/
    corrent_file = open_file_for_reading(file_name,"as");
    if (!corrent_file) return false; /*file not found*/
    
    /*progress the file's data*/
    (*text) = make_str_from_file(corrent_file);
        fclose(corrent_file);

    find_macros(*text);
    delete_macros(text);
    add_macros(text);


    /*send the new file*/
    corrent_file = make_xx_file(file_name,".am");
    fprintf(corrent_file,"%s",*text);    
    fclose(corrent_file);

    free(*text);
    free (text);
    
    return true;
}

/************************************************************************
* Function Name: find_macros
* Input: char *text (string with a file's data)
* Function Operation: the function allocate all macros in corrent string,
                      and put there data in the global var- "macro_list"
************************************************************************/
void find_macros(char *text)
{
    char *start_of_macro;
    int content_length = 0;
    num_of_macros = 0;
    
    start_of_macro = find_start_of_exactly(text,START_OF_MACRO_WORD);
    
    while(start_of_macro != NULL) /*there is more macros*/
    {
        num_of_macros++;

        if (num_of_macros == 0) get_memory(macro_list,1,macro)
        
        get_more_memory(macro_list,num_of_macros,macro)
        
        text = start_of_macro + strlen(START_OF_MACRO_WORD); /*go beyond the starting word*/
        text = move_to_not_white(text);
        
        macro_list[num_of_macros-1].name = get_first_word(text);
        text = move_to_white(text);
        text = strstr(text,"\n")+1;
        
        content_length = distance_between_chars(find_start_of_exactly(text,END_OF_MACRO_WORD),text);
        get_memory(macro_list[num_of_macros-1].content,content_length,char)
        strncpy(macro_list[num_of_macros-1].content,text,content_length);
        macro_list[num_of_macros-1].content[content_length] = '\0';/*last char is white*/
        
        /*Preparations for the next iteration*/
        start_of_macro = find_start_of_exactly(text,START_OF_MACRO_WORD);
    }   
}

/************************************************************************
* Function Name: delete_macros
* Input: char **txt (pointer to the string [pointer to pointer])
* Function Operation: the function delete the macros from the text
************************************************************************/
void delete_macros(char **txt)
{
    char *text = *txt;
    char *new; /*get the new string*/
    int t = 0;/*index of text*/
    int n = 0;/*index of new*/
    boolean in_macro = false;
    
    get_memory(new,1,char)
    
    while(text[t] != '\0')/*for all text's chars*/
    {
        if(in_macro == true) /*no need to save this content*/
        {
            if(!strncmp(text+t,END_OF_MACRO_WORD,strlen(END_OF_MACRO_WORD))) /*if we get to the end of the macro*/
            {
                in_macro = false;
                t += strlen(END_OF_MACRO_WORD);
            }
            t++;
        }
        else /*not in macro*/
        {
            if(!strncmp(text+t,START_OF_MACRO_WORD,strlen(START_OF_MACRO_WORD))) /*start of macro*/
            {
                in_macro = true;
            }
            else /*need to copy the next char*/
            {
                new[n] = text[t];
                get_more_memory(new,n+2,char)
                t++;
                n++;
                new[n] = '\0';
            }  
        }
    }
    free(text);
    get_memory(text,strlen(new)+1,char)
    strcpy(text,new);
    free(new);
    *txt = text;
}

/************************************************************************
* Function Name: add_macros
* Input: char **txt (pointer to the string [pointer to pointer])
* Function Operation: the function add the macros from macro_list 
                      into *txt
************************************************************************/
void add_macros(char **txt)
{
    char *text = *txt;
    char *new; /*get the new string*/
    int t = 0; /*index of text*/
    int n = 0; /*index of new*/
    int m = 0; /*index of macros*/
    int content_length = 0;

    while(m < num_of_macros) /*pass all macros in macro_list*/
    {
        get_memory(new,1,char)

        while (text [t] != '\0')
        {
            if (!strncmp(text+t,  /*if the next word in the text equal to the macro's name*/
                         macro_list[m].name,
                         strlen(macro_list[m].name)))
            {
                t += strlen(macro_list[m].name);
                content_length = strlen(macro_list[m].content);
                get_more_memory(new,1+n+content_length,char)
                strncpy(new+n,macro_list[m].content,content_length);
                n += content_length;
                new[n] = '\0';
            }
            else /*regular char*/
            {
                new [n] = text [t];
                n++;
                t++;
                get_more_memory(new,n+1,char)
                new [n] = '\0';
            }
        }
        
        m++;
        t = 0;
        n = 0;
        free(text);
        get_memory(text,strlen(new)+1,char)
        strcpy(text,new);
        free(new);
        free(macro_list->content);
        free(macro_list->name);
    }
    *txt = text;
}