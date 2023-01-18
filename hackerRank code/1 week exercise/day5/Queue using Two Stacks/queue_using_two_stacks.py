#!/bin/python3

import math
import os
import random
import re
import sys

# Enter your code here. Read input from STDIN. Print output to STDOUT
class queue_node:
    def __init__(self, data):
        self.data = data
        self.next = None
        
class queue:
    def __init__(self):
        self.head = None
        self.tail = None
        
    def enqueue(self,data):
        new_node = queue_node(data)
        if not self.head:
            self.head = new_node
        else:
            self.tail.next = new_node
        self.tail = new_node
        
    def dequeue(self):
        self.head = self.head.next
        
    def print_head(self):
        fptr.write(str(self.head.data))
        fptr.write('\n')
        

        
        
if __name__ == '__main__':
    fptr = open(os.environ['OUTPUT_PATH'], 'w')

    new_queue = queue()
    
    num_of_queries = int(input())

    for query_itr in range(num_of_queries):
        query_mission = list(map(int, input().rstrip().split()))
        if query_mission[0] == 1:
            new_queue.enqueue(query_mission[1])            
        elif query_mission[0] == 2:
            new_queue.dequeue()
        elif query_mission[0] == 3:
            new_queue.print_head()
            
            
    fptr.close()
