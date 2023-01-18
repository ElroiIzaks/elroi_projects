#!/bin/python3

import math
import os
import random
import re
import sys

#
# Complete the 'gridChallenge' function below.
#
# The function is expected to return a STRING.
# The function accepts STRING_ARRAY grid as parameter.
#

def sort_grid(grid):
    new_grid = []
    for i in grid:
        temp = ""
        for j in sorted(i):
            temp += j
        new_grid.append(temp)
    return new_grid

def rotate_grid(grid):
    new_grid = []
    n = len(grid)
    m = len(grid[0])
    for i in range(m):
        temp = ""
        for j in range(n):
            temp += grid[j][i]
        new_grid.append(temp)
    return new_grid
    
    
def gridChallenge(grid):
    if sort_grid(rotate_grid(sort_grid(grid))) == rotate_grid(sort_grid(grid)):
        return "YES"
    return "NO"

if __name__ == '__main__':
    fptr = open(os.environ['OUTPUT_PATH'], 'w')

    t = int(input().strip())

    for t_itr in range(t):
        n = int(input().strip())

        grid = []

        for _ in range(n):
            grid_item = input()
            grid.append(grid_item)

        result = gridChallenge(grid)

        fptr.write(result + '\n')

    fptr.close()
