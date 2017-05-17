*----------------------------------------------------------------------
* Programmer: Ahmad Elhamad
* Class Account: masc1096
* Assignment or Title: Assignment #3
* Filename: prog3.s
* Date completed: 4/24/2014
*----------------------------------------------------------------------
* Program Memory Initialization 
        ORG     $0
        DC.L    $3000           * Stack pointer value after a reset
        DC.L    start           * Program counter value after a reset
        ORG     $3000           * Start at location 3000 Hex

*----------------------------------------------------------------------
*
#minclude /home/ma/cs237/bsvc/iomacs.s
#minclude /home/ma/cs237/bsvc/evtmacs.s
*
*----------------------------------------------------------------------
* Register use
* D0: Macro Use
* D1: Input Value
* D2: Input Copy
* D3: String Length
* D4: DBRA Coubnter
* D5: Factor
* D6: N/A
* D7: Input Value
*----------------------------------------------------------------------
*
start:  initIO                  	* Initialize (required for I/O)
setEVT	* Error handling routines					      
        clr.l		buffer
        clr.b		buffer+4 
        
      	lineout		title       
begin:
      	lineout		skipln	
      	lineout		title2 
      	linein		buffer
      	move.l		D0,D4         	* strlen to D4
      	move.l		D0,D3		        * and D3
      	subq		  #1,D4           * DBRA counter
      	tst.l		  D0		          * is strlen == 0?
      	beq		    bad
      	cmpi.l		#5,D0
      	bhi		    bad
      	bra		    length      
      
again2:   
	      clr.l		  buffer 
        BRA		    begin       
bad:  
      	lineout		error_msg   * If error occurs, output error message
     	  clr.l		  buffer      * clear memory buffers
     	  clr.b	  	buffer+4
     	  bra		    begin       * and restart       
      
        * check that all input are digits 0-9      
length:
        lea	    	buffer,A0   
        
check:      
        cmpi.b		#'0',(A0)    * check if ascii code < 47
        blo			  bad          * if it is goto bad
        cmpi.b		#'9',(A0)+   * check if ascii code > 58
        bhi		    bad          * if it is, goto bad
        dbra		  D4,check
      
       * and that value is from 2-65535
      	    
       cvta2		  buffer,D0
       move.l	  	D0,D1         *   and D1
       move.l		  D0,D2        * copy input value to D2
       cmpi.l		  #2,D0
       blo		    bad
       cmpi.l		  #65535,D0
       bhi	  	  bad
      
      *  ALGORITHM
            
      move.w		#2,D5          * factor stored in D5 with value of 2
      lea		    solut,A2
      * check that factor <= number
      
FOR: 
      cmp.w		D1,D5   * see if factor <= number
      BHI		DONE       * if  condition is not met, goto DONE      
    
       * check if number % factor equals 0  
            
      andi.l		#$0000FFFF,D1  
      move.l		D1,D7             * D7 is the input val 
      divu.w		D5,D7           
      swap		  D7                * D7 % D5
      tst.w		  D7                * see if number % factor == 0
      BNE		    ENDIF       
      move.w		D5,D0         
      andi.l		#$0000FFFF,D0
      cvt2a	  	(A2),#2
      stripp	  (A2),#2
      adda.l		D0,A2
      move.b		#'*',(A2)+
      divu		  D5,D1	* number /= factor
      subq.w		#1,D5

ENDIF:                   * 
      addq.w		#1,D5
      BRA		    For      
       
done: 	 
       
      clr.b		  -(A2)
      lineout		facts 
      lineout		solut
      
      clr.l		buffer
      clr.b		buffer+4
      
prompt:
      lineout		redo           
      linein		buffer2
      lea		    buffer2,A1
      cmpi.b		#1,D0
      BNE		    prompt
      cmpi.b		#89,(A1)      * (Y/N)  ?
      BEQ		    begin
      cmpi.b		#121,(A1)
      BEQ		    begin         * makes sure user entered either YyNn
      cmpi.b		#110,(A1)
      BEQ 		  fin
      cmpi.b		#78,(A1)
      BEQ		    fin
      lineout		invalid
      BRA		    prompt
	
fin:  
	    lineout		terminate
        
break                   * Terminate execution
**----------------------------------------------------------------------
*       Storage declarations


buffer2:	    ds.b		80
skipln:		    dc.b		0
title:	    	dc.b		'Program #3, Ahmad Elhamad, masc1096',0
title2:		    dc.b		'Enter an integer to factor (2..65535):',0
error_msg:    dc.b		'Sorry, your input is not a valid integer.',0
invalid:	    dc.b		'Invalid Answer',0
facts:		    dc.b		'The factors of '
buffer: 	    ds.b		5
are:		      dc.b		' are:',0
solut:		    ds.b		40
redo:		      dc.b		'Do you want to factor another integer (Y/N)?',0
terminate:    dc.b		'Program terminated.'


