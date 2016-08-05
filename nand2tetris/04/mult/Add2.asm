//Program: Add2.asm
//Computes: RAM[2] = RAM[0] + RAM[1]
//Usage: put values in RAM[0], RAM[1]

@275
D=A
@0
M=D //RAM[0]=275

@861
D=A
@1
M=D //RAM[1]=861

@0
D=M //D=RAM[0]

@1
D=D+M //D=D+RAM[1]

@2
M=D //RAM[2]=D
