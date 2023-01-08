                                      ~~ project 2022a ~~
                                        ~~   mmn 14 ~~
                                       ~~ Elroi Izaks ~~
                                        ~~ 209382514 ~~


~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
files:

~~~~~~~
main files:

#project: the main function 
          runing the pre-assembler (the macros' stage) and the assembler stage (the main program).

#macro: the pre-assembler stage 
        arange and generate new file (with ".am" ending) with the unfold macros.

#assembler: the assembler stage (all the remain program, for each file, occurs inside this program)
            create the basic needed structs, and runing the stages. if any error occurred, the 
            function stop the second stage from running.

#first: checks for problems and enter the data
        this file responsible for mapping all the problem and entering the data (the costumer's
        program allocated memory) to the system.

#second: complete the machine language (entering the code's data)
         responsible for entering codes' data. the file did not check for errors, and 
         assumes that there is non. the file relies that all the labels are known.

#final: produces all output files        
        create 3 files- ".ob" (with the machine code, represented by the hexadecimal base)
                        ".ext" (with all the usage of the ".as" file with any external data)
                        ".ent" (with information of any entry file the program used)

~~~~~~~

data structure files:

symbols: structures and functions for the symbols' table.

code: structures and functions for the machine-code table.

line: structures and functions for the processed line.

~~~~~~~

auxiliary files:

string: useful and efficient functions for string and text.

file: useful and efficient functions for opening and creating files.

valid: useful and efficient functions for checking validity of variables.

import: useful and efficient functions and macros for import memory.

basic.h (header): hold the basic information of the program, for efficient changes.

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

The general method of the program:

the program take a line from the ".as" file, and check the #syntax# of the line.
if the syntax is ok, the program save the patameters of the line in "line"-struct.

the program check for any label in the file and enter the label to "symbol_table"-struct.
the program check for the validity of the line, as assembley line, while entering the 
data words to the "machine_code"-struct.

if no error happend, the program enter the remain data, the code words, to the machine cide table.
the program send the machine code and the symbols table to produces output files.