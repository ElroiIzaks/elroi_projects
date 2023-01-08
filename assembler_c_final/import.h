#include "basic.h"

#define get_memory(to_here, size, type)\
    {\
        to_here = (type*)calloc(size,sizeof(type));\
        check_if_successfull_alloc(to_here);\
    }

#define get_more_memory(to_here, size, type)\
    {\
        to_here = (type*)realloc(to_here,(size)*sizeof(type));\
        check_if_successfull_alloc(to_here);\
    }
        

void check_if_successfull_alloc(void *);