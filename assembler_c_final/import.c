#include "import.h"

void check_if_successfull_alloc(void *ptr)
{
    if (!ptr)
    {
        printf("CRITICAL ERROR: MEMORY ALLOCATION FAILED.");
        exit(0);
    }
}