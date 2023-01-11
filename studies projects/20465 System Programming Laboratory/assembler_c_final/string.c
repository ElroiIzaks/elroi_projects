#include "string.h"

/************************************************************************
* Function Name: make_int_from_str
* Input: char *str
* Output: int
* Function Operation: the function return a int from a str present a 
                      LEGAL int (may have '+' or '-' sign before)
************************************************************************/
int make_int_from_str(char *str)
{
    int i = 0;
    int temp = 0;
    int str_length = strlen(str);
    boolean minus = false;

    if(str[0] == '-') minus = true;
    if (minus == true) i++;
    if (str[0] == '+') i++;
    while (str[i] != '\0')
    {
        temp += ((int)str[i]-48)*pow(10,str_length-(i+1));
        i++;
    }
    if (minus == true) temp = temp*(-1);
    return temp;
}

/************************************************************************
* Function Name: legal_int_from_str
* Input: char *str
* Output: boolean (true if legal)
* Function Operation: the function return true if the string represents
                      correctly a int (can begin with '+' or '-')
************************************************************************/
boolean legal_int_from_str(char *str)
{
    int i = 1;

    if ((!isdigit(str[0]) && str[0] != '-' && str[0] != '+') || str[0] == '0')
        return false;

    if (str[1] == '0' && (str[0] == '-' || str[0] == '+'))
        return false;

    while (str[i] != '\0')
    {
        if (!isdigit(str[i]))
            return false;
        
        i++;
    }
    return true;
}

/************************************************************************
* Function Name: str_equals
* Input: char *a
         char *b
* Output: boolean (true if equals)
* Function Operation: the function return true if the two strings are
                      equals.
************************************************************************/
boolean str_equals(char *a , char *b)
{
    if (strcmp(a,b) == 0)
        return true;

    return false;
}

/************************************************************************
* Function Name: str_not_equals
* Input: char *a (string to compair)
         ... (any number of 'char *'s. last parameter must be NULL)
* Output: boolean (true if not equals)
* Function Operation: the function return true if the first string 
                      not equals to any of the other strings
                      (can be use for: "!str_not_equals(a,...)" - a is
                      equal to one of the parameters).
************************************************************************/
boolean str_not_equals(char *a , ...)
{
    char * compared_str;
    va_list list;
    va_start(list,a);

    compared_str = va_arg(list,char *);

    while(compared_str != NULL)
    {
        if (str_equals(a,compared_str))
        return false;
        compared_str = va_arg(list,char *);
    }
    return true;
}

/************************************************************************
* Function Name: string_length_is_ok
* Input: char *string
         int max_valid_length
* Output: boolean (true if string's length is under "max_valid_length")
* Function Operation: the function return true if the string is shorter
                      or (is length) equals to "max_valid_length"
************************************************************************/
boolean string_length_is_ok (char * string, int max_valid_length)
{
    int i = 0;
    while (i <= max_valid_length)
    {
        if (string[i] == '\n' || string[i] == '\0')
        {
            return true;
        }
        i++;
    }
    return false;
}

/************************************************************************
* Function Name: find_start_of_exactly
* Input: char *text
         char *word
* Output: char * (pointer to the place of word's value in text)
* Function Operation: the function return the place of the exact word's
                      value, as the word is not part of longer un-white
                      string. if the word is not located, the function
                      return NULL.
************************************************************************/
char *find_start_of_exactly(char *text,char *word)
{
    char *ptr;
    ptr = strstr(text,word); /*if non- NULL*/
    
    if (ptr == NULL) return NULL;

    if (strncmp(text,word,strlen(word)) == 0 && /*the text starts with the word*/ 
        !isspace(*(ptr+strlen(word)))) 
            return ptr;
        
    if (*(ptr+strlen(word)) == '\0' && /*the text ends with the word*/
        !isspace(*(ptr-1))) 
            return ptr;
    
    while (ptr != NULL && /*true if word has found*/
           !isspace(*(ptr-1)) && /*true if there is non-blank char before the word*/
           !isspace(*(ptr+strlen(word)))) /*true if there is non-blank char after the word*/
    {
        ptr = strstr(text,word);
    }

    return ptr;
}

/************************************************************************
* Function Name: move_to_not_white
* Input: char *str (pointer to a place in a string)
* Output: char * (pointer to the first non-white char)
* Function Operation: the function return a pointer to the first char
                      (in corrent string) who are'nt white (space)
************************************************************************/
char *move_to_not_white(char *str)
{ 
    int i = 0;
    while (isspace(str[i]) && str[i] != '\0')
    {
        i++;
    }
    return str+i;
}

/************************************************************************
* Function Name: move_to_white
* Input: char *str (pointer to a place in a string)
* Output: char * (pointer to the first white char)
* Function Operation: the function return a pointer to the first
                      char (in corrent string) who are white (space)
************************************************************************/
char *move_to_white (char *str)
{
    int i = 0;
    while (!isspace(str[i]))
    {
        i++;
    }
    return str+i;
}

/************************************************************************
* Function Name: get_first_word
* Input: char *from (pointer to a non-blank char in a string)
* Output: char * (pointer to new string with a word in it)
* Function Operation: the function return a pointer to the first word in
                      the corrent string (as a new string)
************************************************************************/
char *get_first_word (char *from)
{
    int i = 0;
    char *to;
    get_memory(to,1,char)
    while(!isspace(from[i]) && from[i] != '\0' && from[i] != ',')
    {
        to[i] = from[i];
        i++;
        get_more_memory(to,i+1,char)
    }
    to[i] = '\0';
    return to;
}

/************************************************************************
* Function Name: distance_between_chars
* Input: char *first (pointer to a place in a string)
         char *second (pointer to a place in a string)
* Output: int (the distance between the two pointers)
* Function Operation: the function return the distance between the 
                      pointers, consider only one of them
                      (if text = "abcdef" than "dis...(b,d)" = 2)
************************************************************************/
int distance_between_chars(char *first, char *second)
{
    return ((first - second)/sizeof(char)-1);
}