#Title: q3	Filename: mmn12
#Author: Elroi Izaks	Date: 22.8.22
#########################Data segment#########################
.data

CharStr: .asciiz "AEZKLBXWZXYALKFKWZRYLAKWLQLEK"
ResultArray: .space 26

newline: .asciiz "\n"
massage: .asciiz "for repeat the process, please press 1, for exit press 2\n"
#########################Code segment#########################
.text
.globl main

#s1 is for corrent's value
#s2 is for corrent's address
#s3 is for next's address

main: 
	
	jal clearResultArray
	
	#part 1#
	
	la $a0, CharStr
	la $a1, ResultArray
	
	jal char_occurrences
	move $s0, $v0 #for later (part 4)
	
	#part 2#
	
	#print the char
	move $a0, $s0
	li $v0, 11
	syscall

	jal println

	#print the num of occurrences
	move $a0, $v1
	li $v0, 1
	syscall

	jal println
	
	#part 3#
	
	la $a0, CharStr
	la $a1, ResultArray
	
	jal print_char_by_occurrences
	
	#part 4#
	
	la $a0, CharStr
	move $a1, $s0
	
	jal delete
	
	la $a0, CharStr
	li $v0, 4
	syscall
	
	jal println
	
	#part 5#
	
	lb $t0, CharStr
	beq $t0, 0, ending #if CharStr is empty- end the program
	
	la $a0, massage
	li $v0, 4
	syscall	
	
	li $v0, 5
	syscall
	
	beq $v0, 1, main #if answer is 1- repeat
	
	ending:
	li $v0,10
	syscall
################end of main################

char_occurrences:
	move $t0, $a0
	loop1:
	lb $t1, ($t0)
	add $t0, $t0, 1#for each letter in $a0 (till null)
	beq $t1, 0, endLoop1 #get to null
	add $t2, $a1, $t1 
	sub $t2, $t2, 65
	lb $t3, ($t2)
	add $t3, $t3, 1
	sb $t3, ($t2)
	j loop1

	endLoop1:

	#find the max
	
	move $t0, $a1 #$to = address of ResultArray	
	lb $t1, ($t0) #$t1 = max
	move $t2, $t0 #$t2 = address of max
	loop2:
	lb $t3, ($t0) #$t3 = corrent's vlaue
	
	#check if loop need to stop
	sub $t5, $t0, 26
	beq $t5, $a1, endChar_occurrences #get to end of ResultArray
	
	#chack if corrent value is grater than max
	slt $t4, $t3, $t1 #if new<max t4=1
	beq $t4, 1, continueLoop2
	
	#if true (new max)
	move $t2, $t0
	move $t1, $t3	

	continueLoop2:
	add $t0, $t0, 1 
	j loop2
	
	endChar_occurrences:
	sub $v0, $t2, $a1
	add $v0, $v0, 65
	move $v1, $t1
	jr $ra
#####end of Char_occurrences


println: 
	move $t0, $a0
	la $a0, newline #print new line
      	li $v0, 4
      	syscall
      	move $a0, $t0
      	jr $ra
#####end of println

print_char_by_occurrences:
	move $t0, $a1 #$to = address of ResultArray	
	loop3:
	lb $t1, ($t0) #$t1 = corrent's value (num of occurrences)
	sub $t2, $t0, $a1 #$t2 = num of Iteration (place in ResultArray)
	add $t2, $t2, 65 #corresponding char in ascii
	beq $t1, 0, continueLoop3
	innerLoop1:
		#check if loop need to stop
		beq $t1, 0, endInnerLoop1
		
		#print the char
		move $a0, $t2
		li $v0, 11
		syscall
		
		#go to next Iteration
		sub $t1, $t1, 1
		j innerLoop1
		
	endInnerLoop1:
	#print new line
	la $a0, newline
	li $v0, 4
	syscall
	
	continueLoop3:
	
	add $t0, $t0, 1 #fo to next Iteration
	beq $t2, 90, endLoop3 #no more chars
	j loop3
	
	endLoop3:
	jr $ra
#####end of print_char_by_occurrences
	
delete:
	move $s1, $ra #remember where to go back
	loop4:
	lb $t0, ($a0)
	beq $t0, 0, endLoop4
	bne $t0, $a1, continueLoop4
	jal reduction
	j loop4
	continueLoop4:
	add $a0, $a0, 1
	j loop4
	endLoop4:
	move $ra, $s1
	jr $ra
	
reduction:
	move $t1, $a0 #$t1 = corrent's address
	loop5:
	add $t2, $t1, 1 #$t2 = next's address
	lb $t3, ($t2) #$t3 = next's value	
	sb $t3, 0($t1)
	beq $t3, 0, endLoop5
	add $t1, $t1, 1
	j loop5
	endLoop5:
	jr $ra
	
clearResultArray:
	la $t0, ResultArray
	la $t1, ResultArray
	loop6:
	sb $zero, ($t0)
	add $t0, $t0, 1
	sub $t2, $t0, 26
	beq $t2, $t1, endLoop6 #get to end of ResultArray
	j loop6
	endLoop6:
	jr $ra	