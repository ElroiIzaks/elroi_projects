assembler: project.o macro.o assembler.o code.o final.o first.o line.o string.o symbols.o import.o file.o second.o valid.o
	gcc -g project.o macro.o assembler.o code.o final.o first.o line.o string.o symbols.o import.o file.o second.o valid.o -o assembler -lm
project.o: project.c project.h
	gcc -c -Wall -ansi -pedantic project.c -o project.o
macro.o: macro.c macro.h
	gcc -c -Wall -ansi -pedantic macro.c -o macro.o
line.o: line.c line.h
	gcc -c -Wall -ansi -pedantic line.c -o line.o
assembler.o: assembler.c assembler.h
	gcc -c -Wall -ansi -pedantic assembler.c -o assembler.o
code.o: code.c code.h 
	gcc -c -Wall -ansi -pedantic code.c -o code.o
final.o: final.c final.h
	gcc -c -Wall -ansi -pedantic final.c -o final.o
first.o: first.c first.h
	gcc -c -Wall -ansi -pedantic first.c -o first.o
string.o: string.c string.h
	gcc -c -Wall -ansi -pedantic string.c -o string.o
symbols.o: symbols.c symbols.h
	gcc -c -Wall -ansi -pedantic symbols.c -o symbols.o
import.o: import.c import.h
	gcc -c -Wall -ansi -pedantic import.c -o import.o
second.o: second.c second.h
	gcc -c -Wall -ansi -pedantic second.c -o second.o
file.o: file.c file.h
	gcc -c -Wall -ansi -pedantic file.c -o file.o
valid.o: valid.c valid.h
	gcc -c -Wall -ansi -pedantic valid.c -o valid.o