print( "Ahmad Elhamad, Assignment 5-2, masc1376")

F="Fizz"	//solution to classic "FizzBuzz" interview question
B="Buzz"	// in Lua script
FB="FizzBuzz"	// which prints out iterations of FizzBuzz every 5 prints

for i = 1,100
do

if i%3 == 0 and i%5 == 0 then
	print(FB)

elseif i%3 == 0 then
	print(F)

elseif i%5 == 0 then
	print(B)
else
print(i)
end
end	
