#!/bin/python3

import math
import os
import random
import re
import sys

#
# Complete the 'timeConversion' function below.
#
# The function is expected to return a STRING.
# The function accepts STRING s as parameter.
#

def timeConversion(s):
    x=(s[0:2])
    if s[-2] == 'P' and x != "12":
        x=(int(x)+12)%24
    if s[-2] == 'A' and (s[0:2]) == "12":
        x = "00"
    return str(x)+s[2:-2]

if __name__ == '__main__':
    fptr = open(os.environ['OUTPUT_PATH'], 'w')

    s = input()

    result = timeConversion(s)

    fptr.write(result + '\n')

    fptr.close()
