//Pointers.asm
// A for loop
//	for(i=0; i<n; i++)
//		arr[i] = -1

//initialize
@100
D=A
@arr
M=D	//set arr to base address
@10
D=A
@n
M=D	//set number of iterations
@i
M=0

(LOOP) 
	//if i==n goto END
	@i
	D=M
	@n
	D=D-M
	@END
	D;JEQ
	
	//RAM[arr+i] = -1
	@arr
	D=M
	@i
	A=D+M
	M=-1
	
	//i++
	@i
	M=M+1
	@LOOP
	0;JMP

(END)
	@END
	0;JMP
