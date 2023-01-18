#!/bin/python3

import math
import os
import random
import re
import sys

class SinglyLinkedListNode:
    def __init__(self, node_data):
        self.data = node_data
        self.next = None

class SinglyLinkedList:
    def __init__(self):
        self.head = None
        self.tail = None

    def insert_node(self, node_data):
        node = SinglyLinkedListNode(node_data)

        if not self.head:
            self.head = node
        else:
            self.tail.next = node


        self.tail = node

def print_singly_linked_list(node, sep, fptr):
    while node:
        fptr.write(str(node.data))

        node = node.next

        if node:
            fptr.write(sep)

# Complete the mergeLists function below.

#
# For your reference:
#
# SinglyLinkedListNode:
#     int data
#     SinglyLinkedListNode next
#
#
def mergeLists(head1:SinglyLinkedListNode, head2:SinglyLinkedListNode):
    new_lnk_lst = SinglyLinkedList()
    temp1 = head1
    temp2 = head2
    while  temp1 != None or temp2 != None:
        if temp2 == None or (temp1 and temp1.data <= temp2.data):
            new_lnk_lst.insert_node(temp1.data)
            temp1 = temp1.next
        elif temp2:
            new_lnk_lst.insert_node(temp2.data)
            temp2 = temp2.next
    return(new_lnk_lst.head)

if __name__ == '__main__':