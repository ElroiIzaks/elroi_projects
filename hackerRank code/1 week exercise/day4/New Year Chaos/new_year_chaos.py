#!/bin/python3

import math
import os
import random
import re
import sys

#
# Complete the 'minimumBribes' function below.
#
# The function accepts INTEGER_ARRAY q as parameter.
#

def minimumBribes(q):
    sum = 0
    index = [True]*len(q)
    expected = 1
    for i in range(len(q)):
        if q[i]>i+3:
            print ("Too chaotic")
            return
        if q[i] != expected:
            dis = 0
            for j in range(expected-1,q[i]-1):
                if index[j]: dis+=1
            sum += dis
            index[q[i]-1] = False 
        else: # q[i] = expected
            index[expected-1] = False
            j = expected
            while(j<len(index) and not index[j]): j+=1 
            expected = j+1
    print(int(sum))

if __name__ == '__main__':
    t = int(input().strip())

    for t_itr in range(t):
        n = int(input().strip())

        q = list(map(int, input().rstrip().split()))

        minimumBribes(q)
