#include <stdio.h>
#include <math.h>
int main() {

	
	int input;
	float answer;
	float output;
	
	printf("%s", "Assignment 1-3, Ahmad Elhamad, masc1376 \n"); 
	
	printf("%s","Please input an integer: \n");
	scanf("%d",&input);
	
	answer = input*(M_PI/180); /* convert radian input to degrees */
	output=sin(answer);
	printf("%.3f\n",output); /* limit output to 3 decimal points */
	return 0;
}
