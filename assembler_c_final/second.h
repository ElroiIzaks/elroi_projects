#include "code.h"
#include "final.h"
#include "string.h"
#include "file.h"

void second_pass (char *, machine_code *, symbols_table **);
boolean enter_code(machine_code *, line, symbols_table **);