#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <string.h>
#include <math.h>


#define MAX_MEMORY_CELLS 8192 /*page 18*/
#define MAX_LINE_SIZE 80 /*page 24*/

/************************/
#ifndef BOOL
#define BOOL

typedef enum {false, true} boolean;

#endif
/************************/