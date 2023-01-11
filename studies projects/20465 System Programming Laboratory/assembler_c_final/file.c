#include "file.h"

/************************************************************************
* Function Name: make_xx_file
* Input: char *file_name
         char *file_type
* Output: FILE *(a pointer to the new file)
* Function Operation: the function make file for writing with a suit
                      ending.
************************************************************************/
FILE *make_xx_file(char *file_name,char *file_type)
{
    FILE *file;
    char *new_name;

    get_memory(new_name,strlen(file_name)+strlen(file_type)+1,char)
    strcpy(new_name,file_name);
    strcat(new_name,file_type);
    file = fopen(new_name,"w");
    free(new_name);
    return file;
}

/************************************************************************
* Function Name: open_file_for_reading
* Input: char *file_name
         char *ending
* Output: FILE *(a pointer to the new file)
* Function Operation: the function open existing file (with a suit
                      ending) for reading.
************************************************************************/
FILE *open_file_for_reading(char * file_name,char *ending)
{
    char *file_new_name;
    FILE *file;
    get_memory(file_new_name,strlen(file_name)+strlen(ending)+2,char)
    strcpy(file_new_name,file_name);
    strcat(file_new_name,".");
    strcat(file_new_name,ending);
    
    file = fopen(file_new_name,"r");
    if (!file)
    {
        printf("ERROR: can't found file \"%s\"\n",file_new_name);
        free(file_new_name);
        return NULL;
    }
    free(file_new_name);
    fseek(file,0,SEEK_SET);
    return file;
}

/************************************************************************
* Function Name: make_str_from_file
* Input: FILE *file (the file we take the content from)
* Output: string (as pointer)
* Function Operation: the function make string outside the file
************************************************************************/
char *make_str_from_file(FILE *file)
{
    char *str;
    int i = 0;
    char c = fgetc(file);
    get_memory(str,1,char)
    while (!feof(file))
    {
        get_more_memory(str,i+1,char)
        str [i] = c;
        c = fgetc(file);
        i++;
    }
    str[i] = '\0';
    return str;
}