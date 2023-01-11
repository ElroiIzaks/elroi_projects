#Title: q2	Filename: mmn12
#Author: Elroi Izaks	Date: 22.8.22
#########################Data segment#########################
.data
num1: .word -8, num3
num2: .word 1988, 0
num3: .word -9034, num5
num4: .word -100, num2
num5: .word 1972, num4

newline: .asciiz "\n"
printHere: .space 11  #11 chars
	   .asciiz "" #for ending null
#########################Code segment#########################
.text
.globl main

#s1 is for corrent's value
#s2 is for corrent's address
#s3 is for next's address

main: 

#s4 is for summing all numbers
#s5 is for summing all positive numbers that devided by 4

	jal InsertFirst
	li $s4 , 0 #start summering

loop1:
	move $a1, $s1 #a1 is corrent-node's value
	jal sumA #for part A
	jal sumB #for part B
	
	beq $s3, $zero, endOfLoop1 #if next-node's "address" is 0
	
	jal goToNext
	j loop1
	
endOfLoop1:
	#print sum A
	move $a0, $s4
	li $v0,1
	syscall
	
	jal println
	
	#print sum B
	move $a0, $s5
	li $v0,1
	syscall

	jal println
				
	# part C #
	jal InsertFirst
loop2:
	jal printInBase4

	jal println
		
	beq $s3, $zero, endOfLoop2 #if next node's "address" is 0
	jal goToNext
	j loop2
endOfLoop2:	


ending:
	li $v0,10
	syscall
################end of main################


InsertFirst: #start (put info of) with first node
	la $s2, num1
	lw $s1,($s2)
	lw $s3, 4($s2)
	jr $ra
#####end of InsertFirst


sumA: #regular sum
	add $s4, $s4, $a1
	jr $ra   
#####end of sumA


sumB: #only if positive and devided by 4
	slt $t0, $a1, $zero #check if positive
	bne $t0, $zero, endSunB
	
	andi $t0, $a1, 3 #check if devided by 4
	beq $t0, $a1, endSunB
	
	#get here only if pos and dev by 4
	add $s5, $s5, $a1
	
	endSunB: 
	jr $ra
#####end of sumB


println: la $a0, newline #print new line
      	li $v0, 4
      	syscall
      	jr $ra
#####end of println
      	

goToNext: #go to the next node
	la $s2, ($s3)
	lw $s1,($s2)
	lw $s3, 4($s2)
	jr  $ra
#####end of goToNext


printInBase4:
	move $t0, $s1 #$t0 is for corrent-node's value (will get changes)
	li $t1, 10 #starting from last digit (future position in space of "printHere")
	
	slti $t4, $t0, 0 #check if number is negitive
	
#if num is pos:	
	beq $t4, 0, loop3

#if num is neg:
	#print '-'	
	li $a0, 45
	li $v0, 11
	syscall
	#make the num pos by mul with -1
	li $t4, -1
	mul $t0, $t0, $t4
	
#any case:
loop3:
	beq $t0, 0, endingLoop3 #if the num is over (if he was divided till 0) jump to next
	
	and $t2, $t0, 3 #$t2 save the value of last 2 binary digits
	add $t2, $t2, 48 #add 48 to make it num in ascii (48=0,49=1...)
	sb $t2, printHere + 0($t1) #put char in "printHere"
	div $t0, $t0, 4 #divide by 4 (move num 2 bits right)
	beq $t1, 0, endingLoop3 #(for preventing leaking)
	sub $t1, $t1, 1
	j loop3
	
endingLoop3:
	la $a0, printHere + 1($t1) #print "printHere" from the place that have the corrent string (may have garbage-values before)
	li $v0, 4
	syscall
	jr $ra
#####end of printInBase4