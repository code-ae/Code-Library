#! /usr/bin/python

def Tokenize(filename):


	strings=[]
	txtfile=open('comp.txt')
	strings=txtfile.readlines()  # 'strings' contains list of lines from file
	
	print(strings)					
	
	tokens=[]
	for i in range(len(strings)):  # 'tokens' contains list of list of words from lines
		print(strings[i])
		item = strings[i]
		item=item.split()
		tokens.append(item)
	
return tokens