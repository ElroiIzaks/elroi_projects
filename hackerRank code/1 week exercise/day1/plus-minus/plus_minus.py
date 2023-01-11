#!/bin/python3

import math
import os
import random
import re
import sys

#
# Complete the 'plusMinus' function below.
#
# The function accepts INTEGER_ARRAY arr as parameter.
#

def plusMinus(arr):
    pos,neg,zero = 0,0,0
    for i in arr:
        if i>0:
            pos += 1/len(arr)
        elif i<0:
            neg += 1/len(arr)
        else: zero += 1/len(arr)
    round(pos,6)
    round(neg,6)
    round(zero,6)
    print(pos, "\n" , neg, "\n" , zero)
    

if __name__ == '__main__':
    n = int(input().strip())

    arr = list(map(int, input().rstrip().split()))

    plusMinus(arr)
