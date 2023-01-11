#include "final.h"

/************************************************************************
* Function Name: export_files
* Input: machine_code *machine_code
         symbols_table *symbols_table
         char *file_name
* Function Operation: the function makes all required output files
************************************************************************/
void export_files(machine_code *machine_code,symbols_table *symbols_table, char *file_name)
{
    wrote_ob_file((*machine_code) , symbols_table, file_name);
    wrote_ext_file(machine_code->code,symbols_table,file_name);
    wrote_ent_file(symbols_table,file_name);
}

/************************************************************************
* Function Name: wrote_ob_file
* Input: machine_code machine_code
         symbols_table *symbols_table
         char *file_name
* Function Operation: the function makes ob file, based on the Received
                      machine_code and symbols_table.
************************************************************************/
void wrote_ob_file(machine_code machine_code,symbols_table *symbols_table,char * file_name)
{
    int i = 0;
    FILE *file;
    file = make_xx_file(file_name,".ob");
    fprintf(file,"\t\t%d %d\n",machine_code.ic-100,machine_code.dc);
    while (machine_code.code[i].first_word.ic_num != -1)
    {
        wrote_binary_code_line(machine_code.code[i], file);
        i++;
    }
    i = 0;
    while (machine_code.data[i].dc_num != -1)
    {
        wrote_binary_data_line(machine_code.data[i], file,machine_code.ic);
        i++;
    }
    fclose(file);
}

/************************************************************************
* Function Name: wrote_binary_code_line
* Input: code code_line
         FILE *file
* Function Operation: the function wrote in the ob file the content of
                      code word.
************************************************************************/
void wrote_binary_code_line(code code_line,FILE *file)
{
    int i = 0;
    int for_minus = 0;
    int   A = 0,
          B = 0,
          C = 0,
          D = 0,
          E = 0;
    
    /*line's num*/
    fprintf(file,"%s%d  ", code_line.first_word.ic_num < 1000? "0":"",code_line.first_word.ic_num);
        
    /*first word*/
    A = 4;
    B = (int)pow(2,(code_line.first_word.opcode-12));
    C = (int)pow(2,(code_line.first_word.opcode-8));
    if (C>=16) C = 0;
    D = (int)pow(2,(code_line.first_word.opcode-4));
    if (D>=16) D = 0;
    E = (int)pow(2,(code_line.first_word.opcode));
    if (E>=16) E = 0;
    fprintf(file,"A%x-B%x-C%x-D%x-E%x\n",A,B,C,D,E); 
    
    /*second word*/
    if (code_line.has_second == false) return;

    /*line's num*/
    fprintf(file,"%s%d  ", code_line.second_word.ic_num < 1000? "0":"",code_line.second_word.ic_num);

    A = 4;
    B = code_line.second_word.funct;
    if (B == -1) B = 0;
    C = code_line.second_word.source_reg;
    D = (code_line.second_word.address_source)*4+(code_line.second_word.target_reg)/4;
    if (code_line.second_word.address_source == no_method)
        D = (code_line.second_word.target_reg)/4;
    E = (code_line.second_word.target_reg-(code_line.second_word.target_reg/4)*4)*4+(code_line.second_word.address_target);

    fprintf(file,"A%x-B%x-C%x-D%x-E%x\n",A,B,C,D,E); 

    /*another words*/
    if (code_line.has_another == false) return;

    /*line's num*/
    while(code_line.another_words[i].ic_num != -1)
    {
        fprintf(file,"%s%d  ", code_line.another_words[i].ic_num < 1000? "0":"",code_line.another_words[i].ic_num);

        if (code_line.another_words[i].are == a)     A = 4;
        if (code_line.another_words[i].are == r)     A = 2;
        if (code_line.another_words[i].are == e)     A = 1;
        
        for_minus = code_line.another_words[i].value;
        for_minus = (for_minus<0)? pow(2,16)+for_minus:for_minus;
        B = for_minus/pow(2,12); 
        C = (for_minus-B*pow(2,12))/pow(2,8);
        D = (for_minus-B*pow(2,12)-C*pow(2,8))/pow(2,4);
        E = (for_minus-B*pow(2,12)-C*pow(2,8)-D*pow(2,4));
        fprintf(file,"A%x-B%x-C%x-D%x-E%x\n",A,B,C,D,E); 
        i++;
    }
}

/************************************************************************
* Function Name: wrote_binary_data_line
* Input: data_word data
         FILE *file
         int ic (for writing the correct dc value)
* Function Operation: the function wrote in the ob file the content of
                      data word.
************************************************************************/
void wrote_binary_data_line(data_word data,FILE *file,int ic)
{
    int for_minus = 0;
    int   A = 4,
          B = 0,
          C = 0,
          D = 0,
          E = 0;
    
    fprintf(file,"%s%d  ", data.dc_num < 1000? "0":"",data.dc_num+ic);

    if (data.is_char == true) for_minus = data.character;
    if (data.is_num == true) for_minus = data.num;

    for_minus = for_minus<0? pow(2,16)+for_minus:for_minus;
    B = for_minus/pow(2,12); 
    C = (for_minus-B*pow(2,12))/pow(2,8);
    D = (for_minus-B*pow(2,12)-C*pow(2,8))/pow(2,4);
    E = (for_minus-B*pow(2,12)-C*pow(2,8)-D*pow(2,4));
    fprintf(file,"A%x-B%x-C%x-D%x-E%x\n",A,B,C,D,E);
}

/************************************************************************
* Function Name: wrote_ext_file
* Input: code *code
         symbols_table *symbols_table
         char *file_name
* Function Operation: the function makes ext file, based on the Received
                      machine_code and symbols_table.
************************************************************************/
void wrote_ext_file(code *code,symbols_table *symbols_table,char *file_name)
{
    int i = 0;
    int j = 0;
    FILE *file;
    file = make_xx_file(file_name,".ext");
    
    while (code[i].first_word.ic_num != -1)
    {
        if (code[i].has_another == true)
        {
            while (code[i].another_words[j].ic_num != -1)
            {
                if (code[i].another_words[j].is_external == true)
                {
                    fprintf(file,"%s BASE %s%d\n",code[i].another_words[j].external,code[i].another_words[j].ic_num > 999? "":"0",code[i].another_words[j].ic_num);
                    j++;
                    fprintf(file,"%s OFFSET %s%d\n",code[i].another_words[j].external,code[i].another_words[j].ic_num > 999? "":"0",code[i].another_words[j].ic_num);
                }
                j++;
            }
            j = 0;
        }
        i++;
    }
    fclose(file);
}

/************************************************************************
* Function Name: wrote_ent_file
* Input: symbols_table *symbols_table
         char *file_name
* Function Operation: the function makes ent file, based on the Received
                      symbols_table.
************************************************************************/
void wrote_ent_file(symbols_table *symbols_table,char *file_name)
{
    int i = 0;
    int base;
    FILE *file;
    file = make_xx_file(file_name,".ent");
 
    while (symbols_table[i].name != NULL)
    {
        if (symbols_table[i].attributes_table.entry == true)
        {
            base = (symbols_table[i].value / 16)*16;
            fprintf(file, "%s,%s%s%d",symbols_table[i].name,base < 100? "0":"",base < 1000? "0":"",base);
            fprintf(file,",00%s%d\n",symbols_table[i].value-base < 10? "0":"",symbols_table[i].value-base);
        }
        i++;
    }
    fclose(file);
}