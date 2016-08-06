//Program: Add2.asm
//Computes: RAM[2] = RAM[0] + RAM[1]
//Usage: put values in RAM[0], RAM[1]

@275
D=A
@R0
M=D //RAM[0]=275

@861
D=A
@R1
M=D //RAM[1]=861

@R0
D=M //D=RAM[0]

@R1
D=D+M //D=D+RAM[1]

@R2
M=D //RAM[2]=D

@14
0;JMP
