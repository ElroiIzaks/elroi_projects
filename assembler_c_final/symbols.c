#include "symbols.h"

/************************************************************************
* Function Name: new_symbols_table
* Output: symbols_table **(that we will use later)
* Function Operation: the function make a table of symbols that we will
                      use, for save values of symbols. the function reset
                      the values to be null (in pointers values), and -1
                      (in int values).
************************************************************************/
symbols_table **new_symbols_table()
{
    symbols_table **table;
    get_memory(table,1,symbols_table *)
    get_memory(*table,1,symbols_table)
    (*table)->name                            = NULL;
    (*table)->value                           =   -1;
    new_attributes_table(&((*table)->attributes_table));
    
    return table;
}

/************************************************************************
* Function Name: release_symbols_table
* Output: symbols_table **
* Function Operation: the function release all memory allocated by 
                      symbols_table.
************************************************************************/
void release_symbol_table(symbols_table **symbols_table)
{
    int i = 0;
    while ((*symbols_table)[i].name != NULL)
    {
        free((*symbols_table)[i].name);
        i++;
    }
    free(*symbols_table);
    free(symbols_table);
}

/************************************************************************
* Function Name: add_symbol
* Input: char **table (a pointer to a symbols' matrix)
         char *new_name (a symbol name)
         int new_value (the symbol's value)
         char *attribute (the name of the new attribute)
* Function Operation: the function add a new line to the "table"'s matrix
                      with the suitable given values (name: new_name; 
                      value: new_value; base and offset: accordance to
                      new_value; attribute: attribute).
                      IF "TABLE" HAD ALLREADY SYMBOL WITH THE SAME NAME,
                      NO LINE BE ADDED, AND THE NEW ATTRIBUTE WILL BE 
                      ADDED TO THE SUITABLE LINE
************************************************************************/
void add_symbol(symbols_table **table,char *new_name,int new_value,char *attribute)
{
    int table_size;
    int temp = find_symbol(table,new_name);

    if (temp != -1)
    {
        add_attribute(&(*table)[temp].attributes_table,attribute);
        if ((*table)[temp].value == -1) /*entered first time since "get_all_symbols" function*/
        {
            (*table)[temp].value = new_value;
        }
        if ((*table)[temp].value == 0 && /*entered only as entry*/
            (*table)[temp].attributes_table.external == false)
        {
            (*table)[temp].value = new_value;
        }
    }
    else /*first time entered*/
    {
        table_size = size_of_symbols_table(table);
        get_more_memory(*table,table_size+1,symbols_table)

        /*name*/
        get_memory((*table)[table_size-1].name,strlen(new_name)+1,char)
        strcpy((*table)[table_size-1].name,new_name);
        (*table)[table_size].name = NULL;

        /*value*/
        (*table)[table_size-1].value = new_value;

        /*attributes_table*/
        add_attribute(&((*table)[table_size-1].attributes_table), attribute);
        new_attributes_table(&((*table)[table_size].attributes_table));
    }
}

/************************************************************************
* Function Name: find_symbol
* Input: symbols_table ** table (a pointer to a symbols' matrix)
         char *name (a symbol's name)
* Output: int (the symbol's line's number. -1 if no such symbol's name)
* Function Operation: the function find the line's number wich his name's
                      value is equal to the value of the "name" var
************************************************************************/
int find_symbol(symbols_table **table,char *name)
{
    int i = 0;
    while ((*table)[i].name != NULL)
    {
        if (str_equals((*table)[i].name,name))
        {
            return i;
        }
        i++;
    }
    return -1;
}

/************************************************************************
* Function Name: find_symbol_by_index
* Input: symbols_table ** table (a pointer to a symbols' matrix)
         char *name (an index parameter))
* Output: int (the symbol's line's number. -1 if no such symbol's name)
* Function Operation: the function find the line's number wich his name's
                      value is equal to the value of the "name" var
                      without the index
************************************************************************/
int find_symbol_by_index(symbols_table **table,char *name)
{
    int i = 0;
    int name_length = 0;
    while ((*table)[i].name != NULL)
    {
        name_length = strlen((*table)[i].name);
        if (!strncmp(name,(*table)[i].name,name_length))
        {
            return i;
        }
        i++;
    }
    return -1;
}

/************************************************************************
* Function Name: add_attribute
* Input: attribute_table *table (a pointer to an attribute table)
         char *new_attribute (an attribute's name)
* Function Operation: the function change the value (in the attribute
                      table) of the corresponding var to true
************************************************************************/
void add_attribute(attributes_table *table, char *new_attribute)
{
    if (str_equals(new_attribute,"code")) table->code = true;
    if (str_equals(new_attribute,"data")) table->data = true;
    if (str_equals(new_attribute,"entry")) table->entry = true;
    if (str_equals(new_attribute,"external")) table->external = true;
}

/************************************************************************
* Function Name: new_attributes_table
* Input: attribute_table *table (a pointer to attributes_table)
* Function Operation: the function change all values in an attribute
                      table to false
************************************************************************/
void new_attributes_table(attributes_table *table)
{
    table->code     = false;
    table->data     = false;
    table->entry    = false;
    table->external = false;
}

/*delete!!*/
void print_symbol_table(symbols_table **symbol_table)
{
    int i = 0;
    symbols_table *table = *symbol_table;
    printf("symbols table:\n");
    while(table[i].name != NULL)
    {
        printf("%s\t%d\t",table[i].name,table[i].value);
        if(table[i].attributes_table.code == true) printf(" code");
        if(table[i].attributes_table.data == true) printf(" data");
        if(table[i].attributes_table.entry == true) printf(" entry");
        if(table[i].attributes_table.external == true) printf(" external");
        printf("\n");
        i++;
    }
}

/************************************************************************
* Function Name: size_of_symbols_table
* Input: symbols_table ** symbol_table
* Output: int
* Function Operation: the function return how many lines there is in the
                      corresponding symbols' table (including the null
                      line)
************************************************************************/
int size_of_symbols_table(symbols_table **table)
{
    int i = 1;
    while ((*table)[i-1].name != NULL)
    {
        i++;
    }
    return i;
}
