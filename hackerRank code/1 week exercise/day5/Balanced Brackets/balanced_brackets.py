#!/bin/python3

import math
import os
import random
import re
import sys

#
# Complete the 'isBalanced' function below.
#
# The function is expected to return a STRING.
# The function accepts STRING s as parameter.
#

def isBalanced(s):
    remain_opened = 0
    for i in range(len(s)):
        if s[i] == '[' or s[i] == '(' or s[i] == '{':
            remain_opened += 1
            remain_opened_for_i = 0
            for j in range(i,len(s)):
                if s[j] == '[' or s[j] == '(' or s[j] == '{':
                    remain_opened_for_i += 1
                else:
                    remain_opened_for_i -= 1
                if remain_opened_for_i==0:
                    if (s[i] == '{' and s[j] != '}')\
                    or (s[i] == '(' and s[j] != ')')\
                    or (s[i] == '[' and s[j] != ']'):
                        return "NO"
                    break
        else:
            remain_opened -= 1
            if remain_opened<0:
                return "NO"
    if remain_opened!=0:
        return "NO" 
    return "YES"
    
if __name__ == '__main__':
    fptr = open(os.environ['OUTPUT_PATH'], 'w')

    t = int(input().strip())

    for t_itr in range(t):
        s = input()

        result = isBalanced(s)

        fptr.write(result + '\n')

    fptr.close()
