#include <stdio.h>

/* Participation Project 05
* COMPLETED 1-30-25
*
* Prompts the user to enter a positive integer (>0)
* You must display a message with instructions
* Do not re-prompt the user if they enter an invalid number, just accept what they entered
*
* Using a recursive function named print_factors, finds and prints al
* factors of the provided integer in descending order, one per line
* Use modulo math: 49 % 7 = 0 means 7 is a factor of 49, and 49 % 5 = 4 means 5 is not
* If value to be factored is ≤0, don't output any factors
*
*
*/
int main(){
 int number;

scanf("%d\n", &number);

//if number is over 0, print number and call recursion

if(number>0){
    printf("%d\n", number);
    print_factors(number, 1);

//else prompt user to input a positive int
} else {
    puts("Input a positive int");
}


    return 0;
}