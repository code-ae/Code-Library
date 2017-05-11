tokens=[]
strings=[]
txtfile=open('comp.txt')
strings=txtfile.readlines()  # print(strings) outputs ['2\n , tertrib,65,3' , 'Scorear,70,14\n']
								# each element is a line / strings is 1 table
for lines in strings:
		tokens.append(lines.splitlines()) # a is set to the current line being tokenized
	
	print(tokens) # outputs ['2'] , ['terrtrib',65,3] , ['scorear',70,14]	
					# each index holds table of all tokens on a line
