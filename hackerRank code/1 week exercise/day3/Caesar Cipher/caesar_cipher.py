#!/bin/python3

import math
import os
import random
import re
import sys

#
# Complete the 'caesarCipher' function below.
#
# The function is expected to return a STRING.
# The function accepts following parameters:
#  1. STRING s
#  2. INTEGER k
#

def caesarCipher(s, k):
    while k>25: k-=26
    str = ""
    for i in s:
        if i.isalpha():
            if (i.islower() and chr(ord(i)+k).islower() or i.isupper() and chr(ord(i)+k).isupper()):
                str += chr(ord(i)+k)
            else: 
                str += chr(ord(i)+k-26)
        else: str+=i
    return str
            

if __name__ == '__main__':
    fptr = open(os.environ['OUTPUT_PATH'], 'w')

    n = int(input().strip())

    s = input()

    k = int(input().strip())

    result = caesarCipher(s, k)

    fptr.write(result + '\n')

    fptr.close()
