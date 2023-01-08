#include "project.h"

int main (int argc, char *argv[])
{
    int i;

    for (i = 1 ; i < argc ; i++)/*for each file*/
    {

        if (pre_assembler(argv[i]) == false) continue;
        assembler(argv[i]);
    }

    return 1;
}