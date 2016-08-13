// This file is part of www.nand2tetris.org
// and the book "The Elements of Computing Systems"
// by Nisan and Schocken, MIT Press.
// File name: projects/04/Fill.asm

// Runs an infinite loop that listens to the keyboard input. 
// When a key is pressed (any key), the program blackens the screen,
// i.e. writes "black" in every pixel. When no key is pressed, the
// program clears the screen, i.e. writes "white" in every pixel.

// Put your code here.
(INIT)
@SCREEN
D=A
@8192
D=D+A
@addr
M=D	//addr=16384 + 256 * 512 (screen base address + all screen addresses)
@laddr
M=D

(LOOP)
	@addr
	D=M
	@SCREEN
	D=D-A
	@RESET
	D;JLT

	(SCAN)
	@KBD
	D=M
	@CLEAR
	D;JEQ
	
	@addr
	A=M
	M=-1
	
	(INC)
	@addr
	M=M-1
	@LOOP
	0;JMP

(RESET)
	@laddr
	D=M
	@addr
	M=D
	@SCAN
	0;JMP
	
(CLEAR)
	@addr
	A=M
	M=0
	@INC
	0;JMP
